package com.bsu.lab.AccountingSystem.service;

import com.bsu.lab.AccountingSystem.domain.Entrance;
import com.bsu.lab.AccountingSystem.domain.Floor;
import lombok.NonNull;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public interface EntranceService {

    @NonNull Entrance createEntrance(int floorsCount, List<ArrayList<Double>> squareOfRoomsOfFlats);

    @NotNull Entrance copyEntrance(Entrance entrance);

    void addFloor(@NotNull Entrance entrance, @NotNull Floor floor);

    Floor getFloorByFlatNumber(@NotNull Entrance entrance, int flatNumber);

    int getFlatsCount(@NotNull Entrance entrance);

    Entrance getEntranceByFloor(Floor floor);
}
