This example uses springboot, spring-security and jjwt (https://github.com/jwtk/jjwt) to show how you can use JWT with your requests.

1) First, we defined a concrete implementation of the WebSecurityConfigurerAdapter to restrict the URL access to certain endpoints.
2) Then we expose 2 rest endpoint URLS:
    - /login
    - /secure/landing

3) To get the server to create a new JWT token, perform a POST on the following endpoint:
    http://localhost:8082/login

    with the following "application/json" RequestBody:
{
	"password": "password",
	"username": "admin"
}

4) This will get generate and sign a JWT token and return it to the response.

5) You can then add the JWT token to your request header with the "authorization" attribute with the token prefixed with "Bearer " and perform a GET on the following endpoint:

    http://localhost:8082/secure/landing

    This will force the CustomJWTFilter class to parse and authorize the token, extract the "Claims" and enrich the request.


You can use any REST client like POSTMAN for Chrome browser to make the web-service invocations.


