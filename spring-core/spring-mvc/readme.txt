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



