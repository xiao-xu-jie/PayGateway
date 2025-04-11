package com.xujie.api.feign;

import com.xujie.convert.SiteDTOConvert;
import com.xujie.domain.entity.Site;
import com.xujie.domain.service.SiteDomainService;
import com.xujie.payGateway.common.entity.ResponseEntity;
import com.xujie.site.api.dto.SiteDTO;
import com.xujie.site.api.feign.SiteFeignApi;
import jakarta.annotation.Resource;
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
}
