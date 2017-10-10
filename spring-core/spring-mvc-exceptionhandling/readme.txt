This module will illustrate how Spring MVC can handle exceptions.

There are 2 ways:

1) @ResponseStatus annotation: Here you can define your own Exception that will have the @ResponseStatus annotation, which is them mapped to a specific HTTP status. When you trigger the exception, a BAD_REQUEST (Http Status 400) will be shown.

2) @ExceptionHandler annotation:  

You can build and package the module by executing: gradle clean build

Then deploy to a application container and invoke the following url in your browser:

http://localhost:9080/springmvc-exception-handling

