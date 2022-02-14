package com.bsu.lab.AccountingSystem;

import com.bsu.lab.AccountingSystem.util.consolecontrol.ConsoleControlForHousesAccounting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories("com.bsu.lab.AccountingSystem.repository")
@EntityScan("com.bsu.lab.AccountingSystem")
public class AccountingSystemApplication implements CommandLineRunner {


    public static void main(String[] args) {
        SpringApplication.run(AccountingSystemApplication.class, args);
    }


    @Autowired
    private ConsoleControlForHousesAccounting control;

    @Override
    public void run(String... args) throws Exception {
        control.start();
    }
}
