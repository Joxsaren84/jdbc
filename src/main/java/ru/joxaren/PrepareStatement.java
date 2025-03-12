package ru.joxaren;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PrepareStatement {

    public static void main(String[] args) {
        Long flightId = 2L;
        Long flightId2 = 3L;
        List<Long> result = GetTicketsByFlightId(flightId, flightId2);
        System.out.println(result);
    }

    private static List<Long> GetTicketsByFlightId(Long flightId, Long flightId2) {
        String sql = "SELECT id from ticket WHERE flight_id BETWEEN ? AND ?";

        List<Long> result = new ArrayList<>();

        try(Connection connection = ConnectionManager.open();){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setLong(1, flightId);
            preparedStatement.setLong(2, flightId2);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                result.add(resultSet.getObject("id", Long.class));
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

        return result;
    }


}
