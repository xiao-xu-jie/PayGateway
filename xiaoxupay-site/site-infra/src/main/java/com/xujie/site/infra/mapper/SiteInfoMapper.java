package com.xujie.site.infra.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xujie.site.infra.entity.SiteInfo;

import java.util.List;

public interface SiteInfoMapper extends BaseMapper<SiteInfo> {
    List<SiteInfo> selectByAll(SiteInfo siteInfo);


}