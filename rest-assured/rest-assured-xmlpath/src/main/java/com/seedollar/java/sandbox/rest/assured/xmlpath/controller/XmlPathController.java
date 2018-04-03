package com.seedollar.java.sandbox.rest.assured.xmlpath.controller;

import com.google.common.collect.Lists;
import com.seedollar.java.sandbox.rest.assured.xmlpath.domain.LevelEnumeration;
import com.seedollar.java.sandbox.rest.assured.xmlpath.domain.XmlPathPayload;
import com.seedollar.java.sandbox.rest.assured.xmlpath.domain.XmlPathSubElement;
import com.seedollar.lambda.builder.LambdaPojoBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/api/xmlpath")
public class XmlPathController {

    final LambdaPojoBuilder<XmlPathPayload> xmlPathPayloadBuilder = XmlPathPayload::new;
    final LambdaPojoBuilder<XmlPathSubElement> xmlPathSubElementBuilder = XmlPathSubElement::new;

    @GetMapping(value = "/payload", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<XmlPathPayload> getPayload() {
        return new ResponseEntity<>(xmlPathPayloadBuilder.build(res -> {
            res.setDescription("XmlPath description example");
            res.setName("XmlPath Rest Assured");
            res.setTimestamp(LocalDateTime.now().toString());
            res.setElements(Lists.newArrayList(
                    xmlPathSubElementBuilder.build(sub -> {
                        sub.setCounter(46);
                        sub.setEnabled(false);
                        sub.setLevel(LevelEnumeration.BOTTOM);
                        sub.setPrice(923.23d);
                        sub.setTax(43.2d);
                    }),
                    xmlPathSubElementBuilder.build(sub -> {
                        sub.setCounter(7);
                        sub.setEnabled(true);
                        sub.setLevel(LevelEnumeration.TOP);
                        sub.setPrice(769.33d);
                        sub.setTax(59.3d);
                    }),
                    xmlPathSubElementBuilder.build(sub -> {
                        sub.setCounter(97);
                        sub.setEnabled(false);
                        sub.setLevel(LevelEnumeration.TOP);
                        sub.setPrice(2987.67d);
                        sub.setTax(86.3d);
                    }),
                    xmlPathSubElementBuilder.build(sub -> {
                        sub.setCounter(62);
                        sub.setEnabled(true);
                        sub.setLevel(LevelEnumeration.MIDDLE);
                        sub.setPrice(59.71d);
                        sub.setTax(43.2d);
                    }))
            );
        }), HttpStatus.OK);
    }
}
