package com.xujie.admin.domain.BO;

import com.xujie.admin.common.base.model.BaseBO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * (SiteInfo)BO
 *
 * @author xujie
 * @since 2025-04-17 12:04:53
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SiteInfoBO extends BaseBO {


    /**
     * ID
     */

    private Long id;

    /**
     * 站点名称
     */

    private String siteName;

    /**
     * 站点描述
     */

    private String siteDesc;

    /**
     * 回调类型
     */

    private Integer notifyType;

    /**
     * 回调URL
     */

    private String notifyUrl;

    /**
     * 站点ID
     */

    private String siteAppid;

    /**
     * 站点SEC
     */

    private String siteSecret;

    private Date createTime;

    private Date updateTime;

    private Integer isDelete;


}

