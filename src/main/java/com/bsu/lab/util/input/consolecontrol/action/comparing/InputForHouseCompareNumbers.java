package com.bsu.lab.util.input.consolecontrol.action.comparing;

import com.bsu.lab.model.House;
import com.bsu.lab.util.input.SecuredNumbersScanner;

import java.util.Set;


public class InputForHouseCompareNumbers {
    private static int firstHouseCompareNumber = 0;
    private static int secondHouseCompareNumber = 0;

    public static void input(Set<House> setOfHouses) {
        System.out.println("Выберите первый дом для сравнения: ");
        firstHouseCompareNumber = SecuredNumbersScanner.enteringInfoCheckForHouseNumber(setOfHouses);

        System.out.println("Выберите второй дом для сравнения: ");
        secondHouseCompareNumber = SecuredNumbersScanner.enteringInfoCheckForHouseNumber(setOfHouses);
        while (secondHouseCompareNumber == firstHouseCompareNumber ) {
            System.out.println("Дом с таким номером уже добавлен к сравнению");
            secondHouseCompareNumber = SecuredNumbersScanner.enteringInfoCheckForHouseNumber(setOfHouses);
        }
    }

    public static int getFirstHouseCompareNumber() {
        return firstHouseCompareNumber;
    }

    public static int getSecondHouseCompareNumber() {
        return secondHouseCompareNumber;
    }
}
