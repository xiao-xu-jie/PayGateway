package com.xujie.strategy.context;

import com.xujie.application.RocketMQProducer;
import com.xujie.application.redis.utils.RedisUtils;
import com.xujie.common.dto.WxOrderDTO;
import com.xujie.common.dto.WxOrderRequest;
import com.xujie.common.exception.CustomException;
import com.xujie.strategy.AbstractPayContext;
import com.xujie.strategy.PayService;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * 支付策略上下文
 */
@Slf4j
@RefreshScope
@Component
public class WxPayContext extends AbstractPayContext {
    @Autowired
    private ApplicationContext applicationContext;
    @Resource
    private List<PayService> wxPayServices;
    @Resource
    private RocketMQProducer rocketMQProducer;
    @Resource
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    /**
     * 排序PayService
     */
    @PostConstruct
    public void init() {
        // 排序
        wxPayServices.sort(Comparator.comparingInt(PayService::getOrder));
        log.info("WxPayService 排序：{}", wxPayServices);
    }

    @Override
    public WxOrderDTO processOrder(WxOrderRequest request) {
        Map<String, PayService> payServiceMap = applicationContext.getBeansOfType(PayService.class);
        for (PayService payService : wxPayServices) {
            try {
                WxOrderDTO order = payService.createOrder(request);
                // 缓存订单信息与发送消息
                CompletableFuture.runAsync(() -> {
                    cacheAndSendMsg(request);
                }, threadPoolTaskExecutor);
                return order;
            } catch (Exception e) {
                // 找到当前 PayService 的 Bean 名称
                String beanName = payServiceMap.entrySet().stream()
                        .filter(entry -> entry.getValue() == payService)
                        .map(Map.Entry::getKey)
                        .findFirst()
                        .orElse("UnknownBean"); // 如果找不到，返回默认值
                log.error("[PayContext] {} 订单创建异常", beanName, e);
            }
        }
        throw new CustomException("订单创建失败");
    }


    protected void cacheAndSendMsg(WxOrderRequest orderRequest) {
        StopWatch stopWatch = new StopWatch("cacheAndSendMsg");
        stopWatch.start();
        RedisUtils.setCacheObject("order:" + orderRequest.getOpenNo(), orderRequest.getSiteAppid(), 15, TimeUnit.MINUTES);
        rocketMQProducer.sendDelayMessage("order", "expire", orderRequest.getOpenNo(), 14);

        stopWatch.stop();
        log.info("cacheAndSendMsg 消耗时间：{}", stopWatch.getTotalTimeMillis());
    }


}
