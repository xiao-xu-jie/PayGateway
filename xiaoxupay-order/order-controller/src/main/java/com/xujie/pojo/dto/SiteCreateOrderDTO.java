package com.xujie.pojo.dto;

import com.xujie.common.enums.ChannelTypeEnum;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SiteCreateOrderDTO {

    /**
     * 站点内NO
     */
    @NotNull(message = "请传入站内订单号")
    private Long tradeNo;

    /**
     * 站点ID
     */
    @NotEmpty(message = "请传入站点appid")
    @Pattern(regexp = "^[a-zA-Z0-9_-]{16}$", message = "站点appid格式不正确")
    private String siteAppid;

    /**
     * 订单标题
     */
    @NotEmpty(message = "请传入订单标题")
    private String title;

    /**
     * 订单描述
     */
    @NotEmpty(message = "请传入订单描述")
    private String orderDesc;

    /**
     * 实际金额
     */
    @Range(min = 0,max = 9999)
    private double realFee;

    private double discount;

    /**
     * 总金额
     */
    @Range(min = 0,max = 9999)
    private double totalFee;

    /**
     * 支付通道
     */
    @NotNull(message = "请传入通道")
    private ChannelTypeEnum channel;

    /**
     * 备注
     */
    private String remark;

    /**
     * hash校验
     */
    private String hash;

}
