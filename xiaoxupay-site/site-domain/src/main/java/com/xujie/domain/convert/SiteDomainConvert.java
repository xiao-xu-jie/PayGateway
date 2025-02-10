package com.xujie.domain.convert;

import com.xujie.domain.entity.Site;
import com.xujie.infra.entity.SiteInfo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SiteDomainConvert {
    SiteInfo bo2do(Site site);
    Site do2bo(SiteInfo siteInfo);
}
