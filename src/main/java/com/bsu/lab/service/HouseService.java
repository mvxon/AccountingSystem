package com.bsu.lab.service;


import com.bsu.lab.dao.HouseDAO;
import com.bsu.lab.model.House;
import com.bsu.lab.model.Floor;
import com.bsu.lab.model.Entrance;
import com.bsu.lab.model.Flat;
import com.bsu.lab.util.input.service.InputForEntrancesCount;
import com.bsu.lab.util.input.service.InputForHouseNumber;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

@Setter
@NoArgsConstructor
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
        int houseNumber = InputForHouseNumber.input();
        if (houseNumber == 0) {
            house.setHouseNumber(); // auto house number set
        } else {
            house.setHouseNumber(houseNumber);
        }
        int entrancesCount = InputForEntrancesCount.input();
        while (house.getEntrancesCount() < entrancesCount) {
            if (house.getEntrancesCount() == 0) {
                HouseService.addEntrance(house, EntranceService.createEntrance()); // creating first entrance
            } else {
                // copying first entrance by copy constructor
                HouseService.addEntrance(house, new Entrance(house.getEntrances().get(0)));
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
        int i = 0;
        int j = house.getEntrances().get(0).getFloorsCount()
                * house.getEntrances().get(0).getFloors().get(0).getFlatsCount();
        for (int k = 0; k < house.getEntrancesCount(); k++) {
            if (flatNumber >= i && flatNumber <= j) {
                result = k;
                return house.getEntrances().get(result);
            }
            i += house.getEntrances().get(0).getFloorsCount() *
                    house.getEntrances().get(0).getFloors().get(0).getFlatsCount() - 1;
            j += house.getEntrances().get(0).getFloorsCount()
                    * house.getEntrances().get(0).getFloors().get(0).getFlatsCount();
        }
        return house.getEntrances().get(result);
    }

    public static Floor getFloorByFlatNumber(@NotNull Entrance entrance, int flatNumber) {
        int temp = flatNumber - entrance.getEntranceNumber() * entrance.getFloorsCount() *
                entrance.getFloors().get(0).getFlatsCount();
        if (temp % entrance.getFloors().get(0).getFlatsCount() != 0) {
            do {
                temp++;
            } while (temp % entrance.getFloors().get(0).getFlatsCount() != 0);
        }
        return entrance.getFloors().get((temp / entrance.getFloors().get(0).getFlatsCount()) - 1);
    }

    public static Flat getFlat(House house, int flatNumber) {
        int entranceNumber = HouseService.getEntranceByFlatNumber(house, flatNumber).getEntranceNumber();
        int floorsCount = HouseService.getEntranceByFlatNumber(house, flatNumber).getFloorsCount();
        int flatsPerFloor = HouseService.getEntranceByFlatNumber(house, flatNumber).getFloors().get(0).getFlatsCount();
        int subtrahend = entranceNumber * floorsCount * flatsPerFloor;
        Entrance entrance = HouseService.getEntranceByFlatNumber(house, flatNumber);
        int floorNumber = HouseService.getFloorByFlatNumber(entrance, flatNumber).getFloorNumber();
        int temp2 = floorNumber * flatsPerFloor;
        subtrahend += temp2;
        int result = flatNumber - subtrahend;
        result--;
        Floor floor = HouseService.getFloorByFlatNumber(entrance, flatNumber);
        return floor.getFlats().get(result);
    }

    public static int getFlatsCount(@NotNull House house) {
        return house.getEntrancesCount() * house.getEntrances().get(0).getFloors().get(0).getFlatsCount() *
                house.getEntrances().get(0).getFloorsCount();
    }

    public static void addEntrance(@NotNull House house, @NonNull Entrance entrance) {
        house.getEntrances().add(entrance);
        house.setEntrancesCount(house.getEntrancesCount() + 1);
    }


}
