package com.xujie.convert;

import com.xujie.domain.entity.Site;
import com.xujie.dto.SiteDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SiteDTOConvert {
    Site dto2bo(SiteDTO siteDTO);
    SiteDTO bo2dto(Site site);
}
