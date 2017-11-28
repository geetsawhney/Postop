package com.postop.dao;

import com.postop.dao.interfaces.FitnessHistoryDao;
import com.postop.exceptions.IllegalSqlException;
import com.postop.model.FitnessHistory;
import com.postop.utils.DbConnector;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class FitnessHistoryDaoImpl implements FitnessHistoryDao {

    private Connection connection;
    private final Logger logger = LoggerFactory.getLogger(FitnessHistoryDaoImpl.class);

    public FitnessHistoryDaoImpl() {
        connection = DbConnector.getConnection();
    }

    @Override
    public void addFitnessData(JSONObject jsonObject) throws IllegalSqlException {

        String sql = "INSERT INTO \"Fitness_History\" (email, capture_date, step_count, calories_expended) "
                + "VALUES (?,?,?,?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, jsonObject.get("email").toString());
            preparedStatement.setString(2, jsonObject.get("captureDate").toString());
            preparedStatement.setInt(3, Integer.parseInt(jsonObject.get("stepCount").toString()));
            preparedStatement.setInt(4, Integer.parseInt(jsonObject.get("caloriesExpended").toString()));

            preparedStatement.execute();
        } catch (SQLException e) {
            logger.error("Failed to add fitness data");
            throw new IllegalSqlException(e.getMessage());
        }

    }

    @Override
    public FitnessHistory getFitnessDataByEmail(String email) throws IllegalSqlException {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -7);
        Date lastWeekDate = calendar.getTime();

        String lastWeekDateString = new SimpleDateFormat("yyyy-MM-dd").format(lastWeekDate);

        String sql = "SELECT email as email, MAX(capture_date) as capture_date, SUM(step_count)/7 as step_count, " +
                "SUM(calories_expended)/7 as calories_expended FROM \"Fitness_History\" WHERE email = \'" + email +
                "\' AND capture_date >= \'" + lastWeekDateString + "\' GROUP BY email;";


        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            FitnessHistory fitnessHistory = new FitnessHistory();
            while (resultSet.next()) {

                fitnessHistory.setEmail(resultSet.getString("email"));
                fitnessHistory.setCaptureDate(resultSet.getString("capture_date"));
                fitnessHistory.setStepCount(resultSet.getInt("step_count"));
                fitnessHistory.setCaloriesExpended(resultSet.getInt("calories_expended"));
            }

            return fitnessHistory;

        } catch (SQLException e) {
            logger.error("Failed to add fitness data");
            throw new IllegalSqlException(e.getMessage());
        }


    }

    @Override
    public boolean deleteFitnessData(FitnessHistory fitnessHistory) {
        String sql = "DELETE FROM \"Fitness_History\" WHERE " +
                "email= \'"+ fitnessHistory.getEmail()+
                "\' AND capture_date=\'"+fitnessHistory.getCaptureDate()+
                "\' AND step_count="+fitnessHistory.getStepCount()+
                "AND calories_expended="+fitnessHistory.getCaloriesExpended();

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
}
