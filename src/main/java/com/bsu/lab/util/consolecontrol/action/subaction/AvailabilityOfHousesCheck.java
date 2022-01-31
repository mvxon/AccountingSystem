package com.bsu.lab.util.consolecontrol.action.subaction;

import com.bsu.lab.dao.HouseDAO;


public class AvailabilityOfHousesCheck {
    public static boolean check() {
        if (HouseDAO.getHousesCount() == 0){
            System.out.println("Домов нет");
            return true;
        }
        else return false;
    }
}
