package ru.joxaren;

import org.postgresql.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args){

   // Class <Driver> driverClass = Driver.class;
        try (Connection connection = ConnectionManager.open()){
            System.out.println(connection.getTransactionIsolation());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}