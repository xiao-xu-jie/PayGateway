package com.xujie.infra.service.impl;

import com.xujie.infra.entity.NotifyLog;
import com.xujie.infra.mapper.NotifyLogMapper;
import com.xujie.infra.service.NotifyLogService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class NotifyLogServiceImpl implements NotifyLogService {
    @Resource
    private NotifyLogMapper notifyLogMapper;

    @Override
    public void insertLog(NotifyLog notifyLog) {
        notifyLogMapper.insert(notifyLog);
    }
}
