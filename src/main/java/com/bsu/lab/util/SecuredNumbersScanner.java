package com.bsu.lab.util;

import com.bsu.lab.model.House;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Scanner;

public class SecuredNumbersScanner {

    public static int EnteringInfoCheck(String question) {
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

    public static int EnteringInfoCheckForHouseNumber(@NotNull List<House> arrayOfHouses) {
        int result = 0;
        Scanner numbersScanner = new Scanner(System.in);
        if (arrayOfHouses.size() != 1)
            System.out.print("Введите номер нужного дома" + "(1-" + arrayOfHouses.size() + "): ");
        else
            System.out.print("Введите номер нужного дома(1): ");

        boolean numberFormatHouseCompareNumber = true;
        do {
            if (numberFormatHouseCompareNumber == false) {
                if (arrayOfHouses.size() != 1)
                    System.out.print("Введите номер нужного дома" + "(1-" + arrayOfHouses.size() + "): ");
                else
                    System.out.print("Введите номер нужного дома(1): ");
            }
            try {
                numberFormatHouseCompareNumber = true;
                result = Integer.parseInt(numbersScanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Введено неверное значение");
                numberFormatHouseCompareNumber = false;
            }
            if(result-1 < 0 || result > arrayOfHouses.size()){
                System.out.println("Введен номер несуществующего дома");
                numberFormatHouseCompareNumber = false;
                continue;
            }
        } while (!numberFormatHouseCompareNumber);
        return result;
    }

}

