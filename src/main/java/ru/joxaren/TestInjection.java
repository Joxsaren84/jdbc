package ru.joxaren;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TestInjection {
    public static void main(String[] args) {
        Long flightId = 2L;
        System.out.println(getTicketsByFlightId(flightId));
        LocalDateTime start = LocalDateTime.of(2020, 3, 10, 0, 0);
        LocalDateTime end = LocalDateTime.now();
        System.out.println(flightBetween(start, end));

    }


    private static List<Long> flightBetween(LocalDateTime start, LocalDateTime end){
        String sql = "select id from flight where departure_date between ? and ?";

        List<Long> result = new ArrayList<>();

        try(Connection connection = ConnectionManager.open()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setTimestamp(1, Timestamp.valueOf(start));
            preparedStatement.setTimestamp(2, Timestamp.valueOf(end));

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                result.add(resultSet.getObject("id", Long.class));
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

        return result;

    }

    private static List<Long> getTicketsByFlightId(Long flightId){
        String sql = "select id from ticket where flight_id = ?";

        List<Long> result = new ArrayList<>();


        try(Connection connection = ConnectionManager.open()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setFetchSize(50);
            preparedStatement.setQueryTimeout(10);
            preparedStatement.setMaxRows(100);
            preparedStatement.setLong(1, flightId);

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
