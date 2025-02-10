package com.xujie.infra.mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Date;
import com.xujie.common.enums.NotifyType;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xujie.infra.entity.SiteInfo;

public interface SiteInfoMapper extends BaseMapper<SiteInfo> {
    List<SiteInfo> selectByAll(SiteInfo siteInfo);



}