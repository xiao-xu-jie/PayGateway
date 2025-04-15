package com.xujie.client.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

public class XOrderDto {
    @Data
    @ToString
    @Builder
    public static class XOrderCreateRequest {
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
         * 客户端
         */
        private String client;

        /**
         * 备注
         */
        private String remark;

    }

    @Data
    @ToString
    public static class XOrderCreateResponse {
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
         * 站点APPID
         */
        private String siteAppid;


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
         * 支付通道
         */
        private String client;

        private String urlQrcode;

        private String url;

        /**
         * 备注
         */
        private String remark;

        private String jsonData;

        private String createTime;

        /**
         * 过期时间
         */
        private String expireTime;

        /**
         * 支付时间
         */
        private String payTime;


        /**
         * 订单状态
         */
        private Integer orderStatus;
        /**
         * 订单通知状态
         */
        private Integer notifyStatus;

        /**
         * 随机字符串
         */
        private String nonceStr;

        /**
         * 时间戳
         */
        private String timestamp;

        /**
         * hash校验
         */
        private String hash;
    }

    @Data
    @ToString
    public static class XOrderNotifyRequest {
        private String openNo;

        private String nonceStr;

        private String timestamp;

        private String hash;

    }

    @Data
    @ToString
    @Builder
    public static class XOrderQueryRequest {
        private Long tradeNo;
    }

    public static class XOrderQueryResponse {
    }
}
