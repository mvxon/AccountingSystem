package com.bsu.lab.util.consolecontrol.action;

import com.bsu.lab.dao.HouseDAO;
import com.bsu.lab.model.Flat;
import com.bsu.lab.model.House;
import com.bsu.lab.service.FlatService;
import com.bsu.lab.service.HouseService;
import com.bsu.lab.util.consolecontrol.action.subaction.NoAvailableHousesCheck;
import com.bsu.lab.util.input.SecuredNumbersScanner;
import com.bsu.lab.constant.ConstantsForConsoleControl;
import com.bsu.lab.constant.GeneralConstants;
import com.bsu.lab.util.input.consolecontrol.action.inputForFlatNumber;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class GetHouseInfoAction {
    public static void execute(@NotNull List<House> arrayOfHouses) {
        if (NoAvailableHousesCheck.check()) return;

        int houseNumber = SecuredNumbersScanner.EnteringInfoCheckForHouseNumber(arrayOfHouses); // house number entering
        House houseForAdditionalAction = arrayOfHouses.get(houseNumber - 1); // house for additional action
        int additionalAction;
        do {
            additionalAction =
                    SecuredNumbersScanner.EnteringInfoCheck(ConstantsForConsoleControl.QUESTION_OF_ADDITIONAL_ACTION);
            System.out.println(GeneralConstants.SEPARATION);
            switch (additionalAction) {
                case 1: // printing all information about house
                    System.out.println(houseForAdditionalAction);
                    break;
                case 2: // print information about flat by number in this house
                    int flatNumber = inputForFlatNumber.input(houseForAdditionalAction);
                    Flat flatForCheckInfo = HouseService.getFlat(houseForAdditionalAction, flatNumber);
                    System.out.println(FlatService.flatInfoToString(houseForAdditionalAction, flatForCheckInfo));
                    break;
                case 3: // exit to menu
                    break;
            }
        } while (additionalAction != 3);
    }
}
