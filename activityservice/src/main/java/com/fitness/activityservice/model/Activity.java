package com.fitness.activityservice.model;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@Document(collection = "activities")
@Builder // This is a lombok annotation that will generate a builder for the class
@AllArgsConstructor // This is a lombok annotation that will generate a constructor for all the fields in the class
@NoArgsConstructor
public class Activity {
    @Id
    private String id;
    private String userId;
    private ActivityType type;
    private Integer duration;
    private Integer caloriesBurned;
    private LocalDateTime startTime;
    @Field("metrics")
    private Map<String, Object> additionalMetrics; // To store additional metrics like heart rate, distance, etc.
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;
}
