package com.bsu.lab.util.database.connection;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {
    private static DataBaseConnection dataBaseConnection;
    private static Connection connection;

    private DataBaseConnection() {
        try {
            String user = "postgres";
            String password = "1";
            String url = "jdbc:postgresql://localhost:5432/accounting_system";
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        if(dataBaseConnection == null && connection == null){
            dataBaseConnection = new DataBaseConnection();
        }
        return connection;
    }

    public static void closeConnection() throws SQLException {
        connection.close();
    }
}
