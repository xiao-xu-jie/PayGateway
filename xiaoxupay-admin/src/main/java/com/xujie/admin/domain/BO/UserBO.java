package com.xujie.admin.domain.BO;

import com.xujie.admin.common.base.model.BaseBO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author Xujie
 * @since 2024/9/19 10:40
 **/

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserBO extends BaseBO {
    /**
     * 用户头像
     */

    private String avatar;

    /**
     * 用户ID
     */

    private Long id;

    /**
     * 用户名
     */

    private String username;

    /**
     * 密码
     */

    private String password;

    /**
     * 名称
     */

    private String name;
    /**
     * 性别
     */

    private Integer sex;

    /**
     * 邮箱
     */

    private String email;

    /**
     * openId
     */

    private Integer openId;

    /**
     * 手机号
     */

    private String phone;

    /**
     * 更新时间
     */

    private Date updateTime;

    /**
     * 创建时间
     */

    private Date createTime;

    /**
     * 角色codes
     */
    List<String> roles;

}
