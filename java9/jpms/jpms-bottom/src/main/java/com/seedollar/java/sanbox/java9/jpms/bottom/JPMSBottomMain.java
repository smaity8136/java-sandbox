package com.seedollar.java.sanbox.java9.jpms.bottom;

import com.seedollar.sandbox.java9.jpms.top.api.JPMSTopAPI;
import com.seedollar.sandbox.java9.jpms.top.api.internal.InternalTopAPI;

public class JPMSBottomMain {
    public static void main(String[] args) {
        System.out.println(((JPMSTopAPI) () -> "JPMS Top Api invoked from bottom").invoke());
        System.out.println(((InternalTopAPI) () -> "JPMS Internal Top invoked from bottom").invokeInternal());
    }

}
