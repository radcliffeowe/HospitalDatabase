package edu.group16;

import edu.group16.database.AdmissionDAO;
import edu.group16.database.DatabaseManager;
import edu.group16.database.DoctorDAO;
import edu.group16.database.PatientDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Reporting {

    public static void main(String[] args) {

        String username = args[0]; //Oracle username
        String password = args[1]; //Oracle password

        System.out.println("1- Report Patients Basic Information");
        System.out.println("2- Report Doctors Basic Information");
        System.out.println("3- Report Admissions Information");
        System.out.println("4- Update Admissions Payment");

        Scanner userInput = new Scanner(System.in);

        DatabaseManager.initialize(username, password);

        PatientDAO patientDAO = new PatientDAO();
        DoctorDAO doctorDAO = new DoctorDAO();
        AdmissionDAO admissionDAO = new AdmissionDAO();

        if(args.length > 2){

            switch(args[2]){
                case "1":
                    System.out.print("Enter Patient SSN: ");
                    int ssn = userInput.nextInt();
                    try {
                        ResultSet patientInfo = patientDAO.get(ssn);
                        patientDAO.report(patientInfo);
                        patientInfo.close();
                    }catch(SQLException e){
                        System.out.println("Query failed");
                    }
                    break;
                case "2":
                    System.out.print("Enter Doctor ID: ");
                    int doctorId = userInput.nextInt();
                    try {
                        ResultSet doctorInfo = doctorDAO.get(doctorId);
                        doctorDAO.report(doctorInfo);
                        doctorInfo.close();
                    }catch(SQLException e){
                        System.out.println("Query failed");
                    }
                    break;
                case "3":
                    break;
                case "4":
                    System.out.println("Enter Admission Number: ");
                    int aNumber = userInput.nextInt();
                    System.out.println("Enter the new total payment: ");
                    double newPayment = userInput.nextDouble();
                    String updateStatement = String.format("Update Admission set totalPayment = " + newPayment + "where visitCode = " + aNumber);
                    try {
                        DatabaseManager.getInstance().runUpdate(updateStatement);
                    } catch (SQLException e) {
                        System.out.println("Update failed");
                    }
                    break;
                default:
                    break;
            }
        }
        try {
            DatabaseManager.getInstance().closeConnection();
        }catch (SQLException e){
            System.out.println("Couldn't close database connection");
        }
        userInput.close();
    }
}
