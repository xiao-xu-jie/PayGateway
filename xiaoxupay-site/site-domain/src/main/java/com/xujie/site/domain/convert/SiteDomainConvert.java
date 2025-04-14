package com.xujie.site.domain.convert;

import com.xujie.site.domain.entity.Site;
import com.xujie.site.infra.entity.SiteInfo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SiteDomainConvert {
    SiteInfo bo2do(Site site);

    Site do2bo(SiteInfo siteInfo);
}
