package com.xujie.site.controller;

import com.xujie.site.convert.SiteDTOConvert;
import com.xujie.site.domain.service.SiteDomainService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/site")
public class SiteController {
    @Resource
    private SiteDomainService siteDomainService;
    @Resource
    private SiteDTOConvert siteDTOConvert;

}
