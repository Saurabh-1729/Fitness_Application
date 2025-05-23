package com.fitness.aiservice.service;

import com.fitness.aiservice.DTO.RecommendationResponse;
import com.fitness.aiservice.repository.RecommendationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecommendationService {
    private final RecommendationRepository recommendationRepository;

    public List<RecommendationResponse> getUserRecommendations(String userId) {
        return recommendationRepository.findByUserId(userId);
    }

    public RecommendationResponse getRecommendationsByActivity(String activityId) {
        return recommendationRepository.findByActivityId(activityId).orElseThrow(() -> new RuntimeException("Recommendation not found: " + activityId));
    }
}
