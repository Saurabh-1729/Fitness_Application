package com.fitness.aiservice.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Service

public class GeminiService {

    private final WebClient webClient;

    @Value("${GEMINI_API_KEY}")
    private String GEM_API_KEY;
    @Value("${GEMINI_API_URL}")
    private String GEM_API_URL;

    public GeminiService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    public String getAnswer(String question) {
        Map<String, Object> requestBody = Map.of(
                "contents", new Object[]{
                        Map.of(
                                "text", question
                        )
                }
        );
        // Make a request to the Gemini API with the question
        String response = webClient.post()
            .uri(GEM_API_URL + GEM_API_KEY)
                .header("Content-Type", "application/json")
                .bodyValue(requestBody)
            .retrieve()
            .bodyToMono(String.class)
            .block();

        // Return the response from the Gemini API
        return response;
    }
}
