Gradle
------

You can execute tests using the following command: gradle clean junitPlatformTest

IntelliJ
--------

I had to add the testCompile("org.junit.platform:junit-platform-runner:1.0.0-M3") dependency to get IntelliJ 2.2016 to execute my tests in the IDE.
