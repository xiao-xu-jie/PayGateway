package com.xujie.site.domain.service;

import com.xujie.site.domain.entity.Site;

public interface SiteDomainService {
    void addOneSite(Site site);

    void deleteOneSite(Site site);

    Site getSiteByAppId(String appid);
}
