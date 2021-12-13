package com.bsu.lab.service;


import com.bsu.lab.model.Flat;
import com.bsu.lab.model.Floor;
import com.bsu.lab.util.input.service.InputForFlatsCount;
import org.jetbrains.annotations.NotNull;

public class FloorService {
    private static FloorService floorService;

    public static FloorService getInstance() {
        if (floorService == null) {
            floorService = new FloorService();
        }
        return floorService;
    }

    public static @NotNull Floor createFloor() {
        Floor floor = new Floor();
        floor.setFloorNumber();
        int flatsCount = InputForFlatsCount.input();
        for (int i = 0; i < flatsCount; i++) {
            FloorService.addFlat(floor, FlatService.createFlat()); // flats creating
        }
        return floor;
    }

    public static void addFlat(@NotNull Floor floor, @NotNull Flat flat) {
        floor.getFlats().add(flat);
        floor.setFlatsCount(floor.getFlatsCount() + 1);
    }


}
