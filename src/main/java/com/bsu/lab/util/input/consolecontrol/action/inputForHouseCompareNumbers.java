package com.bsu.lab.util.input.consolecontrol.action;

import com.bsu.lab.model.House;
import com.bsu.lab.util.input.SecuredNumbersScanner;

import java.util.List;


public class inputForHouseCompareNumbers {
    private static int firstHouseCompareNumber = 0;
    private static int secondHouseCompareNumber = 0;

    public static void input(List<House> arrayOfHouses) {
        System.out.println("Выберите первый дом для сравнения: ");
        int houseCompareNumber1 = SecuredNumbersScanner.EnteringInfoCheckForHouseNumber(arrayOfHouses);

        System.out.println("Выберите второй дом для сравнения: ");
        int houseCompareNumber2 = SecuredNumbersScanner.EnteringInfoCheckForHouseNumber(arrayOfHouses);
        while (houseCompareNumber2 == houseCompareNumber1) {
            System.out.println("Дом с таким номером уже добавлен к сравнению");
            houseCompareNumber2 = SecuredNumbersScanner.EnteringInfoCheckForHouseNumber(arrayOfHouses);
        }
    }

    public static int getFirstHouseCompareNumber() {
        return firstHouseCompareNumber;
    }

    public static int getSecondHouseCompareNumber() {
        return secondHouseCompareNumber;
    }
}
