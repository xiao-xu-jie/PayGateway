package com.xujie.admin.infra.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xujie.admin.infra.DO.SysRole;
import com.xujie.admin.infra.mapper.SysRoleMapper;
import com.xujie.admin.infra.service.RoleService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 角色服务实现类
 *
 * @author Xujie
 * @since 2024/9/18 15:31
 **/
@Slf4j
@Service
public class RoleServiceImpl implements RoleService {
    @Resource
    private SysRoleMapper baseMapper;

    @Override
    public Long addOne(SysRole role) {
        try {
            baseMapper.insert(role);
        } catch (Exception e) {
            log.error("添加角色失败：{}", e.getMessage());
            return null;
        }
        return role.getId();
    }

    @Override
    public boolean deleteOne(Long id) {
        try {
            baseMapper.deleteById(id);
        } catch (Exception e) {
            log.error("删除角色失败：{}", e.getMessage());
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public boolean updateOne(Long id, SysRole role) {
        try {
            role.setId(id);
            baseMapper.updateById(role);
        } catch (Exception e) {
            log.error("更新角色失败：{}", e.getMessage());
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public List<SysRole> getListByEntity(SysRole role) {
        LambdaQueryWrapper<SysRole> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(ObjectUtils.isNotNull(role.getId()), SysRole::getId, role.getId())
                .eq(ObjectUtils.isNotNull(role.getName()), SysRole::getName, role.getName())
                .eq(ObjectUtils.isNotNull(role.getCode()), SysRole::getCode, role.getCode())
                .like(ObjectUtils.isNotNull(role.getRoleDesc()), SysRole::getRoleDesc, role.getRoleDesc());
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public List<SysRole> getAllRoleByCodes(List<String> codes) {
        LambdaQueryWrapper<SysRole> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.in(SysRole::getCode, codes);
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public SysRole getOneByEntity(SysRole role) {
        return getListByEntity(role).stream().findFirst().orElse(null);
    }

    @Override
    public Page<SysRole> getPageList(SysRole role, Integer pageNum, Integer pageSize) {
        LambdaQueryWrapper<SysRole> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper
                .eq(ObjectUtils.isNotNull(role.getId()), SysRole::getId, role.getId())
                .eq(ObjectUtils.isNotNull(role.getName()), SysRole::getName, role.getName())
                .eq(ObjectUtils.isNotNull(role.getCode()), SysRole::getCode, role.getCode())
                .like(ObjectUtils.isNotNull(role.getRoleDesc()), SysRole::getRoleDesc, role.getRoleDesc());

        return baseMapper.selectPage(new Page<>(pageNum, pageSize), queryWrapper);
    }

    @Override
    public boolean deleteBatch(Long[] ids) {
        return baseMapper.deleteByIds(List.of(ids)) > 0;
    }
}
