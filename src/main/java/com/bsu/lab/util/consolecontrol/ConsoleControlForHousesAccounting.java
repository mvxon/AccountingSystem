
package com.bsu.lab.util.consolecontrol;

import com.bsu.lab.model.House;
import com.bsu.lab.util.database.HibernateUtil;
import com.bsu.lab.util.database.LoadHousesFromDatabaseAction;
import com.bsu.lab.util.input.SecuredNumbersScanner;
import com.bsu.lab.util.consolecontrol.action.*;
import com.bsu.lab.constant.ConstantsForConsoleControl;
import com.bsu.lab.constant.GeneralConstants;


import java.util.*;


public class ConsoleControlForHousesAccounting {
    public void start() {
        Set<House> arrayOfHouses = new HashSet<>();
        LoadHousesFromDatabaseAction.execute(arrayOfHouses);
        int mainAction;
        do {
            mainAction = SecuredNumbersScanner.enteringInfoCheck(ConstantsForConsoleControl.QUESTION_OF_MAIN_ACTION);
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
                    HibernateUtil.closeConnection();
                    break;
                default:
                    System.out.println("Введено неверное значение. Повторите попытку");
                    break;
            }
        } while (mainAction != 6);
    }

}


