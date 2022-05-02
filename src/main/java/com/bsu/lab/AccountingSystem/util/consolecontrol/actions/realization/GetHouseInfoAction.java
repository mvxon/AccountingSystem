package com.bsu.lab.AccountingSystem.util.consolecontrol.actions.realization;

import com.bsu.lab.AccountingSystem.domain.Flat;
import com.bsu.lab.AccountingSystem.domain.House;
import com.bsu.lab.AccountingSystem.domain.Resident;
import com.bsu.lab.AccountingSystem.service.FlatService;
import com.bsu.lab.AccountingSystem.service.HouseService;
import com.bsu.lab.AccountingSystem.service.ResidentService;
import com.bsu.lab.AccountingSystem.util.consolecontrol.actions.AdditionalAction;
import com.bsu.lab.AccountingSystem.util.consolecontrol.availability_of_houses_check.AvailabilityOfHousesCheck;
import com.bsu.lab.AccountingSystem.util.consolecontrol.inputs.SecuredNumbersScanner;
import com.bsu.lab.AccountingSystem.util.consolecontrol.inputs.consolecontrol.actions.InputForActionSelection;
import com.bsu.lab.AccountingSystem.util.consolecontrol.inputs.consolecontrol.actions.InputForFlatNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class GetHouseInfoAction {
    private final HouseService houseService;
    private final FlatService flatService;
    private final InputForFlatNumber inputForFlatNumber;
    private final SecuredNumbersScanner securedNumbersScanner;
    private final InputForActionSelection inputForActionSelection;
    private final AvailabilityOfHousesCheck availabilityOfHousesCheck;

    @Autowired
    public GetHouseInfoAction(
            HouseService houseService,
            @Lazy FlatService flatService,
            InputForFlatNumber inputForFlatNumber,
            SecuredNumbersScanner securedNumbersScanner,
            InputForActionSelection inputForActionSelection,
            AvailabilityOfHousesCheck availabilityOfHousesCheck
    ) {
        this.houseService = houseService;
        this.flatService = flatService;
        this.inputForFlatNumber = inputForFlatNumber;
        this.securedNumbersScanner = securedNumbersScanner;
        this.inputForActionSelection = inputForActionSelection;
        this.availabilityOfHousesCheck = availabilityOfHousesCheck;
    }


    public void execute() {
        if (availabilityOfHousesCheck.check()) return;

        int houseNumber = securedNumbersScanner.enteringInfoCheckForHouseNumber();
        House houseForAdditionalAction = houseService.getHouseByHouseNumber(houseNumber);

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
                    String flatInfo = flatService.flatInfoToString(houseForAdditionalAction, flatForCheckInfo);
                    System.out.println(flatInfo);
                    break;
                case EXIT_TO_MAIN_MENU:
                    break;
            }
        }
    }
}
