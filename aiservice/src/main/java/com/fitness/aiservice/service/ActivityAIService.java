package com.fitness.aiservice.service;

import com.fitness.aiservice.model.Activity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ActivityAIService {

    private final GeminiService geminiService;

    public String generateRecommendation(Activity activity) {
        String prompt = createPrompt(activity);
        String aiREsponse = geminiService.getAnswer(prompt);
        log.info("AI Response: {}", aiREsponse);
        return aiREsponse;
    }

    private String createPrompt(Activity activity) {

    }
}
