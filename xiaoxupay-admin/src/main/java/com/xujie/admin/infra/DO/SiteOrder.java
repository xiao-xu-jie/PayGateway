package com.xujie.admin.infra.DO;

import com.baomidou.mybatisplus.annotation.*;
import com.xujie.admin.common.base.model.BaseDO;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

/**
 * (SiteOrder)表实体类
 *
 * @author xujie
 * @since 2025-04-17 12:04:57
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName(value = "site_order")
public class SiteOrder extends BaseDO {

    public static final String COL_id = "id" ;
    public static final String COL_transactionId = "transaction_id" ;
    public static final String COL_openNo = "open_no" ;
    public static final String COL_tradeNo = "trade_no" ;
    public static final String COL_siteAppid = "site_appid" ;
    public static final String COL_title = "title" ;
    public static final String COL_orderDesc = "order_desc" ;
    public static final String COL_realFee = "real_fee" ;
    public static final String COL_discount = "discount" ;
    public static final String COL_totalFee = "total_fee" ;
    public static final String COL_channel = "channel" ;
    public static final String COL_urlQrcode = "url_qrcode" ;
    public static final String COL_url = "url" ;
    public static final String COL_remark = "remark" ;
    public static final String COL_jsonData = "json_data" ;
    public static final String COL_createTime = "create_time" ;
    public static final String COL_expireTime = "expire_time" ;
    public static final String COL_payTime = "pay_time" ;
    public static final String COL_updateTime = "update_time" ;
    public static final String COL_orderStatus = "order_status" ;
    public static final String COL_notifyStatus = "notify_status" ;
    public static final String COL_isDelete = "is_delete" ;


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
    private Object jsonData;
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
    private Integer orderStatus;

    /**
     * 通知状态
     */
    @TableField(value = "notify_status")
    private Integer notifyStatus;
    @TableField(value = "is_delete")
    @TableLogic(value = "0", delval = "1")
    private Integer isDelete;


}

