package com.xujie.admin.common.utils;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xujie.common.exception.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;

/**
 * 生成MybatisPlus的QueryWrapper工具类
 *
 * @author Xujie
 * @since 2024/10/28 23:43
 */
@Slf4j
public class QueryWrapperUtil {

    public static void main(String[] args) {
    }

    public static <T> QueryWrapper<T> addQueryWrapper(QueryWrapper<T> queryWrapper, T t) {
        Field[] fields = t.getClass().getDeclaredFields();
        TableName annotation = t.getClass().getAnnotation(TableName.class);
        for (Field field : fields) {
            if (field.getAnnotation(TableField.class) == null
                    && field.getAnnotation(TableId.class) == null) {
                continue;
            }

            field.setAccessible(true);
            Class<?> type = field.getType();
            String typeName = type.getName();
            String fieldName = null;
            if (field.getAnnotation(TableField.class) != null) {
                fieldName = field.getAnnotation(TableField.class).value();
            } else if (field.getAnnotation(TableId.class) != null) {
                fieldName = field.getAnnotation(TableId.class).value();
            } else {
                continue;
            }

            try {
                Object value = field.get(t);
                if (value != null) {
                    switch (typeName) {
                        case "java.lang.String":
                            if (value instanceof String str && StringUtils.isNotBlank(str)) {
                                if (fieldName.contains("name")) {
                                    queryWrapper.like(annotation.value() + "." + fieldName, value);
                                } else {
                                    queryWrapper.eq(annotation.value() + "." + fieldName, value);
                                }
                            }
                            break;
                        case "java.lang.Integer", "java.lang.Long", "java.util.Date":
                        default:
                            queryWrapper.eq(ObjectUtils.isNotEmpty(value), fieldName, value);
                            break;
                    }
                }
            } catch (Exception e) {
                log.error("[QueryWrapperUtil] 条件Map创建失败: {}", e.getMessage());
                throw new CustomException("条件Map创建失败");
            }
        }
        return queryWrapper;
    }

    public static <T> QueryWrapper<T> buildQueryWrapper(T t) {
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        Field[] fields = t.getClass().getDeclaredFields();
        //    TableName annotation = t.getClass().getAnnotation(TableName.class);
        for (Field field : fields) {
            if (field.getAnnotation(TableField.class) == null
                    && field.getAnnotation(TableId.class) == null) {
                continue;
            }

            field.setAccessible(true);
            Class<?> type = field.getType();
            String typeName = type.getName();
            String fieldName = null;
            if (field.getAnnotation(TableField.class) != null) {
                fieldName = field.getAnnotation(TableField.class).value();
            } else if (field.getAnnotation(TableId.class) != null) {
                fieldName = field.getAnnotation(TableId.class).value();
            } else {
                continue;
            }
            if (StringUtils.isBlank(fieldName)) {
                continue;
            }
            try {
                Object value = field.get(t);
                if (value != null) {
                    switch (typeName) {
                        case "java.lang.String":
                            if (value instanceof String str && StringUtils.isNotBlank(str)) {
                                if (fieldName.contains("name")
                                        || fieldName.contains("title")
                                        || fieldName.contains("desc")
                                        || fieldName.contains("content")
                                        || fieldName.contains("location")) {
                                    queryWrapper.like(fieldName, value);
                                } else {
                                    queryWrapper.eq(fieldName, value);
                                }
                            }
                            break;
                        case "java.lang.Integer", "java.lang.Long", "java.util.Date":
                        default:
                            queryWrapper.eq(ObjectUtils.isNotEmpty(value), fieldName, value);
                            break;
                    }
                }
            } catch (Exception e) {
                log.error("[QueryWrapperUtil] 条件Map创建失败: {}", e.getMessage());
                throw new CustomException("条件Map创建失败");
            }
        }
        return queryWrapper;
    }
}
