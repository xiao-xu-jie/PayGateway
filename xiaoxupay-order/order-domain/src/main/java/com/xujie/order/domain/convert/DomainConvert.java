package com.xujie.order.domain.convert;

import com.xujie.order.domain.entity.Order;
import com.xujie.order.infra.entity.SiteOrder;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DomainConvert {
    SiteOrder bo2do(Order order);

    Order do2bo(SiteOrder order);
}
