package jdbctests;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        String dbUrl = "jdbc:oracle:thin:@52.203.145.13:1521:xe";
        String dbUsername = "hr";
        String dbPassword = "hr";

        // create connection
        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);


        Connection con2 = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);

        // create statement object
        Statement statement = connection.createStatement();
        // run query and get the result in resultset object
        ResultSet resultSet = statement.executeQuery("Select * from regions");

        //move point to first row
        // it returns a boolean value
        // satir satir takip eder
        resultSet.next();

        // getting information with column index name
        System.out.println(resultSet.getString("region_name"));
        // getting information with column index (starts from 1)
        System.out.println(resultSet.getString(2));

        // 1-Europe
        System.out.println(resultSet.getInt(1)+" - "+ resultSet.getString("region_name"));


        while (resultSet.next()){
            System.out.println(resultSet.getString(1) + " - " + resultSet.getString("region_name"));
        }

        // close all connections

        resultSet.close();
        statement.close();
        connection.close();

    }
}
