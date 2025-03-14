package com.example.ChoVe.service;

import com.example.ChoVe.entity.Account;
import com.example.ChoVe.model.AccountResponse;
import com.example.ChoVe.repository.AccountRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.UUID;


@Service
public class TokenService {

    @Autowired
    AccountRepository accountRepository;

    public final String SECRET_KEY = "4bb6d1dfbafb64a681139d1586b8f1160d18159afd57c8c79136d7490630407cnt";

    //function nay dung de lay cai key de ma hoa token
    private SecretKey getSigninKey(){
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public  String generateToken(Account account){
         String token = Jwts.builder()
                 .subject(account.getId()+"")
                 .issuedAt(new Date(System.currentTimeMillis()))// thoi gian tao token
                 .expiration(new Date(System.currentTimeMillis() + 1000*60*60*24))// thoi gian ton tai cua token
                    .signWith(getSigninKey())
                    .compact();
         return token;
    }

    public Account getAccountByToken(String token){

        Claims claims = Jwts.parser()
                .verifyWith(getSigninKey())
                .build().parseSignedClaims(token)
                .getPayload();
        String idString = claims.getSubject();
        UUID id = UUID.fromString(idString);
        return accountRepository.findAccountById(id);
    }

}
