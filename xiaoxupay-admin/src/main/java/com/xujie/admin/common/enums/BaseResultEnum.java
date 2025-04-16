package com.xujie.admin.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum BaseResultEnum {
    SUCCESS(200, "成功"),
    FAIL(500, "失败"),
    ORDER_NOT_FOUND(501, "订单不存在"), ORDER_PAYED(502, "订单已支付");
    private final Integer code;
    private final String message;

    //通过code获取枚举
    public static BaseResultEnum getEnumByCode(Integer code) {
        for (BaseResultEnum baseResultEnum : BaseResultEnum.values()) {
            if (baseResultEnum.getCode().equals(code)) {
                return baseResultEnum;
            }
        }
        return null;
    }
}
