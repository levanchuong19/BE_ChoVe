package com.example.ChoVe.repository;

import com.example.ChoVe.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, UUID> {
    Account findAccountByEmail(String email);
    Account findAccountByPhone(String phone);
    Account findAccountById(UUID id);
}
