package com.xujie.admin.domain.BO;

import com.xujie.admin.DTO.res.Meta;
import com.xujie.admin.common.base.model.BaseBO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * (SysRouters)BO
 *
 * @author xujie
 * @since 2024-09-22 14:00:51
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoutersBO extends BaseBO {


    /**
     * 子节点
     */
    List<RoutersBO> children;
    /**
     * 路由ID
     */

    private Long id;
    /**
     * 名称
     */

    private String name;
    /**
     * icon
     */

    private String icon;
    /**
     * 标题
     */

    private String title;
    /**
     * 父亲节点
     */

    private Long parentId;
    /**
     * 路径
     */

    private String path;
    /**
     * 重定向
     */

    private String redirect;
    /**
     * 组件
     */

    private String component;
    /**
     * 是否显示
     */

    private Boolean showlink;
    /**
     * 排序
     */

    private Integer rank;
    /**
     * 创建时间
     */

    private Date createTime;
    /**
     * 更新时间
     */

    private Date updateTime;
    /**
     * meta
     */
    private Meta meta;

    boolean flag;

}

