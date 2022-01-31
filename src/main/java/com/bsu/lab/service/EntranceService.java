package com.bsu.lab.service;


import com.bsu.lab.model.Entrance;
import com.bsu.lab.model.Floor;
import com.bsu.lab.util.input.service.InputForFloorsCount;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;


public class EntranceService {
    private static EntranceService entranceService;

    public static EntranceService getInstance() {
        if (entranceService == null) {
            entranceService = new EntranceService();
        }
        return entranceService;
    }

    public static @NotNull Entrance createEntrance() {
        Entrance entrance = new Entrance();
        entrance.setEntranceNumber();
        int floorsCount = InputForFloorsCount.input();
        for (int i = 0; i < floorsCount; i++) {
            if (i == 0) {
                EntranceService.addFloor(entrance, FloorService.createFloor()); // first floor creating
            } else {
                // copying first floor by copy constructor
                EntranceService.addFloor(entrance, new Floor(entrance.getFloors().iterator().next()));
            }
        }
        return entrance;
    }

    public static void addFloor(@NotNull Entrance entrance, @NotNull Floor floor) {
        entrance.getFloors().add(floor);
        entrance.setFloorsCount(entrance.getFloorsCount() + 1);
    }
    public static Floor getFloorByFlatNumber(@NotNull Entrance entrance, int flatNumber) {
        Floor resultFloor = new Floor();
        int floorsCount = entrance.getFloorsCount();
        int flatsPerFloor = entrance.getFloors().iterator().next().getFlatsCount();
        int entranceNumber = entrance.getEntranceNumber() - 1;
        int temp = flatNumber - entranceNumber * floorsCount * flatsPerFloor;

        while (temp % flatsPerFloor != 0) {
            temp++;
        }
        Iterator<Floor> floorIterator = entrance.getFloors().iterator();
        for (int i = 0; i <= (temp / flatsPerFloor) - 1; i++) {
            resultFloor = floorIterator.next();
        }
        return resultFloor;
    }

    public static int getFlatsCount(@NotNull Entrance entrance) {
        int flatsPerFloor = entrance.getFloors().iterator().next().getFlatsCount();
        int floorsCount = entrance.getFloorsCount();
        return flatsPerFloor * floorsCount;
    }
}
