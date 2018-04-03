package com.seedollar.java.sandbox.rest.assured.jsonpath.controller;

import com.google.common.collect.Lists;
import com.seedollar.java.sandbox.rest.assured.jsonpath.domain.JsonPathPayload;
import com.seedollar.java.sandbox.rest.assured.jsonpath.domain.JsonPathSubElement;
import com.seedollar.lambda.builder.LambdaPojoBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/jsonpath")
public class JsonPathController {

    @GetMapping("/payload")
    JsonPathPayload getPayload() {
        final LambdaPojoBuilder<JsonPathPayload> jsonPathPayloadBuilder = JsonPathPayload::new;
        final LambdaPojoBuilder<JsonPathSubElement> jsonPathSubElementBuilder = JsonPathSubElement::new;

        return jsonPathPayloadBuilder.build(res -> {
            res.setName("JsonPath Payload Example");
            res.setActive(true);
            res.setCost(9979.56d);
            res.setComment("This is a payload");
            res.setElements(Lists.newArrayList(
                    jsonPathSubElementBuilder.build(sub -> {
                        sub.setCategory("Plateform 1");
                        sub.setDescription("Plateform One Description");
                        sub.setTimestamp(LocalDateTime.now());
                        sub.setFlagCount(67);
                    }),
                    jsonPathSubElementBuilder.build(sub -> {
                        sub.setCategory("Plateform 2");
                        sub.setDescription("Plateform Two Description");
                        sub.setTimestamp(LocalDateTime.now());
                        sub.setFlagCount(92);
                    }),
                    jsonPathSubElementBuilder.build(sub -> {
                        sub.setCategory("Plateform 3");
                        sub.setDescription("Plateform Three Description");
                        sub.setTimestamp(LocalDateTime.now());
                        sub.setFlagCount(34);
                    })
            ));
        });
    }
}
