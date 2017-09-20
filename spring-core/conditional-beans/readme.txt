This module will illustrate how we can use @Conditional annotations to wire or activate certain beans based on some conditions, in this case whether a environment variable 'capture' is found and a Spring profile 'gupta' is active.


To test, you can execute the following:

1) gradle bootRun -> You will only see an normal employee
2) gradle -Dcapture=true bootRun -> Only 1 condition satisfied, so should still only show 1 normal employee
2) gradle -Dcapture=true -Dspring.profiles.default=gupta bootRun -> Both conditions satisfied, so you will see 2 employees, one normal and one captured.


