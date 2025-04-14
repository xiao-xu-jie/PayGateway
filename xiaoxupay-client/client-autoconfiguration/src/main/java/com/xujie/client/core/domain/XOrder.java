package com.xujie.client.core.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class XOrder {
    /**
     * 站点内NO
     */
    private Long tradeNo;

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

    /**
     * 总金额
     */
    private double totalFee;

    /**
     * 支付通道
     */
    private String channel;
    /**
     * 支付通道
     */
    private String client;

    private String urlQrcode;

    private String url;

    /**
     * 备注
     */
    private String remark;

    private Date createTime;

    /**
     * 过期时间
     */
    private Date expireTime;

    /**
     * 支付时间
     */
    private Date payTime;


    /**
     * 订单状态
     */
    private Integer orderStatus;

}
