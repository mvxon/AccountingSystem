package com.bsu.lab.util.input.consolecontrol.action;

import com.bsu.lab.model.House;
import com.bsu.lab.util.input.SecuredNumbersScanner;

import java.util.List;


public class InputForHouseCompareNumbers {
    private static int firstHouseCompareNumber = 0;
    private static int secondHouseCompareNumber = 0;

    public static void input(List<House> arrayOfHouses) {
        System.out.println("Выберите первый дом для сравнения: ");
        firstHouseCompareNumber = SecuredNumbersScanner.EnteringInfoCheckForHouseNumber(arrayOfHouses);

        System.out.println("Выберите второй дом для сравнения: ");
        secondHouseCompareNumber = SecuredNumbersScanner.EnteringInfoCheckForHouseNumber(arrayOfHouses);
        while (secondHouseCompareNumber == firstHouseCompareNumber) {

            System.out.println("Дом с таким номером уже добавлен к сравнению");
            secondHouseCompareNumber = SecuredNumbersScanner.EnteringInfoCheckForHouseNumber(arrayOfHouses);
        }
    }

    public static int getFirstHouseCompareNumber() {
        return firstHouseCompareNumber;
    }

    public static int getSecondHouseCompareNumber() {
        return secondHouseCompareNumber;
    }
}
