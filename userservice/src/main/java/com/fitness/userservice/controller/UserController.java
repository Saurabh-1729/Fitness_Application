package com.fitness.userservice.controller;

import com.fitness.userservice.DTO.RegisterRequest;
import com.fitness.userservice.DTO.UserResponse;
import com.fitness.userservice.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor // This is a lombok annotation that will generate a constructor for all the fields in the class
public class UserController {

    private UserService userService;

//    public UserController(UserService userService) {
//        this.userService = userService;
//    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUserProfile(@PathVariable String userId) {
        return ResponseEntity.ok(userService.getUserProfile(userId));
    }

    //    @Valid will validate the request coming in the form of body
    @PostMapping("/register")
    public ResponseEntity<UserResponse> registerUser(@Valid @RequestBody RegisterRequest registerRequest) {
        System.out.println(registerRequest);
        return ResponseEntity.ok(userService.registerUser(registerRequest));
    }

    @GetMapping("/{userId}/validate")
    public ResponseEntity<Boolean> validateUser(@PathVariable String userId) {
//        System.out.println(userId);
        return ResponseEntity.ok(userService.existsById(userId));
    }
}
