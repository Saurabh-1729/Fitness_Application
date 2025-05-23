package com.fitness.activityservice.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    @LoadBalanced // Many instances of the same service can be registered in Eureka
//    To make the service load balanced, we need to use the @LoadBalanced annotation.
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }

//    This below method is used to create a WebClient bean.
    @Bean
    public WebClient userServiceWebClient(WebClient.Builder webClientBuilder) {
        return webClientBuilder()
                .baseUrl("http://USER-SERVICE")
                .build();
    }
}
