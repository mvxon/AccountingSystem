package com.bsu.lab.util.consolecontrol.actions;

import com.bsu.lab.house.House;
import com.bsu.lab.service.FlatService;
import com.bsu.lab.service.HouseService;
import com.bsu.lab.util.SecuredNumbersScanner;
import com.bsu.lab.util.constants.ConstantsForFlatsComparison;
import org.jetbrains.annotations.NotNull;

import java.util.List;
public class CompareFlatsAction {
    public static void execute(@NotNull List<House> arrayOfHouses){
        if (arrayOfHouses.isEmpty()) {
            System.out.println("Домов нет");
            return;
        }
        if (arrayOfHouses.size() == 1 && HouseService.getFlatsCount(arrayOfHouses.get(0)) == 1) {
            System.out.println("Недостаточно квартир для сравнения. Добавьте еще дома");
            return;
        }
        System.out.println("Выберите первый дом для сравнения: ");

        int flatCompareNumber2; // number of the second comparing flat
        // number of the first house for a flats comparing
        int houseCompareNumber1 = SecuredNumbersScanner.EnteringInfoCheckForHouseNumber(arrayOfHouses);
        House houseForCompare1 = arrayOfHouses.get(houseCompareNumber1 - 1);
        int flatCompareNumber1; // number of the first comparing flat
        String question = "Введите номер нужной квартиры(1-" +
                HouseService.getFlatsCount(arrayOfHouses.get(houseCompareNumber1 - 1)) + "): ";
        // entering the first comparing flat number
        flatCompareNumber1 = SecuredNumbersScanner.EnteringInfoCheck(question);
        if (flatCompareNumber1 > HouseService.getFlatsCount(houseForCompare1)
                || flatCompareNumber1 <= 0)
            do {
                System.out.println("Несуществующий номер квартиры...Введите еще раз");
                flatCompareNumber1 = SecuredNumbersScanner.EnteringInfoCheck(question);
            } while (flatCompareNumber1 > HouseService.getFlatsCount(houseForCompare1)
                    || flatCompareNumber1 <= 0);
        System.out.println(ConstantsForFlatsComparison.FLAT_ADDED_INFORMING);
        System.out.print("\nВыберите второй дом для сравнения: \n");
        // number of the second house for a flats comparing
        int houseCompareNumber2 = SecuredNumbersScanner.EnteringInfoCheckForHouseNumber(arrayOfHouses);
        if (houseCompareNumber2 == houseCompareNumber1
                && HouseService.getFlatsCount(houseForCompare1) == 1) {
            System.out.println(ConstantsForFlatsComparison.ONLY_ONE_FLAT_IN_HOUSE_ERROR);
            question = (ConstantsForFlatsComparison.QUESTION_FOR_ACTION);
            int additionalAction;
            do {
                additionalAction = SecuredNumbersScanner.EnteringInfoCheck(question);
                if (additionalAction <= 0 || additionalAction > 2) {
                    System.out.println("Введено неверное значение. Повторите ввод");
                }
            } while (additionalAction <= 0 || additionalAction > 2);

            if (additionalAction == 1) {
                System.out.println("Выберите второй дом для сравнения: ");
                houseCompareNumber2 = SecuredNumbersScanner.EnteringInfoCheckForHouseNumber(arrayOfHouses);
            }
            if (additionalAction == 2) {
                return;
            }
        }

        House houseForCompare2 = arrayOfHouses.get(houseCompareNumber2 - 1);
        question = "Введите номер нужной квартиры(1-";
        question += HouseService.getFlatsCount(houseForCompare2) + "): ";
        flatCompareNumber2 = SecuredNumbersScanner.EnteringInfoCheck(question);

        if (flatCompareNumber2 > HouseService.getFlatsCount(houseForCompare2)
                || flatCompareNumber2 <= 0
                || (houseCompareNumber1 == houseCompareNumber2 && flatCompareNumber1 == flatCompareNumber2)) {

            do {
                System.out.println("Несуществующий номер квартиры(или квартира с таким"
                        + "номером уже добавлена к сравнению)" +
                        "...Введите еще раз");
                flatCompareNumber2 = SecuredNumbersScanner.EnteringInfoCheck(question);
            } while (flatCompareNumber2 > HouseService.getFlatsCount(houseForCompare2)
                    || flatCompareNumber2 <= 0
                    || (houseCompareNumber1 == houseCompareNumber2 && flatCompareNumber1 == flatCompareNumber2));
        }
        System.out.print("Квартира 1"
                + FlatService.flatInfoToString(houseForCompare1, HouseService.getFlat(houseForCompare1,
                flatCompareNumber1)));
        System.out.println("Квартира 2"
                + FlatService.flatInfoToString(houseForCompare2, HouseService.getFlat(houseForCompare2,
                flatCompareNumber2)));
    }
}
