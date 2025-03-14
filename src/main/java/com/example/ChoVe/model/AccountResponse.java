package com.example.ChoVe.model;

import lombok.Data;

import java.util.UUID;

@Data
public class AccountResponse {
    UUID id;
    String email;
    String phone;
    String token;
}
