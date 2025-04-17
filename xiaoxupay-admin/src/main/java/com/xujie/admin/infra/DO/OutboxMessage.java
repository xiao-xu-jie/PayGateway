package com.xujie.admin.infra.DO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xujie.admin.common.base.model.BaseDO;
import lombok.*;

import java.util.Date;

/**
 * 事务性消息发送箱表(OutboxMessage)表实体类
 *
 * @author xujie
 * @since 2025-04-17 12:04:56
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName(value = "outbox_message")
public class OutboxMessage extends BaseDO {

    public static final String COL_id = "id" ;
    public static final String COL_aggregateType = "aggregate_type" ;
    public static final String COL_aggregateId = "aggregate_id" ;
    public static final String COL_type = "type" ;
    public static final String COL_payload = "payload" ;
    public static final String COL_createdAt = "created_at" ;
    public static final String COL_priority = "priority" ;
    public static final String COL_processed = "processed" ;
    public static final String COL_processedAt = "processed_at" ;
    public static final String COL_errorMessage = "error_message" ;
    public static final String COL_retryCount = "retry_count" ;


    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 聚合根类型，如Order、Product等
     */
    @TableField(value = "aggregate_type")
    private String aggregateType;

    /**
     * 业务实体ID，如订单号
     */
    @TableField(value = "aggregate_id")
    private String aggregateId;

    /**
     * 事件类型，如OrderPaidEvent
     */
    @TableField(value = "type")
    private String type;

    /**
     * 消息内容/负载
     */
    @TableField(value = "payload")
    private String payload;

    /**
     * 创建时间
     */
    @TableField(value = "created_at")
    private Date createdAt;

    /**
     * 消息优先级，数字越大优先级越高
     */
    @TableField(value = "priority")
    private Integer priority;

    /**
     * 是否已处理
     */
    @TableField(value = "processed")
    private Boolean processed;

    /**
     * 处理时间
     */
    @TableField(value = "processed_at")
    private Date processedAt;

    /**
     * 处理错误信息
     */
    @TableField(value = "error_message")
    private String errorMessage;

    /**
     * 重试次数
     */
    @TableField(value = "retry_count")
    private Integer retryCount;


}

