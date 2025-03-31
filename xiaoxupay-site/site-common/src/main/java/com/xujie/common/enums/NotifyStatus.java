package com.xujie.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum NotifyStatus {
    SUCCESS(1, "success"),
    FAILED(-1, "failed");
    @EnumValue
    private final Integer code;
    @JsonValue
    private final String desc;
}
