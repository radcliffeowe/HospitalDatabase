package edu.group16.database;

import java.sql.*;

public class DatabaseManager {

    protected String dbUrl = "jdbc:oracle:thin:@csorcl.cs.wpi.edu:1521:orcl";
    private Connection dbConnection;

    private static DatabaseManager databaseManager;

    public DatabaseManager(String username, String password){
        dbConnection = connectDatabase(username, password);
    }

    public static void initialize(String username, String password){
        databaseManager = new DatabaseManager(username, password);
    }

    public static DatabaseManager getInstance(){
        return databaseManager;
    }

    public void closeConnection() throws SQLException {
        dbConnection.close();
    }

    private Connection connectDatabase(String username, String password) {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e){
            System.out.println("Oracle JDBC Driver registration failed");
            System.exit(1);
        }
        Connection tempConn = null;
        try{
            tempConn = DriverManager.getConnection(dbUrl, username, password);
        } catch (SQLException e){
            System.out.println("Database Connection failed");
            System.exit(1);
        }
        return tempConn;
    }

    public ResultSet runQuery(String query) throws SQLException {
        Statement stm = dbConnection.createStatement();
        try{
            return stm.executeQuery(query);
        }catch(SQLException e){
            System.out.println("Query failed");
        }
        stm.close();
        return null;
    }
}
