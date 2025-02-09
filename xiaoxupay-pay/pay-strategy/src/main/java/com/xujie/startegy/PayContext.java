package com.xujie.startegy;

import com.xujie.common.dto.WxOrderDTO;
import com.xujie.common.dto.WxOrderRequest;
import com.xujie.common.exception.CustomException;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 支付策略上下文
 */
@Slf4j
@RefreshScope
@Component
public class PayContext extends AbstractPayContext{
    @Autowired
    private ApplicationContext applicationContext;
    @Resource
    private List<PayService> wxPayServices;


    @Override
    public WxOrderDTO processOrder(WxOrderRequest request) {
        Map<String, PayService> payServiceMap = applicationContext.getBeansOfType(PayService.class);
        for(PayService payService:wxPayServices) {

            try {
                return payService.createOrder(request);
            }catch (Exception e){
                // 找到当前 PayService 的 Bean 名称
                String beanName = payServiceMap.entrySet().stream()
                        .filter(entry -> entry.getValue() == payService)
                        .map(Map.Entry::getKey)
                        .findFirst()
                        .orElse("UnknownBean"); // 如果找不到，返回默认值
                log.error("[PayContext] "+beanName+" 订单创建异常",e);
            }
        }
        throw new CustomException("订单创建失败");
    }



}
