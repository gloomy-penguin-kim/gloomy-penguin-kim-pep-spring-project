package com.example.service;

// public class AccountService {
// }

import org.springframework.stereotype.Service;

import com.example.repository.AccountRepository;
import com.example.entity.Account;
import com.example.exception.InvalidPasswordException;
import com.example.exception.InvalidUsernameException;

import org.springframework.beans.factory.annotation.Autowired;
 
import java.util.Optional; 

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account login(Account account) throws InvalidUsernameException, InvalidPasswordException {
        Account foundAccount = accountRepository.findByUsername(account.getUsername()).orElseThrow(InvalidUsernameException::new);
        
        if (!foundAccount.getPassword().equals(account.getPassword())) {
            throw new InvalidPasswordException();
        }
        
        return foundAccount;
    }

    public boolean verifyAccountExistsByAccountId(Integer accountId) { 
        Optional<Account> foundAccount = accountRepository.findByAccountId(accountId);
        return foundAccount.isPresent(); 
    }

    public Account save(Account account) {
        return (Account) accountRepository.save(account);
    }

    public Account register(Account account) {
        return (Account) accountRepository.save(account); 
    }
}