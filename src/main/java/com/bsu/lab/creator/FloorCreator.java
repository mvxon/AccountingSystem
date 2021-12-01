package com.bsu.lab.creator;

import com.bsu.lab.model.Floor;
import com.bsu.lab.service.FloorService;
import com.bsu.lab.util.input.creator.InputForFlatsCount;
import org.jetbrains.annotations.NotNull;

public class FloorCreator {
    public static @NotNull Floor createFloor() {
        Floor floor = new Floor();
        int flatsCount = InputForFlatsCount.input();
        while (floor.getFlatsCount() < flatsCount) {
            FloorService.addFlat(floor, FlatCreator.createFlat()); // flats creating
        }
        return floor;
    }
}
