
package com.bsu.lab.AccountingSystem.util.consolecontrol;

import com.bsu.lab.AccountingSystem.constant.ConstantsForConsoleControl;
import com.bsu.lab.AccountingSystem.constant.GeneralConstants;
import com.bsu.lab.AccountingSystem.model.House;
import com.bsu.lab.AccountingSystem.util.consolecontrol.action.*;
import com.bsu.lab.AccountingSystem.util.database.LoadHousesFromDatabaseAction;
import com.bsu.lab.AccountingSystem.util.input.SecuredNumbersScanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;


@Service
public class ConsoleControlForHousesAccounting {
    private final AddHouseAction addHouseAction;
    private final LoadHousesFromDatabaseAction loadHousesFromDatabaseAction;
    private final GetHouseInfoAction getHouseInfoAction;
    private final RemoveHouseAction removeHouseAction;
    private final CompareHousesAction compareHousesAction;
    private final CompareFlatsAction compareFlatsAction;
    private final SecuredNumbersScanner securedNumbersScanner;

    @Autowired
    public ConsoleControlForHousesAccounting(
            AddHouseAction addHouseAction,
            LoadHousesFromDatabaseAction loadHousesFromDatabaseAction,
            GetHouseInfoAction getHouseInfoAction,
            RemoveHouseAction removeHouseAction,
            CompareHousesAction compareHousesAction,
            CompareFlatsAction compareFlatsAction,
            SecuredNumbersScanner securedNumbersScanner) {
        this.addHouseAction = addHouseAction;
        this.loadHousesFromDatabaseAction = loadHousesFromDatabaseAction;
        this.getHouseInfoAction = getHouseInfoAction;
        this.removeHouseAction = removeHouseAction;
        this.compareHousesAction = compareHousesAction;
        this.compareFlatsAction = compareFlatsAction;
        this.securedNumbersScanner = securedNumbersScanner;
    }

    public void start() {
        Set<House> setOfHouses = new HashSet<>();
        loadHousesFromDatabaseAction.execute(setOfHouses);
        int mainAction;
        do {
            mainAction = securedNumbersScanner.enteringInfoCheck(ConstantsForConsoleControl.QUESTION_OF_MAIN_ACTION);
            System.out.println(GeneralConstants.SEPARATION);
            switch (mainAction) {
                case 1: // add house
                    addHouseAction.execute(setOfHouses);// house adding
                    break;
                case 2: // get info about existing house
                    getHouseInfoAction.execute(setOfHouses);
                    break;
                case 3: // remove house
                    removeHouseAction.execute(setOfHouses);
                    break;
                case 4: // compare houses
                    compareHousesAction.execute(setOfHouses);
                    break;
                case 5: // compare flats
                    compareFlatsAction.execute(setOfHouses);
                    break;
                case 6: // exit
                    break;
                default:
                    System.out.println("Введено неверное значение. Повторите попытку");
                    break;
            }
        } while (mainAction != 6);
    }

}


