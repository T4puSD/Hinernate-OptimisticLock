package com.tapusd.testoptimistic.controller;

import com.tapusd.testoptimistic.domain.Account;
import com.tapusd.testoptimistic.repository.AccountRepository;
import com.tapusd.testoptimistic.service.AccountService;
import org.hibernate.StaleObjectStateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class TestOptimisticController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestOptimisticController.class);

    @Autowired
    AccountService accountService;

//    private Account updateBalanceById(Long accountId, Double balance){
//        Account account =  accountRepository.findById(accountId).get();
//        account.setBalance(balance);
//        accountRepository.save(account);
//        return account;
//    }
//
    @PostMapping("/")
    public ResponseEntity<Account> createAccount(@RequestParam(required = true) String name, @RequestParam(required = true) Double balance){
        Account account = accountService.createAccount(name,balance);
        return ResponseEntity.status(HttpStatus.CREATED).body(account);
    }


    @GetMapping("/{accountId}")
    public ResponseEntity<Account> getAccountById(@PathVariable Long accountId){
       Account account = accountService.getAccountById(accountId);
       return ResponseEntity.status(HttpStatus.OK).body(account);
//       if(StringUtils.isEmpty(account)){
//           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(account);
//       } else {
//           return ResponseEntity.status(HttpStatus.OK).body(account);
//       }
    }

    @PutMapping("/{accountId}/fast")
    public Account updateBalance(@PathVariable Long accountId, @RequestParam Double balance){
        LOGGER.debug("Fast Updating Started!");
        Account account = accountService.updateBalanceFast(accountId,balance);
        LOGGER.debug("Fast Updating Stopped!");
        return account;
    }

    @PutMapping("/{accountId}/slow")
    public Account updateBalanceSlow(@PathVariable Long accountId, @RequestParam Double balance) throws InterruptedException {
        LOGGER.debug("Slow Updating Started!");
        Account account = accountService.updateBalanceSlow(accountId,balance);
//        try {
//            account = updateBalanceById(accountId,balance);
//        }catch (StaleObjectStateException staleObjectStateException){
//           account = updateBalanceById(accountId,balance);
//        }
        LOGGER.debug("Slow Updating Stopped!");
        return account;
    }

}
