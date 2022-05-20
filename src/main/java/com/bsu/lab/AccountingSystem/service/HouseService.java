package com.bsu.lab.AccountingSystem.service;

import com.bsu.lab.AccountingSystem.domain.Entrance;
import com.bsu.lab.AccountingSystem.domain.Flat;
import com.bsu.lab.AccountingSystem.domain.House;
import com.bsu.lab.AccountingSystem.dto.HouseDTO;
import lombok.NonNull;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public interface HouseService {

    @NotNull House createHouse(int houseNumber,
                               int entrancesCount,
                               int floorsCount,
                               List<List<Double>> squareOfRoomsOfFlats
    );

    double findTotalHouseSquare(@NotNull House house);

    int findMaximumResidentsCapacity(@NotNull House house);

    Entrance getEntranceByFlatNumber(@NotNull House house, int flatNumber);

    String allHouseInfoToString(@NotNull House house);

    Flat getFlatByNumber(House house, int flatNumber);

    int getFlatsCount(@NotNull House house);

    void addEntrance(@NotNull House house, @NonNull Entrance entrance);

    int getFloorsCount(@NotNull House house);

    int getFlatsPerFloor(@NotNull House house);

    Set<Flat> getHouseFlats(@NotNull House house);

    int generateUniqueHouseNumber();

    void deleteHouseByHouseNumber(int houseNumber);

    House getHouseByHouseNumber(int houseNumber);

    TreeSet<Integer> getUsedHouseNumbers();

    Long getHousesCount();

    List<House> getAllHouses();

    boolean isFlatNumberExists(House house, int flatNumber);

    House getHouseByFlat(Flat flat);

    boolean isHouseWithNumberExists(int houseNumber);

    void save(HouseDTO house);

    Set<House> getAllUnFinishedHouses();

    House firstStepSave(HouseDTO houseDTO);

    void secondStepSave(HouseDTO houseDTO);

    House getHouseById(Long id);

    void deleteHouse(Long id);

    HouseDTO houseToDto(House house);

    int findTotalResidentsCount(House house);

    void save(House house);
}
