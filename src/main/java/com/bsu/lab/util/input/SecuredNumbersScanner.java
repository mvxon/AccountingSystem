package com.bsu.lab.util.input;

import com.bsu.lab.model.House;
import com.bsu.lab.service.HouseService;
import org.jetbrains.annotations.NotNull;

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
            String houseNumbers = "";
            for (Integer houseNumber : HouseService.getUsedHouseNumbers()) {
                houseNumbers += houseNumber + ", ";
            }
            System.out.print("Введите номер нужного дома" + "(" + houseNumbers + "\b\b): ");

            try {
                numberFormatHouseCompareNumber = true;
                result = Integer.parseInt(numbersScanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Введено неверное значение");
                numberFormatHouseCompareNumber = false;
            }
            if (!HouseService.getUsedHouseNumbers().contains(result)) {
                System.out.println("Введен номер несуществующего дома");
                numberFormatHouseCompareNumber = false;
                continue;
            }
        }
        return result;
    }

}

