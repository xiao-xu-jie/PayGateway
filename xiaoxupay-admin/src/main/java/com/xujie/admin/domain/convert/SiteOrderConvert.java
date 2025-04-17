package com.xujie.admin.domain.convert;

import com.xujie.admin.DTO.req.SiteOrderAddReqDTO;
import com.xujie.admin.DTO.req.SiteOrderQueryReqDTO;
import com.xujie.admin.DTO.res.SiteOrderQueryResDTO;
import com.xujie.admin.common.base.convert.BaseConvert;
import com.xujie.admin.domain.BO.SiteOrderBO;
import com.xujie.admin.infra.DO.SiteOrder;
import org.mapstruct.Mapper;

/**
 * (SiteOrder)Convert 类
 *
 * @author xujie
 * @since 2025-04-17 12:04:57
 */

@Mapper(componentModel = "spring")
public interface SiteOrderConvert extends BaseConvert<SiteOrderQueryResDTO, SiteOrderAddReqDTO, SiteOrderQueryReqDTO, SiteOrderBO, SiteOrder> {


}

