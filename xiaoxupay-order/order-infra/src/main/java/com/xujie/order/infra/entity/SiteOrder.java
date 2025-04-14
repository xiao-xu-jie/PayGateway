package com.xujie.order.infra.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.xujie.order.common.enums.OrderNotifyStatus;
import com.xujie.order.common.enums.OrderStatus;
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
@TableName(value = "site_order")
public class SiteOrder {
    /**
     * ID
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 第三方ID
     */
    @TableField(value = "transaction_id")
    private String transactionId;

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
    @TableField(value = "site_appid")
    private String siteAppid;

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
     * 支付通道
     */
    @TableField(value = "channel")
    private String channel;

    @TableField(value = "url_qrcode")
    private String urlQrcode;

    @TableField(value = "url")
    private String url;

    /**
     * 备注
     */
    @TableField(value = "remark")
    private String remark;

    @TableField(value = "json_data")
    private String jsonData;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
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

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * 订单状态
     */
    @TableField(value = "order_status")
    private OrderStatus orderStatus;
    /**
     * 订单通知状态
     */
    @TableField(value = "notify_status")
    private OrderNotifyStatus notifyStatus;

    @TableField(value = "is_delete")
    private Integer isDelete;


    public static final String COL_ID = "id";

    public static final String COL_TRANSACTION_ID = "transaction_id";

    public static final String COL_OPEN_NO = "open_no";

    public static final String COL_TRADE_NO = "trade_no";

    public static final String COL_SITE_ID = "site_id";

    public static final String COL_TITLE = "title";

    public static final String COL_ORDER_DESC = "order_desc";

    public static final String COL_REAL_FEE = "real_fee";

    public static final String COL_DISCOUNT = "discount";

    public static final String COL_TOTAL_FEE = "total_fee";

    public static final String COL_CHANNEL = "channel";

    public static final String COL_URL_QRCODE = "url_qrcode";

    public static final String COL_URL = "url";

    public static final String COL_REMARK = "remark";

    public static final String COL_JSON_DATA = "json_data";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_EXPIRE_TIME = "expire_time";

    public static final String COL_PAY_TIME = "pay_time";

    public static final String COL_UPDATE_TIME = "update_time";

    public static final String COL_ORDER_STATUS = "order_status";

    public static final String COL_IS_DELETE = "is_delete";
}