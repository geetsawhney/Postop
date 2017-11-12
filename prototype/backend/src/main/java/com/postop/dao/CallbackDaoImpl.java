package com.postop.dao;

import com.postop.dao.interfaces.CallbackDao;
import com.postop.exceptions.IllegalSqlException;
import com.postop.model.Callback;
import com.postop.utils.DbConnector;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class CallbackDaoImpl implements CallbackDao {
    private Connection connection;
    private final Logger logger = LoggerFactory.getLogger(CallbackDaoImpl.class);

    public CallbackDaoImpl() {
        this.connection = DbConnector.getConnection();
    }

    @Override
    public boolean checkCallbackExists(String email) throws IllegalSqlException {
        String sql = "SELECT * FROM \"Callback\" WHERE email = \'" + email + "\'";
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
    public void updateCallback(String email, JSONObject jsonObject) throws IllegalSqlException {

        String sql = "";
        if(jsonObject.containsKey("hasPain")){
            sql = "UPDATE \"Callback\" " +
                    "SET callback_date = \'" + jsonObject.get("callbackDate").toString() +
                    "\',severity = \'" + jsonObject.get("severity").toString() +
                    "\', has_pain = \'" + Boolean.parseBoolean(jsonObject.get("hasPain").toString()) +
                    "\',has_nausea = \'" + Boolean.parseBoolean(jsonObject.get("hasNausea").toString()) +
                    "\',has_fever = \'" + Boolean.parseBoolean(jsonObject.get("hasFever").toString()) +
                    "\', has_fatigue =\'" + Boolean.parseBoolean(jsonObject.get("hasFatigue").toString()) +
                    "\', urine_color =\'" + jsonObject.get("urineColor").toString()+
                    "\', is_resolved =\'" +  Boolean.parseBoolean(jsonObject.get("isResolved").toString()) +
                    "\' WHERE email = \'" + email + "\'";
        }else{
            sql = "UPDATE \"Callback\" " +
                    "SET is_resolved =\'" +  Boolean.parseBoolean(jsonObject.get("isResolved").toString()) +
                    "\' WHERE email = \'" + email + "\'";
        }


        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new IllegalSqlException(e.getMessage());
        }
    }

    @Override
    public void addCallback(String email, JSONObject jsonObject) throws IllegalSqlException {
       String sql= "INSERT INTO \"Callback\" (email, callback_date, severity, has_pain, has_nausea, " +
                "has_fever, has_fatigue, urine_color, is_resolved) VALUES (?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, email);
            preparedStatement.setString(2, jsonObject.get("callbackDate").toString());
            preparedStatement.setInt(3, Integer.parseInt(jsonObject.get("severity").toString()));
            preparedStatement.setBoolean(4, Boolean.parseBoolean(jsonObject.get("hasPain").toString()));
            preparedStatement.setBoolean(5, Boolean.parseBoolean(jsonObject.get("hasNausea").toString()));
            preparedStatement.setBoolean(6, Boolean.parseBoolean(jsonObject.get("hasFever").toString()));
            preparedStatement.setBoolean(7, Boolean.parseBoolean(jsonObject.get("hasFatigue").toString()));
            preparedStatement.setString(8, jsonObject.get("urineColor").toString());
            preparedStatement.setBoolean(9, Boolean.parseBoolean(jsonObject.get("isResolved").toString()));

            preparedStatement.execute();
        }catch (SQLException e){
            throw  new IllegalSqlException(e.getMessage());
        }
    }

    @Override
    public List<Callback> getAllCallbacks() throws IllegalSqlException {
        List<Callback> allCallbacks = new ArrayList<>();
        String sql = "SELECT * FROM \"Callback\" WHERE is_resolved = false";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            Callback callback;
            while (resultSet.next()) {
                callback = new Callback();

                callback.setEmail(resultSet.getString("email"));
                callback.setCallbackDate(resultSet.getString("callback_date"));
                callback.setSeverity(Integer.parseInt(resultSet.getString("severity")));
                callback.setHasFatigue(resultSet.getBoolean("has_fatigue"));
                callback.setHasNausea(resultSet.getBoolean("has_nausea"));
                callback.setHasFever(resultSet.getBoolean("has_fever"));
                callback.setHasPain(resultSet.getBoolean("has_pain"));
                callback.setUrineColor(resultSet.getString("urine_color"));
                callback.setIsResolved(resultSet.getBoolean("is_resolved"));
                allCallbacks.add(callback);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            throw new IllegalSqlException(e.getMessage());
        }
        return allCallbacks;
    }

    public Callback getCallback(String email) throws IllegalSqlException {

        String sql = "SELECT * FROM \"Callback\" WHERE email = \'" +email + "\'";

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            Callback callback = null;
            if (resultSet.next()) {
                callback = new Callback();
                callback.setEmail(resultSet.getString("email"));
                callback.setCallbackDate(resultSet.getString("callback_date"));
                callback.setSeverity(Integer.parseInt(resultSet.getString("severity")));
                callback.setHasFatigue(resultSet.getBoolean("has_fatigue"));
                callback.setHasNausea(resultSet.getBoolean("has_nausea"));
                callback.setHasFever(resultSet.getBoolean("has_fever"));
                callback.setHasPain(resultSet.getBoolean("has_pain"));
                callback.setUrineColor(resultSet.getString("urine_color"));
                callback.setIsResolved(resultSet.getBoolean("is_resolved"));
            }

            resultSet.close();
            statement.close();

            return callback;
        } catch (SQLException e) {
            throw new IllegalSqlException(e.getMessage());
        }

    }
}
