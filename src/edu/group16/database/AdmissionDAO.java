package edu.group16.database;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AdmissionDAO {

    public void update(String updateStatement) throws SQLException {
        DatabaseManager.getInstance().runUpdate(updateStatement);
    }


    public ResultSet get(int visitCode) throws SQLException {
        String query = "Select * from Admission where visitCode = " + visitCode;
        return DatabaseManager.getInstance().runQuery(query);
    }

    public void report(ResultSet rset) throws SQLException {
        while(rset.next()) {
            System.out.println(String.format("Admission Number: %d", rset.getInt("visitCode")));
            System.out.println(String.format("Total Payment: %d", rset.getInt("totalPayment")));
        }

    }
}
