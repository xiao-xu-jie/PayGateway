package com.xujie.admin.DTO.req;

import com.xujie.admin.common.base.model.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * (OperLog)添加DTO
 *
 * @author xujie
 * @since 2024-09-25 16:00:13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OperLogAddReqDTO extends BaseDTO {


    /**
     * 日志ID
     */

    private Long id;

    /**
     * 日志描述
     */

    private String logDesc;

    /**
     * 执行方法名称
     */

    private String methodName;

    /**
     * 请求参数
     */

    private String requestParams;

    /**
     * 请求方法
     */

    private String requestType;

    /**
     * 返回体
     */

    private String responseBody;
    /**
     * 返回体
     */

    private String requestPath;

    /**
     * 错误信息
     */

    private String errorLog;

    /**
     * 操作人
     */

    private Long operateUser;

    /**
     * 操作IP
     */

    private String operateIp;

    /**
     * 通知用户
     */

    private String isNotify;

    /**
     * 消耗毫秒
     */

    private Long costTime;

    /**
     * 创建时间
     */

    private Date createTime;

    /**
     * 是否删除
     */

    private Integer isDelete;


}

