package com.xujie.admin.infra.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xujie.admin.infra.DO.SysRole;
import com.xujie.admin.infra.DO.SysUserRole;

import java.util.List;

public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {
    List<SysRole> selectRoleByUserId(Long loginId);
}