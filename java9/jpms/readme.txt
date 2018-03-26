This example shows the basics of the JPMS system. We have 3 modules, namely:

- jpms-top
- jpms-sub
- jpms-client

The jpms-top module defines 2 interfaces, JPMSTopAPI and InternalTopAPI. The JPMSTopAPI interface is exported in the module-info.java, which allows downstream dependencies to see the and use the interface.

The jpms-sub depends on jpms-top, and also explicitly uses the 'transitive' keyword to ensure that any downstream modules that depend on jpms-sub also require jpms-top.

The jpms-top module also defines a qualified export on the com.seedollar.java.sandbox.java9.jpms.top.internal package, and only allows the jpms-bottom module to see the InternalTopAPI.java interface.

The jpms-sub module defines a ReflectionFactory.java class which creates an instance of SubReflection.java. We define the module to only expose the SubReflection.java class to be accessible at runtime for reflection using the 'opens' keyword in the module-info.java

Thus the jpms-bottom module has a dependency on jpms-sub and transitivly on jpms-top. 


