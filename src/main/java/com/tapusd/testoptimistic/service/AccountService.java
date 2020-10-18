package com.tapusd.testoptimistic.service;

import com.tapusd.testoptimistic.domain.Account;
import com.tapusd.testoptimistic.repository.AccountRepository;
import org.hibernate.StaleStateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@EnableRetry
public class AccountService {
    @Autowired
    AccountRepository accountRepository;

    @Transactional
    public Account createAccount(String name, Double balance){
        return accountRepository.save(new Account().setName(name).setBalance(balance));
    }

    public Account getAccountById(Long accountId){
        return accountRepository.findById(accountId).orElse(null);
    }

    @Transactional
    public Account updateBalanceFast(Long accountId, Double balance){
       Account account = accountRepository.findById(accountId).orElseThrow(
               ()-> new IllegalArgumentException("Account Not found with this id!"));
       account.setBalance(balance);
       return accountRepository.save(account);
    }


    @Transactional
    @Retryable(maxAttempts = 2,value = StaleStateException.class)
    public Account updateBalanceSlow(Long accountId, Double balance) throws InterruptedException {
        Account account = accountRepository.findById(accountId).orElseThrow(
                ()-> new IllegalArgumentException("Account Not found with this id!"));
        account.setBalance(balance);
        Thread.sleep(7000);
        return accountRepository.save(account);
    }


}
