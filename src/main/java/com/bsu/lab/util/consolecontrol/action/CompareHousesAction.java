package com.bsu.lab.util.consolecontrol.action;

import com.bsu.lab.constant.GeneralConstants;
import com.bsu.lab.model.House;
import com.bsu.lab.util.getter.GetHouseFromSetByNumber;
import com.bsu.lab.util.consolecontrol.action.subaction.AvailabilityOfHousesCheck;
import com.bsu.lab.util.input.consolecontrol.action.comparing.InputForHouseCompareNumbers;
import com.bsu.lab.util.comparer.HousesComparer;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class CompareHousesAction {
    public static void execute(@NotNull Set<House> setOfHouses) {
        if (AvailabilityOfHousesCheck.check()) return;

        if (setOfHouses.size() < 2) {
            System.out.println("Недостаточно домов в списке для сравнения. Добавьте еще дома");
            return;
        }

        InputForHouseCompareNumbers.input(setOfHouses);
        int houseCompareNumber1 = InputForHouseCompareNumbers.getFirstHouseCompareNumber();
        int houseCompareNumber2 = InputForHouseCompareNumbers.getSecondHouseCompareNumber();

        House houseForCompare1 = GetHouseFromSetByNumber.get(setOfHouses, houseCompareNumber1);
        House houseForCompare2 = GetHouseFromSetByNumber.get(setOfHouses, houseCompareNumber2);

        System.out.print(GeneralConstants.SEPARATION + "\nДом 1" + houseForCompare1);

        System.out.print("Дом 2" + houseForCompare2);


        if (houseForCompare1.compareTo(houseForCompare2) == 0) {
            System.out.println("Дома одинаковы!");
        } else {
            HousesComparer housesDifferingParameters =
                    new HousesComparer(houseForCompare1, houseForCompare2);
            housesDifferingParameters.printDifferingParameters();
        }
    }
}

