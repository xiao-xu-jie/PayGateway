package com.xujie.infra.service;

import com.xujie.infra.entity.SiteInfo;

import java.util.List;

public interface SiteService {

    void insertOneSite(SiteInfo siteInfo);
    void updateOneSiteBySiteId(Long siteAppId,SiteInfo siteInfo);
    void deleteOneSiteBySiteId(String siteAppId);
    SiteInfo selectOneByAppid(String appid);
    List<SiteInfo> selectListByEntity(SiteInfo siteInfo);
}
