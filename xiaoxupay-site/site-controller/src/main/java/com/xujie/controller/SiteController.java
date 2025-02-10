package com.xujie.controller;

import com.xujie.common.entity.ResponseEntity;
import com.xujie.common.groups.CreateGroup;
import com.xujie.convert.SiteDTOConvert;
import com.xujie.domain.entity.Site;
import com.xujie.domain.service.SiteDomainService;
import com.xujie.dto.SiteDTO;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/site")
public class SiteController {
    @Resource
    private SiteDomainService siteDomainService;
    @Resource
    private SiteDTOConvert siteDTOConvert;

    @PostMapping("/add")
    public ResponseEntity<?> addSite(@RequestBody @Validated(CreateGroup.class)SiteDTO createDTO) {
        Site site = siteDTOConvert.dto2bo(createDTO);
        siteDomainService.addOneSite(site);
        return ResponseEntity.success("添加成功");
    }
    @GetMapping("/searchByAppid")
    public ResponseEntity<?> searchByAppid(@RequestParam("appid") String appid) {
        Site siteByAppId = siteDomainService.getSiteByAppId(appid);
        return ResponseEntity.success(siteDTOConvert.bo2dto(siteByAppId));
    }
}
