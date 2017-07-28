This module gives a simple illustration of how to specify a default binder when there are multiple implementations on the classpath.

In this module, we explicitly include both the kafka and rabbit binder dependencies. This causes a conflict as the spring context can needs to know when default
implementation to use.

We can specify the drfault by populating the following property:

spring.cloud.stream.defaultBinder=kafka

To start this microservice: gradle bootRun