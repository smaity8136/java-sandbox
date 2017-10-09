This module will give some illustrations of how to use the Spring MVC API and components.

Under the covers, AbstractAnnotationConfigDispatcherServletInitializer creates both a DispatcherServlet (Spring MVC Beans/Components) and a ContextLoaderListener (Normal Spring Beans/Components). 

Spring MVC aims to de-couple request-handling and view-resolving from the DispatcherServlet. 

The life of a HTTP request using Spring MVC can be summed up in the following 7 steps:

1) A web browser makes a request, carrying the requested URL and possibly other additional information submitted in a form.
2) The first point of call is the DispatcherServlet, which does an enquiry on all handler mappings to find the qualified Spring Controller to handle the request.
3) Once the appropriate Spring controller has been identified, the request is sent to it to process the request. 
4) The Spring controller will package the Model and add any required information to it. It will then send the Model and the logical view name back to the DispatcherServlet.
5) The DispatcherServlet then takes the logical view name and enquires the ViewResolver implementation to map a view implementation, either a JSP file or something else.
6) Now that the DispatcherServlet knows what the view implementation is, it sends the Model data to it so that is can render the output accordingly.
7) Once the view output is populated, it is sent back to the browser in a Response object.

If your Spring Web Configuration (WebMvcConfigurerAdapter) does specify a view resolver, the default implementation is the BeanNameViewResolver.

You can also use apply Validation to your domain entity beans and get Spring MVC to implicitly execute validation on required annotations from the Java Validaton API.

To build and package the example, execute the following command: gradle clean build

Then deploy the war file to a application container (JBOSS, Tomcat etc). The following request handlers are mapped:

HOME: http://localhost:8080/mobshop/

List view: http://localhost:8080/mobshop/shop

@PathVariable: http://localhost:8080/mobshop/shop/view/1

@RequestParam: http://localhost:9080/mobshop/shop/discounts?amount=2000

@Valid @ModelAttriubte: http://localhost:9080/mobshop/member/registration




