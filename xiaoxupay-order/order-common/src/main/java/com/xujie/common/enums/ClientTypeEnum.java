package com.xujie.common.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 支付通道客户端类型：app h5 pc
 */
@Getter
@AllArgsConstructor
public enum ClientTypeEnum {
    APP("app"),
    H5("h5"),
    PC("pc");

    @JsonValue
    private final String type;
}
