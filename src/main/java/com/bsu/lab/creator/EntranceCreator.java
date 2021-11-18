package com.bsu.lab.creator;

import com.bsu.lab.house.Entrance;
import com.bsu.lab.house.Floor;
import com.bsu.lab.util.constants.ConstantsForHouseCreating;
import com.bsu.lab.util.SecuredNumbersScanner;
import org.jetbrains.annotations.NotNull;

public class EntranceCreator {

    public static @NotNull Entrance createEntrance() {
        Entrance entrance = new Entrance();
        entrance.setFloorsCount(SecuredNumbersScanner.EnteringInfoCheck(ConstantsForHouseCreating.questionForFloorsCount));
        if (entrance.getFloorsCount() <= 0 || entrance.getFloorsCount() > 163)
            do {
                System.out.println(ConstantsForHouseCreating.invalidInputError);
                entrance.setFloorsCount(SecuredNumbersScanner.EnteringInfoCheck(ConstantsForHouseCreating.questionForFloorsCount));
            } while (entrance.getFloorsCount() <= 0 || entrance.getFloorsCount() > 163);
        for (int i = 0; i < entrance.getFloorsCount(); i++) {
            if (i == 0) {
                entrance.addFloor(FloorCreator.createFloor()); // first floor creating
            } else {
                // copying first floor by copy constructor
                entrance.addFloor(new Floor(entrance.getFloors().get(0)));
            }
        }
        return entrance;
    }
}
