package com.fitness.aiservice.service;

import com.fitness.aiservice.model.Activity;
import com.fitness.aiservice.model.Recommendation;
import com.fitness.aiservice.repository.RecommendationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ActivityMessageListener {

//    @Value("${rabbitmq.queue.name}")
//    private String Queue;
    private final RecommendationRepository recommendationRepository;
    private final ActivityAIService activityAIService;

//    This tells the listener to listen to the queue named "activity.queue"
    @RabbitListener(queues = "activity.queue")
    public void processActivityMessage(Activity activity) {
        log.info("Received message: {}", activity.getId());
        log.info("Generated Recommendation: {}", activityAIService.generateRecommendation(activity));
        Recommendation recommendation = activityAIService.generateRecommendation(activity);
        recommendationRepository.save(recommendation);
    }
}