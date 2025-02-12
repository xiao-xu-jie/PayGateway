package com.xujie.domain.service.impl;

import com.xujie.domain.convert.NotifyLogDomainConvert;
import com.xujie.domain.entity.NotifySiteLog;
import com.xujie.domain.service.NotifyLogDomainService;
import com.xujie.infra.service.NotifyLogService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class NotifyLogDomainServiceImpl implements NotifyLogDomainService {
    @Resource
    private NotifyLogService notifyLogService;
    @Resource
    private NotifyLogDomainConvert notifyLogDomainConvert;

    @Override
    public void addOneLog(NotifySiteLog siteLog) {
        notifyLogService.insertLog(notifyLogDomainConvert.bo2do(siteLog));
    }
}
