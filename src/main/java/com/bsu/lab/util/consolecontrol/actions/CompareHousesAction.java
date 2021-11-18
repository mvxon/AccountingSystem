package com.bsu.lab.util.consolecontrol.actions;

import com.bsu.lab.house.House;
import com.bsu.lab.util.SecuredNumbersScanner;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CompareHousesAction {
    public static void execute(@NotNull List<House> arrayOfHouses) {
        if (arrayOfHouses.size() < 2) { // validation
            System.out.println("Недостаточно домов в списке для сравнения. Добавьте еще дома");
            return;
        }
        int houseCompareNumber1; // house number of the first comparing house
        int houseCompareNumber2; // house number of the second comparing house
        System.out.println("Выберите первый дом для сравнения: ");
        houseCompareNumber1 = SecuredNumbersScanner.EnteringInfoCheckForHouseNumber(arrayOfHouses);
        System.out.println("Выберите второй дом для сравнения: ");
        houseCompareNumber2 = SecuredNumbersScanner.EnteringInfoCheckForHouseNumber(arrayOfHouses);
        if (houseCompareNumber2 == houseCompareNumber1) {
            do {
                System.out.println("Несуществующий номер дома/Дом с таким номером уже добавлен к сравнению");
                houseCompareNumber2 = SecuredNumbersScanner.EnteringInfoCheckForHouseNumber(arrayOfHouses);
            } while (houseCompareNumber2 == houseCompareNumber1);
        }
        System.out.println(arrayOfHouses.get(houseCompareNumber1 - 1));
        System.out.println(arrayOfHouses.get(houseCompareNumber2 - 1));
        if (arrayOfHouses.get(houseCompareNumber1 - 1).equals(arrayOfHouses.get(houseCompareNumber2 - 1)))
            System.out.println("Дома одинаковы!");
    }
}
