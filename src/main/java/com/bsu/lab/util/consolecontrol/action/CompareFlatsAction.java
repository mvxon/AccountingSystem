package com.bsu.lab.util.consolecontrol.action;

import com.bsu.lab.model.Flat;
import com.bsu.lab.model.House;
import com.bsu.lab.service.FlatService;
import com.bsu.lab.service.HouseService;
import com.bsu.lab.util.comparer.FlatsComparer;
import com.bsu.lab.constant.GeneralConstants;
import com.bsu.lab.util.consolecontrol.action.subaction.NoAvailableHousesCheck;
import com.bsu.lab.util.input.consolecontrol.action.InputForFlatCompareNumbers;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CompareFlatsAction {
    public static void execute(@NotNull List<House> arrayOfHouses) {
        if (NoAvailableHousesCheck.check()) return;

        if (arrayOfHouses.size() == 1 && HouseService.getFlatsCount(arrayOfHouses.get(0)) == 1) {
            System.out.println("Недостаточно квартир для сравнения. Добавьте еще дома");
            return;
        }

        InputForFlatCompareNumbers.input(arrayOfHouses);
        House houseForCompare1 = arrayOfHouses.get(InputForFlatCompareNumbers.getFirstHouseNumberForFlatsCompare() - 1);
        Flat flatForCompare1 = HouseService.getFlat(houseForCompare1, InputForFlatCompareNumbers.getFirstFlatCompareNumber());
        House houseForCompare2 = arrayOfHouses.get(InputForFlatCompareNumbers.getSecondHouseNumberForFlatsCompare() - 1);
        Flat flatForCompare2 = HouseService.getFlat(houseForCompare2, InputForFlatCompareNumbers.getSecondFlatCompareNumber());

        System.out.println(GeneralConstants.SEPARATION);
        System.out.print("Квартира 1" + FlatService.flatInfoToString(houseForCompare1, flatForCompare1));

        System.out.print("Квартира 2" + FlatService.flatInfoToString(houseForCompare2, flatForCompare2));

        if (flatForCompare1.equals(flatForCompare2)) {
            System.out.println("Квартиры одинаковы!");
        } else {
            FlatsComparer flatsDifferingParameters =
                    new FlatsComparer(flatForCompare1, flatForCompare2);
            flatsDifferingParameters.printDifferingParameters();
        }
    }

}
