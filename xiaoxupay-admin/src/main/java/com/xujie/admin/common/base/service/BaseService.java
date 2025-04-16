package com.xujie.admin.common.base.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xujie.admin.common.base.model.BaseDO;

import java.util.List;

public interface BaseService<DO extends BaseDO> {
    Long addOne(DO baseDO);

    DO getOneByEntity(DO baseDO);

    List<DO> getListByEntity(DO baseDO);

    boolean deleteOne(Long id);

    boolean updateOne(Long id, DO baseDO);

    Page<DO> getPageList(DO baseDO, Integer pageNum, Integer pageSize);

    boolean deleteBatch(Long[] ids);

}
