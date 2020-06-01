package com.kismet.cloud.configserver.condition;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Conditional;

/**
 * @author kismet
 * @since 2020/5/16
 */
@Target({ ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Conditional(NormalCondition.class)
public @interface ConditionalBean {
    Class<?>[] value();
}
