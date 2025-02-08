package com.xujie.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ResponseCodeEnum {
    SUCCESS(200,"ok"),
    ERROR(201,"fail");

    private Integer code;
    private String message;
}
