package com.bsu.lab.util.database;

import com.bsu.lab.dao.HouseDAO;
import com.bsu.lab.model.House;
import com.bsu.lab.service.HouseService;
import org.jetbrains.annotations.NotNull;


import java.util.Set;
import java.util.stream.Collectors;

public class LoadHousesFromDatabaseAction {
    private static final HouseDAO houseDAO = HouseDAO.getInstance();

    public static void execute(@NotNull Set<House> setOfHouses) {
        if (HouseDAO.getHousesCount() != 0) {
            setOfHouses.addAll(houseDAO.read());
            HouseService.getUsedHouseNumbers()
                    .addAll(setOfHouses
                            .stream()
                            .map(House::getHouseNumber)
                            .collect(Collectors.toList()));
        }
    }
}
