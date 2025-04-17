package com.xujie.admin.controller;


import com.xujie.admin.DTO.req.OutboxMessageAddReqDTO;
import com.xujie.admin.DTO.req.OutboxMessageQueryReqDTO;
import com.xujie.admin.DTO.res.OutboxMessageQueryResDTO;
import com.xujie.admin.common.base.controller.BaseController;
import com.xujie.admin.domain.BO.OutboxMessageBO;
import com.xujie.admin.domain.convert.OutboxMessageConvert;
import com.xujie.admin.domain.service.OutboxMessageDomainService;
import com.xujie.admin.infra.DO.OutboxMessage;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 事务性消息发送箱表(OutboxMessage)表控制层
 *
 * @author xujie
 * @since 2025-04-17 12:04:56
 */
@RestController
@RequestMapping("/outboxMessage")
public class OutboxMessageController extends BaseController<OutboxMessageQueryReqDTO, OutboxMessageQueryResDTO, OutboxMessageAddReqDTO, OutboxMessageBO, OutboxMessage, OutboxMessageConvert, OutboxMessageDomainService> {

    public OutboxMessageController(OutboxMessageConvert baseConvert, OutboxMessageDomainService baseDomainService) {
        this.baseConvert = baseConvert;
        this.baseDomainService = baseDomainService;
    }
}

