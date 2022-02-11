package com.bsu.lab.AccountingSystem.util.consolecontrol.action.realization;

import com.bsu.lab.AccountingSystem.constant.GeneralConstants;
import com.bsu.lab.AccountingSystem.model.Flat;
import com.bsu.lab.AccountingSystem.model.House;
import com.bsu.lab.AccountingSystem.service.FlatService;
import com.bsu.lab.AccountingSystem.service.HouseService;
import com.bsu.lab.AccountingSystem.util.consolecontrol.action.AdditionalAction;
import com.bsu.lab.AccountingSystem.util.consolecontrol.availability_of_houses_check.AvailabilityOfHousesCheck;
import com.bsu.lab.AccountingSystem.util.input.SecuredNumbersScanner;
import com.bsu.lab.AccountingSystem.util.input.consolecontrol.action.InputForActionSelection;
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
    private final InputForActionSelection inputForActionSelection;

    @Autowired
    public GetHouseInfoAction(
            HouseService houseService,
            FlatService flatService,
            InputForFlatNumber inputForFlatNumber,
            SecuredNumbersScanner securedNumbersScanner,
            InputForActionSelection inputForActionSelection) {
        this.houseService = houseService;
        this.flatService = flatService;
        this.inputForFlatNumber = inputForFlatNumber;
        this.securedNumbersScanner = securedNumbersScanner;
        this.inputForActionSelection = inputForActionSelection;
    }


    public void execute(@NotNull Set<House> setOfHouses) {
        if (AvailabilityOfHousesCheck.check(setOfHouses)) return;

        int houseNumber = securedNumbersScanner.enteringInfoCheckForHouseNumber(setOfHouses);
        House houseForAdditionalAction = houseService.getHouseByNumberFromSetOfHouses(setOfHouses, houseNumber);

        AdditionalAction additionalAction = null;
        while (additionalAction != AdditionalAction.EXIT_TO_MAIN_MENU) {
            additionalAction = inputForActionSelection.inputForAdditionalAction();
            switch (additionalAction) {
                case PRINT_ALL_HOUSE_INFO: // printing all information about house
                    System.out.println(houseService.allHouseInfoToString(houseForAdditionalAction));
                    break;
                case PRINT_INFO_ABOUT_FLAT:
                    int flatNumber = inputForFlatNumber.input(houseForAdditionalAction);
                    Flat flatForCheckInfo = houseService.getFlatByNumber(houseForAdditionalAction, flatNumber);
                    System.out.println(flatService.flatInfoToString(houseForAdditionalAction, flatForCheckInfo));
                    break;
                case EXIT_TO_MAIN_MENU:
                    break;
            }
        }
    }
}
