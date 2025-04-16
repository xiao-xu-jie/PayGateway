package com.xujie.admin.controller;


import com.xujie.admin.DTO.req.OperLogAddReqDTO;
import com.xujie.admin.DTO.req.OperLogQueryReqDTO;
import com.xujie.admin.DTO.res.OperLogQueryResDTO;
import com.xujie.admin.common.base.controller.BaseController;
import com.xujie.admin.domain.BO.OperLogBO;
import com.xujie.admin.domain.convert.OperLogConvert;
import com.xujie.admin.domain.service.OperLogDomainService;
import com.xujie.admin.infra.DO.SysOperLog;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * (OperLog)表控制层
 *
 * @author xujie
 * @since 2024-09-25 16:00:16
 */
@RestController
@RequestMapping("/operLog")
public class OperLogController extends BaseController<OperLogQueryReqDTO, OperLogQueryResDTO, OperLogAddReqDTO, OperLogBO, SysOperLog, OperLogConvert, OperLogDomainService> {

    public OperLogController(OperLogConvert baseConvert, OperLogDomainService baseDomainService) {
        this.baseConvert = baseConvert;
        this.baseDomainService = baseDomainService;
    }
}

