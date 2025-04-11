package com.xujie.infra.service;

import com.xujie.infra.entity.OutboxMessage;
import com.xujie.payGateway.common.enums.AggregateType;
import com.xujie.payGateway.common.enums.OutBoxMessageType;

import java.util.List;

public interface OutBoxMessageService {
    OutboxMessage findOutboxMessageByAggregateId(String aggregateId);

    List<OutboxMessage> findOutboxMessageByPriority(AggregateType aggregateType, OutBoxMessageType outBoxMessageType);

    List<OutboxMessage> findOutboxMessageByPriority(AggregateType aggregateType, OutBoxMessageType outBoxMessageType, Integer limit);

    List<OutboxMessage> findOutboxMessageByType(AggregateType aggregateType, OutBoxMessageType outBoxMessageType);

    void saveOrUpdate(OutboxMessage outboxMessage);
}
