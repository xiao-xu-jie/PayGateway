package com.xujie.admin.infra.DO;

import com.baomidou.mybatisplus.annotation.*;
import com.xujie.admin.common.base.model.BaseDO;
import lombok.*;

import java.util.Date;

/**
 * (OperLog)表实体类
 *
 * @author xujie
 * @since 2024-09-25 16:00:17
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName(value = "sys_oper_log")
public class SysOperLog extends BaseDO {

    public static final String COL_id = "id";
    public static final String COL_logDesc = "log_desc";
    public static final String COL_methodName = "method_name";
    public static final String COL_requestParams = "request_params";
    public static final String COL_requestType = "request_type";
    public static final String COL_responseBody = "response_body";
    public static final String COL_requestPath = "requestPath";

    public static final String COL_errorLog = "error_log";
    public static final String COL_operateUser = "operate_user";
    public static final String COL_operateIp = "operate_ip";
    public static final String COL_isNotify = "is_notify";
    public static final String COL_costTime = "cost_time";
    public static final String COL_createTime = "create_time";
    public static final String COL_isDelete = "is_delete";


    /**
     * 日志ID
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 日志描述
     */
    @TableField(value = "log_desc")
    private String logDesc;

    /**
     * 执行方法名称
     */
    @TableField(value = "method_name")
    private String methodName;

    /**
     * 请求参数
     */
    @TableField(value = "request_params")
    private String requestParams;

    /**
     * 请求方法
     */
    @TableField(value = "request_type")
    private String requestType;

    /**
     * 返回体
     */
    @TableField(value = "response_body")
    private String responseBody;
    /**
     * 请求地址
     */
    @TableField(value = "request_path")
    private String requestPath;

    /**
     * 错误信息
     */
    @TableField(value = "error_log")
    private String errorLog;

    /**
     * 操作人
     */
    @TableField(value = "operate_user")
    private Long operateUser;

    /**
     * 操作IP
     */
    @TableField(value = "operate_ip")
    private String operateIp;

    /**
     * 通知用户
     */
    @TableField(value = "is_notify")
    private String isNotify;

    /**
     * 消耗毫秒
     */
    @TableField(value = "cost_time")
    private Long costTime;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 是否删除
     */
    @TableField(value = "is_delete")
    @TableLogic(value = "0", delval = "1")
    private Integer isDelete;

    /**
     * 操作时间
     */
    @TableField(exist = false)
    private String[] searchDate;

}

