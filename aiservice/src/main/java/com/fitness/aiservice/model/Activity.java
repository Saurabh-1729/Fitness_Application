package com.fitness.aiservice.model;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.Map;

@Data
//@AllArgsConstructor
public class Activity {
    private String id;
    private String userId;
    private String activityType;
    private Integer duration;
    private Integer caloriesBurned;
    private LocalDateTime startTime;
    private Map<String, Object> additionalMetrics; // To store additional metrics like heart rate, distance, etc.
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
