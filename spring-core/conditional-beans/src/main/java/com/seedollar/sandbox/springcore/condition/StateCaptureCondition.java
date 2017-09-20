package com.seedollar.sandbox.springcore.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class StateCaptureCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        if (context.getEnvironment().acceptsProfiles("gupta")) {
            return context.getEnvironment().containsProperty("capture");
        }
        return false;
    }
}
