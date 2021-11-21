package com.bsu.lab.creator;

import com.bsu.lab.model.Floor;
import com.bsu.lab.service.FloorService;
import com.bsu.lab.util.constants.ConstantsForHouseCreating;
import com.bsu.lab.util.SecuredNumbersScanner;
import org.jetbrains.annotations.NotNull;

public class FloorCreator {
    public static @NotNull Floor createFloor() {
        Floor floor = new Floor();
        int flatsCount = SecuredNumbersScanner.EnteringInfoCheck(ConstantsForHouseCreating.QUESTION_FOR_FLATS_PER_FLOOR);
        if (flatsCount <= 0 || flatsCount > 20) {
            do {
                System.out.println("Введено неверное значение...Повторите ввод");
                flatsCount = SecuredNumbersScanner.EnteringInfoCheck(ConstantsForHouseCreating.QUESTION_FOR_FLATS_PER_FLOOR);
            } while (flatsCount <= 0 || flatsCount > 20);
        }
        for (int i = 0; i < flatsCount; i++) {
            FloorService.addFlat(floor, FlatCreator.createFlat()); // flats creating
        }
        return floor;
    }
}
