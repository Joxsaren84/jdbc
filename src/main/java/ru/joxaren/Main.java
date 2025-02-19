package ru.joxaren;

import org.postgresql.Driver;

import java.sql.*;

public class Main {
    public static void main(String[] args){

        /*
        String sql = "CREATE TABLE IF NOT EXISTS info" +
                "(id SERIAL PRIMARY KEY," +
                "data TEXT NOT NULL);";
         */

        /*
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
      */

        String sql = "SELECT * FROM ticket";

   // Class <Driver> driverClass = Driver.class;
        try (Connection connection = ConnectionManager.open();
            Statement statement = connection.createStatement()){
             /*
            int executeUpdate = statement.executeUpdate(sql);
            System.out.println(executeUpdate);
            System.out.println(statement.getUpdateCount());
            //System.out.println(connection.getTransactionIsolation());*/

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()){
                System.out.print(resultSet.getInt("id") + " ");
                System.out.print(resultSet.getDouble("cost") + " ");
                System.out.print(resultSet.getString("passenger_name") + "\n");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}