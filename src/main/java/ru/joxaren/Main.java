package ru.joxaren;

import org.postgresql.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args){

        String sql = "CREATE TABLE IF NOT EXISTS info" +
                "(id SERIAL PRIMARY KEY," +
                "data TEXT NOT NULL);";

   // Class <Driver> driverClass = Driver.class;
        try (Connection connection = ConnectionManager.open();
            Statement statement = connection.createStatement()){

            boolean executeResult = statement.execute(sql);
            System.out.println(executeResult);
            //System.out.println(connection.getTransactionIsolation());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}