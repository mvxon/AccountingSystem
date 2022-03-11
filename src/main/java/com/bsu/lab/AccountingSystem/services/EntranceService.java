package com.bsu.lab.AccountingSystem.services;

import com.bsu.lab.AccountingSystem.entities.Entrance;
import com.bsu.lab.AccountingSystem.entities.Floor;
import lombok.NonNull;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public interface EntranceService {
    @NonNull Entrance createEntrance(int floorsCount, List<ArrayList<Double>> squareOfRoomsOfFlats);

    void addFloor(@NotNull Entrance entrance, @NotNull Floor floor);

    Floor getFloorByFlatNumber(@NotNull Entrance entrance, int flatNumber);

    int getFlatsCount(@NotNull Entrance entrance);
}
