package com.xujie.order.pojo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

public class OrderDto {
    @Data
    @NoArgsConstructor
    @ToString
    public static class SiteCreateOrderRequest {

        /**
         * 站点内NO
         */
        @NotNull(message = "请传入站内订单号")
        private Long tradeNo;

        /**
         * 站点ID
         */
        @NotEmpty(message = "请传入站点appid")
        private String siteAppid;

        /**
         * 订单标题
         */
        @Length(min = 1, max = 15)
        @NotEmpty(message = "请传入订单标题")
        private String title;

        /**
         * 订单描述
         */
        @Length(min = 1, max = 30)
        @NotEmpty(message = "请传入订单描述")
        private String orderDesc;

        /**
         * 实际金额
         */
        @Range(min = 0, max = 9999)
        private double realFee;

        private double discount;

        /**
         * 总金额
         */
        @Range(min = 0, max = 9999)
        private double totalFee;

        /**
         * 支付通道
         */
        @NotNull(message = "请传入通道")
        private String channel;

        /**
         * 客户端
         */
        @NotNull(message = "请传入客户端")
        private String client;

        /**
         * 备注
         */
        private String remark;
        /**
         * 随机字符串
         */
        @NotEmpty(message = "请传入随机字符串")
        private String nonceStr;

        /**
         * 时间戳
         */
        @NotEmpty(message = "请传入时间戳")
        private String timestamp;
        /**
         * hash校验
         */
        @NotEmpty(message = "请传入hash")
        private String hash;

    }


    @Data
    @NoArgsConstructor
    @ToString
    public static class SiteQueryOrderRequest {
        /**
         * 站点的appId
         */
        @NotBlank
        private String siteAppid;


        /**
         * 站点内NO
         */
        @NotNull
        private Long tradeNo;
    }
}
