package com.xujie.payGateway.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum OutBoxMessageType {
    ORDER_PAID("paid"),
    SITE_NOTIFY("notify");
    private String type;
}
