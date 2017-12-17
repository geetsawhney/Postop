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
 *
 */
public class PatientLoginDaoImpl implements PatientLoginDao {

    private final Logger logger = LoggerFactory.getLogger(PatientLoginDaoImpl.class);
    private Connection connection;

    /**
     *
     */
    public PatientLoginDaoImpl() {
        connection = DbConnector.getConnection();
    }

    /**
     * @param email
     * @param password
     * @return
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
     * @param email
     * @return
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
     * @param jsonObject
     * @return
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
