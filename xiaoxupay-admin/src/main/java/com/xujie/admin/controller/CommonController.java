package com.xujie.admin.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import com.xujie.admin.DTO.req.LoginReqDTO;
import com.xujie.admin.DTO.res.RouterResDTO;
import com.xujie.admin.DTO.res.UserLoginResDTO;
import com.xujie.admin.common.annotations.WebLog;
import com.xujie.admin.common.entity.Result;
import com.xujie.admin.domain.BO.RoutersBO;
import com.xujie.admin.domain.BO.UserBO;
import com.xujie.admin.domain.convert.RoutersConvert;
import com.xujie.admin.domain.service.RoutersDomainService;
import com.xujie.admin.domain.service.UserDomainService;
import jakarta.annotation.Resource;
import org.dromara.x.file.storage.core.FileStorageService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 通用控制器
 *
 * @author Xujie
 * @since 2024/9/17 20:56
 **/

@RestController
@RequestMapping("/common")
public class CommonController {

    @Resource
    private RoutersDomainService routerDomainService;

    @Resource
    private RoutersConvert dtoConvert;

    @Resource
    private UserDomainService userDomainService;

    @Resource
    private FileStorageService fileStorageService;

    /**
     * 用户登录
     *
     * @param loginReqDTO 登录信息
     * @return 用户信息
     */
    @WebLog(desc = "后台用户登录",method = "POST")
    @PostMapping("/login")
    public Result<UserLoginResDTO> userLogin(@RequestBody @Validated LoginReqDTO loginReqDTO) {
        String password = loginReqDTO.getPassword();
        String username = loginReqDTO.getUsername();
        UserBO userBO = userDomainService.handleLogin(username, password);
        UserLoginResDTO user = dtoConvert.convertBo2LoginDTO(userBO);
        user.setAccessToken(StpUtil.getTokenValue());
        user.setRefreshToken(StpUtil.getTokenValue());
        user.setRoles(StpUtil.getRoleList());
        user.setPermissions(StpUtil.getPermissionList());
        return Result.ok(user);
    }

    /**
     * 获取路由信息
     *
     * @return 路由信息
     */
    @WebLog(desc = "获取路由信息",method = "GET")
    @GetMapping("/getSyncRouter")
    public Result<List<RouterResDTO>> getSyncRouter() {
        List<RoutersBO> routers = routerDomainService.getRouters();
        List<RouterResDTO> routerResDTOS = dtoConvert.convertListBO2DTO2(routers);
        return Result.ok(routerResDTOS);
    }

    /**
     * 上传文件
     *
     * @param file 文件
     * @return 文件url
     */
    @PostMapping("/upload")
    @SaCheckLogin
    public Result<String> uploadFile(@RequestParam("file") MultipartFile file) {
        String imgUrl = fileStorageService.of(file)
                .setPath("img/")
                .upload()
                .getUrl();
        return Result.ok(imgUrl);
    }
}
