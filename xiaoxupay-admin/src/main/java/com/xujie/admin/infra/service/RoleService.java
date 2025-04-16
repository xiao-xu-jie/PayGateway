package com.xujie.admin.infra.service;

import com.xujie.admin.common.base.service.BaseService;
import com.xujie.admin.infra.DO.SysRole;

import java.util.List;

public interface RoleService extends BaseService<SysRole> {
    List<SysRole> getAllRoleByCodes(List<String> codes);

}
