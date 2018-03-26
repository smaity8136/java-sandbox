package com.seedollar.java.sandbox.java9.jpms.sub.reflection;


import com.seedollar.sandbox.java9.jpms.top.api.ReflectionAPI;

public class SubReflection implements ReflectionAPI {

    public String description;

    @Override
    public Boolean isOpen() {
        return true;
    }
}
