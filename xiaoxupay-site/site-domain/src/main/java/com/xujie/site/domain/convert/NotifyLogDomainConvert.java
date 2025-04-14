package com.xujie.site.domain.convert;

import com.xujie.site.domain.entity.NotifySiteLog;
import com.xujie.site.infra.entity.NotifyLog;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NotifyLogDomainConvert {
    NotifyLog bo2do(NotifySiteLog siteLog);
}
