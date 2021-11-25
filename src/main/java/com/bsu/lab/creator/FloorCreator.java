package com.bsu.lab.creator;

import com.bsu.lab.model.Floor;
import com.bsu.lab.service.FloorService;
import com.bsu.lab.util.constants.ConstantsForHouseCreating;
import com.bsu.lab.util.SecuredNumbersScanner;
import com.bsu.lab.util.validation.validationForFlatsCount;
import org.jetbrains.annotations.NotNull;

public class FloorCreator {
    public static @NotNull Floor createFloor() {
        Floor floor = new Floor();
        int flatsCount = validationForFlatsCount.validate();
        for (int i = 0; i < flatsCount; i++) {
            FloorService.addFlat(floor, FlatCreator.createFlat()); // flats creating
        }
        return floor;
    }
}
