package com.xujie.admin.auth;

import cn.dev33.satoken.stp.StpInterface;
import com.google.common.collect.Lists;
import com.xujie.admin.infra.service.UserRoleService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Xujie
 * @since 2024/9/19 15:25
 **/
@Slf4j
@Component
public class StpInterfaceImpl implements StpInterface {
    @Resource
    private UserRoleService userRoleService;

    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        return Lists.newArrayList("*.*.*");
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<String> getRoleList(Object loginId, String loginType) {

        return userRoleService.getRoleList(Long.valueOf(loginId.toString()));
    }
}
