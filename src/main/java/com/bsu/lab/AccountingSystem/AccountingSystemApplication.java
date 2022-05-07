package com.bsu.lab.AccountingSystem;

import com.bsu.lab.AccountingSystem.consolecontrol.ConsoleControlForHousesAccounting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
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
