package com.fitness.aiservice.repository;

import com.fitness.aiservice.DTO.RecommendationResponse;
import com.fitness.aiservice.model.Recommendation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface RecommendationRepository extends MongoRepository<Recommendation, String> {

    List<RecommendationResponse> findByUserId(String userId);

    Optional<RecommendationResponse> findByActivityId(String activityId);
}
