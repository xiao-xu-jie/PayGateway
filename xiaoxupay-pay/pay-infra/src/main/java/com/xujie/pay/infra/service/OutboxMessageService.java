package com.xujie.pay.infra.service;

import com.xujie.pay.infra.entity.OutboxMessage;
import com.xujie.pay.infra.mapper.OutboxMessageMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @author Xujie
 * @since 2025/2/27 14:22
 **/

@Service
public class OutboxMessageService {
    @Resource
    private OutboxMessageMapper outboxMessageMapper;

    public void save(OutboxMessage outboxMessage) {
        outboxMessageMapper.insert(outboxMessage);
    }
}
