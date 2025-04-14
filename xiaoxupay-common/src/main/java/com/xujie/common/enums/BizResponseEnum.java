package com.xujie.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum BizResponseEnum {
    AUTH_FAILED(1001, "认证失败，请正确提交请求"),
    TRADE_NO_EXISTED(1002, "站点下已经存在该订单号"),
    TIMESTAMP_EXPIRED(1003, "传入时间戳失效"),
    CHANNEL_TYPE_NOT_EXIST(1004, "Channel 类型不存在"),
    CLIENT_TYPE_NOT_EXIST(1005, "Client 类型不存在"),
    UN_EXCEPT_ERROR(1006, "系统意外异常，请看日常排除");
    private Integer code;
    private String message;
}
