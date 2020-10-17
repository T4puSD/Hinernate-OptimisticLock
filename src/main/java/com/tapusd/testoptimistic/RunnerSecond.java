package com.tapusd.testoptimistic;

import com.tapusd.testoptimistic.domain.Account;
import com.tapusd.testoptimistic.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ThreadPoolExecutor;

@Configuration
public class RunnerSecond implements CommandLineRunner {
    @Autowired
    AccountRepository accountRepository;

    @Override
    public void run(String... args) throws Exception {

//       Account account = accountRepository.findById(1L).get();
//       account.setBalance(account.getBalance()+10);
//       accountRepository.save(account);
//        System.out.println(account);
//
    }
}
