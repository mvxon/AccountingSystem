package com.bsu.lab.AccountingSystem.util.input.consolecontrol.action;

import com.bsu.lab.AccountingSystem.constant.ConstantsForConsoleControl;
import com.bsu.lab.AccountingSystem.constant.GeneralConstants;
import com.bsu.lab.AccountingSystem.util.consolecontrol.action.AdditionalAction;
import com.bsu.lab.AccountingSystem.util.consolecontrol.action.MainAction;
import com.bsu.lab.AccountingSystem.util.input.SecuredNumbersScanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class InputForActionSelection {

    @Autowired
    SecuredNumbersScanner securedNumbersScanner;

    public MainAction inputForMainAction() {
        int mainAction = 0;
        while (mainAction < 1 || mainAction > 6) {
            mainAction = securedNumbersScanner.enteringInfoCheck(ConstantsForConsoleControl.QUESTION_OF_MAIN_ACTION);
            if (mainAction < 1 || mainAction > 6) {
                System.out.println("Введено неверное значение. Повторите попытку");
            }
        }
        System.out.println(GeneralConstants.SEPARATION);
        MainAction action = MainAction.values()[mainAction - 1];
        return action;
    }

    public AdditionalAction inputForAdditionalAction() {
        int additionalAction = 0;
        while (additionalAction < 1 || additionalAction > 3) {
            additionalAction = securedNumbersScanner
                    .enteringInfoCheck(ConstantsForConsoleControl.QUESTION_OF_ADDITIONAL_ACTION);
            if (additionalAction < 1 || additionalAction > 3) {
                System.out.println("Введено неверное значение. Повторите попытку");
            }
        }
        System.out.println(GeneralConstants.SEPARATION);
        AdditionalAction action = AdditionalAction.values()[additionalAction - 1];
        return action;
    }

}
