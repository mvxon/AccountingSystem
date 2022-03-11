package com.bsu.lab.AccountingSystem.services;

import com.bsu.lab.AccountingSystem.entities.Entrance;
import com.bsu.lab.AccountingSystem.entities.Flat;
import com.bsu.lab.AccountingSystem.entities.House;
import lombok.NonNull;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public interface HouseService {

    @NotNull House createHouse(int houseNumber,
                               int entrancesCount,
                               int floorsCount,
                               List<ArrayList<Double>> squareOfRoomsOfFlats
    );

    double findTotalHouseSquare(@NotNull House house);

    int findTotalHouseResidentsCount(@NotNull House house);

    Entrance getEntranceByFlatNumber(@NotNull House house, int flatNumber);

    String allHouseInfoToString(@NotNull House house);

    Flat getFlatByNumber(House house, int flatNumber);

    int getFlatsCount(@NotNull House house);

    void addEntrance(@NotNull House house, @NonNull Entrance entrance);

    int getFloorsCount(@NotNull House house);

    int getFlatsPerFloor(@NotNull House house);

    Set<Flat> getFlats(@NotNull House house);

    int generateUniqueHouseNumber();

}
