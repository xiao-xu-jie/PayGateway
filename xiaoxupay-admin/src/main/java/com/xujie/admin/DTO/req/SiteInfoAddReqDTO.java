package com.xujie.admin.DTO.req;

import com.xujie.admin.common.base.model.BaseDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * (SiteInfo)添加DTO
 *
 * @author xujie
 * @since 2025-04-17 12:04:52
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SiteInfoAddReqDTO extends BaseDTO {


    /**
     * ID
     */

    private Long id;

    /**
     * 站点名称
     */

    @NotBlank
    private String siteName;

    /**
     * 站点描述
     */
    @NotBlank
    private String siteDesc;

    /**
     * 回调类型
     */

    @NotNull
    private Integer notifyType;

    /**
     * 回调URL
     */
    @NotBlank
    private String notifyUrl;
    

    private Date createTime;

    private Date updateTime;

    private Integer isDelete;


}

