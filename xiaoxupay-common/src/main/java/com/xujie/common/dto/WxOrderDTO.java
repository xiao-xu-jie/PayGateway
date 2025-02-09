package com.xujie.common.dto;

import cn.hutool.json.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WxOrderDTO {
    private String transactionId;
    private String openNo;
    private String urlQrcode;
    private String url;
    private String channel;
    private Date expireTime;
    private JSONObject jsonData;
}
