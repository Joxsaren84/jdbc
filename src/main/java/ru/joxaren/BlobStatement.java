package ru.joxaren;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BlobStatement {

    public static void main(String[] args) throws SQLException, IOException {
        //blob - bytea
        //clob - Text
        saveImage();
        getImage();

    }


    private static void getImage() throws SQLException, IOException {
        String sql = "SELECT image from aircraft where id = ?";

        try(Connection connection = ConnectionManager.get();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1, 2);
            ResultSet result = preparedStatement.executeQuery();
            if(result.next()){
                byte [] image = result.getBytes("image");
                Files.write(Path.of("src\\main","resources\\new_boing.jpg"), image);
            }

        }
    }

    private static void saveImage() throws SQLException, IOException {

        String sql = "UPDATE aircraft SET image = ? WHERE id = 2";

        try(Connection connection = ConnectionManager.get();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){
                preparedStatement.setBytes(1, Files.readAllBytes(Path.of("src\\main","resources\\boing777.jpg")));
                preparedStatement.executeUpdate();

        }
    }

}
