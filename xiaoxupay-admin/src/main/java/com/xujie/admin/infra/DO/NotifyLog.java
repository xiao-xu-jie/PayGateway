package com.xujie.admin.infra.DO;

import com.baomidou.mybatisplus.annotation.*;
import com.xujie.admin.common.base.model.BaseDO;
import lombok.*;

import java.util.Date;

/**
 * (NotifyLog)表实体类
 *
 * @author xujie
 * @since 2025-04-17 12:04:55
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName(value = "notify_log")
public class NotifyLog extends BaseDO {

    public static final String COL_id = "id" ;
    public static final String COL_openNo = "open_no" ;
    public static final String COL_siteAppid = "site_appid" ;
    public static final String COL_notifuUrl = "notifu_url" ;
    public static final String COL_resBody = "res_body" ;
    public static final String COL_currentCount = "current_count" ;
    public static final String COL_notifyType = "notify_type" ;
    public static final String COL_notifyStatus = "notify_status" ;
    public static final String COL_createTime = "create_time" ;
    public static final String COL_updateTime = "update_time" ;
    public static final String COL_isDelete = "is_delete" ;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 订单号
     */
    @TableField(value = "open_no")
    private String openNo;

    /**
     * 站点appID
     */
    @TableField(value = "site_appid")
    private String siteAppid;

    /**
     * 通知地址
     */
    @TableField(value = "notifu_url")
    private String notifuUrl;

    /**
     * 站点返回体
     */
    @TableField(value = "res_body")
    private String resBody;

    /**
     * 当前第几次通知
     */
    @TableField(value = "current_count")
    private Integer currentCount;

    /**
     * 通知方式 1/自动 2/手动
     */
    @TableField(value = "notify_type")
    private Integer notifyType;

    /**
     * 是否成功
     */
    @TableField(value = "notify_status")
    private Integer notifyStatus;
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    @TableField(value = "is_delete")
    @TableLogic(value = "0", delval = "1")
    private Integer isDelete;


}

