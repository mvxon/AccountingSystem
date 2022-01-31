package com.bsu.lab.service;


import com.bsu.lab.dao.HouseDAO;
import com.bsu.lab.model.House;
import com.bsu.lab.model.Floor;
import com.bsu.lab.model.Entrance;
import com.bsu.lab.model.Flat;
import com.bsu.lab.util.input.service.InputForEntrancesCount;
import com.bsu.lab.util.input.service.InputForHouseNumber;
import lombok.NonNull;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

@Setter
public class HouseService {
    private static HouseService houseService;
    private static final HouseDAO dao = HouseDAO.getInstance();

    public static HouseService getInstance() {
        if (houseService == null) {
            houseService = new HouseService();
        }
        return houseService;
    }

    public static @NotNull House createHouse() {
        House house = new House();
        HouseService.setUniqueHouseNumber(house);
        int entrancesCount = InputForEntrancesCount.input();
        while (house.getEntrancesCount() < entrancesCount) {
            if (house.getEntrancesCount() == 0) {
                HouseService.addEntrance(house, EntranceService.createEntrance()); // creating first entrance
            } else {
                // copying first entrance by copy constructor
                HouseService.addEntrance(house, new Entrance(house.getEntrances().iterator().next()));
            }
        }
        dao.create(house);
        System.out.println("Дом номер " + house.getHouseNumber() + " успешно добавлен!");
        return house;
    }

    public static double findTotalHouseSquare(@NotNull House house) {
        double result = 0;
        for (int i = 1; i < HouseService.getFlatsCount(house) + 1; i++) {
            result += FlatService.findFlatSquare(HouseService.getFlat(house, i));
        }
        return result;
    }

    public static int findTotalHouseResidentsCount(@NotNull House house) {
        int result = 0;
        for (int i = 1; i < HouseService.getFlatsCount(house) + 1; i++) {
            result += HouseService.getFlat(house, i).getResidentsCount();
        }
        return result;
    }

    public static Entrance getEntranceByFlatNumber(@NotNull House house, int flatNumber) {
        int result = 0;
        Entrance resultEntrance = new Entrance();
        if (flatNumber == 0) return resultEntrance;

        int leftBorder = 0;
        int rightBorder = HouseService.getFloorsCount(house)
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


    public static Flat getFlat(House house, int flatNumber) {
        Flat resultFlat = new Flat();
        Entrance entrance = HouseService.getEntranceByFlatNumber(house, flatNumber);
        Floor floor = EntranceService.getFloorByFlatNumber(entrance, flatNumber);

        int entranceNumber = entrance.getEntranceNumber() - 1;
        int floorsCount = HouseService.getFloorsCount(house);
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

    public static int getFlatsCount(@NotNull House house) {
        int entrancesCount = house.getEntrancesCount();
        int flatsPerFloor = house.getEntrances().iterator().next().getFloors().iterator().next().getFlatsCount();
        int floorsCount = house.getEntrances().iterator().next().getFloorsCount();

        return entrancesCount * flatsPerFloor * floorsCount;
    }

    public static void addEntrance(@NotNull House house, @NonNull Entrance entrance) {
        house.getEntrances().add(entrance);
        house.setEntrancesCount(house.getEntrancesCount() + 1);
    }


    public static int getFloorsCount(@NotNull House house) {
        int floorsCount = house.getEntrances().iterator().next().getFloorsCount();
        return floorsCount;
    }

    public static void setUniqueHouseNumber(@NotNull House house) {
        int houseNumber = InputForHouseNumber.input();
        if (House.getHouseNumbers() != null) {
            while (House.getHouseNumbers().contains(houseNumber)) {
                System.out.println("Дом с таким номером уже создан. Введите еще раз");
                houseNumber = InputForHouseNumber.input();
            }
        }
        house.setHouseNumber(houseNumber);
        House.getHouseNumbers().add(houseNumber);
    }


}
