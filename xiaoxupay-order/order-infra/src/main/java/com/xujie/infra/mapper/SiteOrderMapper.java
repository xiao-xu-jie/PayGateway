package com.xujie.infra.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xujie.infra.entity.SiteOrder;

import java.util.List;

public interface SiteOrderMapper extends BaseMapper<SiteOrder> {
    List<SiteOrder> selectByAll(SiteOrder siteOrder);


}