package ru.joxaren;

import org.postgresql.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args){

        /*
        String sql = "CREATE TABLE IF NOT EXISTS info" +
                "(id SERIAL PRIMARY KEY," +
                "data TEXT NOT NULL);";
         */

        String sql = "insert into info (data) values" +
                " ('test1')," +
                " ('test2')," +
                " ('test3')," +
                " ('test4');"+
        " insert into info (data) values" +
                " ('test1')," +
                " ('test2')," +
                " ('test3')," +
                " ('test4');";

   // Class <Driver> driverClass = Driver.class;
        try (Connection connection = ConnectionManager.open();
            Statement statement = connection.createStatement()){

            int executeUpdate = statement.executeUpdate(sql);
            System.out.println(executeUpdate);
            System.out.println(statement.getUpdateCount());
            //System.out.println(connection.getTransactionIsolation());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}