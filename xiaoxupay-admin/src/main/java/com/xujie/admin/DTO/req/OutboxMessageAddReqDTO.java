package com.xujie.admin.DTO.req;

import com.xujie.admin.common.base.model.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 事务性消息发送箱表(OutboxMessage)添加DTO
 *
 * @author xujie
 * @since 2025-04-17 12:04:55
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OutboxMessageAddReqDTO extends BaseDTO {


    /**
     * 主键ID
     */

    private Long id;

    /**
     * 聚合根类型，如Order、Product等
     */

    private String aggregateType;

    /**
     * 业务实体ID，如订单号
     */

    private String aggregateId;

    /**
     * 事件类型，如OrderPaidEvent
     */

    private String type;

    /**
     * 消息内容/负载
     */

    private String payload;

    /**
     * 创建时间
     */

    private Date createdAt;

    /**
     * 消息优先级，数字越大优先级越高
     */

    private Integer priority;

    /**
     * 是否已处理
     */

    private Boolean processed;

    /**
     * 处理时间
     */

    private Date processedAt;

    /**
     * 处理错误信息
     */

    private String errorMessage;

    /**
     * 重试次数
     */

    private Integer retryCount;


}

