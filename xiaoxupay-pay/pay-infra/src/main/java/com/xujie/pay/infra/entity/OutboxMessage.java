package com.xujie.pay.infra.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 事务性消息发送箱表
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "outbox_message")
public class OutboxMessage {
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
    @TableField(value = "`type`")
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

    public static final String COL_ID = "id";

    public static final String COL_AGGREGATE_TYPE = "aggregate_type";

    public static final String COL_AGGREGATE_ID = "aggregate_id";

    public static final String COL_TYPE = "type";

    public static final String COL_PAYLOAD = "payload";

    public static final String COL_CREATED_AT = "created_at";

    public static final String COL_PRIORITY = "priority";

    public static final String COL_PROCESSED = "processed";

    public static final String COL_PROCESSED_AT = "processed_at";

    public static final String COL_ERROR_MESSAGE = "error_message";

    public static final String COL_RETRY_COUNT = "retry_count";
}