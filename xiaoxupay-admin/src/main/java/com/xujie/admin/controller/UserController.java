package com.xujie.admin.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import com.xujie.admin.DTO.req.UserAddReqDTO;
import com.xujie.admin.DTO.req.UserQueryReqDTO;
import com.xujie.admin.DTO.res.UserQueryResDTO;
import com.xujie.admin.common.base.controller.BaseController;
import com.xujie.admin.common.entity.Result;
import com.xujie.admin.domain.BO.UserBO;
import com.xujie.admin.domain.convert.UserConvert;
import com.xujie.admin.domain.service.UserDomainService;
import com.xujie.admin.infra.DO.SysUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 用户控制器
 *
 * @author Xujie
 * @since 2024/9/19 10:39
 **/

@RestController
@RequestMapping("/user")
public class UserController extends BaseController<UserQueryReqDTO, UserQueryResDTO, UserAddReqDTO, UserBO, SysUser, UserConvert, UserDomainService> {

    public UserController(UserConvert baseConvert, UserDomainService baseDomainService) {
        this.baseConvert = baseConvert;
        this.baseDomainService = baseDomainService;
    }

    /**
     * 获取用户角色
     *
     * @param id 用户id
     * @return 用户角色
     */
    @GetMapping("/getUserRole")
    @SaCheckRole("admin")
    public Result<List<String>> getUserRole(@RequestParam(name = "id", required = true) Long id) {

        List<String> list = baseDomainService.getUserRoleList(id);
        return Result.ok(list);
    }
}
