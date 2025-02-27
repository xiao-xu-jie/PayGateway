package com.xujie.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum OutBoxMessageType {
    ORDER_PAID("paid"),
    SITE_NOTIFY("site");
    private String type;
}
