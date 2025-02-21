package ru.joxaren;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TestInjection {
    public static void main(String[] args) {
        String flightId = "2 or 1 = 1" ;
        System.out.println(getTicketsByFlightId(flightId));

    }

    private static List<Long> getTicketsByFlightId(String flightId){
        String sql = "select id from ticket where flight_id = " + flightId;

        List<Long> result = new ArrayList<>();

        try(Connection connection = ConnectionManager.open()){
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
