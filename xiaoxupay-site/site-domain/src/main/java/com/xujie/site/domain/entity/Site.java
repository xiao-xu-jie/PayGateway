package com.xujie.site.domain.entity;


import com.xujie.site.common.enums.NotifyType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Site {
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
    private NotifyType notifyType;

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
}
