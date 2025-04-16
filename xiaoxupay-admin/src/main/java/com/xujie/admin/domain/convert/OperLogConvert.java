package com.xujie.admin.domain.convert;

import com.xujie.admin.DTO.req.OperLogAddReqDTO;
import com.xujie.admin.DTO.req.OperLogQueryReqDTO;
import com.xujie.admin.DTO.res.OperLogQueryResDTO;
import com.xujie.admin.common.base.convert.BaseConvert;
import com.xujie.admin.domain.BO.OperLogBO;
import com.xujie.admin.infra.DO.SysOperLog;
import org.mapstruct.Mapper;

/**
 * (OperLog)Convert 类
 *
 * @author xujie
 * @since 2024-09-25 16:00:17
 */

@Mapper(componentModel = "spring")
public interface OperLogConvert extends BaseConvert<OperLogQueryResDTO, OperLogAddReqDTO, OperLogQueryReqDTO, OperLogBO, SysOperLog> {


}

