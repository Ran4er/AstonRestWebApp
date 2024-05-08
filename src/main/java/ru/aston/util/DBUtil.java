package ru.aston.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    
	private static final String URL = "jdbc:postgresql://localhost:5432/bookstore";
	private static final String USER = "postgres";
	private static final String PASSWORD = "toor36547";
	
    //private static Connection connection;

	public static Connection getConnection() {
        Connection connection;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        return connection;
    }

    public static void closeConnection(Connection connection) throws SQLException{
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
