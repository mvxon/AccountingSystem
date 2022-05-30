package com.bsu.lab.AccountingSystem.service;

import com.bsu.lab.AccountingSystem.domain.Flat;
import com.bsu.lab.AccountingSystem.domain.Floor;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public interface FloorService {

    @NotNull Floor createFloor(@NotNull List<List<Double>> squareOfRoomsOfFlats);

    Floor copyFloor(Floor floor);

    void addFlat(@NotNull Floor floor, @NotNull Flat flat);

    Floor getFloorByFlat(Flat flat);
}
