package com.xujie.admin.domain.BO;

import com.xujie.admin.common.base.model.BaseBO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * (SiteOrder)BO
 *
 * @author xujie
 * @since 2025-04-17 12:04:57
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SiteOrderBO extends BaseBO {


    /**
     * ID
     */

    private Long id;

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

    private BigDecimal realFee;

    private BigDecimal discount;

    /**
     * 总金额
     */

    private BigDecimal totalFee;

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

    private Object jsonData;

    private Date createTime;

    /**
     * 过期时间
     */

    private Date expireTime;

    /**
     * 支付时间
     */

    private Date payTime;

    private Date updateTime;

    /**
     * 订单状态
     */

    private Integer orderStatus;

    /**
     * 通知状态
     */

    private Integer notifyStatus;

    private Integer isDelete;


}

