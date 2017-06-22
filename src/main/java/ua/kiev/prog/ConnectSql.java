package ua.kiev.prog;

import java.sql.*;

public class ConnectSql {
    private static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/apartment";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Vfhbyf1975";
    private static final String DB_NAME="CREATE TABLE IF NOT EXISTS apartments (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
            "district VARCHAR(30) NOT NULL, address VARCHAR(60) NOT NULL, area DOUBLE NOT NULL, " +
            "number_of_rooms TINYINT NOT NULL, price DOUBLE NOT NULL, date DATE);";

    public static Connection connectDB() {
        Connection connection=null;
        try {
            connection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
        } catch (SQLException e){
            e.printStackTrace();
        }
        return connection;
    }

    public static void initDB() {

        try {
            Statement statement = connectDB().createStatement();
            statement.execute(DB_NAME);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
