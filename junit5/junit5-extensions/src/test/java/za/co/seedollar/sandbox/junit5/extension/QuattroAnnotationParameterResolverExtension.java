package za.co.seedollar.sandbox.junit5.extension;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;
import za.co.seedollar.sandbox.junit5.annotation.Quattro;
import za.co.seedollar.sandbox.junit5.domain.Q5;

/**
 * Created by seedollar on 7/11/17.
 */
public class QuattroAnnotationParameterResolverExtension implements ParameterResolver {
    @Override
    public boolean supports(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        if (parameterContext.getParameter().isAnnotationPresent(Quattro.class)) {
            return true;
        }
        return false;
    }

    @Override
    public Object resolve(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return new Q5();
    }
}
