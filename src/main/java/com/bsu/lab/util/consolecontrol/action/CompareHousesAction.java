package com.bsu.lab.util.consolecontrol.action;

import com.bsu.lab.model.House;
import com.bsu.lab.util.consolecontrol.action.subaction.AvailabilityOfHousesCheck;
import com.bsu.lab.util.consolecontrol.action.subaction.FindDiffParametersForHousesAction;
import com.bsu.lab.util.constants.GeneralConstants;
import com.bsu.lab.util.input.consolecontrol.action.InputForHouseCompareNumbers;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CompareHousesAction {
    public static void execute(@NotNull List<House> arrayOfHouses) {
        if(!AvailabilityOfHousesCheck.check(arrayOfHouses)) return;

        if (arrayOfHouses.size() < 2) {
            System.out.println("Недостаточно домов в списке для сравнения. Добавьте еще дома");
            return;
        }

        InputForHouseCompareNumbers.input(arrayOfHouses);
        int houseCompareNumber1 = InputForHouseCompareNumbers.getFirstHouseCompareNumber();
        int houseCompareNumber2 = InputForHouseCompareNumbers.getSecondHouseCompareNumber();

        House houseForCompare1 = arrayOfHouses.get(houseCompareNumber1 - 1);
        House houseForCompare2 = arrayOfHouses.get(houseCompareNumber2 - 1);

        System.out.println(GeneralConstants.SEPARATION);
        System.out.print("Дом 1: ");
        System.out.print(houseForCompare1);
        System.out.print("Дом 2: ");
        System.out.print(houseForCompare2);

        if (houseForCompare1.equals(houseForCompare2)) {
            System.out.println("Дома одинаковы!");
        } else {
            FindDiffParametersForHousesAction.execute(houseForCompare1, houseForCompare2);
        }
    }
}
