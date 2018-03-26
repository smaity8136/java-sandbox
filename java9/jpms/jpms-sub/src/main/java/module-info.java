module com.seedollar.java.sandbox.java9.jpms.sub {
    // 'transitive' ensures that downstream modules also depend on jpms.top automatically
    requires transitive com.seedollar.java.sandbox.java9.jpms.top;
    // export the factory package
    exports com.seedollar.java.sandbox.java9.jpms.sub.factory;
    // Open caters for reflection. The classes are not accessible at compile time, but are at runtime.
    opens com.seedollar.java.sandbox.java9.jpms.sub.reflection;
}
