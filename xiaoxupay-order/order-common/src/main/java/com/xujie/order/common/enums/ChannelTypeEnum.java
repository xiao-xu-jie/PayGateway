package com.xujie.order.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ChannelTypeEnum {
    WX("wx"),
    ZFB("zfb"),
    QQ("qq");
    @JsonValue
    @EnumValue
    private final String type;
}
