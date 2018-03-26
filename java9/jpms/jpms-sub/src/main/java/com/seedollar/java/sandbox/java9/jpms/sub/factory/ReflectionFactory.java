package com.seedollar.java.sandbox.java9.jpms.sub.factory;

import com.seedollar.java.sandbox.java9.jpms.sub.reflection.SubReflection;
import com.seedollar.sandbox.java9.jpms.top.api.ReflectionAPI;

public class ReflectionFactory {

    public ReflectionAPI getReflectiveImplentation() {
        return new SubReflection();
    }

}
