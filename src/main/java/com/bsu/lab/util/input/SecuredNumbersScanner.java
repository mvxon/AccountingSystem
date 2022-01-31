package com.bsu.lab.util.input;

import com.bsu.lab.model.House;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class SecuredNumbersScanner {

    public static int enteringInfoCheck(String question) {
        int result = 0;
        Scanner numbersScanner = new Scanner(System.in);
        boolean numberFormat = true;
        System.out.print(question);
        do {
            if (!numberFormat) {
                System.out.print(question);
            }
            try {
                numberFormat = true;
                result = Integer.parseInt(numbersScanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Введено неверное значение...Повторите ввод");
                numberFormat = false;
            }
        } while (!numberFormat);
        return result;
    }

    public static int enteringInfoCheckForHouseNumber(@NotNull Set<House> setOfHouses) {
        int result = 0;
        Scanner numbersScanner = new Scanner(System.in);
        boolean numberFormatHouseCompareNumber = false;

        while (!numberFormatHouseCompareNumber) {
            if (setOfHouses.size() != 1) {
                String houseNumbers = "";
                for (Integer houseNumber : House.getHouseNumbers()) {
                    houseNumbers += houseNumber + ", ";
                }
                System.out.print("Введите номер нужного дома" + "(" + houseNumbers + "\b\b): ");
            } else
                System.out.print("Введите номер нужного дома(" + setOfHouses.iterator().next().getHouseNumber() + "): ");
            try {
                numberFormatHouseCompareNumber = true;
                result = Integer.parseInt(numbersScanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Введено неверное значение");
                numberFormatHouseCompareNumber = false;
            }
            if (!House.getHouseNumbers().contains(result)) {
                System.out.println("Введен номер несуществующего дома");
                numberFormatHouseCompareNumber = false;
                continue;
            }
        }
        return result;
    }

}

