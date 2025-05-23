package com.fitness.activityservice.service;

import com.fitness.activityservice.DTO.ActivityRequest;
import com.fitness.activityservice.DTO.ActivityResponse;
import com.fitness.activityservice.model.Activity;
import com.fitness.activityservice.repository.ActivityRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor // This is a lombok annotation that will generate a constructor for final fields and fields with not null
public class ActivityService {

    private final ActivityRepository activityRepository;
    private final UserValidationService userValidationService;
    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    public ActivityResponse trackActivity(ActivityRequest activityRequest) {
        boolean isValidUser = userValidationService.validateUser(activityRequest.getUserId());

        if(!isValidUser) {
            throw new RuntimeException("User not found");
        }

        if(activityRepository.findById(activityRequest.getUserId()).isPresent()) {
            throw new RuntimeException("User already exists");
        }

        Activity activity = Activity.builder()
                .userId(activityRequest.getUserId())
                .type(activityRequest.getType())
                .duration(activityRequest.getDuration())
                .caloriesBurned(activityRequest.getCaloriesBurned())
                .startTime(activityRequest.getStartTime())
                .additionalMetrics(activityRequest.getAdditionalMetrics()).build();

        activityRepository.save(activity);

//        Publish to RabbitMQ for AI recommendation
        try{
               rabbitTemplate.convertAndSend(exchange, routingKey, activity);
        }catch (Exception e){
            log.error("Failed to publish to RabbitMQ: ", e);
        }

        return MapToActivityResponse(activity);
    }

    private ActivityResponse MapToActivityResponse(Activity activity) {
        ActivityResponse activityResponse = new ActivityResponse();
        activityResponse.setId(activity.getId());
        activityResponse.setUserId(activity.getUserId());
        activityResponse.setType(activity.getType());
        activityResponse.setDuration(activity.getDuration());
        activityResponse.setCaloriesBurned(activity.getCaloriesBurned());
        activityResponse.setStartTime(activity.getStartTime());
        activityResponse.setAdditionalMetrics(activity.getAdditionalMetrics());
        activityResponse.setCreatedAt(activity.getCreatedAt());
        activityResponse.setUpdatedAt(activity.getUpdatedAt());
        return activityResponse;
    }

    public List<ActivityResponse> getActivitiesByUserId(String userId) {
        List<Activity> activities = activityRepository.findByUserId(userId);
        return activities.stream().map(this::MapToActivityResponse).collect(Collectors.toList());
    }


    public ActivityResponse getActivityById(String activityId) {
        return activityRepository.findById(activityId)
                .map(this::MapToActivityResponse)
                .orElseThrow(() -> new RuntimeException("Activity not found"));
    }
}
