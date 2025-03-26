package ru.joxaren;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SqlInjectionStatement {
    public static void main(String[] args) {
        String flightId = "2; CREATE TABLE IF NOT EXISTS info (id SERIAL PRIMARY KEY, data TEXT NOT NULL);"; //можно дописать что угодно
        List<Long> result = GetTicketsByFlightId(flightId);
        System.out.println(result);
    }

    private static List<Long> GetTicketsByFlightId(String flightId) {
        String sql = "SELECT id from ticket WHERE flight_id = %s".formatted(flightId);

        List<Long> result = new ArrayList<>();

        try(Connection connection = ConnectionManager.get()){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                result.add(resultSet.getObject("id", Long.class));
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

        return result;
    }
}
