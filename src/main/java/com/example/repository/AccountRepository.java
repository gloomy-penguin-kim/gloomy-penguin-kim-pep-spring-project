package com.example.repository; 

import java.util.Optional; 

import com.example.entity.Account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface AccountRepository extends JpaRepository<Account, Long> {  
    Optional<Account> findByAccountId(@Param("accountId") Integer accountId); 
    Optional<Account> findByUsername(@Param("username") String username); 
    Optional<Account> findByUsernameAndPassword(@Param("username") String username, @Param("password") String password); 
}