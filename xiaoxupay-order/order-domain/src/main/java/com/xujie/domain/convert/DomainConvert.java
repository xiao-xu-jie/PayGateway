package com.xujie.domain.convert;

import com.xujie.domain.entity.Order;
import com.xujie.infra.entity.SiteOrder;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DomainConvert {
    SiteOrder bo2do(Order order);
    Order do2bo(SiteOrder order);
}
