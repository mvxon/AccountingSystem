package com.bsu.lab.AccountingSystem.consolecontrol.inputs.consolecontrol.actions.comparing;

import com.bsu.lab.AccountingSystem.consolecontrol.constants.ConstantsForFlatsComparison;
import com.bsu.lab.AccountingSystem.consolecontrol.constants.GeneralConstants;
import com.bsu.lab.AccountingSystem.domain.House;
import com.bsu.lab.AccountingSystem.service.HouseService;
import com.bsu.lab.AccountingSystem.consolecontrol.inputs.SecuredNumbersScanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InputForFlatCompareNumbers {
    private static int firstHouseNumberForFlatsCompare = 0; // number of the first house for a flats comparing
    private static int firstFlatCompareNumber = 0; // number of the first comparing flat
    private static int secondHouseNumberForFlatsCompare = 0; // number of the second comparing flat
    private static int secondFlatCompareNumber = 0; // number of the second comparing flat
    private final HouseService houseService;
    private final SecuredNumbersScanner securedNumbersScanner;

    @Autowired
    public InputForFlatCompareNumbers(HouseService houseService,
                                      SecuredNumbersScanner securedNumbersScanner) {
        this.houseService = houseService;
        this.securedNumbersScanner = securedNumbersScanner;
    }

    public void input() {
        System.out.println("Выберите первый дом для сравнения: ");

        firstHouseNumberForFlatsCompare = securedNumbersScanner.enteringInfoCheckForHouseNumber();
        House houseForCompare1 = houseService.getHouseByHouseNumber(firstHouseNumberForFlatsCompare);

        String question = "Введите номер нужной квартиры(1-" +
                houseService.getFlatsCount(houseForCompare1) + "): ";
        firstFlatCompareNumber = securedNumbersScanner.enteringInfoCheck(question);

        while (firstFlatCompareNumber > houseService.getFlatsCount(houseForCompare1) || firstFlatCompareNumber <= 0) {
            System.out.println("Несуществующий номер квартиры...Введите еще раз");
            firstFlatCompareNumber = securedNumbersScanner.enteringInfoCheck(question);
        }
        System.out.println(ConstantsForFlatsComparison.FLAT_ADDED_INFORMING);

        System.out.print("Выберите второй дом для сравнения: \n");
        // number of the second house for a flats comparing
        secondHouseNumberForFlatsCompare = securedNumbersScanner.enteringInfoCheckForHouseNumber();

        while (secondHouseNumberForFlatsCompare == firstHouseNumberForFlatsCompare
                && houseService.getFlatsCount(houseForCompare1) == 1) {
            System.out.println(ConstantsForFlatsComparison.ONLY_ONE_FLAT_IN_HOUSE_ERROR);
            int additionalAction;
            do {
                additionalAction
                        = securedNumbersScanner.enteringInfoCheck(ConstantsForFlatsComparison.QUESTION_FOR_ACTION);
                if (additionalAction <= 0 || additionalAction > 2) {
                    System.out.println("Введено неверное значение. Повторите ввод");
                }
            } while (additionalAction <= 0 || additionalAction > 2);

            if (additionalAction == 1) {
                System.out.println(GeneralConstants.SEPARATION);
                System.out.println("Выберите второй дом для сравнения: ");
                secondHouseNumberForFlatsCompare = securedNumbersScanner.enteringInfoCheckForHouseNumber();
            }
            if (additionalAction == 2) {
                return;
            }
        }

        House houseForCompare2 = houseService.getHouseByHouseNumber(secondHouseNumberForFlatsCompare);
        question = "Введите номер нужной квартиры(1-" + houseService.getFlatsCount(houseForCompare2) + "): ";
        secondFlatCompareNumber = securedNumbersScanner.enteringInfoCheck(question);

        while (secondFlatCompareNumber > houseService.getFlatsCount(houseForCompare2)
                || secondFlatCompareNumber <= 0
                || (firstHouseNumberForFlatsCompare == secondHouseNumberForFlatsCompare &&
                firstFlatCompareNumber == secondFlatCompareNumber)) {

            System.out.println("Несуществующий номер квартиры(или квартира с таким"
                    + " номером уже добавлена к сравнению)" +
                    "...Введите еще раз");
            secondFlatCompareNumber = securedNumbersScanner.enteringInfoCheck(question);
        }
    }

    public int getFirstHouseNumberForFlatsCompare() {
        return firstHouseNumberForFlatsCompare;
    }

    public int getSecondHouseNumberForFlatsCompare() {
        return secondHouseNumberForFlatsCompare;
    }

    public int getFirstFlatCompareNumber() {
        return firstFlatCompareNumber;
    }

    public int getSecondFlatCompareNumber() {
        return secondFlatCompareNumber;
    }
}
