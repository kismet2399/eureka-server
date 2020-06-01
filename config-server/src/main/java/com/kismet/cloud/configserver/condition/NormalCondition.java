package com.kismet.cloud.configserver.condition;

import java.util.Arrays;
import java.util.Map;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.CollectionUtils;

/**
 * @author kismet
 * @since 2020/5/16
 */
public class NormalCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Map<String, Object> annoMap = metadata.getAnnotationAttributes(ConditionalBean.class.getName());
        if (CollectionUtils.isEmpty(annoMap)) {
            return false;
        }
        Class<?>[] value = (Class<?>[]) annoMap.get("value");
        return Arrays.stream(value).anyMatch(item -> context.getBeanFactory().getBeansOfType(item).size() > 0);
    }
}
