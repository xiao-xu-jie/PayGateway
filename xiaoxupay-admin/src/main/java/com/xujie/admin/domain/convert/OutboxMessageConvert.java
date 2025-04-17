package com.xujie.admin.domain.convert;

import com.xujie.admin.DTO.req.OutboxMessageAddReqDTO;
import com.xujie.admin.DTO.req.OutboxMessageQueryReqDTO;
import com.xujie.admin.DTO.res.OutboxMessageQueryResDTO;
import com.xujie.admin.common.base.convert.BaseConvert;
import com.xujie.admin.domain.BO.OutboxMessageBO;
import com.xujie.admin.infra.DO.OutboxMessage;
import org.mapstruct.Mapper;

/**
 * 事务性消息发送箱表(OutboxMessage)Convert 类
 *
 * @author xujie
 * @since 2025-04-17 12:04:56
 */

@Mapper(componentModel = "spring")
public interface OutboxMessageConvert extends BaseConvert<OutboxMessageQueryResDTO, OutboxMessageAddReqDTO, OutboxMessageQueryReqDTO, OutboxMessageBO, OutboxMessage> {


}

