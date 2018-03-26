module com.seedollar.java.sandbox.java9.jpms.top {
    exports com.seedollar.sandbox.java9.jpms.top.api;
    // A qualified export that only allows jpms-bottom to see InternalTopAPI.java
    exports com.seedollar.sandbox.java9.jpms.top.api.internal to com.seedollar.java.sandbox.java9.jpms.bottom;
}
