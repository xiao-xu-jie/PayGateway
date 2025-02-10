package com.xujie.domain.service;

import com.xujie.domain.entity.Site;

public interface SiteDomainService {
    void addOneSite(Site site);
    void deleteOneSite(Site site);
    Site getSiteByAppId(String appid);
}
