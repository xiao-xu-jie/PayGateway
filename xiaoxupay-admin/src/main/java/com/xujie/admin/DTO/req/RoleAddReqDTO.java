package com.xujie.admin.DTO.req;

import com.xujie.admin.common.base.model.BaseDTO;
import com.xujie.admin.common.entity.Groups;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Xujie
 * @since 2024/9/21 14:07
 **/

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleAddReqDTO extends BaseDTO {

    @NotNull(message = "角色id不能为空",groups = {Groups.Update.class})
    private Long id;
    /**
     * 角色名称
     */
    @NotBlank(message = "角色名称不能为空")
    private String name;
    /**
     * 角色编码
     */
    @NotBlank(message = "角色编码不能为空")
    private String code;
    /**
     * 角色描述
     */
    private String roleDesc;

    /**
     * 路由id列表
     */
    List<Long> routers;
}
