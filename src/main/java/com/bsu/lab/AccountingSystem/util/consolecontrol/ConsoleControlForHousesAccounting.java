
package com.bsu.lab.AccountingSystem.util.consolecontrol;

import com.bsu.lab.AccountingSystem.util.consolecontrol.action.*;
import com.bsu.lab.AccountingSystem.util.consolecontrol.action.realization.*;
import com.bsu.lab.AccountingSystem.util.input.consolecontrol.action.InputForActionSelection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ConsoleControlForHousesAccounting {
    private final InputForActionSelection inputForActionSelection;
    private final AddHouseAction addHouseAction;
    private final GetHouseInfoAction getHouseInfoAction;
    private final RemoveHouseAction removeHouseAction;
    private final CompareHousesAction compareHousesAction;
    private final CompareFlatsAction compareFlatsAction;

    @Autowired
    public ConsoleControlForHousesAccounting(
            AddHouseAction addHouseAction,
            GetHouseInfoAction getHouseInfoAction,
            RemoveHouseAction removeHouseAction,
            CompareHousesAction compareHousesAction,
            CompareFlatsAction compareFlatsAction,
            InputForActionSelection inputForActionSelection) {
        this.addHouseAction = addHouseAction;
        this.getHouseInfoAction = getHouseInfoAction;
        this.removeHouseAction = removeHouseAction;
        this.compareHousesAction = compareHousesAction;
        this.compareFlatsAction = compareFlatsAction;
        this.inputForActionSelection = inputForActionSelection;
    }

    public void start() {
        MainAction mainAction = null;
        while (mainAction != MainAction.EXIT) {
            mainAction = inputForActionSelection.inputForMainAction();
            switch (mainAction) {

                case ADD_HOUSE:
                    addHouseAction.execute();
                    break;
                case GET_HOUSE_INFO:
                    getHouseInfoAction.execute();
                    break;
                case REMOVE_HOUSE:
                    removeHouseAction.execute();
                    break;
                case COMPARE_HOUSES:
                    compareHousesAction.execute();
                    break;
                case COMPARE_FLATS:
                    compareFlatsAction.execute();
                    break;
                case EXIT:
                    break;
            }
        }
    }

}


