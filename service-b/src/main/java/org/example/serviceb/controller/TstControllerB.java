package org.example.serviceb.controller;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

@RestController
public class TstControllerB {

    private final DiscoveryClient discoveryClient;
    private final RestClient restClient;

    public TstControllerB(DiscoveryClient discoveryClient, RestClient.Builder restClient) {
        this.discoveryClient = discoveryClient;
        this.restClient = restClient.build();
    }


    @GetMapping("/testB")
    public String test() {
        ServiceInstance serviceInstance = discoveryClient.getInstances("service-a").get(0);
        String serviceAResponse = restClient.get()
                .uri(serviceInstance.getUri() + "/test")
                .retrieve()
                .body(String.class);
        return serviceAResponse;
    }
}
