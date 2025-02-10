package com.xujie.dto;

import com.xujie.common.enums.NotifyType;
import com.xujie.common.groups.CreateGroup;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SiteDTO {

    /**
     * 站点名称
     */
    @NotEmpty(groups = {CreateGroup.class},message = "站点名称不为空")
    private String siteName;

    /**
     * 站点描述
     */
    @NotEmpty(groups = {CreateGroup.class},message = "站点描述不为空")
    private String siteDesc;

    /**
     * 回调类型
     */
    @NotNull(groups = {CreateGroup.class},message = "站点回调类型不为空")
    private NotifyType notifyType;

    /**
     * 回调URL
     */
    @NotEmpty(groups = {CreateGroup.class},message = "站点回调URL不为空")
    private String notifyUrl;

    /**
     * 站点ID
     */
    private String siteAppid;

    /**
     * 站点SEC
     */
    private String siteSecret;
}
