package com.bsu.lab.util.consolecontrol.action;

import com.bsu.lab.dao.HouseDAO;
import com.bsu.lab.model.House;
import com.bsu.lab.util.input.SecuredNumbersScanner;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class RemoveHouseAction {
    public static void execute(@NotNull List<House> arrayOfHouses) {
        if (arrayOfHouses.isEmpty()) {
            System.out.println("Домов нет");
            return;
        }
        int houseNumber = SecuredNumbersScanner.EnteringInfoCheckForHouseNumber(arrayOfHouses);
        HouseDAO houseDAO = HouseDAO.getInstance();
        houseDAO.delete(arrayOfHouses.get(houseNumber-1));
        arrayOfHouses.remove(houseNumber - 1); // house removing
        System.out.println("Дом успешно удалён!");
    }
}
