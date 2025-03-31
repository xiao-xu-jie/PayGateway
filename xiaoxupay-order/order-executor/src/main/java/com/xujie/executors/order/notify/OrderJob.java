package com.xujie.executors.order.notify;

import cn.hutool.core.date.DateUtil;
import com.aizuda.snailjob.client.job.core.annotation.JobExecutor;
import com.aizuda.snailjob.client.job.core.dto.JobArgs;
import com.aizuda.snailjob.client.model.ExecuteResult;
import com.xujie.common.enums.AggregateType;
import com.xujie.common.enums.OutBoxMessageType;
import com.xujie.domain.service.OrderDomainService;
import com.xujie.infra.entity.OutboxMessage;
import com.xujie.infra.service.OutBoxMessageService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Xujie
 * @since 2025/2/27 13:56
 * 订单回调兜底处理
 **/
@Slf4j
@Component
public class OrderJob {

    @Resource
    private OutBoxMessageService outBoxMessageService;
    @Resource
    private OrderDomainService orderDomainService;

    /**
     * 定时执行订单支付OutBoxMessage
     *
     * @param jobArgs 参数
     * @return 执行结果
     */
    @JobExecutor(name = "orderPaid")
    @Transactional(rollbackFor = Exception.class)
    public ExecuteResult jobExecute(JobArgs jobArgs) {
        log.info("开始执行OrderPaid补偿定时任务");
        List<OutboxMessage> outboxMessageByPriority = outBoxMessageService.findOutboxMessageByPriority(AggregateType.ORDER, OutBoxMessageType.ORDER_PAID);
        List<String> list = outboxMessageByPriority.stream()
                .map(OutboxMessage::getAggregateId)
                .toList();
        log.info("补偿列表：{}", list);
        // 执行补偿
        outboxMessageByPriority
                .forEach(this::handleOrderPaidMessage);
        log.info("补偿执行完成");
        return ExecuteResult.success("订单支付OutBoxMessage任务执行成功");
    }

    @Transactional(rollbackFor = Exception.class)
    protected void handleOrderPaidMessage(OutboxMessage outboxMessage) {
        String payload = outboxMessage.getPayload();
        try {
            orderDomainService.handleOrderPaid(payload);
            outboxMessage.setProcessed(Boolean.TRUE);
            outboxMessage.setProcessedAt(DateUtil.date());
        } catch (Exception e) {
            log.info("订单已支付消息补偿异常：{}", payload, e);
//            outboxMessage.addRetryCount();
            outboxMessage.setErrorMessage(e.getMessage());
            outboxMessage.addPriority(1);
        }
        outBoxMessageService.saveOrUpdate(outboxMessage);
    }
}
