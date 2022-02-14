package com.bsu.lab.AccountingSystem.util.consolecontrol.action.realization;

import com.bsu.lab.AccountingSystem.constant.GeneralConstants;
import com.bsu.lab.AccountingSystem.model.Flat;
import com.bsu.lab.AccountingSystem.model.House;
import com.bsu.lab.AccountingSystem.repository.HouseRepository;
import com.bsu.lab.AccountingSystem.service.FlatService;
import com.bsu.lab.AccountingSystem.service.HouseService;
import com.bsu.lab.AccountingSystem.util.comparer.FlatsComparer;
import com.bsu.lab.AccountingSystem.util.consolecontrol.availability_of_houses_check.AvailabilityOfHousesCheck;
import com.bsu.lab.AccountingSystem.util.input.consolecontrol.action.comparing.InputForFlatCompareNumbers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompareFlatsAction {
    private final HouseService houseService;
    private final InputForFlatCompareNumbers inputForFlatCompareNumbers;
    private final FlatService flatService;
    private final AvailabilityOfHousesCheck availabilityOfHousesCheck;
    private final HouseRepository houseRepository;

    @Autowired
    public CompareFlatsAction(
            HouseService houseService,
            InputForFlatCompareNumbers inputForFlatCompareNumbers,
            FlatService flatService,
            AvailabilityOfHousesCheck availabilityOfHousesCheck,
            HouseRepository houseRepository) {
        this.houseService = houseService;
        this.inputForFlatCompareNumbers = inputForFlatCompareNumbers;
        this.flatService = flatService;
        this.availabilityOfHousesCheck = availabilityOfHousesCheck;
        this.houseRepository = houseRepository;
    }

    public void execute() {
        if (availabilityOfHousesCheck.check()) return;

        if (houseRepository.count() == 1 && houseService.getFlatsCount(houseRepository.findAll().get(0)) == 1) {
            System.out.println("Недостаточно квартир для сравнения. Добавьте еще дома");
            return;
        }

        inputForFlatCompareNumbers.input();
        House houseForCompare1 = houseRepository
                .findByHouseNumber(inputForFlatCompareNumbers.getFirstHouseNumberForFlatsCompare());
        Flat flatForCompare1 = houseService.getFlatByNumber(houseForCompare1,
                inputForFlatCompareNumbers.getFirstFlatCompareNumber());

        House houseForCompare2 = houseRepository
                .findByHouseNumber(inputForFlatCompareNumbers.getSecondHouseNumberForFlatsCompare());
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
