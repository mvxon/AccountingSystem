package com.bsu.lab.AccountingSystem.services;

import com.bsu.lab.AccountingSystem.entities.Flat;
import com.bsu.lab.AccountingSystem.entities.House;
import com.bsu.lab.AccountingSystem.entities.Room;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface FlatService {
    double findFlatSquare(@NotNull Flat flat);

    @NotNull Flat createFlat(@NotNull List<Double> squareOfRoomsOfFlat);

    @NotNull String flatInfoToString(@NotNull House house, @NotNull Flat flat);

    void addRoom(@NotNull Flat flat, @NotNull Room room);
}
