package com.bsu.lab.AccountingSystem.service;


import com.bsu.lab.AccountingSystem.constant.GeneralConstants;
import com.bsu.lab.AccountingSystem.model.Entrance;
import com.bsu.lab.AccountingSystem.model.Flat;
import com.bsu.lab.AccountingSystem.model.Floor;
import com.bsu.lab.AccountingSystem.model.House;
import com.bsu.lab.AccountingSystem.repository.HouseRepository;
import com.bsu.lab.AccountingSystem.util.input.service.InputForEntrancesCount;
import com.bsu.lab.AccountingSystem.util.input.service.InputForHouseNumber;
import lombok.NonNull;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class HouseService {

    private final HouseRepository houseRepository;
    private final EntranceService entranceService;
    private final FlatService flatService;
    private final InputForHouseNumber inputForHouseNumber;

    @Autowired
    public HouseService(HouseRepository houseRepository,
                        @Lazy EntranceService entranceService,
                        @Lazy FlatService flatService,
                        @Lazy InputForHouseNumber inputForHouseNumber) {
        this.houseRepository = houseRepository;
        this.entranceService = entranceService;
        this.flatService = flatService;
        this.inputForHouseNumber = inputForHouseNumber;
    }


    public @NotNull House createHouse(int houseNumber,
                                      int entrancesCount,
                                      int floorsCount,
                                      List<ArrayList<Double>> squareOfRoomsOfFlats
    ) {
        House house = new House();
        house.setHouseNumber(houseNumber);
        while (house.getEntrancesCount() < entrancesCount) {
            if (house.getEntrancesCount() == 0) {
                this.addEntrance(house, entranceService.createEntrance(floorsCount, squareOfRoomsOfFlats));
                // creating first entrance
            } else {
                // copying first entrance by copy constructor
                this.addEntrance(house, new Entrance(house.getEntrances().iterator().next()));
            }
        }

        return house;
    }

    public double findTotalHouseSquare(@NotNull House house) {
        double result = 0;
        for (int i = 1; i < this.getFlatsCount(house) + 1; i++) {
            result += flatService.findFlatSquare(this.getFlatByNumber(house, i));
        }
        return result;
    }

    public int findTotalHouseResidentsCount(@NotNull House house) {
        int result = 0;
        for (int i = 1; i < this.getFlatsCount(house) + 1; i++) {
            result += this.getFlatByNumber(house, i).getResidentsCount();
        }
        return result;
    }

    public Entrance getEntranceByFlatNumber(@NotNull House house, int flatNumber) {
        int result = 0;
        Entrance resultEntrance = new Entrance();
        if (flatNumber == 0) return resultEntrance;

        int leftBorder = 0;
        int rightBorder = this.getFloorsCount(house)
                * house.getEntrances().iterator().next().getFloors().iterator().next().getFlatsCount();
        int counter = 0;
        while (counter < house.getEntrancesCount()) {
            if (flatNumber > leftBorder && flatNumber <= rightBorder) {
                result = counter;
                break;
            }
            leftBorder += house.getEntrances().iterator().next().getFloorsCount() *
                    house.getEntrances().iterator().next().getFloors().iterator().next().getFlatsCount();
            rightBorder += house.getEntrances().iterator().next().getFloorsCount()
                    * house.getEntrances().iterator().next().getFloors().iterator().next().getFlatsCount();
            counter++;
        }
        Iterator<Entrance> entranceIterator = house.getEntrances().iterator();
        for (int i = 0; i <= result; i++) {
            resultEntrance = entranceIterator.next();
        }
        return resultEntrance;
    }

    public String allHouseInfoToString(@NotNull House house) {
        String result = "\n" + GeneralConstants.SEPARATION +
                "\nНомер дома: " + (house.getHouseNumber()) +
                "\nКоличество подъездов: " + (house.getEntrancesCount());
        if (!house.getEntrances().isEmpty()) {
            result +=
                    "\nКоличество этажей: " + this.getFloorsCount(house) +
                            "\nКоличество квартир на одном этаже: " + this.getFlatsPerFloor(house) +
                            "\nОбщее количество квартир: " + this.getFlatsCount(house) +
                            "\nОбщая площадь дома: " + (this.findTotalHouseSquare(house)) +
                            "\nОбщее количество жильцов: " + (this.findTotalHouseResidentsCount(house)) +
                            "\n" + GeneralConstants.SEPARATION + "\n";
        }
        return result;
    }


    public Flat getFlatByNumber(House house, int flatNumber) {
        Flat resultFlat = new Flat();
        Entrance entrance = this.getEntranceByFlatNumber(house, flatNumber);
        Floor floor = entranceService.getFloorByFlatNumber(entrance, flatNumber);

        int entranceNumber = entrance.getEntranceNumber() - 1;
        int floorsCount = this.getFloorsCount(house);
        int flatsPerFloor = house.getEntrances().iterator().next().getFloors().iterator().next().getFlatsCount();
        int floorNumber = floor.getFloorNumber() - 1;
        int totalFlatsCount = floorNumber * flatsPerFloor;

        int subtrahend = entranceNumber * floorsCount * flatsPerFloor;
        subtrahend += totalFlatsCount;
        int result = flatNumber - subtrahend;
        result--;

        Iterator<Flat> flatIterator = floor.getFlats().iterator();
        for (int i = 0; i <= result; i++) {
            resultFlat = flatIterator.next();
        }
        return resultFlat;
    }

    public int getFlatsCount(@NotNull House house) {
        int entrancesCount = house.getEntrancesCount();
        int flatsPerFloor = house.getEntrances().iterator().next().getFloors().iterator().next().getFlatsCount();
        int floorsCount = house.getEntrances().iterator().next().getFloorsCount();

        return entrancesCount * flatsPerFloor * floorsCount;
    }

    public void addEntrance(@NotNull House house, @NonNull Entrance entrance) {
        if (house.getEntrances().add(entrance)) {
            house.setEntrancesCount(house.getEntrancesCount() + 1);
        }
    }


    public int getFloorsCount(@NotNull House house) {
        int floorsCount = house.getEntrances().iterator().next().getFloorsCount();
        return floorsCount;
    }

    public int generateUniqueHouseNumber() {
        int houseNumber = inputForHouseNumber.input();
        Set usedHouseNumbers = new TreeSet(houseRepository.findUsedHouseNumbers());
        if (houseRepository.count() != 0) {
            while (usedHouseNumbers.contains(houseNumber)) {
                System.out.println("Дом с таким номером уже создан. Введите еще раз");
                houseNumber = inputForHouseNumber.input();
            }
        }
        return houseNumber;
    }
    public int getFlatsPerFloor(@NotNull House house){
        return  house.getEntrances().iterator().next().getFloors().iterator().next().getFlatsCount();
    }


}
