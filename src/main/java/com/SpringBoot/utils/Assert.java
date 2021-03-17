//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.SpringBoot.utils;

import java.util.Collection;
import java.util.Map;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

public final class Assert {
    public Assert() {
    }

    public static void isTrue(boolean expression, String message, Object... params) {
        if (!expression) {
            throw new NullPointerException(format(message, params));
        }
    }

    private static String format(String target, Object... params) {
        return target.contains("%s") && params != null && params.length > 0 ? String.format(target, params) : target;
    }

    public static void isFalse(boolean expression, String message, Object... params) {
        isTrue(!expression, message, params);
    }

    public static void isNull(Object object, String message, Object... params) {
        isTrue(object == null, message, params);
    }

    public static void notNull(Object object, String message, Object... params) {
        isTrue(object != null, message, params);
    }

    public static void notEmpty(String value, String message, Object... params) {
        isTrue(!StringUtils.isEmpty(value), message, params);
    }

    public static void notEmpty(Collection<?> collection, String message, Object... params) {
        isTrue(!CollectionUtils.isEmpty(collection), message, params);
    }

    public static void notEmpty(Map<?, ?> map, String message, Object... params) {
        isTrue(!CollectionUtils.isEmpty(map), message, params);
    }

    public static void notEmpty(Object[] array, String message, Object... params) {
        isTrue(array != null && array.length > 0, message, params);
    }
}
