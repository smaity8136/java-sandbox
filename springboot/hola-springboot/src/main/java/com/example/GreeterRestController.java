package com.example;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by seedollar on 11/15/16.
 */
@RestController
@RequestMapping("/api")
@ConfigurationProperties(prefix = "greeting")
public class GreeterRestController {

    private RestTemplate template = new RestTemplate();

    private String saying;

    private String backendServiceHost;

    private String backendServicePort;

    @RequestMapping(value = "/greeting", method = RequestMethod.GET, produces = "text/plain")
    public String greeting() {
        String backendServiceUrl = String.format("http://%s:%s/api/backend?greeting={saying}", backendServiceHost, backendServicePort);
        System.out.println("backendServiceUrl = " + backendServiceUrl);

        BackendDTO response = template.getForObject(backendServiceUrl, BackendDTO.class, saying);
        System.out.println("Sending to: " + backendServiceUrl);
        return response.getGreeting() + " at host: " +
                response.getIp();
    }

    public String getSaying() {
        return saying;
    }

    public void setSaying(String saying) {
        this.saying = saying;
    }

    public String getBackendServiceHost() {
        return backendServiceHost;
    }

    public void setBackendServiceHost(String backendServiceHost) {
        this.backendServiceHost = backendServiceHost;
    }

    public String getBackendServicePort() {
        return backendServicePort;
    }

    public void setBackendServicePort(String backendServicePort) {
        this.backendServicePort = backendServicePort;
    }
}
