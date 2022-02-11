
package com.bsu.lab.AccountingSystem.util.consolecontrol;

import com.bsu.lab.AccountingSystem.model.House;
import com.bsu.lab.AccountingSystem.util.consolecontrol.action.*;
import com.bsu.lab.AccountingSystem.util.consolecontrol.action.realization.*;
import com.bsu.lab.AccountingSystem.util.database.LoadHousesFromDatabaseAction;
import com.bsu.lab.AccountingSystem.util.input.consolecontrol.action.InputForActionSelection;
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
    private final InputForActionSelection inputForActionSelection;

    @Autowired
    public ConsoleControlForHousesAccounting(
            AddHouseAction addHouseAction,
            LoadHousesFromDatabaseAction loadHousesFromDatabaseAction,
            GetHouseInfoAction getHouseInfoAction,
            RemoveHouseAction removeHouseAction,
            CompareHousesAction compareHousesAction,
            CompareFlatsAction compareFlatsAction,
            InputForActionSelection inputForActionSelection) {
        this.addHouseAction = addHouseAction;
        this.loadHousesFromDatabaseAction = loadHousesFromDatabaseAction;
        this.getHouseInfoAction = getHouseInfoAction;
        this.removeHouseAction = removeHouseAction;
        this.compareHousesAction = compareHousesAction;
        this.compareFlatsAction = compareFlatsAction;
        this.inputForActionSelection = inputForActionSelection;
    }

    public void start() {
        Set<House> setOfHouses = new HashSet<>();
        loadHousesFromDatabaseAction.execute(setOfHouses);
        MainAction mainAction = null;
        while (mainAction != MainAction.EXIT) {
            mainAction = inputForActionSelection.inputForMainAction();
            switch (mainAction) {

                case ADD_HOUSE:
                    addHouseAction.execute(setOfHouses);
                    break;
                case GET_HOUSE_INFO:
                    getHouseInfoAction.execute(setOfHouses);
                    break;
                case REMOVE_HOUSE:
                    removeHouseAction.execute(setOfHouses);
                    break;
                case COMPARE_HOUSES:
                    compareHousesAction.execute(setOfHouses);
                    break;
                case COMPARE_FLATS:
                    compareFlatsAction.execute(setOfHouses);
                    break;
                case EXIT:
                    break;
            }
        }
    }

}


