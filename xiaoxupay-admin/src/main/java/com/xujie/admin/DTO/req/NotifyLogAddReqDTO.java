package com.xujie.admin.DTO.req;

import com.xujie.admin.common.base.model.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * (NotifyLog)添加DTO
 *
 * @author xujie
 * @since 2025-04-17 12:04:54
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotifyLogAddReqDTO extends BaseDTO {


    private Long id;

    /**
     * 订单号
     */

    private String openNo;

    /**
     * 站点appID
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

    private Integer notifyType;

    /**
     * 是否成功
     */

    private Integer notifyStatus;

    private Date createTime;

    private Date updateTime;

    private Integer isDelete;


}

