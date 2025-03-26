package ru.joxaren;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransactionStatement {

    public static void main(String[] args) throws SQLException {

        String sql1 = "DELETE FROM flight WHERE id = ?";
        String sql2 = "DELETE FROM ticket WHERE flight_id = ?";
        final long flightId = 9L;

        Connection connection = null;
        PreparedStatement statement1 = null;
        PreparedStatement statement2 = null;

        try{
            connection = ConnectionManager.get();
            statement1 = connection.prepareStatement(sql1);
            statement2 = connection.prepareStatement(sql2);

            connection.setAutoCommit(false);

            statement1.setLong(1, flightId);
            statement2.setLong(1, flightId);

            statement2.executeUpdate();
            statement1.executeUpdate();

            connection.commit();

        }catch (Exception e){
            if(connection != null) {
                connection.rollback();
            }
            throw e;
        }finally {
            if(connection != null)
                connection.close();
            if (statement1 != null)
                statement1.close();
            if (statement2 != null)
                statement2.close();
        }
    }
}
