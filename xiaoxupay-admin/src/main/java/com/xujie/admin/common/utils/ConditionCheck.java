package com.xujie.admin.common.utils;

import java.lang.reflect.InvocationTargetException;

/**
 * @author Xujie
 * @since 2024/9/16 11:15
 **/


public class ConditionCheck {

    public static <T extends RuntimeException> void trueAndThrow(boolean condition, T t) {
        if (condition) {
            throw t;
        }
    }

    public static <T extends RuntimeException> void trueAndThrow(boolean condition, String message, Class<T> t) {
        if (condition) {
            try {
                throw t.getConstructor(String.class).newInstance(message);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                     NoSuchMethodException e) {
                throw new RuntimeException(e);
            }

        }
    }

    public static <T extends RuntimeException> void falseAndThrow(boolean condition, T t) {
        if (!condition) {
            throw t;
        }
    }

    public static <T extends RuntimeException> void nullAndThrow(Object obj, T t) {
        if (obj == null) {
            throw t;
        }
    }

    public static <T extends RuntimeException> void anyNull(T t, Object... obj) {
        for (int i = 0; i < obj.length; i++) {
            if (obj[i] == null) {
                throw t;
            }
        }
    }
}
