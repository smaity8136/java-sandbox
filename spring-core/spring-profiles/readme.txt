This module will illustrate how to use the @Profile annotation to decide which beans to define and create based on the following properties:

- spring.profiles.default
- spring.profiles.active

If no active profile is set, we use the default. If no default or active, then Spring doesn't use any profiled beans.

In this module we define 4 different profiles, namely:

- dev
- uat
- perf
- prod

Then we define a Biller interface that has 5 concrete implementations:

- DevBiller
- UatBiller
- PerfBiller
- ProdBiller
- OpsBiller (default if no spring profile set)

We also have a Container class that will be configured according to the following SpringConfiguration classes, namely:

- DevConfiguration
- UatConfiguration
- PerfConfiguration
- ProdConfiguration
- SpringProfilesConfiguration

If both "spring.profiles.active" and "spring.profiles.default" are both not set, Spring will wire the OpsBiller and the WEBSPHERE container for our example.

To test the different profile implementations, use the following command:

gradle bootRun (no profiles)
gradle -Dspring.profiles.default=perf bootRun
gradle -Dspring.profiles.active=prod bootRun
gradle -Dspring.profiles.detault=perf -Dspring.profiles.active=dev bootRun
