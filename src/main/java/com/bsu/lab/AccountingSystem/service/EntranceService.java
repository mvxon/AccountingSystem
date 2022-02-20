package com.bsu.lab.AccountingSystem.service;


import com.bsu.lab.AccountingSystem.model.Entrance;
import com.bsu.lab.AccountingSystem.model.Floor;
import com.bsu.lab.AccountingSystem.util.input.service.InputForFloorsCount;
import lombok.NonNull;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@Service
public class EntranceService {
    private final FloorService floorService;

    @Autowired
    public EntranceService(@Lazy FloorService floorService) {
        this.floorService = floorService;
    }

    public @NonNull Entrance createEntrance(int floorsCount, List<ArrayList<Double>> squareOfRoomsOfFlats) {
        Entrance entrance = new Entrance();
        entrance.setEntranceNumber();
        for (int i = 0; i < floorsCount; i++) {
            if (i == 0) {
                this.addFloor(entrance, floorService.createFloor(squareOfRoomsOfFlats)); // first floor creating
            } else {
                // copying first floor by copy constructor
                this.addFloor(entrance, new Floor(entrance.getFloors().iterator().next()));
            }
        }
        return entrance;
    }

    public void addFloor(@NotNull Entrance entrance, @NotNull Floor floor) {
        if (entrance.getFloors().add(floor)) {
            entrance.setFloorsCount(entrance.getFloorsCount() + 1);
        }
    }

    public Floor getFloorByFlatNumber(@NotNull Entrance entrance, int flatNumber) {
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

    public int getFlatsCount(@NotNull Entrance entrance) {
        int flatsPerFloor = entrance.getFloors().iterator().next().getFlatsCount();
        int floorsCount = entrance.getFloorsCount();
        return flatsPerFloor * floorsCount;
    }
}
