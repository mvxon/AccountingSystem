package com.bsu.lab.AccountingSystem.util.consolecontrol.action.realization;

import com.bsu.lab.AccountingSystem.constant.GeneralConstants;
import com.bsu.lab.AccountingSystem.model.House;
import com.bsu.lab.AccountingSystem.repository.HouseRepository;
import com.bsu.lab.AccountingSystem.service.HouseService;
import com.bsu.lab.AccountingSystem.util.comparer.HousesComparer;
import com.bsu.lab.AccountingSystem.util.consolecontrol.availability_of_houses_check.AvailabilityOfHousesCheck;
import com.bsu.lab.AccountingSystem.util.input.consolecontrol.action.comparing.InputForHouseCompareNumbers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompareHousesAction {
    private final HouseService houseService;
    private final InputForHouseCompareNumbers inputForHouseCompareNumbers;
    private final AvailabilityOfHousesCheck availabilityOfHousesCheck;
    private final HouseRepository houseRepository;

    @Autowired
    public CompareHousesAction(
            HouseService houseService,
            InputForHouseCompareNumbers inputForHouseCompareNumbers,
            AvailabilityOfHousesCheck availabilityOfHousesCheck,
            HouseRepository houseRepository) {
        this.houseService = houseService;
        this.inputForHouseCompareNumbers = inputForHouseCompareNumbers;
        this.availabilityOfHousesCheck = availabilityOfHousesCheck;
        this.houseRepository = houseRepository;
    }

    public void execute() {
        if (availabilityOfHousesCheck.check()) return;

        if (houseRepository.count() < 2) {
            System.out.println("Недостаточно домов в списке для сравнения. Добавьте еще дома");
            return;
        }

        inputForHouseCompareNumbers.input();
        int houseCompareNumber1 = inputForHouseCompareNumbers.getFirstHouseCompareNumber();
        int houseCompareNumber2 = inputForHouseCompareNumbers.getSecondHouseCompareNumber();

        House houseForCompare1 = houseRepository.findByHouseNumber(houseCompareNumber1);
        House houseForCompare2 = houseRepository.findByHouseNumber(houseCompareNumber2);

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

