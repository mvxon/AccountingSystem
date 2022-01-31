package com.bsu.lab.util.consolecontrol.action;

import com.bsu.lab.dao.HouseDAO;
import com.bsu.lab.model.House;
import com.bsu.lab.util.getter.GetHouseFromSetByNumber;
import com.bsu.lab.util.input.SecuredNumbersScanner;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class RemoveHouseAction {
    public static void execute(@NotNull Set<House> setOfHouses) {
        if (setOfHouses.isEmpty()) {
            System.out.println("Домов нет");
            return;
        }
        int houseNumber = SecuredNumbersScanner.enteringInfoCheckForHouseNumber(setOfHouses);
        HouseDAO houseDAO = HouseDAO.getInstance();
        House houseForDeleting = GetHouseFromSetByNumber.get(setOfHouses, houseNumber);
        houseDAO.delete(houseForDeleting);
        setOfHouses.remove(houseForDeleting); // house removing
        House.getHouseNumbers().remove(houseForDeleting.getHouseNumber());
        System.out.println("Дом успешно удалён!");
    }
}
