package com.xujie.convert;

import com.xujie.domain.entity.Order;
import com.xujie.pojo.dto.SiteCreateOrderDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface OrderDTOConvert {
    @Mappings({
            @Mapping(source = "tradeNo", target = "tradeNo"),
            @Mapping(source = "siteAppid", target = "siteAppid"),
            @Mapping(source = "title", target = "title"),
            @Mapping(source = "orderDesc", target = "orderDesc"),
            @Mapping(source = "realFee", target = "realFee"),
            @Mapping(source = "discount", target = "discount"),
            @Mapping(source = "totalFee", target = "totalFee"),
            @Mapping(source = "channel", target = "channel"),
            @Mapping(source = "remark", target = "remark"),
            @Mapping(source = "hash", target = "hash"),
            @Mapping(target = "transactionId", ignore = true),
            @Mapping(target = "openNo", ignore = true),
            @Mapping(target = "urlQrcode", ignore = true),
            @Mapping(target = "url", ignore = true),
            @Mapping(target = "jsonData", ignore = true),
            @Mapping(target = "createTime", ignore = true),
            @Mapping(target = "expireTime", ignore = true),
            @Mapping(target = "payTime", ignore = true),
            @Mapping(target = "orderStatus", ignore = true)
    })
    Order dto2bo(SiteCreateOrderDTO siteCreateOrderDTO);
}
