package com.xujie.domain.convert;

import com.xujie.domain.entity.NotifySiteLog;
import com.xujie.infra.entity.NotifyLog;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NotifyLogDomainConvert {
    NotifyLog bo2do(NotifySiteLog siteLog);
}
