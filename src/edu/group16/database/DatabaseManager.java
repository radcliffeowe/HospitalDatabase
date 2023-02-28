package edu.group16.database;

import java.sql.*;

public class DatabaseManager {

    protected String dbUrl = "jdbc:oracle:thin:@csorcl.cs.wpi.edu:1521:orcl";
    private Connection dbConnection;

    private static DatabaseManager databaseManager;

    public DatabaseManager(){

        dbConnection = connectDatabase();
    }


    public static DatabaseManager getInstance(){
        if(databaseManager == null){
            databaseManager = new DatabaseManager();
        }
        return databaseManager;
    }

    public void closeConnection() throws SQLException {
        dbConnection.close();
    }

    private Connection connectDatabase() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e){
            System.out.println("Oracle JDBC Driver registration failed");
            System.exit(1);
        }
        Connection tempConn = null;
        try{
            tempConn = DriverManager.getConnection(dbUrl, System.getenv("USERNAME"), System.getenv("PASSWORD"));
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

    public void runUpdate(String statement) throws SQLException {
        Statement stm = dbConnection.createStatement();
        try{
            stm.executeUpdate(statement);
        } catch(SQLException e){
            System.out.println("Update failed");
        }
        stm.close();

    }

















}
