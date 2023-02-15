package edu.group16.database;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DoctorDAO {

    public ResultSet get(int doctorId) throws SQLException {
        String query = "Select Doctor.employeeId, fName, lName, gender, graduatedFrom, specialty from Doctor, Employee where Doctor.employeeId = Employee.employeeId AND Doctor.employeeId = " + doctorId;
        return DatabaseManager.getInstance().runQuery(query);
    }

    public void report(ResultSet rset) throws SQLException {
        while(rset.next()) {
            System.out.println(String.format("Doctor ID: %d", rset.getInt("employeeId")));
            System.out.println(String.format("Doctor First Name: %s", rset.getString("fName")));
            System.out.println(String.format("Doctor Last Name: %s", rset.getString("lName")));
            System.out.println(String.format("Doctor Gender: %s", rset.getString("gender")));
            System.out.println(String.format("Doctor Graduated From: %s", rset.getString("graduatedFrom")));
            System.out.println(String.format("Doctor Specialty: %s", rset.getString("specialty")));
        }
    }
}
