package com.bsu.lab.AccountingSystem.service;

import com.bsu.lab.AccountingSystem.domain.Flat;
import com.bsu.lab.AccountingSystem.domain.House;
import com.bsu.lab.AccountingSystem.domain.Room;
import com.bsu.lab.AccountingSystem.dto.FlatDTO;
import org.jetbrains.annotations.NotNull;

import java.util.List;
public interface FlatService {

    double findFlatSquare(@NotNull Flat flat);

    Flat copyFlat(Flat flat);

    @NotNull Flat createFlat(@NotNull List<Double> squareOfRoomsOfFlat);

    @NotNull String flatInfoToString(@NotNull House house, @NotNull Flat flat);

    void addRoom(@NotNull Flat flat, @NotNull Room room);

    void save(Flat flat);

    Flat getFlatById(Long id);

    FlatDTO flatToDto(Flat flat);

}
