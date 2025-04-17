package com.xujie.admin.domain.convert;

import com.xujie.admin.DTO.req.SiteInfoAddReqDTO;
import com.xujie.admin.DTO.req.SiteInfoQueryReqDTO;
import com.xujie.admin.DTO.res.SiteInfoQueryResDTO;
import com.xujie.admin.common.base.convert.BaseConvert;
import com.xujie.admin.domain.BO.SiteInfoBO;
import com.xujie.admin.infra.DO.SiteInfo;
import org.mapstruct.Mapper;

/**
 * (SiteInfo)Convert 类
 *
 * @author xujie
 * @since 2025-04-17 12:04:53
 */

@Mapper(componentModel = "spring")
public interface SiteInfoConvert extends BaseConvert<SiteInfoQueryResDTO, SiteInfoAddReqDTO, SiteInfoQueryReqDTO, SiteInfoBO, SiteInfo> {


}

