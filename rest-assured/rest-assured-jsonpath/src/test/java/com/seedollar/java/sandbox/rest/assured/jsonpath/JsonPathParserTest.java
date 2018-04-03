package com.seedollar.java.sandbox.rest.assured.jsonpath;

import com.seedollar.java.sandbox.rest.assured.jsonpath.domain.JsonPathPayload;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;

@RunWith(SpringRunner.class)
@TestPropertySource("classpath:application.yml")
public class JsonPathParserTest {

    private String rootContextPath = null;
    private int port;

    @Autowired
    Environment environment;

    @Before
    public void init() {
        rootContextPath = environment.getProperty("contextPath") + "/api/jsonpath";
        port = Integer.parseInt(environment.getProperty("port"));
    }

    @Test
    public void testPayloadJsonPathParser() {
        Response response = given().port(port).basePath(rootContextPath)
                .when().get("/payload");
        // Retrieve the response body
        final String body = response.asString();
        JsonPath jsonPathParser = new JsonPath(body);
        // Use the parser to extract fields
        Assertions.assertEquals("JsonPath Payload Example", jsonPathParser.getString("name"));
        // Example of how to apply the closure aggergate function sum()
        int totalFlagCount = jsonPathParser.getInt("elements.flagCount.sum()");
        System.out.println("totalFlagCount = " + totalFlagCount);

        // Example of how we can deserialize json to their POJO counterparts
        JsonPathPayload jsonPathPayload = response.as(JsonPathPayload.class);
        Assertions.assertTrue(jsonPathPayload.getCost() > 9900);
    }


}
