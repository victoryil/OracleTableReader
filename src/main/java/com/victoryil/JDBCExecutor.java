package com.victoryil;

import lombok.AllArgsConstructor;
import oracle.jdbc.pool.OracleDataSource;

import java.sql.*;

@AllArgsConstructor
public class JDBCExecutor implements Runnable{
  Configuration configuration;

    @Override
    public void run() {

        OracleDataSource ods = null;
        try {
            ods = new OracleDataSource();
            ods.setURL(configuration.getUrl()); // jdbc:oracle:thin@//[nombre de host]:[puerto]/[nombre de servicio BD]
            ods.setUser(configuration.getUser());
            ods.setPassword(configuration.getPassword());
            Connection conn = ods.getConnection();

            PreparedStatement stmt = conn.prepareStatement(configuration.getQuery());
            ResultSet rslt = stmt.executeQuery();
            ResultSetMetaData rsmd = rslt.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            // Print column names
            for (int i = 1; i <= columnsNumber; i++) {
                if (i > 1) System.out.print(",");
                String columnName = rsmd.getColumnName(i);
                System.out.print(columnName);
            }
            System.out.println();
            while (rslt.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    if (i > 1) System.out.print(",");
                    String columnValue = rslt.getString(i);
                    System.out.print(columnValue);
                }
                System.out.println();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
