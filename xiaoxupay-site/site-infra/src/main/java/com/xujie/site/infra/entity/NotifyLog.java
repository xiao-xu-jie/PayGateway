package com.xujie.site.infra.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.xujie.site.common.enums.NotifyStatus;
import com.xujie.site.common.enums.NotifyType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "notify_log")
public class NotifyLog {
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 订单号
     */
    @TableField(value = "open_no")
    private String openNo;

    /**
     * 站点ID
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
    private NotifyType notifyType;

    /**
     * 是否成功
     */
    @TableField(value = "notify_status")
    private NotifyStatus notifyStatus;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @TableField(value = "is_delete")
    private Integer isDelete;

    public static final String COL_ID = "id";

    public static final String COL_OPEN_NO = "open_no";

    public static final String COL_SITE_ID = "site_id";

    public static final String COL_NOTIFU_URL = "notifu_url";

    public static final String COL_RES_BODY = "res_body";

    public static final String COL_CURRENT_COUNT = "current_count";

    public static final String COL_NOTIFY_TYPE = "notify_type";

    public static final String COL_NOTIFY_STATUS = "notify_status";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_TIME = "update_time";

    public static final String COL_IS_DELETE = "is_delete";
}