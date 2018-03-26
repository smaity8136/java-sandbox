package com.seedollar.java.sandbox.java9.jpms.sub;

import com.seedollar.sandbox.java9.jpms.top.api.JPMSTopAPI;

public class JPMSSubMain {

    public static void main(String[] args) {
        System.out.println(((JPMSTopAPI) () -> "JPMS Top Invoked from Sub").invoke());
    }
}
