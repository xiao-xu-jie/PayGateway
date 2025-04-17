package com.xujie.admin.controller;


import com.xujie.admin.DTO.req.SiteInfoAddReqDTO;
import com.xujie.admin.DTO.req.SiteInfoQueryReqDTO;
import com.xujie.admin.DTO.res.SiteInfoQueryResDTO;
import com.xujie.admin.common.base.controller.BaseController;
import com.xujie.admin.domain.BO.SiteInfoBO;
import com.xujie.admin.domain.convert.SiteInfoConvert;
import com.xujie.admin.domain.service.SiteInfoDomainService;
import com.xujie.admin.infra.DO.SiteInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * (SiteInfo)表控制层
 *
 * @author xujie
 * @since 2025-04-17 12:04:53
 */
@RestController
@RequestMapping("/siteInfo")
public class SiteInfoController extends BaseController<SiteInfoQueryReqDTO, SiteInfoQueryResDTO, SiteInfoAddReqDTO, SiteInfoBO, SiteInfo, SiteInfoConvert, SiteInfoDomainService> {

    public SiteInfoController(SiteInfoConvert baseConvert, SiteInfoDomainService baseDomainService) {
        this.baseConvert = baseConvert;
        this.baseDomainService = baseDomainService;
    }
}

