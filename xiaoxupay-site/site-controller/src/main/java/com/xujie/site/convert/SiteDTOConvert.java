package com.xujie.site.convert;

import com.xujie.site.api.dto.SiteDTO;
import com.xujie.site.domain.entity.Site;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SiteDTOConvert {
    Site dto2bo(SiteDTO siteDTO);

    SiteDTO bo2dto(Site site);
}
