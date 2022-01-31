package com.bsu.lab.util.consolecontrol.action;

import com.bsu.lab.model.Flat;
import com.bsu.lab.model.House;
import com.bsu.lab.service.FlatService;
import com.bsu.lab.service.HouseService;
import com.bsu.lab.util.getter.GetHouseFromSetByNumber;
import com.bsu.lab.util.comparer.FlatsComparer;
import com.bsu.lab.constant.GeneralConstants;
import com.bsu.lab.util.consolecontrol.action.subaction.AvailabilityOfHousesCheck;
import com.bsu.lab.util.input.consolecontrol.action.comparing.InputForFlatCompareNumbers;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class CompareFlatsAction {
    public static void execute(@NotNull Set<House> setOfHouses) {
        if (AvailabilityOfHousesCheck.check()) return;

        if (setOfHouses.size() == 1 && HouseService.getFlatsCount(setOfHouses.iterator().next()) == 1) {
            System.out.println("Недостаточно квартир для сравнения. Добавьте еще дома");
            return;
        }

        InputForFlatCompareNumbers.input(setOfHouses);
        House houseForCompare1 = GetHouseFromSetByNumber.get(setOfHouses,
                InputForFlatCompareNumbers.getFirstHouseNumberForFlatsCompare());
        Flat flatForCompare1 = HouseService.getFlat(houseForCompare1, InputForFlatCompareNumbers.getFirstFlatCompareNumber());
        House houseForCompare2 = GetHouseFromSetByNumber.get(setOfHouses,
                InputForFlatCompareNumbers.getSecondHouseNumberForFlatsCompare());
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
