package com.tapusd.testoptimistic;

import com.tapusd.testoptimistic.service.AccountService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
public class TestoptimisticApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestoptimisticApplication.class, args);
    }

}
