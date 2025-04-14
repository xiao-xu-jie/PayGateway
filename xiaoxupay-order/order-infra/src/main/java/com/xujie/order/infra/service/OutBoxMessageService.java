package com.xujie.order.infra.service;

import com.xujie.common.enums.AggregateType;
import com.xujie.common.enums.OutBoxMessageType;
import com.xujie.order.infra.entity.OutboxMessage;

import java.util.List;

public interface OutBoxMessageService {
    OutboxMessage findOutboxMessageByAggregateId(String aggregateId);

    List<OutboxMessage> findOutboxMessageByPriority(AggregateType aggregateType, OutBoxMessageType outBoxMessageType);

    List<OutboxMessage> findOutboxMessageByPriority(AggregateType aggregateType, OutBoxMessageType outBoxMessageType, Integer limit);

    List<OutboxMessage> findOutboxMessageByType(AggregateType aggregateType, OutBoxMessageType outBoxMessageType);

    void saveOrUpdate(OutboxMessage outboxMessage);
}
