package com.xujie.admin.controller;


import com.xujie.admin.DTO.req.NotifyLogAddReqDTO;
import com.xujie.admin.DTO.req.NotifyLogQueryReqDTO;
import com.xujie.admin.DTO.res.NotifyLogQueryResDTO;
import com.xujie.admin.common.base.controller.BaseController;
import com.xujie.admin.domain.BO.NotifyLogBO;
import com.xujie.admin.domain.convert.NotifyLogConvert;
import com.xujie.admin.domain.service.NotifyLogDomainService;
import com.xujie.admin.infra.DO.NotifyLog;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * (NotifyLog)表控制层
 *
 * @author xujie
 * @since 2025-04-17 12:04:55
 */
@RestController
@RequestMapping("/notifyLog")
public class NotifyLogController extends BaseController<NotifyLogQueryReqDTO, NotifyLogQueryResDTO, NotifyLogAddReqDTO, NotifyLogBO, NotifyLog, NotifyLogConvert, NotifyLogDomainService> {

    public NotifyLogController(NotifyLogConvert baseConvert, NotifyLogDomainService baseDomainService) {
        this.baseConvert = baseConvert;
        this.baseDomainService = baseDomainService;
    }
}

