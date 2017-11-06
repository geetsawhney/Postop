package com.postop.dao;

import com.postop.dao.interfaces.PatientLoginDao;
import com.postop.exceptions.IllegalSqlException;
import com.postop.utils.DbConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
}
