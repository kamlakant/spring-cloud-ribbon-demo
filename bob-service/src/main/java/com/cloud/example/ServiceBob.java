package com.cloud.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
@EnableDiscoveryClient
public class ServiceBob {

    @LoadBalanced
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Value("${server.port}")
    private int serverPort;

    @Autowired
    private RestTemplate restTemplate;

    public static void main(String[] args) {
        SpringApplication.run(ServiceBob.class, args);
    }

    @GetMapping("/generate-stuff")
    public String generateStuff() {
        return "Stuff by Bob " + serverPort;
    }

    @GetMapping("/get-stuff/{serviceName}")
    public String generateStuff(@PathVariable("serviceName") String serviceName) {
        return getServiceResponse(serviceName, String.class);
    }

    private <T> T getServiceResponse(String serviceName, Class<T> responseType) {
        return restTemplate.getForObject("http://{serviceName}/generate-stuff", responseType, serviceName);
    }

}
