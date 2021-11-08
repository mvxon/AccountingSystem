package util;

import house.House;

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
                System.out.println("Введено неверное значение");
                numberFormat = false;
            }
        } while (!numberFormat);
        return result;
    }

    public static int EnteringInfoCheckForHouseNumber(List<House> arrayOfHouses) {
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
        } while (!numberFormatHouseCompareNumber);
        return result;
    }

}

