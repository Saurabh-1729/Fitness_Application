package com.fitness.activityservice.controller;

import com.fitness.activityservice.DTO.ActivityRequest;
import com.fitness.activityservice.DTO.ActivityResponse;
import com.fitness.activityservice.service.ActivityService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/activities")
@AllArgsConstructor // or we can use @Autowired
public class ActivityController {
    private ActivityService activityService;

    @PostMapping
    public ResponseEntity<ActivityResponse> trackActivity(@RequestBody ActivityRequest activityRequest) {
        return ResponseEntity.ok(activityService.trackActivity(activityRequest));
    }

    @GetMapping
    public ResponseEntity<List<ActivityResponse>> getActivitiesByUserId(@RequestHeader("X-User-ID") String userId) {
        return ResponseEntity.ok(activityService.getActivitiesByUserId(userId));
    }

    @GetMapping("/{activityId}")
    public ResponseEntity<ActivityResponse> getActivity(@PathVariable String activityId) {
        return ResponseEntity.ok(activityService.getActivityById(activityId));
    }
}

/*
It extracts the value of the HTTP header named "X-User-ID" from the incoming HTTP request.
It binds this value to the String userId parameter in the controller method.
 */