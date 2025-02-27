package ru.joxaren;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransactionRunner {
    public static void main(String[] args) {

        String deleteFlightSql = "DELETE from flight where ?";
        long flightId = 9;

        try(Connection connection = ConnectionManager.open();
            PreparedStatement preparedStatement = connection.prepareStatement(deleteFlightSql);
        ){
            preparedStatement.setLong(1, flightId);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            new RuntimeException(e);
        }
    }
}
