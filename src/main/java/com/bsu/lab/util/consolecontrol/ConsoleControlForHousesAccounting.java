package com.bsu.lab.util.consolecontrol;

import com.bsu.lab.model.House;
import com.bsu.lab.util.SecuredNumbersScanner;
import com.bsu.lab.util.consolecontrol.actions.*;
import com.bsu.lab.util.constants.ConstantsForConsoleControl;
import com.bsu.lab.util.constants.GeneralConstants;


import java.util.ArrayList;
import java.util.List;

public class ConsoleControlForHousesAccounting {
    public void start() {
        List<House> arrayOfHouses = new ArrayList<>();
        int mainAction;
        do {
            mainAction = SecuredNumbersScanner.EnteringInfoCheck(ConstantsForConsoleControl.QUESTION_OF_MAIN_ACTION);
            System.out.println(GeneralConstants.SEPARATION);
            switch (mainAction) {
                case 1: // add house
                    AddHouseAction.execute(arrayOfHouses);// house adding
                    break;
                case 2: // get info about existing house
                    GetHouseInfoAction.execute(arrayOfHouses);
                    break;
                case 3: // remove house
                    RemoveHouseAction.execute(arrayOfHouses);
                    break;
                case 4: // compare houses
                    CompareHousesAction.execute(arrayOfHouses);
                    break;
                case 5: // compare flats
                    CompareFlatsAction.execute(arrayOfHouses);
                    break;
                case 6: // exit
                    System.exit(0);
                    break;
                default:
                    System.out.println("Введено неверное значение. Повторите попытку");
                    break;
            }
        } while (mainAction != 6);
    }

}

