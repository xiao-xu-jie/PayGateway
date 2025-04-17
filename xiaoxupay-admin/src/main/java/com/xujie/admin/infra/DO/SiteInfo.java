package com.xujie.admin.infra.DO;

import com.baomidou.mybatisplus.annotation.*;
import com.xujie.admin.common.base.model.BaseDO;
import lombok.*;

import java.util.Date;

/**
 * (SiteInfo)表实体类
 *
 * @author xujie
 * @since 2025-04-17 12:04:53
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName(value = "site_info")
public class SiteInfo extends BaseDO {

    public static final String COL_id = "id" ;
    public static final String COL_siteName = "site_name" ;
    public static final String COL_siteDesc = "site_desc" ;
    public static final String COL_notifyType = "notify_type" ;
    public static final String COL_notifyUrl = "notify_url" ;
    public static final String COL_siteAppid = "site_appid" ;
    public static final String COL_siteSecret = "site_secret" ;
    public static final String COL_createTime = "create_time" ;
    public static final String COL_updateTime = "update_time" ;
    public static final String COL_isDelete = "is_delete" ;


    /**
     * ID
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
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
    private Integer notifyType;

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
    @TableLogic(value = "0", delval = "1")
    private Integer isDelete;


}

