package com.bsu.lab.service;


import com.bsu.lab.model.Entrance;
import com.bsu.lab.model.Floor;
import com.bsu.lab.util.input.service.inputForFloorsCount;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

@NoArgsConstructor
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
        int floorsCount = inputForFloorsCount.input();
        for (int i = 0; i < floorsCount; i++) {
            if (i == 0) {
                EntranceService.addFloor(entrance, FloorService.createFloor()); // first floor creating
            } else {
                // copying first floor by copy constructor
                EntranceService.addFloor(entrance, new Floor(entrance.getFloors().get(0)));
            }
        }
        return entrance;
    }

    public static void addFloor(@NotNull Entrance entrance, @NotNull Floor floor) {
        entrance.getFloors().add(floor);
        entrance.setFloorsCount(entrance.getFloorsCount() + 1);
    }
}
