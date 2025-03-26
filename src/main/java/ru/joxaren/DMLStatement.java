package ru.joxaren;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DMLStatement {

    public static void main(String[] args) {
        String sql = "insert into info (data) values" +
                " ('test1')," +
                " ('test2')," +
                " ('test3')," +
                " ('test4');";

        try(Connection connection = ConnectionManager.get()){
            Statement statement = connection.createStatement();
            int result = statement.executeUpdate(sql);
            System.out.println(result);
            System.out.println(statement.getUpdateCount());
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

}
