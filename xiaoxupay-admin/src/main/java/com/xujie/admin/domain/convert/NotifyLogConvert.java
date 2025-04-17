package com.xujie.admin.domain.convert;

import com.xujie.admin.DTO.req.NotifyLogAddReqDTO;
import com.xujie.admin.DTO.req.NotifyLogQueryReqDTO;
import com.xujie.admin.DTO.res.NotifyLogQueryResDTO;
import com.xujie.admin.common.base.convert.BaseConvert;
import com.xujie.admin.domain.BO.NotifyLogBO;
import com.xujie.admin.infra.DO.NotifyLog;
import org.mapstruct.Mapper;

/**
 * (NotifyLog)Convert 类
 *
 * @author xujie
 * @since 2025-04-17 12:04:55
 */

@Mapper(componentModel = "spring")
public interface NotifyLogConvert extends BaseConvert<NotifyLogQueryResDTO, NotifyLogAddReqDTO, NotifyLogQueryReqDTO, NotifyLogBO, NotifyLog> {


}

