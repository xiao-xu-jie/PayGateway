package com.xujie.site.domain.service.impl;

import com.xujie.site.domain.convert.NotifyLogDomainConvert;
import com.xujie.site.domain.entity.NotifySiteLog;
import com.xujie.site.domain.service.NotifyLogDomainService;
import com.xujie.site.infra.service.NotifyLogService;
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
