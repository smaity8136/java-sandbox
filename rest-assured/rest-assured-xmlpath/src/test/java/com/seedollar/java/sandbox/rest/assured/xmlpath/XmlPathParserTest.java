package com.seedollar.java.sandbox.rest.assured.xmlpath;

import com.seedollar.java.sandbox.rest.assured.xmlpath.domain.XmlPathPayload;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.*;

@RunWith(SpringRunner.class)
@TestPropertySource("classpath:application.yml")
public class XmlPathParserTest {

    @Autowired
    Environment environment;

    private String rootContextPath = null;
    private int port;

    @Before
    public void init() {
        rootContextPath = environment.getProperty("contextPath").toString() + "/api/xmlpath";
        port = Integer.parseInt(environment.getProperty("port"));
    }

    @Test
    public void testXmlPathParser() {
        Response response = given().port(port).basePath(rootContextPath)
                .when().get("/payload");

        final String body = response.getBody().asString();
        System.out.println("body = " + body);

        XmlPath xmlPath = new XmlPath(body);
        Assertions.assertEquals("XmlPath Rest Assured", xmlPath.get("xmlPathPayload.name"));

        // Example of object deserialization to POJO
        XmlPathPayload xmlPathPayload = response.as(XmlPathPayload.class);
        Assertions.assertEquals("XmlPath description example", xmlPathPayload.getDescription());

        // Example of aggregate function
//        int totalCount = xmlPath.getInt("xmlPathPayload.elements.counter.sum()");
//        Assertions.assertTrue(totalCount > 200);


    }

}
