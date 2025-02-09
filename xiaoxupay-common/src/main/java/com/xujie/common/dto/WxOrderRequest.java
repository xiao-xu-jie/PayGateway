package com.xujie.common.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WxOrderRequest {
    /**
     * 开放ID
     **/
    @NotNull(message = "请传入开放NO")
    private String openNo;

    /**
     * 订单标题
     **/
    @NotNull(message = "请传入订单标题")
    private String title;
    /**
     * 订单价格 精确分
     **/
    @Range(min = 0,max = 9999)
    private Double totalFee;
    /**
     * 订单描述
     **/
    private String desc;
    /**
     * 订单备注
     **/
    private String remark;
}
