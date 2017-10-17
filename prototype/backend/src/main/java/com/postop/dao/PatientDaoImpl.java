//comment to check git conflicts
package com.postop.dao;

import com.postop.controller.PostOpController;
import com.postop.dao.interfaces.PatientDao;
import com.postop.model.Patient;
import com.postop.utils.DbConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientDaoImpl implements PatientDao {

    Connection connection;
    private final Logger logger = LoggerFactory.getLogger(PatientDaoImpl.class);

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
                patient = populateDetails(resultSet);

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
        logger.info("PatientDaoImp getPatientByEmail");
        String sql = "SELECT * FROM Patient_details WHERE patient_email = \'" + email +"\'";
        return getPatient(sql);
    }

    @Override
    public Patient getPatientByDeviceId(String id) {
        String sql = "SELECT * FROM Patient_details WHERE device_id = \'" + id +"\'";
        return getPatient(sql);
    }

    @Override
    public boolean updatePatient(Patient patient) {

        String sql = "UPDATE Patient_details SET patient_name = \'" + patient.getName() + "\' , device_id = \'" + patient.getDeviceId() + "\', patient_sex = \'" + patient.getSex() + "\' WHERE patient_email = \'" + patient.getEmail() + "\'";

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

    public Patient populateDetails(ResultSet resultSet){
        logger.info("PatientDaoImpl populateDetails");
        Patient patient = new Patient();
        try {
            while(resultSet.next()){
                try {
                    patient.setName(resultSet.getString("patient_name"));
                    patient.setSex(resultSet.getString("patient_sex"));
                    patient.setSsn(resultSet.getString("patient_ssn"));
                    patient.setDob(resultSet.getString("patient_dob"));
                    patient.setEmail(resultSet.getString("patient_email"));
                    patient.setAddress(resultSet.getString("patient_address"));
                    patient.setPhone(resultSet.getString("patient_phone"));
                    patient.setHospitalReason(resultSet.getString("patient_reason_hospital_visit"));
                    patient.setUtiVisitCount(Integer.parseInt(resultSet.getString("patient_uti_visit_count")));
                    patient.setHasCatheter(Boolean.parseBoolean(resultSet.getString("patient_catheter_usage")));
                    patient.setDiabetes(Boolean.parseBoolean(resultSet.getString("diabetes")));
                    patient.setDeviceId(resultSet.getString("device_id"));
                    patient.setLastVisitDate(resultSet.getString("last_visit_date"));


                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patient;
    }

    public Patient getPatient(String sql){
        logger.info("PatientDaoImpl getPatient");
        Patient patient = new Patient();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSetEmail = statement.executeQuery(sql);

            patient = populateDetails(resultSetEmail);

            resultSetEmail.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return patient;
    }
}