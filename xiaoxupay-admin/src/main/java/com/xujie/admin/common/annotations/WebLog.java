package com.xujie.admin.common.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Xujie
 * @since 2024/9/23 23:46
 **/

@Retention(RetentionPolicy.RUNTIME)
@Target({java.lang.annotation.ElementType.METHOD})
@Documented
public @interface WebLog {
    boolean params() default true;
    boolean response() default true;
    boolean requestType() default true;
    boolean ip() default true;
    boolean time() default true;
    boolean classPath() default true;
    boolean userId() default true;
    String method() default "";
    String desc() default "";
}
