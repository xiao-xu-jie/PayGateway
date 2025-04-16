package com.xujie.admin.common.base.convert;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xujie.admin.common.base.model.BaseBO;
import com.xujie.admin.common.base.model.BaseDO;
import com.xujie.admin.common.base.model.BaseDTO;

import java.util.List;


public interface BaseConvert<ResDTO extends BaseDTO, AddReqDTO extends BaseDTO, QueryReqDTO extends BaseDTO, BO extends BaseBO, DO extends BaseDO> {
    Page<ResDTO> convertPageBO2DTO(Page<BO> boPage);

    Page<BO> convertPageDTO2BO(Page<ResDTO> dtoPage);

    Page<BO> convertPageDO2BO(Page<DO> doPage);

    List<ResDTO> convertListBO2DTO(List<BO> boList);

    List<BO> convertListDO2BO(List<DO> doList);

    List<BO> convertListDTO2BO(List<ResDTO> dtoList);

    ResDTO convertBO2DTO(BO bo);

    BO convertDTO2BO(AddReqDTO dto);

    BO convertDO2BO(DO entity);

    DO convertBO2DO(BO bo);

    BO convertQueryDTO2BO(QueryReqDTO queryReqDTO);

}
