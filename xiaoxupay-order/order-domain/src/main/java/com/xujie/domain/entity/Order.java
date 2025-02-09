package com.xujie.domain.entity;

import com.xujie.common.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    /**
     * 第三方ID
     */
    private String transactionId;

    /**
     * 平台统一NO
     */
    private String openNo;

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

    private String urlQrcode;

    private String url;

    /**
     * 备注
     */
    private String remark;

    private String jsonData;

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
    private OrderStatus orderStatus;

    /**
     * hash校验
     */
    private String hash;

}
