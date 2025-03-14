package com.example.ChoVe.model;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginRequest {
    @Email(message = "Invalid email")
    @Column(unique = true)
    String email;
//    @Pattern(regexp = "(84|0[3|5|7|8|9])+(\\d{8})\\b" , message = "Invalid phone number")
//    @Column(unique = true)
//    String phone;
    @Size(min = 6 , message = "Password must be exceed 6 characters ")
    String password;
}
