//comment to check git conflicts
package com.postop.dao;

import com.postop.dao.interfaces.PatientDao;
import com.postop.exceptions.IllegalSqlException;
import com.postop.exceptions.PatientNotFoundException;
import com.postop.model.Patient;
import com.postop.utils.DbConnector;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientDaoImpl implements PatientDao {

    Connection connection;
    private final Logger logger = LoggerFactory.getLogger(PatientDaoImpl.class);

    public PatientDaoImpl() {
        connection = DbConnector.getConnection();
    }


    @Override
    public List<Patient> getAllPatients() {
        List<Patient> allPatients = new ArrayList<>();
        String sql = "SELECT * FROM \"Patient\"";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            Patient patient;
            while (resultSet.next()) {

                patient = new Patient();
                patient.setName(resultSet.getString("name"));
                patient.setSex(resultSet.getString("sex"));
                patient.setSsn(resultSet.getString("ssn"));
                patient.setDob(resultSet.getDate("dob"));
                patient.setEmail(resultSet.getString("email"));
                patient.setAddress(resultSet.getString("address"));
                patient.setPhone(resultSet.getString("phone"));
                patient.setHospitalVisitReason(resultSet.getString("hospital_visit_reason"));
                patient.setUtiVisitCount(Integer.parseInt(resultSet.getString("uti_visit_count")));
                patient.setCatheterUsage(resultSet.getBoolean("catheter_usage"));
                patient.setDiabetic(resultSet.getBoolean("diabetic"));
                patient.setDeviceId(resultSet.getString("device_id"));
                patient.setLastVisitDate(resultSet.getDate("last_visit_date"));
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
    public Patient getPatientByEmail(String email) throws PatientNotFoundException {
        String sql = "SELECT * FROM \"Patient\" WHERE email = \'" + email + "\'";
        return getPatient(sql);
    }

    @Override
    public Patient getPatientByDeviceId(String id) throws PatientNotFoundException {
        String sql = "SELECT * FROM \"Patient\" WHERE device_id = \'" + id + "\'";
        return getPatient(sql);
    }

    @Override
    public boolean updatePatientDeviceId(Patient patient) {

        String sql = "UPDATE \"Patient\" SET device_id = \'" + patient.getDeviceId()
                + "\' WHERE email = \'" + patient.getEmail() + "\'";
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean addPatient(JSONObject jsonObject) throws IllegalSqlException {

        String sql = "INSERT INTO \"Patient\" (email, ssn, device_id, name, sex, " +
                "dob, address, phone, hospital_visit_reason, uti_visit_count, " +
                "catheter_usage, diabetic, last_visit_date ) " + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, jsonObject.get("email").toString().trim());
            preparedStatement.setString(2, jsonObject.get("ssn").toString().trim());
            preparedStatement.setString(3, jsonObject.get("id").toString().trim());
            preparedStatement.setString(4, jsonObject.get("name").toString().trim());
            preparedStatement.setString(5, jsonObject.get("sex").toString().trim());
            preparedStatement.setDate(6, Date.valueOf(jsonObject.get("dob").toString().trim()));
            preparedStatement.setString(7, jsonObject.get("address").toString().trim());
            preparedStatement.setString(8, jsonObject.get("phone").toString().trim());
            preparedStatement.setString(9, jsonObject.get("hospitalVisitReason").toString().trim());
            preparedStatement.setInt(10, Integer.parseInt(jsonObject.get("utiVisitCount").toString().trim()));
            preparedStatement.setBoolean(11, Boolean.parseBoolean(jsonObject.get("catheterUsage").toString().trim()));
            preparedStatement.setBoolean(12, Boolean.parseBoolean(jsonObject.get("diabetic").toString().trim()));
            preparedStatement.setDate(13, Date.valueOf(jsonObject.get("lastVisitDate").toString().trim()));

            preparedStatement.execute();
        } catch (SQLException e) {
            logger.error("Failed to add the patient");
            throw new IllegalSqlException(e.getMessage());
        }
        return true;
    }


    @Override
    public boolean deletePatient(String email) {
        String sql = "DELETE FROM \"Patient\" where email = \'" + email + "\'";
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }


//    @Override
//    public List<Patient> getPatientByName(String s) {
//        return null;
//    }


    public  Patient populateDetails(ResultSet resultSet) throws SQLException {
        Patient patient = null;

        while (resultSet.next()) {
            patient = new Patient();
            patient.setName(resultSet.getString("name"));
            patient.setSex(resultSet.getString("sex"));
            patient.setSsn(resultSet.getString("ssn"));

            patient.setDob(resultSet.getDate("dob"));

            patient.setEmail(resultSet.getString("email"));
            patient.setAddress(resultSet.getString("address"));
            patient.setPhone(resultSet.getString("phone"));
            patient.setHospitalVisitReason(resultSet.getString("hospital_visit_reason"));
            patient.setUtiVisitCount(Integer.parseInt(resultSet.getString("uti_visit_count")));
            patient.setCatheterUsage(resultSet.getBoolean("catheter_usage"));
            patient.setDiabetic(resultSet.getBoolean("diabetic"));
            patient.setDeviceId(resultSet.getString("device_id"));

            patient.setLastVisitDate(resultSet.getDate("last_visit_date"));
        }
        return patient;
    }

    private Patient getPatient(String sql) throws PatientNotFoundException {
        Patient patient = null;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSetEmail = statement.executeQuery(sql);
            patient = populateDetails(resultSetEmail);
            resultSetEmail.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (patient == null)
            throw new PatientNotFoundException("Patient does not exist");
        return patient;
    }

    public boolean updatePatient(String email, Patient patient) throws PatientNotFoundException {

        if (checkPatientExist(email)) {
            String sql = "UPDATE \"Patient\" " +
                    "SET ssn = \'" + patient.getSsn() +
                    "\',device_id = \'" + patient.getDeviceId() +
                    "\', name = \'" + patient.getName() +
                    "\',sex = \'" + patient.getSex() +
                    "\',dob = \'" + patient.getDob() +
                    "\', address =\'" + patient.getAddress() +
                    "\', phone =\'" + patient.getPhone() +
                    "\', hospital_visit_reason =\'" + patient.getHospitalVisitReason() +
                    "\', uti_visit_count =" + patient.getUtiVisitCount() +
                    ",catheter_usage =" + patient.getCatheterUsage() +
                    ", diabetic =" + patient.getDiabetic() +
                    ", last_visit_date = \'" + patient.getLastVisitDate() +
                    "\' WHERE email = \'" + patient.getEmail() + "\'";
            try {
                Statement statement = connection.createStatement();
                statement.executeUpdate(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            throw new PatientNotFoundException("Patient with this email does not exist");
        }
        return true;
    }

    @Override
    public boolean checkPatientExist(String email) {

        String sql = "SELECT * FROM \"Patient\" WHERE email = \'" + email
                + "\'";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}