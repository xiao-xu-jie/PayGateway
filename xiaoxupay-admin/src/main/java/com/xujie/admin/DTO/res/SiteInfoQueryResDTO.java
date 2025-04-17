package com.xujie.admin.DTO.res;


import com.xujie.admin.common.base.model.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * (SiteInfo)查询返回DTO
 *
 * @author xujie
 * @since 2025-04-17 12:04:54
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SiteInfoQueryResDTO extends BaseDTO {


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

