package com.xujie.domain.entity;

import com.xujie.common.enums.NotifyType;
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
