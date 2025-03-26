package ru.joxaren;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetMetadataStatement {

    public static void main(String[] args) {
        try {
            checkMetaData();
        }finally {
            ConnectionManager.closePool();
        }

    }

    private static void checkMetaData() {
        try (Connection connection = ConnectionManager.get()) {
            DatabaseMetaData databaseMetaData = connection.getMetaData();
            ResultSet catalogs = databaseMetaData.getCatalogs();

            while (catalogs.next()) {
                System.out.println("Catalog:");
                System.out.println(catalogs.getString(1));
                System.out.println("  schemas:");
                ResultSet schemas = databaseMetaData.getSchemas();
                while (schemas.next()) {
                    System.out.println("   " + schemas.getString(1));

                    ResultSet getTables = databaseMetaData.getTables(null, null, "%", null);
                    while (getTables.next()) {
                        System.out.println(getTables.getString("TABLE_NAME"));
                    }
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
