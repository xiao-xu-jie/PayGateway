package com.xujie.infra.mapper;
import java.math.BigDecimal;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Date;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xujie.infra.entity.SiteOrder;

public interface SiteOrderMapper extends BaseMapper<SiteOrder> {
    List<SiteOrder> selectByAll(SiteOrder siteOrder);


}