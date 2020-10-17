package com.tapusd.testoptimistic;

import com.tapusd.testoptimistic.domain.Account;
import com.tapusd.testoptimistic.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Runner implements CommandLineRunner {
    @Autowired
    AccountRepository accountRepository;

    @Override
    public void run(String... args) throws Exception {
//        Account account = accountRepository.findById(1L).get();
//        account.setBalance(account.getBalance()+10);
//        accountRepository.save(account);
//        System.out.println(account);
    }
}
