package com.seedollar.sandbox.junit5.extension;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;
import com.seedollar.sandbox.junit5.domain.BMW;
import com.seedollar.sandbox.junit5.domain.X5;

/**
 * Created by seedollar on 7/11/17.
 */
public class BMWParameterResolverExtension implements ParameterResolver {
    @Override
    public boolean supports(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        if (parameterContext.getParameter().getType() == BMW.class) {
            return true;
        }
        return false;
    }

    @Override
    public Object resolve(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return new X5();
    }
}
