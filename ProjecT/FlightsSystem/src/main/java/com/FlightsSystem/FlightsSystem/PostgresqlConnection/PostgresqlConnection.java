package com.FlightsSystem.FlightsSystem.PostgresqlConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class PostgresqlConnection  {

    private static PostgresqlConnection single_instance = null;
    private static Connection connection=null;
    private static Statement statement=null;
    private String url = "//localhost:5432/FLIGHTS";
        private PostgresqlConnection()
        {

        }

        public static PostgresqlConnection getInstance()
        {
            if (single_instance == null)
                single_instance = new PostgresqlConnection();

            return single_instance;
        }

    public Connection getConnection() {
        try {
            Class.forName("org.postgresql.Driver");

            connection= DriverManager.getConnection
                    ("jdbc:postgresql:"+url,"postgres","rannrann4545");
            System.out.println("Done!!");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return connection;
    }

    public Statement getStatement() {
        try {
            statement=connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statement;
    }
}
