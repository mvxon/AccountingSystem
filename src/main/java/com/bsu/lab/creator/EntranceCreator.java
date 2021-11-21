package com.bsu.lab.creator;

import com.bsu.lab.model.Entrance;
import com.bsu.lab.model.Floor;
import com.bsu.lab.service.EntranceService;
import com.bsu.lab.util.constants.ConstantsForHouseCreating;
import com.bsu.lab.util.SecuredNumbersScanner;
import org.jetbrains.annotations.NotNull;

public class EntranceCreator {

    public static @NotNull Entrance createEntrance() {
        Entrance entrance = new Entrance();
        int floorsCount = SecuredNumbersScanner.EnteringInfoCheck(ConstantsForHouseCreating.QUESTION_FOR_FLOORS_COUNT);
        if (floorsCount <= 0 || floorsCount> 163)
            do {
                System.out.println(ConstantsForHouseCreating.INVALID_INPUT_ERROR);
                floorsCount = SecuredNumbersScanner.EnteringInfoCheck(ConstantsForHouseCreating.QUESTION_FOR_FLOORS_COUNT);
            } while (floorsCount <= 0 || floorsCount > 163);
        for (int i = 0; i < floorsCount; i++) {
            if (i == 0) {
                EntranceService.addFloor(entrance, FloorCreator.createFloor()); // first floor creating
            } else {
                // copying first floor by copy constructor
                EntranceService.addFloor(entrance, new Floor(entrance.getFloors().get(0)));
            }
        }
        return entrance;
    }
}
