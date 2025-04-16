package com.xujie.admin.domain.convert;

import com.xujie.admin.DTO.req.UserAddReqDTO;
import com.xujie.admin.DTO.req.UserQueryReqDTO;
import com.xujie.admin.DTO.res.UserQueryResDTO;
import com.xujie.admin.common.base.convert.BaseConvert;
import com.xujie.admin.domain.BO.UserBO;
import com.xujie.admin.infra.DO.SysUser;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface UserConvert extends BaseConvert<UserQueryResDTO, UserAddReqDTO, UserQueryReqDTO, UserBO, SysUser> {


}
