package com.xujie.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SiteCreateOrderDTO {

    /**
     * 站点内NO
     */
    private Long tradeNo;

    /**
     * 站点ID
     */
    private Long siteId;

    /**
     * 订单标题
     */
    private String title;

    /**
     * 订单描述
     */
    private String orderDesc;

    /**
     * 实际金额
     */
    private double realFee;

    private double discount;

    /**
     * 总金额
     */
    private double totalFee;

    /**
     * 支付通道
     */
    private String channel;

    /**
     * 备注
     */
    private String remark;

    /**
     * hash校验
     */
    private String hash;

}
