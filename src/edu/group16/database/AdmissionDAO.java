package edu.group16.database;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class AdmissionDAO {

    public void update(String updateStatement) throws SQLException {
        DatabaseManager.getInstance().runUpdate(updateStatement);
    }


    public ResultSet get(int visitCode) throws SQLException {
        String query = "Select * from Admission where visitCode = " + visitCode;
        return DatabaseManager.getInstance().runQuery(query);
    }

    public ResultSet getRoomInfo(int visitCode) throws SQLException {
        String query = "Select * from StayIn where visitCode = "+ visitCode;
        return DatabaseManager.getInstance().runQuery(query);
    }

    public ResultSet getDoctorInfo(int visitCode) throws SQLException{
        String query = "Select distinct doctorId from Examine where visitCode = "+ visitCode;
        return DatabaseManager.getInstance().runQuery(query);
    }

    public void report(int visitCode) throws SQLException {
        ResultSet admissionInfo = get(visitCode);
        ResultSet roomInfo = getRoomInfo(visitCode);
        ResultSet doctorInfo = getDoctorInfo(visitCode);
        DateFormat admissionDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        while(admissionInfo.next()){
            System.out.println(String.format("Admission Number: %d", admissionInfo.getInt("visitCode")));
            System.out.println(String.format("Patient SSN: %d", admissionInfo.getInt("patientSsn")));
            Date admissionDate = admissionInfo.getDate("admissionDate");
            String formattedDate = admissionDateFormat.format(admissionDate);
            System.out.println(String.format("Admission date (start date): %s", formattedDate));
            System.out.println(String.format("Total Payment: %d", admissionInfo.getInt("totalPayment")));
        }
        admissionInfo.close();
        System.out.println("Rooms:");
        while(roomInfo.next()){
            Date fromDate = roomInfo.getDate("startDate");
            String formattedFromDate = admissionDateFormat.format(fromDate);
            Date toDate = roomInfo.getDate("endDate");
            String formattedToDate = admissionDateFormat.format(toDate);
            System.out.println(String.format("RoomNum: %d\tFromDate: %s\tToDate: %s", roomInfo.getInt("roomNum"), formattedFromDate, formattedToDate));
        }
        roomInfo.close();
        System.out.println("Doctors that examined the patient in this admission:");
        while(doctorInfo.next()){
            System.out.println(String.format("Doctor ID: %d", doctorInfo.getInt("doctorId")));
        }
        doctorInfo.close();
    }
}
