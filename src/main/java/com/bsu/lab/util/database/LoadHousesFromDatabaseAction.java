package com.bsu.lab.util.database;

import com.bsu.lab.dao.HouseDAO;
import com.bsu.lab.model.House;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class LoadHousesFromDatabaseAction {
    private static final HouseDAO houseDAO = HouseDAO.getInstance();

    public static void execute(@NotNull List<House> arrayOfHouses) {
        arrayOfHouses.addAll(houseDAO.readAllExisting());
    }
}
