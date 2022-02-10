package com.bsu.lab.AccountingSystem.util.consolecontrol.action;

import com.bsu.lab.AccountingSystem.model.House;
import com.bsu.lab.AccountingSystem.repository.HouseRepository;
import com.bsu.lab.AccountingSystem.service.HouseService;
import com.bsu.lab.AccountingSystem.util.consolecontrol.action.subaction.AvailabilityOfHousesCheck;
import com.bsu.lab.AccountingSystem.util.input.SecuredNumbersScanner;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class RemoveHouseAction {
    private final HouseRepository houseRepository;
    private final HouseService houseService;
    private final SecuredNumbersScanner securedNumbersScanner;

    @Autowired
    public RemoveHouseAction(HouseRepository houseRepository,
                             HouseService houseService,
                             SecuredNumbersScanner securedNumbersScanner) {
        this.houseRepository = houseRepository;
        this.houseService = houseService;
        this.securedNumbersScanner = securedNumbersScanner;
    }


    public void execute(@NotNull Set<House> setOfHouses) {
        if (AvailabilityOfHousesCheck.check(setOfHouses)) {
            return;
        }
        int houseNumber = securedNumbersScanner.enteringInfoCheckForHouseNumber(setOfHouses);
        House houseForDeleting = houseService.getHouseByNumberFromSetOfHouses(setOfHouses, houseNumber);
        houseRepository.delete(houseForDeleting);
        setOfHouses.remove(houseForDeleting); // house removing
        houseService.getUsedHouseNumbers().remove(houseForDeleting.getHouseNumber());
        System.out.println("Дом успешно удалён!");
    }
}
