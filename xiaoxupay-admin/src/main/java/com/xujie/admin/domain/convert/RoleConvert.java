package com.xujie.admin.domain.convert;

import com.xujie.admin.DTO.req.RoleAddReqDTO;
import com.xujie.admin.DTO.req.RoleQueryReqDTO;
import com.xujie.admin.DTO.res.RoleQueryResDTO;
import com.xujie.admin.common.base.convert.BaseConvert;
import com.xujie.admin.domain.BO.RoleBO;
import com.xujie.admin.infra.DO.SysRole;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleConvert extends BaseConvert<RoleQueryResDTO, RoleAddReqDTO, RoleQueryReqDTO, RoleBO, SysRole> {

}
