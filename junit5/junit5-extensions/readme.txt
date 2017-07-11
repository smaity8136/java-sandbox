This module illustrates how we can utilize Junit 5's extension callback mechanisms to get more access to the test lifecycle.

You normally create a Extension class that will implement an interface and then use the @ExtendWith annotation to plug the
callback into the test class.
