//comment to check git conflicts
package com.postop.dao;

import com.postop.dao.interfaces.PatientDao;
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
        String sql = "SELECT * FROM Patient_details";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            Patient patient;
            while(resultSet.next()){
                patient = new Patient();
                patient.setName(resultSet.getString("patient_name"));
                patient.setEmail(resultSet.getString("patient_email"));
                patient.setSex(resultSet.getString("patient_sex"));
                patient.setSsn(resultSet.getString("patient_ssn"));
                patient.setAddress(resultSet.getString("patient_address"));

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
    public Patient getPatientByEmail(String email) {

        Patient patient = new Patient();
        String sql = "SELECT * FROM Patient_details WHERE patient_email = \'" + email +"\'";

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while(resultSet.next()){
                patient.setName(resultSet.getString("patient_name"));
                patient.setEmail(resultSet.getString("patient_email"));
                patient.setSex(resultSet.getString("patient_sex"));
                patient.setSsn(resultSet.getString("patient_ssn"));
                patient.setAddress(resultSet.getString("patient_address"));
            }
            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patient;
    }

    @Override
    public boolean updatePatient(Patient patient) {

        String sql = "UPDATE Patient_details SET patient_name = \'" + patient.getName() + "\' , patient_sex = \'" + patient.getSex() + "\' WHERE patient_email = \'" + patient.getEmail() + "\'";

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            return false;
        }

        return true;

    }

    @Override
    public boolean addPatient(Patient patient) {
        String sql = "INSERT INTO Patient_details(patient_email, patient_ssn, patient_name, patient_sex)" + "VALUES (?,?,?,?)";

        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(3,patient.getName());
            preparedStatement.setString(1,patient.getEmail());
            preparedStatement.setString(4,patient.getSex());
            preparedStatement.setString(2,patient.getSsn());

            preparedStatement.execute();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean deletePatient(Patient patient) {
        String sql = "DELETE FROM Patient_details where patient_email = \'" + patient.getEmail() +"\'";
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }


    @Override
    public List<Patient> getPatientByName(String s) {
        return null;
    }
}
