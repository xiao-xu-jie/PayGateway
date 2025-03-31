package com.xujie.api.feign;

import com.xujie.common.entity.ResponseEntity;
import com.xujie.convert.SiteDTOConvert;
import com.xujie.domain.entity.Site;
import com.xujie.domain.service.SiteDomainService;
import com.xujie.site.api.feign.SiteFeignApi;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
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
    public ResponseEntity<?> searchByAppid(String appid) {
        Site siteByAppId = siteDomainService.getSiteByAppId(appid);
        return ResponseEntity.success(siteDTOConvert.bo2dto(siteByAppId));
    }
}
