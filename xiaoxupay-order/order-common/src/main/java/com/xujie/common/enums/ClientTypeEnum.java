package com.xujie.common.enums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 支付通道客户端类型：app h5 pc
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ClientTypeEnum {
    APP("app"),
    H5("h5"),
    PC("PC");

    private String type;
}
