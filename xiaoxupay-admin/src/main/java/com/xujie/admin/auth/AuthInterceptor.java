package com.xujie.admin.auth;

import cn.dev33.satoken.fun.SaParamFunction;
import cn.dev33.satoken.interceptor.SaInterceptor;

/**
 * @author Xujie
 * @since 2024/9/15 09:57
 **/


public class AuthInterceptor extends SaInterceptor {
    public AuthInterceptor(SaParamFunction<Object> auth) {
        // 设置认证函数
        this.setAuth(auth);
    }
}
