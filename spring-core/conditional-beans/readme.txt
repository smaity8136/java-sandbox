This module will illustrate how we can use @Conditional annotations to wire or activate certain beans based on some conditions, in this case whether a environment variable 'capture' is found.

To test, you can execute the following:

1) gradle bootRun -> You will only see an normal employee
2) gradle -Dcapture=true bootRun -> You will see 2 employees, one normal and once captured.
