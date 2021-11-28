package com.bsu.lab.util.input.consolecontrol.action;

import com.bsu.lab.model.House;
import com.bsu.lab.service.HouseService;
import com.bsu.lab.util.input.SecuredNumbersScanner;
import com.bsu.lab.util.constants.ConstantsForFlatsComparison;
import com.bsu.lab.util.constants.GeneralConstants;

import java.util.List;

public class inputForFlatCompareNumbers {
    private static int firstHouseNumberForFlatsCompare = 0; // number of the first house for a flats comparing
    private static int firstFlatCompareNumber = 0; // number of the first comparing flat
    private static int secondHouseNumberForFlatsCompare = 0; // number of the second comparing flat
    private static int secondFlatCompareNumber = 0; // number of the second comparing flat

    public static void input(List<House> arrayOfHouses) {
        System.out.println("Выберите первый дом для сравнения: ");

       firstHouseNumberForFlatsCompare = SecuredNumbersScanner.EnteringInfoCheckForHouseNumber(arrayOfHouses);
        House houseForCompare1 = arrayOfHouses.get(firstHouseNumberForFlatsCompare - 1);

        String question = "Введите номер нужной квартиры(1-" +
                HouseService.getFlatsCount(arrayOfHouses.get(firstHouseNumberForFlatsCompare - 1)) + "): ";
        firstFlatCompareNumber = SecuredNumbersScanner.EnteringInfoCheck(question);

        if (firstFlatCompareNumber > HouseService.getFlatsCount(houseForCompare1)
                || firstFlatCompareNumber <= 0)
            do {
                System.out.println("Несуществующий номер квартиры...Введите еще раз");
                firstFlatCompareNumber = SecuredNumbersScanner.EnteringInfoCheck(question);
            } while (firstFlatCompareNumber > HouseService.getFlatsCount(houseForCompare1)
                    || firstFlatCompareNumber <= 0);

        System.out.println(ConstantsForFlatsComparison.FLAT_ADDED_INFORMING);
        System.out.print("Выберите второй дом для сравнения: \n");
        // number of the second house for a flats comparing
        secondHouseNumberForFlatsCompare = SecuredNumbersScanner.EnteringInfoCheckForHouseNumber(arrayOfHouses);

        if (secondHouseNumberForFlatsCompare == firstHouseNumberForFlatsCompare
                && HouseService.getFlatsCount(houseForCompare1) == 1) {
            do {
                System.out.println(ConstantsForFlatsComparison.ONLY_ONE_FLAT_IN_HOUSE_ERROR);
                int additionalAction;
                do {
                    additionalAction
                            = SecuredNumbersScanner.EnteringInfoCheck(ConstantsForFlatsComparison.QUESTION_FOR_ACTION);
                    if (additionalAction <= 0 || additionalAction > 2) {
                        System.out.println("Введено неверное значение. Повторите ввод");
                    }
                } while (additionalAction <= 0 || additionalAction > 2);

                if (additionalAction == 1) {
                    System.out.println(GeneralConstants.SEPARATION);
                    System.out.println("Выберите второй дом для сравнения: ");
                    secondHouseNumberForFlatsCompare = SecuredNumbersScanner.EnteringInfoCheckForHouseNumber(arrayOfHouses);
                }
                if (additionalAction == 2) {
                    return;
                }
            } while (secondHouseNumberForFlatsCompare == firstHouseNumberForFlatsCompare);
        }

        House houseForCompare2 = arrayOfHouses.get(secondHouseNumberForFlatsCompare - 1);
        question = "Введите номер нужной квартиры(1-" + HouseService.getFlatsCount(houseForCompare2) + "): ";
        secondFlatCompareNumber = SecuredNumbersScanner.EnteringInfoCheck(question);

        if (secondFlatCompareNumber > HouseService.getFlatsCount(houseForCompare2)
                || secondFlatCompareNumber <= 0
                || (firstHouseNumberForFlatsCompare == secondHouseNumberForFlatsCompare
                && firstFlatCompareNumber == secondFlatCompareNumber)) {

            do {
                System.out.println("Несуществующий номер квартиры(или квартира с таким"
                        + " номером уже добавлена к сравнению)" +
                        "...Введите еще раз");
                secondFlatCompareNumber = SecuredNumbersScanner.EnteringInfoCheck(question);
            } while (secondFlatCompareNumber > HouseService.getFlatsCount(houseForCompare2)
                    || secondFlatCompareNumber <= 0
                    || (firstHouseNumberForFlatsCompare == secondHouseNumberForFlatsCompare &&
                    firstFlatCompareNumber == secondFlatCompareNumber));
        }
    }
    public static int getFirstHouseNumberForFlatsCompare(){
        return firstHouseNumberForFlatsCompare;
    }
    public static int getSecondHouseNumberForFlatsCompare(){
        return secondHouseNumberForFlatsCompare;
    }
    public static int getFirstFlatCompareNumber(){
        return firstFlatCompareNumber;
    }
    public static int getSecondFlatCompareNumber(){
        return secondFlatCompareNumber;
    }
}
