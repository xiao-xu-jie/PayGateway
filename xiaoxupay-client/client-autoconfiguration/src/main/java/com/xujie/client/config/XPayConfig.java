package com.xujie.client.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "pay.client.site")
public class XPayConfig {
    private String siteAppId;
    private String siteAppSecret;

    private String serverUrl;
}
