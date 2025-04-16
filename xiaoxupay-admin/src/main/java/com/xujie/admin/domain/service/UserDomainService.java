package com.xujie.admin.domain.service;


import com.xujie.admin.common.base.service.BaseDomainService;
import com.xujie.admin.domain.BO.UserBO;

import java.util.List;

/**
 * 用户领域服务
 */

public interface UserDomainService extends BaseDomainService<UserBO> {

    UserBO handleLogin(String username, String password);

    List<String> getUserRoleList(Long userId);
}
