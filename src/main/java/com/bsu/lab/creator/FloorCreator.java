package com.bsu.lab.creator;

import com.bsu.lab.model.Floor;
import com.bsu.lab.service.FloorService;
import com.bsu.lab.util.input.creator.inputForFlatsCount;
import org.jetbrains.annotations.NotNull;

public class FloorCreator {
    public static @NotNull Floor createFloor() {
        Floor floor = new Floor();
        int flatsCount = inputForFlatsCount.input();
        for (int i = 0; i < flatsCount; i++) {
            FloorService.addFlat(floor, FlatCreator.createFlat()); // flats creating
        }
        return floor;
    }
}
