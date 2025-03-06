package ru.joxaren;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectStatement {

    public static void main(String[] args) {


        String sql = "SELECT * FROM ticket;";

        try(Connection connection = ConnectionManager.open()){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++)
                System.out.print(resultSet.getMetaData().getColumnName(i) + " ");
            System.out.println();

            while (resultSet.next()){
                System.out.print(resultSet.getLong(1) + " ");
                System.out.print(resultSet.getString(2) + " ");
                System.out.print(resultSet.getString(3) + " ");
                System.out.print(resultSet.getLong(4) + " ");
                System.out.print(resultSet.getString(5) + " ");
                System.out.println(resultSet.getDouble(6));
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

    }

}
