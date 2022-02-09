package com.bsu.lab.AccountingSystem.util.consolecontrol.action;

import com.bsu.lab.AccountingSystem.constant.ConstantsForConsoleControl;
import com.bsu.lab.AccountingSystem.constant.GeneralConstants;
import com.bsu.lab.AccountingSystem.model.Flat;
import com.bsu.lab.AccountingSystem.model.House;
import com.bsu.lab.AccountingSystem.service.FlatService;
import com.bsu.lab.AccountingSystem.service.HouseService;
import com.bsu.lab.AccountingSystem.util.consolecontrol.action.subaction.AvailabilityOfHousesCheck;
import com.bsu.lab.AccountingSystem.util.input.SecuredNumbersScanner;
import com.bsu.lab.AccountingSystem.util.input.consolecontrol.action.InputForFlatNumber;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class GetHouseInfoAction {
    private final HouseService houseService;
    private final FlatService flatService;
    private final InputForFlatNumber inputForFlatNumber;
    private final SecuredNumbersScanner securedNumbersScanner;

    @Autowired
    public GetHouseInfoAction(
            HouseService houseService,
            FlatService flatService,
            InputForFlatNumber inputForFlatNumber,
            SecuredNumbersScanner securedNumbersScanner
    ) {
        this.houseService = houseService;
        this.flatService = flatService;
        this.inputForFlatNumber = inputForFlatNumber;
        this.securedNumbersScanner = securedNumbersScanner;
    }


    public void execute(@NotNull Set<House> setOfHouses) {
        if (AvailabilityOfHousesCheck.check(setOfHouses)) return;

        int houseNumber = securedNumbersScanner.enteringInfoCheckForHouseNumber(setOfHouses); // house number entering
        // house for additional action
        House houseForAdditionalAction = houseService.getHouseByNumberFromSetOfHouses(setOfHouses, houseNumber);
        int additionalAction;
        do {
            additionalAction =
                    securedNumbersScanner.enteringInfoCheck(ConstantsForConsoleControl.QUESTION_OF_ADDITIONAL_ACTION);
            System.out.println(GeneralConstants.SEPARATION);
            switch (additionalAction) {
                case 1: // printing all information about house
                    System.out.println(houseService.allHouseInfoToString(houseForAdditionalAction));
                    break;
                case 2: // print information about flat by number in this house
                    int flatNumber = inputForFlatNumber.input(houseForAdditionalAction);
                    Flat flatForCheckInfo = houseService.getFlatByNumber(houseForAdditionalAction, flatNumber);
                    System.out.println(flatService.flatInfoToString(houseForAdditionalAction, flatForCheckInfo));
                    break;
                case 3: // exit to menu
                    break;
            }
        } while (additionalAction != 3);
    }
}
