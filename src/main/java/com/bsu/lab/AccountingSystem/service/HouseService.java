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

import java.util.Iterator;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;


@Service
public class HouseService {

    private static SortedSet<Integer> usedHouseNumbers = new TreeSet<Integer>();
    private final HouseRepository houseRepository;
    private final EntranceService entranceService;
    private final FlatService flatService;
    private final InputForEntrancesCount inputForEntrancesCount;
    private final InputForHouseNumber inputForHouseNumber;

    @Autowired
    public HouseService(HouseRepository houseRepository,
                        @Lazy EntranceService entranceService,
                        @Lazy FlatService flatService,
                        @Lazy InputForEntrancesCount inputForEntrancesCount,
                        @Lazy InputForHouseNumber inputForHouseNumber) {
        this.houseRepository = houseRepository;
        this.entranceService = entranceService;
        this.flatService = flatService;
        this.inputForEntrancesCount = inputForEntrancesCount;
        this.inputForHouseNumber = inputForHouseNumber;
    }


    public @NotNull House createHouse() {
        House house = new House();
        this.setUniqueHouseNumber(house);
        int entrancesCount = inputForEntrancesCount.input();
        while (house.getEntrancesCount() < entrancesCount) {
            if (house.getEntrancesCount() == 0) {
                this.addEntrance(house, entranceService.createEntrance()); // creating first entrance
            } else {
                // copying first entrance by copy constructor
                this.addEntrance(house, new Entrance(house.getEntrances().iterator().next()));
            }
        }
        houseRepository.save(house);
        System.out.println("Дом номер " + house.getHouseNumber() + " успешно добавлен!");
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
                    "\nКоличество этажей: " + (house.getEntrances().iterator().next().getFloorsCount()) +
                            "\nКоличество квартир на одном этаже: "
                            + house.getEntrances().iterator().next().getFloors().iterator().next().getFlatsCount() +
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

    public void setUniqueHouseNumber(@NotNull House house) {
        int houseNumber = inputForHouseNumber.input();
        if (usedHouseNumbers != null) {
            while (usedHouseNumbers.contains(houseNumber)) {
                System.out.println("Дом с таким номером уже создан. Введите еще раз");
                houseNumber = inputForHouseNumber.input();
            }
        }
        house.setHouseNumber(houseNumber);
        usedHouseNumbers.add(houseNumber);
    }

    public SortedSet<Integer> getUsedHouseNumbers() {
        return usedHouseNumbers;
    }

    public @NotNull House getHouseByNumberFromSetOfHouses(@NotNull Set<House> setOfHouses, int houseNumber) {
        if (!setOfHouses.isEmpty()) {
            for (House house : setOfHouses) {
                if (house.getHouseNumber() == houseNumber) {
                    return house;
                }
            }
        }
        return new House();
    }
}
