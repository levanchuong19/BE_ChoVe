package com.example.ChoVe.api;

import com.example.ChoVe.entity.Account;
import com.example.ChoVe.model.AccountUpdateResponse;
import com.example.ChoVe.service.AccountService;
import com.example.ChoVe.service.AuthenticationService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/")
@CrossOrigin("*")
@SecurityRequirement(name="api")
public class AccountApi {

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    AccountService accountService;

//    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("accounts")
    public ResponseEntity getAllAccount(){
        return ResponseEntity.ok(accountService.getAllAccount());
    }

    @GetMapping("account/{id}")
    public ResponseEntity getAccount(@PathVariable UUID id){
        return ResponseEntity.ok(accountService.getAccount(id));
    }

    @PutMapping("account/{id}")
    public ResponseEntity<AccountUpdateResponse> updateAccount(@PathVariable UUID id, @RequestBody Account account){
        AccountUpdateResponse updateAccount = accountService.updateAccount(id, account);
        return ResponseEntity.ok(updateAccount);
    }

    @DeleteMapping("account/{id}")
    public ResponseEntity deleteAccount(@PathVariable UUID id){
        Account account = accountService.deleteAccount(id);
        return ResponseEntity.ok(account);
    }
}
