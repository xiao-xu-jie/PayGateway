package com.xujie.admin.common.utils;

import com.xujie.admin.domain.BO.RoutersBO;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Xujie
 * @since 2024/9/17 22:36
 **/


public class RouterUtil {
    public static List<RoutersBO> setChildren(List<RoutersBO> routersBOS) {

        // 递归设置子节点
        for (RoutersBO routersBO : routersBOS) {
            List<RoutersBO> children = getChildren(routersBO.getId(), routersBOS);
            if (!children.isEmpty()) {
                routersBO.setChildren(children);

            }
        }
        return routersBOS.stream().filter(routersBO -> !routersBO.isFlag()).toList();
    }

    private static List<RoutersBO> getChildren(Long id, List<RoutersBO> routersBOS) {
        List<RoutersBO> children = new ArrayList<>();
        for (RoutersBO routersBO : routersBOS) {
            if (routersBO.getParentId() != null && routersBO.getParentId().equals(id)) {
                children.add(routersBO);
                routersBO.setFlag(true);
            }
        }
        for (RoutersBO child : children) {
            List<RoutersBO> children1 = getChildren(child.getId(), routersBOS);
            if (!children1.isEmpty()) {
                child.setChildren(children1);
            }
        }
        return children;
    }

}
