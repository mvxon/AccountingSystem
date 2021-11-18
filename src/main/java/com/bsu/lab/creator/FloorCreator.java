package com.bsu.lab.creator;

import com.bsu.lab.house.Floor;
import com.bsu.lab.util.constants.ConstantsForHouseCreating;
import com.bsu.lab.util.SecuredNumbersScanner;
import org.jetbrains.annotations.NotNull;

public class FloorCreator {
    public static @NotNull Floor createFloor(){
        Floor floor = new Floor();
        Floor.setFlatsPerFloor(SecuredNumbersScanner.EnteringInfoCheck(ConstantsForHouseCreating.questionForFlatsPerFloor));
        if (floor.getFlatsPerFloor()<= 0 ||floor.getFlatsPerFloor() > 20)
            do {
                System.out.println("Введено неверное значение...Повторите ввод");
                Floor.setFlatsPerFloor(SecuredNumbersScanner.EnteringInfoCheck(ConstantsForHouseCreating.questionForFlatsPerFloor));
            } while (floor.getFlatsPerFloor()<= 0 || floor.getFlatsPerFloor() > 20);
            for (int i = 0; i < floor.getFlatsPerFloor(); i++) {
                floor.addFlat(FlatCreator.createFlat()); // flats creating
        }
            return floor;
    }
}
