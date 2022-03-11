package com.bsu.lab.AccountingSystem.util.consolecontrol.actions.realization;

import com.bsu.lab.AccountingSystem.repository.HouseRepository;
import com.bsu.lab.AccountingSystem.util.consolecontrol.availability_of_houses_check.AvailabilityOfHousesCheck;
import com.bsu.lab.AccountingSystem.util.consolecontrol.inputs.SecuredNumbersScanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RemoveHouseAction {
    private final HouseRepository houseRepository;
    private final SecuredNumbersScanner securedNumbersScanner;
    private final AvailabilityOfHousesCheck availabilityOfHousesCheck;

    @Autowired
    public RemoveHouseAction(HouseRepository houseRepository,
                             SecuredNumbersScanner securedNumbersScanner,
                             AvailabilityOfHousesCheck availabilityOfHousesCheck
    ) {
        this.houseRepository = houseRepository;
        this.securedNumbersScanner = securedNumbersScanner;
        this.availabilityOfHousesCheck = availabilityOfHousesCheck;
    }


    public void execute() {
        if (availabilityOfHousesCheck.check()) {
            return;
        }
        int houseNumber = securedNumbersScanner.enteringInfoCheckForHouseNumber();
        houseRepository.deleteHouseByHouseNumber(houseNumber);
        System.out.println("Дом успешно удалён!");
    }
}
