package com.xujie.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderNotifyStatus {
    WAIT_NOTIFY(0, "等待通知"),
    RETRY_NOTIFY(2, "重新通知"),
    NOTIFY_FAILED(-1, "通知失败"),
    NOTIFY_SUCCESS(1, "通知成功");
    @EnumValue
    private final Integer code;
    @JsonValue
    private final String desc;
}
