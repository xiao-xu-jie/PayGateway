package com.xujie.admin.infra.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xujie.admin.infra.DO.SysRole;
import com.xujie.admin.infra.DO.SysUserRole;
import com.xujie.admin.infra.mapper.SysUserRoleMapper;
import com.xujie.admin.infra.service.UserRoleService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 用户角色服务实现类
 *
 * @author Xujie
 * @since 2024/9/18 19:51
 **/

@Slf4j
@Service
public class UserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements UserRoleService {
    @Resource
    private SysUserRoleMapper baseMapper;
    @Resource
    private RoleServiceImpl roleService;

    @Override
    public boolean addUserRole(Long userId, Long roleId) {
        try {
            baseMapper.insert(SysUserRole.builder().roleId(roleId).userId(userId).build());
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteUserRole(Long userId, Long roleId) {
        try {
            baseMapper.deleteByMap(Map.of("user_id", userId, "role_id", roleId));
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public List<String> getRoleList(Long loginId) {
        List<SysRole> roleList = baseMapper.selectRoleByUserId(loginId);
        List<String> list = roleList.stream().map(SysRole::getCode).toList();
        return list;
    }

    @Override
    @CachePut(cacheNames = "role", key = "#p0")
    public List<SysRole> getRoleListByUserId(Long userId) {
        List<SysRole> roleList = baseMapper.selectRoleByUserId(userId);
        return roleList;
    }

    @Override
    public void saveUserRole(Long id, List<String> roles) {
        super.removeByMap(Map.of("user_id", id));
        List<SysUserRole> list = roleService.getAllRoleByCodes(roles)
                .stream()
                .map((item) -> {
                    SysUserRole sysUserRole = SysUserRole.builder()
                            .roleId(item.getId())
                            .userId(id)
                            .build();
                    return sysUserRole;
                }).toList();
        super.saveBatch(list);
    }

}
