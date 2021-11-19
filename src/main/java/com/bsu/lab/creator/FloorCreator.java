package com.bsu.lab.creator;

import com.bsu.lab.house.Floor;
import com.bsu.lab.service.FloorService;
import com.bsu.lab.util.constants.ConstantsForHouseCreating;
import com.bsu.lab.util.SecuredNumbersScanner;
import org.jetbrains.annotations.NotNull;

public class FloorCreator {
    public static @NotNull Floor createFloor() {
        Floor floor = new Floor();
        int flatsPerFloor = SecuredNumbersScanner.EnteringInfoCheck(ConstantsForHouseCreating.QUESTION_FOR_FLATS_PER_FLOOR);
        if (flatsPerFloor <= 0 || flatsPerFloor > 20)
            do {
                System.out.println("Введено неверное значение...Повторите ввод");
                flatsPerFloor = SecuredNumbersScanner.EnteringInfoCheck(ConstantsForHouseCreating.QUESTION_FOR_FLATS_PER_FLOOR);
            } while (flatsPerFloor <= 0 || flatsPerFloor > 20);
        for (int i = 0; i < flatsPerFloor; i++) {
            FloorService.addFlat(floor, FlatCreator.createFlat()); // flats creating
        }
        return floor;
    }
}
