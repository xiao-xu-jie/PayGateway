package com.xujie.infra.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.xujie.common.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "`order`")
public class Order {
    /**
     * ID
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 平台统一NO
     */
    @TableField(value = "open_no")
    private String openNo;

    /**
     * 站点内NO
     */
    @TableField(value = "trade_no")
    private Long tradeNo;

    /**
     * 站点ID
     */
    @TableField(value = "site_id")
    private Long siteId;

    /**
     * 订单标题
     */
    @TableField(value = "title")
    private String title;

    /**
     * 订单描述
     */
    @TableField(value = "order_desc")
    private String orderDesc;

    /**
     * 实际金额
     */
    @TableField(value = "real_fee")
    private BigDecimal realFee;

    @TableField(value = "discount")
    private BigDecimal discount;

    /**
     * 总金额
     */
    @TableField(value = "total_fee")
    private BigDecimal totalFee;

    /**
     * 备注
     */
    @TableField(value = "remark")
    private String remark;

    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 过期时间
     */
    @TableField(value = "expire_time")
    private Date expireTime;

    /**
     * 支付时间
     */
    @TableField(value = "pay_time")
    private Date payTime;

    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * 订单状态
     */
    @TableField(value = "order_status")
    private OrderStatus orderStatus;

    @TableField(value = "is_delete")
    private Integer isDelete;

    public static final String COL_ID = "id";

    public static final String COL_OPEN_NO = "open_no";

    public static final String COL_TRADE_NO = "trade_no";

    public static final String COL_SITE_ID = "site_id";

    public static final String COL_TITLE = "title";

    public static final String COL_ORDER_DESC = "order_desc";

    public static final String COL_REAL_FEE = "real_fee";

    public static final String COL_DISCOUNT = "discount";

    public static final String COL_TOTAL_FEE = "total_fee";

    public static final String COL_REMARK = "remark";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_EXPIRE_TIME = "expire_time";

    public static final String COL_PAY_TIME = "pay_time";

    public static final String COL_UPDATE_TIME = "update_time";

    public static final String COL_ORDER_STATUS = "order_status";

    public static final String COL_IS_DELETE = "is_delete";
}