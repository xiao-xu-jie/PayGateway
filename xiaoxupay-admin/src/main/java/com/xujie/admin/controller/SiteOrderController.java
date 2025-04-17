package com.xujie.admin.controller;


import com.xujie.admin.DTO.req.SiteOrderAddReqDTO;
import com.xujie.admin.DTO.req.SiteOrderQueryReqDTO;
import com.xujie.admin.DTO.res.SiteOrderQueryResDTO;
import com.xujie.admin.common.base.controller.BaseController;
import com.xujie.admin.domain.BO.SiteOrderBO;
import com.xujie.admin.domain.convert.SiteOrderConvert;
import com.xujie.admin.domain.service.SiteOrderDomainService;
import com.xujie.admin.infra.DO.SiteOrder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * (SiteOrder)表控制层
 *
 * @author xujie
 * @since 2025-04-17 12:04:57
 */
@RestController
@RequestMapping("/siteOrder")
public class SiteOrderController extends BaseController<SiteOrderQueryReqDTO, SiteOrderQueryResDTO, SiteOrderAddReqDTO, SiteOrderBO, SiteOrder, SiteOrderConvert, SiteOrderDomainService> {

    public SiteOrderController(SiteOrderConvert baseConvert, SiteOrderDomainService baseDomainService) {
        this.baseConvert = baseConvert;
        this.baseDomainService = baseDomainService;
    }
}

