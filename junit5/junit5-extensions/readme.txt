This module illustrates how we can utilize Junit 5's extension callback mechanisms to get more access to the test lifecycle.

You normally create a Extension class that will implement an interface and then use the @ExtendWith annotation to plug the
callback into the test class.

Junit 5 extensions include the following:

- Callbacks: BeforeAllCallback, BeforeTestExecutionCallback, BeforeEachCallback, AfterTestExecutionCallback, AfterEachCallback, AfterAllCallback
- ParameterResolver: Use this when you have test methods with parameter arguments and want the resolver to initialize the parameters before they are used in the test method body.
- TestInstancePostProcessor: Use this to execute any custom method on the test class before we begin we execute the actual test method.
- TestExecutionExceptionHandler: Use this to intercept any test exceptions and either throw them as different exceptions or ignore them

