package com.seedollar.java.sanbox.java9.jpms.client;

import com.seedollar.sandbox.java9.jpms.top.api.JPMSTopAPI;

public class JPMSBottomMain {
    public static void main(String[] args) {
        System.out.println(((JPMSTopAPI) () -> "JPMS Top Api invoked from bottom").invoke());
    }

}
