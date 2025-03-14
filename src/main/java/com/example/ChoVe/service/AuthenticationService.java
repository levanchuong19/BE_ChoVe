package com.example.ChoVe.service;

import com.example.ChoVe.entity.Account;
import com.example.ChoVe.entity.Role;
import com.example.ChoVe.model.AccountResponse;
import com.example.ChoVe.model.AccountUpdateResponse;
import com.example.ChoVe.model.LoginRequest;
import com.example.ChoVe.model.RegisterRequest;
import com.example.ChoVe.repository.AccountRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AuthenticationService implements UserDetailsService {
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    ModelMapper modelMapper;
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
     TokenService tokenService;

    public AccountResponse register(RegisterRequest registerRequest) {

        if(accountRepository.findAccountByEmail(registerRequest.getEmail())!= null){
            throw new RuntimeException("Duplicate email");
        }
        if(accountRepository.findAccountByPhone(registerRequest.getPhone())!= null){
            throw new RuntimeException("Duplicate phone number");
        }


            Account account = modelMapper.map(registerRequest, Account.class);
            try {
                String originPassword = account.getPassword();
                account.setPassword(passwordEncoder.encode(originPassword));
                account.setCreateAt(new Date());
                if (registerRequest.getRole() == null) {
                    account.setRole(Role.USER);
                } else {
                    account.setRole(registerRequest.getRole());
                }
                Account newAccount = accountRepository.save(account);
                return modelMapper.map(newAccount, AccountResponse.class);

            } catch (Exception e) {
                if (e.getMessage().contains(account.getEmail())) {
                    throw new RuntimeException("Duplicate email");
                } else {
                    throw new RuntimeException("Duplicate phone number");
                }
            }
    }

    public AccountResponse login(LoginRequest loginRequest){
          try{
              //authenticationManager se giup minh kiem tra xem username,password co ton tai trong database hay khong
             Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                      loginRequest.getEmail(),
                      loginRequest.getPassword()
              ));
             //neu dung thi tra ve account
             Account account = (Account) authentication.getPrincipal();
             AccountResponse accountResponse = modelMapper.map(account,AccountResponse.class);
             accountResponse.setToken(tokenService.generateToken(account));
             return accountResponse;
          }catch (Exception e){
              throw new EntityNotFoundException("Email or password invalid");
          }
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return accountRepository.findAccountByEmail(email);
    }

}