package com.xujie.admin.DTO.req;

import com.xujie.admin.common.base.model.BaseDTO;
import com.xujie.admin.common.entity.Groups;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Xujie
 * @since 2024/9/19 15:14
 **/

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAddReqDTO extends BaseDTO {

    /**
     * 用户ID
     */
    private Long id;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    private String username;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空", groups = {Groups.Add.class})
    @Size(min = 6, message = "密码长度不能小于6位", groups = {Groups.Add.class})
    private String password;

    /**
     * 名称
     */
    @NotBlank(message = "名称不能为空")
    private String name;
    /**
     * 性别
     */

    private Integer sex;

    /**
     * 邮箱
     */
    @NotBlank(message = "邮箱不能为空", groups = {Groups.Add.class})
    @Pattern(regexp = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$", message = "邮箱格式不正确")
    private String email;


    /**
     * 手机号
     */
    @NotBlank(message = "手机号不能为空", groups = {Groups.Add.class})
    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号格式不正确")
    private String phone;

    /**
     * 角色
     */
    @NotNull(message = "角色不能为空")
    @Size(min = 1, message = "至少选择一个角色")
    private List<String> roles;

}
