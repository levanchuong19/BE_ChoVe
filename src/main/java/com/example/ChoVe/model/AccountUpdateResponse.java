package com.example.ChoVe.model;

import com.example.ChoVe.entity.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountUpdateResponse {
    private  String phone;
    private String email;
    private String username;
    private Role role;
    @JsonIgnore
    private String password;
    private Date createAt;

}
