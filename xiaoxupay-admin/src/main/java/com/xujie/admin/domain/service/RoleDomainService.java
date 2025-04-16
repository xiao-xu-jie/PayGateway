package com.xujie.admin.domain.service;

import com.xujie.admin.common.base.service.BaseDomainService;
import com.xujie.admin.domain.BO.RoleBO;
import com.xujie.admin.domain.BO.RoutersBO;

import java.util.List;


public interface RoleDomainService extends BaseDomainService<RoleBO> {
    List<RoleBO> getAllRoleList();

    List<Long> getRoutersByRoleId(Long roleId);

    List<RoutersBO> getAllRouters();
}
