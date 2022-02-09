package com.bsu.lab.AccountingSystem.util.input.consolecontrol.action.comparing;

import com.bsu.lab.AccountingSystem.model.House;
import com.bsu.lab.AccountingSystem.util.input.SecuredNumbersScanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class InputForHouseCompareNumbers {
    private static int firstHouseCompareNumber = 0;
    private static int secondHouseCompareNumber = 0;
    private final SecuredNumbersScanner securedNumbersScanner;

    @Autowired
    public InputForHouseCompareNumbers(SecuredNumbersScanner securedNumbersScanner) {
        this.securedNumbersScanner = securedNumbersScanner;
    }

    public void input(Set<House> setOfHouses) {
        System.out.println("Выберите первый дом для сравнения: ");
        firstHouseCompareNumber = securedNumbersScanner.enteringInfoCheckForHouseNumber(setOfHouses);

        System.out.println("Выберите второй дом для сравнения: ");
        secondHouseCompareNumber = securedNumbersScanner.enteringInfoCheckForHouseNumber(setOfHouses);
        while (secondHouseCompareNumber == firstHouseCompareNumber ) {
            System.out.println("Дом с таким номером уже добавлен к сравнению");
            secondHouseCompareNumber = securedNumbersScanner.enteringInfoCheckForHouseNumber(setOfHouses);
        }
    }

    public int getFirstHouseCompareNumber() {
        return firstHouseCompareNumber;
    }

    public int getSecondHouseCompareNumber() {
        return secondHouseCompareNumber;
    }
}
