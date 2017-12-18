package com.postop.dao;

import com.postop.dao.interfaces.PatientLoginDao;
import com.postop.exceptions.IllegalSqlException;
import com.postop.utils.DbConnector;
import com.postop.utils.HashGenerator;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.NoSuchAlgorithmException;
import java.sql.*;

/**
 * Implements PatientLoginDao and defines method for
 * validating, adding anf deleting a patient login information
 * @author Rohit Aakash, Geet Sawhney
 */
public class PatientLoginDaoImpl implements PatientLoginDao {

    private final Logger logger = LoggerFactory.getLogger(PatientLoginDaoImpl.class);
    private Connection connection;

    /**
     * Initializes a database connection
     */
    public PatientLoginDaoImpl() {
        connection = DbConnector.getConnection();
    }

    /**
     * Checks if the login information for a patient exists
     * @param email: email id of the patient
     * @param password: account password of the patient
     * @return true if the patient login information exists else false
     * @throws SQLException
     */
    public boolean validatePatient(String email, String password) throws SQLException {

        String sql = "SELECT * FROM \"Patient_Login\" WHERE email = \'" + email.toLowerCase()
                + "\' AND password = \'" + password + "\'";

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        return resultSet.next();
    }

    /**
     * Deletes the login information of a patient
     * @param email: email id of the patient
     * @return true if the delete was successful else false
     * @throws SQLException
     */
    @Override
    public boolean deletePatient(String email) throws SQLException {
        String sql = "DELETE FROM \"Patient_Login\" WHERE email= \'" + email.toLowerCase() + "\'";

        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);

        return true;
    }

    /**
     * Adds a new login information for a patient
     * @param jsonObject: JSON containing valid key value pairs for login
     * @return true if the insert was successful else false
     * @throws IllegalSqlException
     * @throws SQLException
     * @throws NoSuchAlgorithmException
     */
    @Override
    public boolean addPatient(JSONObject jsonObject) throws IllegalSqlException, SQLException, NoSuchAlgorithmException {
        String sql = "INSERT INTO \"Patient_Login\"(email,password) "
                + "VALUES (?,?)";

        String password = HashGenerator.generateHash(jsonObject.get("password").toString());
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, jsonObject.get("email").toString().toLowerCase().trim());
        preparedStatement.setString(2, password);

        preparedStatement.execute();

        return true;
    }
}
