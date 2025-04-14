package com.xujie.site.domain.entity;

import com.xujie.site.common.enums.NotifyType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotifyRequest {
    private String notifyUrl;
    private String siteSecret;
    private NotifyType notifyType;
    private String openNo;
}
