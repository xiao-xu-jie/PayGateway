package com.xujie.order.infra.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.xujie.common.enums.AggregateType;
import com.xujie.common.enums.OutBoxMessageType;
import com.xujie.order.infra.entity.OutboxMessage;
import com.xujie.order.infra.mapper.OutboxMessageMapper;
import com.xujie.order.infra.service.OutBoxMessageService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Xujie
 * @since 2025/2/27 15:20
 **/
@Slf4j
@Service
public class OutBoxMessageServiceImpl implements OutBoxMessageService {
    @Resource
    private OutboxMessageMapper outboxMessageMapper;

    @Override
    public OutboxMessage findOutboxMessageByAggregateId(String aggregateId) {
        LambdaQueryWrapper<OutboxMessage> eq = Wrappers.lambdaQuery(OutboxMessage.class).eq(OutboxMessage::getAggregateId, aggregateId);
        return outboxMessageMapper.selectOne(eq);
    }

    @Override
    public List<OutboxMessage> findOutboxMessageByPriority(AggregateType aggregateType, OutBoxMessageType outBoxMessageType) {
        LambdaQueryWrapper<OutboxMessage> eq = Wrappers.lambdaQuery(OutboxMessage.class)
                .eq(OutboxMessage::getProcessed, 0)
                .orderByDesc(OutboxMessage::getPriority)
                .eq(OutboxMessage::getAggregateType, aggregateType.getType())
                .eq(OutboxMessage::getType, outBoxMessageType.getType());
        return outboxMessageMapper.selectList(eq);
    }

    @Override
    public List<OutboxMessage> findOutboxMessageByPriority(AggregateType aggregateType, OutBoxMessageType outBoxMessageType, Integer limit) {
        LambdaQueryWrapper<OutboxMessage> eq = Wrappers.lambdaQuery(OutboxMessage.class)
                .eq(OutboxMessage::getProcessed, 0)
                .orderByDesc(OutboxMessage::getPriority)
                .eq(OutboxMessage::getAggregateType, aggregateType.getType())
                .eq(OutboxMessage::getType, outBoxMessageType.getType())
                .last("LIMIT " + limit);

        return outboxMessageMapper.selectList(eq);
    }

    @Override
    public List<OutboxMessage> findOutboxMessageByType(AggregateType aggregateType, OutBoxMessageType outBoxMessageType) {
        LambdaQueryWrapper<OutboxMessage> eq = Wrappers.lambdaQuery(OutboxMessage.class)
                .eq(OutboxMessage::getProcessed, 0)
                .eq(OutboxMessage::getAggregateType, aggregateType.getType())
                .eq(OutboxMessage::getType, outBoxMessageType.getType());
        return outboxMessageMapper.selectList(eq);
    }

    @Override
    public void saveOrUpdate(OutboxMessage outboxMessage) {
        outboxMessageMapper.insertOrUpdate(outboxMessage);
    }
}
