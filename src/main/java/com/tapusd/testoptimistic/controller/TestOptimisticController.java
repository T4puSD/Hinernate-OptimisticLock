package com.tapusd.testoptimistic.controller;

import com.tapusd.testoptimistic.domain.Account;
import com.tapusd.testoptimistic.repository.AccountRepository;
import org.hibernate.StaleObjectStateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class TestOptimisticController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestOptimisticController.class);

    @Autowired
    AccountRepository accountRepository;

    private Account updateBalanceById(Long accountId, Double balance){
        Account account =  accountRepository.findById(accountId).get();
        account.setBalance(balance);
        accountRepository.save(account);
        return account;
    }

    @GetMapping("/{accountId}")
    public Account getAccountById(@PathVariable Long accountId){
       return accountRepository.findById(accountId).orElse(null);
    }

    @PutMapping("/{accountId}/fast")
    public Account updateBalance(@PathVariable Long accountId, @RequestParam Double balance){
        LOGGER.debug("Fast Updating Started!");
        Account account = updateBalanceById(accountId,balance);
        LOGGER.debug("Fast Updating Stopped!");
        return account;
    }

    @PutMapping("/{accountId}/slow")
    public Account updateBalanceSlow(@PathVariable Long accountId, @RequestParam Double balance) throws InterruptedException {
        LOGGER.debug("Slow Updating Started!");
        Thread.sleep(10000);
        Account account;
        try {
            account = updateBalanceById(accountId,balance);
        }catch (StaleObjectStateException staleObjectStateException){
           account = updateBalanceById(accountId,balance);
        }
        LOGGER.debug("Slow Updating Stopped!");
        return account;
    }

}
