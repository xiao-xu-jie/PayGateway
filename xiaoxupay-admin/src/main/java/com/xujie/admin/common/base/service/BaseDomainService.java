package com.xujie.admin.common.base.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * @author Xujie
 * @since 2024/9/21 13:19
 **/


public interface BaseDomainService<BO> {
    void add(BO bo);

    Page<BO> getPageList(BO bo, Integer pageNum, Integer pageSize);

    void delete(Long[] ids);

    void update(BO bo);
}
