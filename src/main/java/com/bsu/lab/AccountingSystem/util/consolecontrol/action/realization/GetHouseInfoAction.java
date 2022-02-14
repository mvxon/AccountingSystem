package com.bsu.lab.AccountingSystem.util.consolecontrol.action.realization;

import com.bsu.lab.AccountingSystem.model.Flat;
import com.bsu.lab.AccountingSystem.model.House;
import com.bsu.lab.AccountingSystem.repository.HouseRepository;
import com.bsu.lab.AccountingSystem.service.FlatService;
import com.bsu.lab.AccountingSystem.service.HouseService;
import com.bsu.lab.AccountingSystem.util.consolecontrol.action.AdditionalAction;
import com.bsu.lab.AccountingSystem.util.consolecontrol.availability_of_houses_check.AvailabilityOfHousesCheck;
import com.bsu.lab.AccountingSystem.util.input.SecuredNumbersScanner;
import com.bsu.lab.AccountingSystem.util.input.consolecontrol.action.InputForActionSelection;
import com.bsu.lab.AccountingSystem.util.input.consolecontrol.action.InputForFlatNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetHouseInfoAction {
    private final HouseService houseService;
    private final HouseRepository houseRepository;
    private final FlatService flatService;
    private final InputForFlatNumber inputForFlatNumber;
    private final SecuredNumbersScanner securedNumbersScanner;
    private final InputForActionSelection inputForActionSelection;
    private final AvailabilityOfHousesCheck availabilityOfHousesCheck;

    @Autowired
    public GetHouseInfoAction(
            HouseService houseService,
            HouseRepository houseRepository, FlatService flatService,
            InputForFlatNumber inputForFlatNumber,
            SecuredNumbersScanner securedNumbersScanner,
            InputForActionSelection inputForActionSelection,
            AvailabilityOfHousesCheck availabilityOfHousesCheck
    ) {
        this.houseService = houseService;
        this.houseRepository = houseRepository;
        this.flatService = flatService;
        this.inputForFlatNumber = inputForFlatNumber;
        this.securedNumbersScanner = securedNumbersScanner;
        this.inputForActionSelection = inputForActionSelection;
        this.availabilityOfHousesCheck = availabilityOfHousesCheck;
    }


    public void execute() {
        if (availabilityOfHousesCheck.check()) return;

        int houseNumber = securedNumbersScanner.enteringInfoCheckForHouseNumber();
        House houseForAdditionalAction = houseRepository.findByHouseNumber(houseNumber);

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
