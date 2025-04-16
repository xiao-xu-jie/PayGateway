package com.xujie.admin.DTO.res;

import com.xujie.admin.common.base.model.BaseDTO;
import lombok.*;

import java.util.List;

/**
 * 用户登录响应DTO
 *
 * @author Xujie
 * @since 2024/9/19 16:08
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserLoginResDTO extends BaseDTO {

    /**
     * 用户头像
     */

    private String avatar;

    /**
     * 用户名
     */

    private String username;


    /**
     * 名称
     */

    private String name;


    /**
     * 角色
     */
    private List<String> roles;

    /**
     * 权限
     */
    private List<String> permissions;

    /**
     * token
     */
    private String accessToken;

    /**
     * 刷新token
     */
    private String refreshToken;

    /**
     * 过期时间
     */
    private String expireTime;


}
