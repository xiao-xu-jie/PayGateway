package com.xujie.order.domain.strategy;

import com.xujie.order.common.enums.ChannelTypeEnum;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ChannelContext {
    @Resource
    private Map<String, ChannelStrategy> map;

    public ChannelStrategy distributeChannelStrategy(ChannelTypeEnum type) {
        return map.get(type.getDesc() + "-Channel");
    }

}
