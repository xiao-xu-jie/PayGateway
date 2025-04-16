package com.xujie.admin.domain.convert;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xujie.admin.DTO.req.RoutersAddReqDTO;
import com.xujie.admin.DTO.req.RoutersQueryReqDTO;
import com.xujie.admin.DTO.res.RouterResDTO;
import com.xujie.admin.DTO.res.RoutersQueryResDTO;
import com.xujie.admin.DTO.res.UserLoginResDTO;
import com.xujie.admin.common.base.convert.BaseConvert;
import com.xujie.admin.domain.BO.RoutersBO;
import com.xujie.admin.domain.BO.UserBO;
import com.xujie.admin.infra.DO.SysRouters;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * (SysRouters)Convert 类
 *
 * @author xujie
 * @since 2024-09-22 14:00:51
 */

@Mapper(componentModel = "spring")
public interface RoutersConvert extends BaseConvert<RoutersQueryResDTO, RoutersAddReqDTO, RoutersQueryReqDTO, RoutersBO, SysRouters> {

    @Override
    @Mappings({
            @Mapping(source = "rankNum",target = "rank")
    })
    RoutersBO convertDO2BO(SysRouters entity);

    @Override
    List<RoutersBO> convertListDO2BO(List<SysRouters> sysRouters);

    @Override
    Page<RoutersBO> convertPageDO2BO(Page<SysRouters> sysRoutersPage);

    UserLoginResDTO convertBo2LoginDTO(UserBO userBO);

    List<RouterResDTO> convertListBO2DTO2(List<RoutersBO> routers);
}

