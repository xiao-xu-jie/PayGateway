package com.xujie.payGateway.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 业务聚合类型
 */

@AllArgsConstructor
@NoArgsConstructor
public enum AggregateType {
    ORDER("order"),
    SITE("site");
    @Getter
    private String type;
}
