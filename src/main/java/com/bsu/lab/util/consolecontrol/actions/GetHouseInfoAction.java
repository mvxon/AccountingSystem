package com.bsu.lab.util.consolecontrol.actions;

import com.bsu.lab.house.Flat;
import com.bsu.lab.house.House;
import com.bsu.lab.service.FlatService;
import com.bsu.lab.service.HouseService;
import com.bsu.lab.util.SecuredNumbersScanner;
import com.bsu.lab.util.constants.ConstantsForConsoleControl;
import com.bsu.lab.util.constants.GeneralConstants;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class GetHouseInfoAction {
    public static void execute(@NotNull List<House> arrayOfHouses) {
        if (arrayOfHouses.isEmpty()) {
            System.out.println("Домов нет");
            return;
        }
        int houseNumber = SecuredNumbersScanner.EnteringInfoCheckForHouseNumber(arrayOfHouses); // house number entering
        House houseForAdditionalAction = arrayOfHouses.get(houseNumber - 1); // house for additional action
        int additionalAction;
        do {
            additionalAction =
                    SecuredNumbersScanner.EnteringInfoCheck(ConstantsForConsoleControl.questionOfAdditionalAction);
            System.out.println(GeneralConstants.separation);
            switch (additionalAction) {
                case 1: // printing all information about house
                    System.out.println(houseForAdditionalAction);
                    break;
                case 2: // print information about flat by number in this house
                    int flatNumber;
                    String flatNumberQuestion;
                    if (HouseService.getFlatsCount(houseForAdditionalAction) != 1) {
                        flatNumberQuestion = "Введите номер нужной квартиры(1-";
                        flatNumberQuestion += HouseService.getFlatsCount(houseForAdditionalAction) + "): ";
                    } else {
                        flatNumberQuestion = "Введите номер нужной квартиры(1): ";
                    }
                    flatNumber = SecuredNumbersScanner.EnteringInfoCheck(flatNumberQuestion);
                    if (flatNumber > HouseService.getFlatsCount(houseForAdditionalAction) || flatNumber <= 0) {
                        do {
                            System.out.println("Введен номер несуществующей квартиры...Повторите ввод");
                            flatNumber = SecuredNumbersScanner.EnteringInfoCheck(flatNumberQuestion);
                        } while (flatNumber > HouseService.getFlatsCount(houseForAdditionalAction) ||
                                flatNumber <= 0);
                    }
                    Flat flatForCheckInfo = HouseService.getFlat(houseForAdditionalAction, flatNumber);
                    System.out.println(FlatService.flatInfoToString(houseForAdditionalAction, flatForCheckInfo));
                    break;
                case 3: // exit to menu
                    break;

            }
        } while (additionalAction != 3);
    }
}
