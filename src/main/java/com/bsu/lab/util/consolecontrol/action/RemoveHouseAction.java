package com.bsu.lab.util.consolecontrol.action;

import com.bsu.lab.dao.HouseDAO;
import com.bsu.lab.model.House;
import com.bsu.lab.service.HouseService;
import com.bsu.lab.util.input.SecuredNumbersScanner;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

public class RemoveHouseAction {
    @Autowired
    private static HouseDAO houseDAO;

    public static void execute(@NotNull Set<House> setOfHouses) {
        if (setOfHouses.isEmpty()) {
            System.out.println("Домов нет");
            return;
        }
        int houseNumber = SecuredNumbersScanner.enteringInfoCheckForHouseNumber(setOfHouses);
        House houseForDeleting = HouseService.getHouseByNumberFromSetOfHouses(setOfHouses, houseNumber);
        houseDAO.delete(houseForDeleting);
        setOfHouses.remove(houseForDeleting); // house removing
        HouseService.getUsedHouseNumbers().remove(houseForDeleting.getHouseNumber());
        System.out.println("Дом успешно удалён!");
    }
}
