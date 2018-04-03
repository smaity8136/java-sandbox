This module shows how we can use the rest-assured test API to invoke and parse a REST endpoint with JSON payload. 

We start by starting up the web application to simulate a REST endpoint: gradle bootRun (port: 8082, context-path: json-path) -> http://localhost:8082/json-path/api/jsonpath/payload [GET]

Once the service is up, you can execute the JsonPathParserTest.java class which will invoke the rest endpoint and give some examples of how to parse and validate the JSON response body.

