package edu.group16.database.patient;

import edu.group16.database.DatabaseManager;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PatientDAO {

    public void add(Patient patient){

    }

    public void delete(Patient patient){

    }

    public void update(Patient patient){

    }

    public ResultSet get(int ssn) throws SQLException {
        String query = "Select * from Patient where ssn = " + ssn;
        return DatabaseManager.getInstance().runQuery(query);
    }

    public void report(ResultSet rset) throws SQLException {
        while(rset.next()) {
            System.out.println(String.format("Patient SSN: %d", rset.getInt("ssn")));
            System.out.println(String.format("Patient First Name: %s", rset.getString("fName")));
            System.out.println(String.format("Patient Last Name: %s", rset.getString("lName")));
            System.out.println(String.format("Patient Address: %s", rset.getString("address")));
            System.out.println(String.format("Patient Telephone number: %s", rset.getString("telNum")));
        }
    }
}
