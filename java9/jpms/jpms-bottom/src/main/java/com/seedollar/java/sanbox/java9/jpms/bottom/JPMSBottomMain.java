package com.seedollar.java.sanbox.java9.jpms.bottom;

import com.seedollar.java.sandbox.java9.jpms.sub.factory.ReflectionFactory;
import com.seedollar.sandbox.java9.jpms.top.api.JPMSTopAPI;
import com.seedollar.sandbox.java9.jpms.top.api.ReflectionAPI;
import com.seedollar.sandbox.java9.jpms.top.api.internal.InternalTopAPI;

import java.lang.reflect.Field;

public class JPMSBottomMain {
    public static void main(String[] args) {
        System.out.println(((JPMSTopAPI) () -> "JPMS Top Api invoked from bottom").invoke());
        System.out.println(((InternalTopAPI) () -> "JPMS Internal Top invoked from bottom").invokeInternal());
        ReflectionFactory reflectionFactory = new ReflectionFactory();
        try {
            ReflectionAPI reflectiveImplementation = reflectionFactory.getReflectiveImplentation();
            Field description = reflectiveImplementation.getClass().getField("description");
            boolean descrptionType = description.getType().isAssignableFrom(String.class);
            String msg = (descrptionType) ? "Sub Reflection Works":"Sub Reflection fails";
            System.out.println("Sub Reflection = " + msg);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

}
