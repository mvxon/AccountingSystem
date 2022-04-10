package com.bsu.lab.AccountingSystem.util.consolecontrol.actions.realization;

import com.bsu.lab.AccountingSystem.service.HouseService;
import com.bsu.lab.AccountingSystem.util.consolecontrol.availability_of_houses_check.AvailabilityOfHousesCheck;
import com.bsu.lab.AccountingSystem.util.consolecontrol.inputs.SecuredNumbersScanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RemoveHouseAction {
    private final HouseService houseService;
    private final SecuredNumbersScanner securedNumbersScanner;
    private final AvailabilityOfHousesCheck availabilityOfHousesCheck;

    @Autowired
    public RemoveHouseAction(HouseService houseService,
                             SecuredNumbersScanner securedNumbersScanner,
                             AvailabilityOfHousesCheck availabilityOfHousesCheck
    ) {
        this.houseService = houseService;
        this.securedNumbersScanner = securedNumbersScanner;
        this.availabilityOfHousesCheck = availabilityOfHousesCheck;
    }


    public void execute() {
        if (availabilityOfHousesCheck.check()) {
            return;
        }
        int houseNumber = securedNumbersScanner.enteringInfoCheckForHouseNumber();
        houseService.deleteHouseByHouseNumber(houseNumber);
        System.out.println("Дом успешно удалён!");

    }
}
