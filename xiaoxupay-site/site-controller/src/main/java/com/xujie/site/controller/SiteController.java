package com.xujie.site.controller;

import com.xujie.common.entity.ResponseEntity;
import com.xujie.common.groups.CreateGroup;
import com.xujie.site.api.dto.SiteDTO;
import com.xujie.site.convert.SiteDTOConvert;
import com.xujie.site.domain.entity.Site;
import com.xujie.site.domain.service.SiteDomainService;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/site")
public class SiteController {
    @Resource
    private SiteDomainService siteDomainService;
    @Resource
    private SiteDTOConvert siteDTOConvert;

    @PostMapping("/add")
    public ResponseEntity<?> addSite(@RequestBody @Validated(CreateGroup.class) SiteDTO createDTO) {
        Site site = siteDTOConvert.dto2bo(createDTO);
        siteDomainService.addOneSite(site);
        return ResponseEntity.success("添加成功");
    }

}
