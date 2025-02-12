package com.xujie.domain.entity;

import com.xujie.common.enums.NotifyStatus;
import com.xujie.common.enums.NotifyType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotifySiteLog {
    private Long id;

    /**
     * 订单号
     */
    private String openNo;

    /**
     * 站点ID
     */
    private String siteAppid;

    /**
     * 通知地址
     */
    private String notifuUrl;

    /**
     * 站点返回体
     */
    private String resBody;

    /**
     * 当前第几次通知
     */
    private Integer currentCount;

    /**
     * 通知方式 1/自动 2/手动
     */
    private NotifyType notifyType;

    /**
     * 是否成功
     */
    private NotifyStatus notifyStatus;

}