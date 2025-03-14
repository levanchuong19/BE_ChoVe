package com.example.ChoVe.api;

import com.example.ChoVe.model.AccountResponse;
import com.example.ChoVe.model.LoginRequest;
import com.example.ChoVe.model.RegisterRequest;
import com.example.ChoVe.service.AuthenticationService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
@CrossOrigin("*")
@SecurityRequirement(name="api") // danh dau cai nay de test swagger an cai token vao
public class AuthenticationAPI {

    @Autowired
    AuthenticationService authenticationService;


    @PostMapping("register")
    public ResponseEntity register(@Valid @RequestBody RegisterRequest registerRequest){
        AccountResponse newAccount = authenticationService.register(registerRequest);
        return ResponseEntity.ok(newAccount);
    }

    @PostMapping("login")
    public ResponseEntity login(@Valid @RequestBody LoginRequest loginRequest){
        AccountResponse newAccount = authenticationService.login(loginRequest);
        return ResponseEntity.ok(newAccount);
    }
}
