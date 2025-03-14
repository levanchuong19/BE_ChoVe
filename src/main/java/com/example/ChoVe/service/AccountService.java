package com.example.ChoVe.service;

import com.example.ChoVe.entity.Account;
import com.example.ChoVe.exception.AccountNotFoundException;
import com.example.ChoVe.model.AccountUpdateResponse;
import com.example.ChoVe.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AccountService {
    @Autowired
    AccountRepository accountRepository;

    public AccountUpdateResponse updateAccount(UUID accountId, Account account){
        Account oldAccount = getAccountById(accountId);
        oldAccount.setEmail(account.getEmail());
        oldAccount.setPhone(account.getPhone());
        oldAccount.setName(account.getName());
        Account updateResponse = accountRepository.save(oldAccount);
        return new AccountUpdateResponse(
                updateResponse.getEmail(),
                updateResponse.getPhone(),
                updateResponse.getUsername(),
                updateResponse.getRole(),
                updateResponse.getPassword(),
                updateResponse.getCreateAt());
    }

    public Account getAccountById(UUID accountId) {
        Account account = accountRepository.findAccountById(accountId);
        if (account == null) {
            throw new AccountNotFoundException("Account not found");
        }
        return account;
    }

    public List<Account> getAllAccount(){
        List<Account> accounts = accountRepository.findAll();
        return accounts;
    }
    public Account getAccount(UUID accountID){
        Account account = accountRepository.findAccountById(accountID);
        if(account == null) throw new AccountNotFoundException("Account not found");
        return account;
    }

    public Account deleteAccount(UUID accountID){
        Account account = accountRepository.findAccountById(accountID);
        if(account == null) throw new AccountNotFoundException("Account not found");
           account.setDeleted(true);
           return accountRepository.save(account);
    }
}
