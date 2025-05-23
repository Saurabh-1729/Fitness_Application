package com.fitness.userservice.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data // This is a lombok annotation that will generate getters and setters for all the fields in the class
public class RegisterRequest {
    @NotBlank(message = "Email is required")
    @Email(message = "Email is not valid")
    private String email;
    @NotBlank(message = "Password is required")
    @Size(message = "Password must be at least 8 characters long", min = 8)
    private String password;
    @NotBlank(message = "First name is required")
    private String firstName;
    private String lastName;

//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
//
//    public String getFirstName() {
//        return firstName;
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
}
