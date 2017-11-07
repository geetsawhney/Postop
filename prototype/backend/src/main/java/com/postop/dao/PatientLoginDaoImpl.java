package com.postop.dao;

import com.postop.dao.interfaces.PatientLoginDao;
import com.postop.exceptions.IllegalSqlException;
import com.postop.exceptions.InvalidHashAlgorithmException;
import com.postop.utils.DbConnector;
import com.postop.utils.HashGenerator;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.NoSuchAlgorithmException;
import java.sql.*;

public class PatientLoginDaoImpl implements PatientLoginDao {

    private Connection connection;
    private final Logger logger = LoggerFactory.getLogger(PatientLoginDaoImpl.class);

    public PatientLoginDaoImpl() {
        connection = DbConnector.getConnection();
    }

    public boolean validatePatient(String email, String password) throws IllegalSqlException{

        String sql = "SELECT * FROM \"Patient_Login\" WHERE email = \'" + email
                +"\' AND password = \'" + password + "\'";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            return resultSet.next();
        } catch (SQLException e) {
            logger.error("SQL Exception");
            throw new IllegalSqlException(e.getMessage());
        }
    }

    @Override
    public void addPatient(JSONObject jsonObject) throws InvalidHashAlgorithmException, IllegalSqlException {
        String sql = "INSERT INTO \"Patient_Login\"(email,password) "
                 + "VALUES (?,?)";
        try {
            String password=HashGenerator.generateHash(jsonObject.get("password").toString());
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, jsonObject.get("email").toString());
            preparedStatement.setString(2, password);

            preparedStatement.execute();
        } catch (SQLException e) {
            logger.error("Failed to add the patient");
            throw new IllegalSqlException(e.getMessage());
        }
        catch (NoSuchAlgorithmException e){
            logger.error("Wrong Algorithm in Hashing");
            throw new InvalidHashAlgorithmException(e.getMessage());
        }
    }
}
