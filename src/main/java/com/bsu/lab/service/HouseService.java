package com.bsu.lab.service;
import com.bsu.lab.house.House;
import com.bsu.lab.house.Floor;
import com.bsu.lab.house.Entrance;
import com.bsu.lab.house.Flat;
import org.jetbrains.annotations.NotNull;

public class HouseService {
    public static double totalHouseSquare(@NotNull House house) {
        double result = 0;
        for (int i = 1; i < house.getFlatsCount(); i++) {
            result += HouseService.getFlat(house,i).getFlatSquare();
        }
        return result;
    }
    public static int totalHouseResidentsCount(@NotNull House house) {
        int result = 0;
        for (int i = 1; i < house.getFlatsCount()+1; i++) {
            result += HouseService.getFlat(house,i).getResidentsCount();
        }
        return result;
    }
     public static Entrance getEntranceByFlatNumber(@NotNull House house, int flatNumber) {
        int result = 0;
        int j = house.getEntrances().get(0).getFloorsCount() * house.getEntrances().get(0).getFlatsPerFloor();
        int i = 0;
        for (int k = 0; k < house.getEntrancesCount(); k++) {
            if (flatNumber >= i && flatNumber <= j) {
                result = k;
                return house.getEntrances().get(result);
            }
            i += house.getEntrances().get(0).getFloorsCount() * house.getEntrances().get(0).getFlatsPerFloor() - 1;
            j += house.getEntrances().get(0).getFloorsCount() * house.getEntrances().get(0).getFlatsPerFloor();
        }
        return house.getEntrances().get(result);
    }
     public static Floor getFloorByFlatNumber(@NotNull Entrance entrance, int flatNumber) {
        int temp = flatNumber - entrance.getEntranceNumber() * entrance.getFloorsCount() * entrance.getFlatsPerFloor();
        if (temp % entrance.getFlatsPerFloor() != 0) {
            do {
                temp++;
            } while (temp % entrance.getFlatsPerFloor() != 0);
        }
        return entrance.getFloors().get((temp / entrance.getFlatsPerFloor()) - 1);
    }

    public static Flat getFlat(House house, int flatNumber) {
        int entranceNumber = HouseService.getEntranceByFlatNumber(house, flatNumber).getEntranceNumber();
        int floorsCount = HouseService.getEntranceByFlatNumber(house, flatNumber).getFloorsCount();
        int flatsPerFloor = HouseService.getEntranceByFlatNumber(house, flatNumber).getFlatsPerFloor();
        int subtrahend = entranceNumber * floorsCount * flatsPerFloor;
        Entrance entrance = HouseService.getEntranceByFlatNumber(house, flatNumber);
        int floorNumber = HouseService.getFloorByFlatNumber(entrance, flatNumber).getFloorNumber();

        int temp2 = floorNumber * flatsPerFloor;

        subtrahend += temp2;
        int result = flatNumber - subtrahend;
        result--;
        Floor floor = HouseService.getFloorByFlatNumber(entrance,flatNumber);
        return floor.getFlats().get(result);
    }
    public static int getFlatsCount(@NotNull House house){
        return house.getEntrancesCount()*house.getEntrances().get(0).getFlatsPerFloor()*
                house.getEntrances().get(0).getFloorsCount();
    }


}