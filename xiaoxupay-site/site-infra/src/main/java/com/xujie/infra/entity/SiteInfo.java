package com.xujie.infra.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xujie.common.enums.NotifyType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "site_info")
public class SiteInfo {
    /**
     * ID
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 站点名称
     */
    @TableField(value = "site_name")
    private String siteName;

    /**
     * 站点描述
     */
    @TableField(value = "site_desc")
    private String siteDesc;

    /**
     * 回调类型
     */
    @TableField(value = "notify_type")
    private NotifyType notifyType;

    /**
     * 回调URL
     */
    @TableField(value = "notify_url")
    private String notifyUrl;

    /**
     * 站点ID
     */
    @TableField(value = "site_appid")
    private String siteAppid;

    /**
     * 站点SEC
     */
    @TableField(value = "site_secret")
    private String siteSecret;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @TableField(value = "is_delete")
    private Integer isDelete;

    public static final String COL_ID = "id";

    public static final String COL_SITE_NAME = "site_name";

    public static final String COL_SITE_DESC = "site_desc";

    public static final String COL_NOTIFY_TYPE = "notify_type";

    public static final String COL_NOTIFY_URL = "notify_url";

    public static final String COL_SITE_APPID = "site_appid";

    public static final String COL_SITE_SECRET = "site_secret";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_TIME = "update_time";

    public static final String COL_IS_DELETE = "is_delete";
}