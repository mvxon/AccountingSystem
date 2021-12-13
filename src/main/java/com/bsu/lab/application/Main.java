package com.bsu.lab.application;

import com.bsu.lab.util.consolecontrol.ConsoleControlForHousesAccounting;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        ConsoleControlForHousesAccounting control = new ConsoleControlForHousesAccounting();
        control.start();
    }

}
