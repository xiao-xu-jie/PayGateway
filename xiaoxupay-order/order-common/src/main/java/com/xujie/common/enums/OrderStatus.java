package com.xujie.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum OrderStatus {
    WAIT_PAY(0,"待支付"),
    CANCELED(-1,"已取消"),
    SUCCESS(1,"支付成功"),
    EXPIRED(2,"已过期"),
    REFUND(3,"已退款");
    @EnumValue
    private Integer code;
    @JsonValue
    private String desc;
}
