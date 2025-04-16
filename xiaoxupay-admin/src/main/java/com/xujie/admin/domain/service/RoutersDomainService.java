package com.xujie.admin.domain.service;

import com.xujie.admin.common.base.service.BaseDomainService;
import com.xujie.admin.domain.BO.RoutersBO;

import java.util.List;

/**
 * (SysRouters)表服务接口
 *
 * @author xujie
 * @since 2024-09-22 14:00:51
 */
public interface RoutersDomainService extends BaseDomainService<RoutersBO> {

    List<RoutersBO> getRouters();

    List<RoutersBO> getChildren(Long parentId);

    List<RoutersBO> getTopRouters();
}

