package ru.joxaren;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class BatchStatement {

    public static void main(String[] args) throws SQLException {

        final long flightId = 9L;

        String sql1 = "DELETE FROM flight WHERE id = " + flightId;
        String sql2 = "DELETE FROM ticket WHERE flight_id = " + flightId;


        Connection connection = null;
        Statement statement = null;


        try{
            connection = ConnectionManager.get();
            connection.setAutoCommit(false);
            statement = connection.createStatement();

            statement.addBatch(sql2);
            statement.addBatch(sql1);

            int[] result = statement.executeBatch();

            connection.commit();

        }catch (Exception e){
            if(connection != null) {
                connection.rollback();
            }
            throw e;
        }finally {
            if(connection != null)
                connection.close();
            if (statement != null)
                statement.close();
        }
    }


}
