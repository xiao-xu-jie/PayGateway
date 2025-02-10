package com.xujie.domain.service.impl;

import cn.hutool.core.util.IdUtil;
import com.xujie.common.exception.CustomException;
import com.xujie.domain.convert.SiteDomainConvert;
import com.xujie.domain.entity.Site;
import com.xujie.domain.service.SiteDomainService;
import com.xujie.infra.entity.SiteInfo;
import com.xujie.infra.service.SiteService;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

@Service
public class SiteDomainServiceImpl implements SiteDomainService {
    @Resource
    private SiteService siteService;
    @Resource
    private SiteDomainConvert siteDomainConvert;
    @Override
    public void addOneSite(Site site) {
        // 生成ID、Secret
        site.setSiteAppid(IdUtil.nanoId(16));
        site.setSiteSecret(IdUtil.randomUUID());
        siteService.insertOneSite(siteDomainConvert.bo2do(site));
    }

    @Override
    public void deleteOneSite(Site site) {
        siteService.deleteOneSiteBySiteId(site.getSiteAppid());
    }

    @Override
    public Site getSiteByAppId(String appid) {
        SiteInfo siteInfo = siteService.selectOneByAppid(appid);
        if(ObjectUtils.isEmpty(siteInfo)) {
            throw new CustomException("站点不存在");
        }
        return siteDomainConvert.do2bo(siteInfo);
    }
}
