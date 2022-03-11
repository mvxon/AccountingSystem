package com.bsu.lab.AccountingSystem.services;

import com.bsu.lab.AccountingSystem.entities.Flat;
import com.bsu.lab.AccountingSystem.entities.Floor;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public interface FloorService {
    @NotNull Floor createFloor(@NotNull List<ArrayList<Double>> squareOfRoomsOfFlats);

    void addFlat(@NotNull Floor floor, @NotNull Flat flat);
}
