package com.bsu.lab.AccountingSystem.consolecontrol.actions.realization;

import com.bsu.lab.AccountingSystem.consolecontrol.constants.GeneralConstants;
import com.bsu.lab.AccountingSystem.domain.House;
import com.bsu.lab.AccountingSystem.service.HouseService;
import com.bsu.lab.AccountingSystem.consolecontrol.comparer.HousesComparer;
import com.bsu.lab.AccountingSystem.consolecontrol.availability_of_houses_check.AvailabilityOfHousesCheck;
import com.bsu.lab.AccountingSystem.consolecontrol.inputs.consolecontrol.actions.comparing.InputForHouseCompareNumbers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompareHousesAction {
    private final HouseService houseService;
    private final InputForHouseCompareNumbers inputForHouseCompareNumbers;
    private final AvailabilityOfHousesCheck availabilityOfHousesCheck;

    @Autowired
    public CompareHousesAction(
            HouseService houseService,
            InputForHouseCompareNumbers inputForHouseCompareNumbers,
            AvailabilityOfHousesCheck availabilityOfHousesCheck) {
        this.houseService = houseService;
        this.inputForHouseCompareNumbers = inputForHouseCompareNumbers;
        this.availabilityOfHousesCheck = availabilityOfHousesCheck;

    }

    public void execute() {
        if (availabilityOfHousesCheck.check()) return;

        if (houseService.getHousesCount() < 2) {
            System.out.println("Недостаточно домов в списке для сравнения. Добавьте еще дома");
            return;
        }

        inputForHouseCompareNumbers.input();
        int houseCompareNumber1 = inputForHouseCompareNumbers.getFirstHouseCompareNumber();
        int houseCompareNumber2 = inputForHouseCompareNumbers.getSecondHouseCompareNumber();

        House houseForCompare1 = houseService.getHouseByHouseNumber(houseCompareNumber1);
        House houseForCompare2 = houseService.getHouseByHouseNumber(houseCompareNumber2);

        System.out.print(GeneralConstants.SEPARATION + "\nДом 1" + houseService.allHouseInfoToString(houseForCompare1));

        System.out.print("Дом 2" + houseService.allHouseInfoToString(houseForCompare2));


        if (houseForCompare1.compareTo(houseForCompare2) == 0) {
            System.out.println("Дома одинаковы!");
        } else {
            HousesComparer housesDifferingParameters =
                    new HousesComparer(houseService);
            housesDifferingParameters.setHousesForComparing(houseForCompare1, houseForCompare2);
            housesDifferingParameters.printDifferingParameters();
        }
    }
}

