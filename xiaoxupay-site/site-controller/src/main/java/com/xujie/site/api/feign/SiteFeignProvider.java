package com.xujie.site.api.feign;

import com.xujie.common.entity.ResponseEntity;
import com.xujie.common.groups.CreateGroup;
import com.xujie.site.api.dto.SiteDTO;
import com.xujie.site.convert.SiteDTOConvert;
import com.xujie.site.domain.entity.Site;
import com.xujie.site.domain.service.SiteDomainService;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SiteFeignProvider implements SiteFeignApi {

    @Resource
    private SiteDomainService siteDomainService;
    @Resource
    private SiteDTOConvert siteDTOConvert;


    /**
     * 提供appid查询站点学习
     *
     * @param appid
     * @return
     */
    @Override
    public ResponseEntity<SiteDTO> searchByAppid(String appid) {
        Site siteByAppId = siteDomainService.getSiteByAppId(appid);
        SiteDTO siteDTO = siteDTOConvert.bo2dto(siteByAppId);
        return ResponseEntity.success(siteDTO);
    }

    /**
     * 站点添加
     *
     * @param createDTO
     * @return
     */
    @Override
    public ResponseEntity<?> addSite(@RequestBody @Validated(CreateGroup.class) SiteDTO createDTO) {
        Site site = siteDTOConvert.dto2bo(createDTO);
        siteDomainService.addOneSite(site);
        return ResponseEntity.success("添加成功");
    }
}
