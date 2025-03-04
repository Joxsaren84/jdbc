package ru.joxaren;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DDLStatement {

    public static void main(String[] args) {

        String sql = "CREATE DATABASE gamedb;";

        try(Connection connection = ConnectionManager.open()){
            Statement statement = connection.createStatement();
            Boolean result = statement.execute(sql);
            System.out.println(result);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

    }

}
