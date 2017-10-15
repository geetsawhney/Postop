package com.postop.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private static Connection connection = null;

    public static Connection getConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            if(connection == null){
                connection = DriverManager.getConnection("jdbc:mysql://sql9.freemysqlhosting.net/sql9199471", "sql9199471", "xJAiiCjcAW");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void closeConnection(){
        try {
            if(connection != null)
                connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

