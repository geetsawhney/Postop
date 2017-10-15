package com.postop.dao;

import com.postop.model.Patient;
import com.postop.utils.DbConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientDaoImpl implements PatientDao {

    Connection connection;

    public PatientDaoImpl(){
        connection = DbConnection.getConnection();
    }

    @Override
    public List<Patient> getAllPatients() {
        List<Patient> allPatients = new ArrayList<>();
        String sql = "SELECT * FROM Patient";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            Patient patient;
            while(resultSet.next()){
                patient = new Patient();
                patient.setPatientId(Integer.parseInt(resultSet.getString("id")));
                patient.setName(resultSet.getString("fname"));
                patient.setEmail(resultSet.getString("email"));
                patient.setSex(resultSet.getString("sex"));
                patient.setSsn(resultSet.getString("ssn"));

                allPatients.add(patient);
            }
            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allPatients;
    }

    @Override
    public Patient getPatientById(int patientId) {

        Patient patient = new Patient();
        String sql = "SELECT * FROM Patient WHERE id = " + patientId;

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while(resultSet.next()){
                patient.setPatientId(Integer.parseInt(resultSet.getString("id")));
                patient.setName(resultSet.getString("fname"));
                patient.setEmail(resultSet.getString("email"));
                patient.setSex(resultSet.getString("sex"));
                patient.setSsn(resultSet.getString("ssn"));
            }
            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patient;
    }

    @Override
    public void updatePatient(Patient patient) {

        int patientId = patient.getPatientId();
        String sql = "UPDATE Patient SET fname = \'" + patient.getName() + "\' , sex = \'" + patient.getSex() + "\' WHERE id = " + patientId;

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void addPatient(Patient patient) {
        String sql = "INSERT INTO Patient(email, ssn, fname, lname, sex)" + "VALUES (?,?,?,?,?)";

        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,"gvijh92@gmail.com");
            preparedStatement.setString(2,"112255441");
            preparedStatement.setString(3,"Gitika");
            preparedStatement.setString(4,"Vijh");
            preparedStatement.setString(5,"F");

            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Patient> getPatientByName(String s) {
        return null;
    }
}
