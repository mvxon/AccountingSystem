package com.bsu.lab.creator;

import com.bsu.lab.model.Entrance;
import com.bsu.lab.model.House;
import com.bsu.lab.service.HouseService;
import com.bsu.lab.util.constants.ConstantsForHouseCreating;
import org.jetbrains.annotations.NotNull;

import static com.bsu.lab.util.SecuredNumbersScanner.EnteringInfoCheck;

public class HouseCreator {
    public static @NotNull House CreateHouse() {
        House house = new House();
        int entrancesCount = EnteringInfoCheck(ConstantsForHouseCreating.QUESTION_FOR_ENTRANCES_COUNT);
        if (entrancesCount <= 0 || entrancesCount > 20) {
            do {
                System.out.println(ConstantsForHouseCreating.INVALID_INPUT_ERROR);
                entrancesCount = EnteringInfoCheck(ConstantsForHouseCreating.QUESTION_FOR_ENTRANCES_COUNT);
            } while (entrancesCount <= 0 || entrancesCount > 20);
        }
        for (int i = 0; i < entrancesCount; i++) {
            if (i == 0) {
                HouseService.addEntrance(house, EntranceCreator.createEntrance()); // creating first entrance
            } else {
                // copying first entrance by copy constructor
                HouseService.addEntrance(house, new Entrance(house.getEntrances().get(0)));
            }
        }
        System.out.println("Дом номер " + house.getHouseNumber() + " успешно добавлен!");
        return house;
    }
}
