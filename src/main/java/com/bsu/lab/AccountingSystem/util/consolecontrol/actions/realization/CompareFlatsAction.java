package com.bsu.lab.AccountingSystem.util.consolecontrol.actions.realization;

import com.bsu.lab.AccountingSystem.constants.GeneralConstants;
import com.bsu.lab.AccountingSystem.domain.Flat;
import com.bsu.lab.AccountingSystem.domain.House;
import com.bsu.lab.AccountingSystem.service.FlatService;
import com.bsu.lab.AccountingSystem.service.HouseService;
import com.bsu.lab.AccountingSystem.util.comparer.FlatsComparer;
import com.bsu.lab.AccountingSystem.util.consolecontrol.availability_of_houses_check.AvailabilityOfHousesCheck;
import com.bsu.lab.AccountingSystem.util.consolecontrol.inputs.consolecontrol.actions.comparing.InputForFlatCompareNumbers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompareFlatsAction {
    private final HouseService houseService;
    private final InputForFlatCompareNumbers inputForFlatCompareNumbers;
    private final FlatService flatService;
    private final AvailabilityOfHousesCheck availabilityOfHousesCheck;

    @Autowired
    public CompareFlatsAction(
            HouseService houseService,
            InputForFlatCompareNumbers inputForFlatCompareNumbers,
            FlatService flatService,
            AvailabilityOfHousesCheck availabilityOfHousesCheck
    ) {
        this.houseService = houseService;
        this.inputForFlatCompareNumbers = inputForFlatCompareNumbers;
        this.flatService = flatService;
        this.availabilityOfHousesCheck = availabilityOfHousesCheck;
    }

    public void execute() {
        if (availabilityOfHousesCheck.check()) return;

        if (houseService.getHousesCount() == 1 && houseService.getFlatsCount(houseService.getAllHouses().get(0)) == 1) {
            System.out.println("Недостаточно квартир для сравнения. Добавьте еще дома");
            return;
        }

        inputForFlatCompareNumbers.input();
        House houseForCompare1 = houseService
                .getHouseByHouseNumber(inputForFlatCompareNumbers.getFirstHouseNumberForFlatsCompare());
        Flat flatForCompare1 = houseService.getFlatByNumber(houseForCompare1,
                inputForFlatCompareNumbers.getFirstFlatCompareNumber());

        House houseForCompare2 = houseService
                .getHouseByHouseNumber(inputForFlatCompareNumbers.getSecondHouseNumberForFlatsCompare());
        Flat flatForCompare2 = houseService.getFlatByNumber(houseForCompare2,
                inputForFlatCompareNumbers.getSecondFlatCompareNumber());

        System.out.println(GeneralConstants.SEPARATION);
        System.out.print("Квартира 1" + flatService.flatInfoToString(houseForCompare1, flatForCompare1));

        System.out.print("Квартира 2" + flatService.flatInfoToString(houseForCompare2, flatForCompare2));

        if (flatForCompare1.equals(flatForCompare2)) {
            System.out.println("Квартиры одинаковы!");
        } else {
            FlatsComparer flatsDifferingParameters =
                    new FlatsComparer(flatService);
            flatsDifferingParameters.setFlatsForComparing(flatForCompare1, flatForCompare2);
            flatsDifferingParameters.printDifferingParameters();
        }
    }

}
