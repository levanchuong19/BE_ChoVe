package com.example.ChoVe.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Getter
@Setter
@Entity
public class Account implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    UUID id;

    @Enumerated(EnumType.STRING)// them dong nay de luu enum xuong db theo dang string chu khong phai dang so
    Role role;

    @NotBlank(message = "Email can not be blank !")
    @Email(message = "Invalid email")
    String email;

    @NotBlank(message = "Name can not be blank !")
    String name;

    @Pattern(regexp = "(84|0[3|5|7|8|9])+(\\d{8})\\b" , message = "Invalid phone number")
    String phone;

    @NotBlank(message = "Code can not be blank !")
    @JsonIgnore
    @Size(min = 6 , message = "Password must be exceed 6 characters ")
    String password;
    Date createAt;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    boolean isDeleted = false;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
       if (this.role != null) {
           authorities.add(new SimpleGrantedAuthority(this.role.toString()));
       }
        return authorities;
    }

    @Override
    public String getUsername() {
        return this.email != null ? this.email : this.phone;
    }
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Override
    public boolean isEnabled() {
        return true;
    }
}
