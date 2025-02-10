package com.xujie.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum NotifyType {
    HTTP(1,"http");
    @EnumValue
    private final Integer code;
    @JsonValue
    private final String desc;
}
