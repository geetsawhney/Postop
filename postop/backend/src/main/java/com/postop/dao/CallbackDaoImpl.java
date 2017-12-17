package com.postop.dao;

import com.postop.dao.interfaces.CallbackDao;
import com.postop.model.Callback;
import com.postop.utils.DbConnector;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 *
 */
public class CallbackDaoImpl implements CallbackDao {
    private Connection connection;
    private final Logger logger = LoggerFactory.getLogger(CallbackDaoImpl.class);

    /**
     *
     */
    public CallbackDaoImpl() {
        this.connection = DbConnector.getConnection();
    }

    /**
     * @param email
     * @return
     */
    @Override
    public boolean checkCallbackExists(String email) {
        String sql = "SELECT * FROM \"Callback\" WHERE email = \'" + email.toLowerCase() + "\'";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            return resultSet.next();

        } catch (SQLException e) {
            logger.error("SQL Exception");
            e.printStackTrace();
        }
        return true;
    }

    /**
     * @param email
     * @param jsonObject
     * @return
     */
    @Override
    public boolean updateCallback(String email, JSONObject jsonObject) {

        String sql = "";
        if (jsonObject.containsKey("hasPain")) {
            sql = "UPDATE \"Callback\" " +
                    "SET callback_date = \'" + Date.valueOf(jsonObject.get("callbackDate").toString()) +
                    "\',severity = \'" + jsonObject.get("severity").toString() +
                    "\', has_pain = \'" + Boolean.parseBoolean(jsonObject.get("hasPain").toString()) +
                    "\',has_nausea = \'" + Boolean.parseBoolean(jsonObject.get("hasNausea").toString()) +
                    "\',has_fever = \'" + Boolean.parseBoolean(jsonObject.get("hasFever").toString()) +
                    "\', has_fatigue =\'" + Boolean.parseBoolean(jsonObject.get("hasFatigue").toString()) +
                    "\', urine_color =\'" + jsonObject.get("urineColor").toString() +
                    "\', is_resolved =\'" + Boolean.parseBoolean(jsonObject.get("isResolved").toString()) +
                    "\' WHERE email = \'" + email.toLowerCase() + "\'";
        } else {
            sql = "UPDATE \"Callback\" " +
                    "SET is_resolved =\'" + Boolean.parseBoolean(jsonObject.get("isResolved").toString()) +
                    "\' WHERE email = \'" + email + "\'";
        }


        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * @param email
     * @param jsonObject
     * @return
     */
    @Override
    public boolean addCallback(String email, JSONObject jsonObject) {
        String sql = "INSERT INTO \"Callback\" (email, callback_date, severity, has_pain, has_nausea, " +
                "has_fever, has_fatigue, urine_color, is_resolved) VALUES (?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, email.toLowerCase());
            preparedStatement.setDate(2, Date.valueOf(jsonObject.get("callbackDate").toString()));
            preparedStatement.setInt(3, Integer.parseInt(jsonObject.get("severity").toString()));
            preparedStatement.setBoolean(4, Boolean.parseBoolean(jsonObject.get("hasPain").toString()));
            preparedStatement.setBoolean(5, Boolean.parseBoolean(jsonObject.get("hasNausea").toString()));
            preparedStatement.setBoolean(6, Boolean.parseBoolean(jsonObject.get("hasFever").toString()));
            preparedStatement.setBoolean(7, Boolean.parseBoolean(jsonObject.get("hasFatigue").toString()));
            preparedStatement.setString(8, jsonObject.get("urineColor").toString());
            preparedStatement.setBoolean(9, Boolean.parseBoolean(jsonObject.get("isResolved").toString()));

            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * @return
     */
    @Override
    public List<JSONObject> getAllCallbacks() {
        List<JSONObject> allCallbacks = new ArrayList<>();
        String sql = "SELECT c.email, c.callback_date, c.severity, c.has_pain, c.has_fever, c.has_nausea, c.has_fatigue, c.urine_color , c.is_resolved, " +
                "p.name, p.phone, p.catheter_usage,p.last_visit_date,p.sex,p.diabetic, p.hospital_visit_reason, p.uti_visit_count " +
                "FROM \"Callback\" c, \"Patient\" p WHERE c.is_resolved = false AND c.email=p.email";

        JSONObject jsonObject;

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                jsonObject=new JSONObject();

                jsonObject.put("email",resultSet.getString("email"));
                jsonObject.put("callbackDate",resultSet.getString("callback_date"));
                jsonObject.put("severity",resultSet.getInt("severity"));
                jsonObject.put("hasFatigue",resultSet.getBoolean("has_fatigue"));
                jsonObject.put("hasNausea",resultSet.getBoolean("has_nausea"));
                jsonObject.put("hasFever",resultSet.getBoolean("has_fever"));
                jsonObject.put("hasPain",resultSet.getBoolean("has_pain"));
                jsonObject.put("urineColor",resultSet.getString("urine_color"));
                jsonObject.put("isResolved",resultSet.getBoolean("is_resolved"));

                jsonObject.put("name",resultSet.getString("name"));
                jsonObject.put("phone",resultSet.getString("phone"));
                jsonObject.put("sex",resultSet.getString("sex"));
                jsonObject.put("hospitalVisitReason",resultSet.getString("hospital_visit_reason"));
                jsonObject.put("utiVisitCount",resultSet.getInt("uti_visit_count"));
                jsonObject.put("diabetic",resultSet.getBoolean("diabetic"));
                jsonObject.put("lastVisitDate",resultSet.getString("last_visit_date"));
                jsonObject.put("catheterUsage",resultSet.getBoolean("catheter_usage"));

                allCallbacks.add(jsonObject);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allCallbacks;
    }

    /**
     * @param email
     * @return
     */
    public Callback getCallback(String email) {

        String sql = "SELECT * FROM \"Callback\" WHERE email = \'" + email.toLowerCase() + "\'";

        Callback callback = null;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                callback = new Callback();
                callback.setEmail(resultSet.getString("email"));
                callback.setCallbackDate(resultSet.getDate("callback_date"));
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


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return callback;
    }

    /**
     * @param email
     * @return
     */
    @Override
    public boolean deleteCallback(String email) {
        String sql = "DELETE FROM \"Callback\" WHERE email=\'" + email.toLowerCase() + "\'";
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
}