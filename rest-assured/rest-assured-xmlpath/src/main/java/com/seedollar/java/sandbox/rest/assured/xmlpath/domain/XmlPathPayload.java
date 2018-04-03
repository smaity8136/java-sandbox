package com.seedollar.java.sandbox.rest.assured.xmlpath.domain;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement
public class XmlPathPayload implements Serializable {

    private String name;

    private String description;

    private String timestamp;

    private List<XmlPathSubElement> elements;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public List<XmlPathSubElement> getElements() {
        return elements;
    }

    public void setElements(List<XmlPathSubElement> elements) {
        this.elements = elements;
    }
}
