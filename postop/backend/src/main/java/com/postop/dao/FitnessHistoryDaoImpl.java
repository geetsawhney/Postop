package com.postop.dao;

import com.postop.dao.interfaces.FitnessHistoryDao;
import com.postop.model.FitnessHistory;
import com.postop.utils.DbConnector;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.Calendar;

public class FitnessHistoryDaoImpl implements FitnessHistoryDao {

    private Connection connection;
    private final Logger logger = LoggerFactory.getLogger(FitnessHistoryDaoImpl.class);

    public FitnessHistoryDaoImpl() {
        connection = DbConnector.getConnection();
    }

    @Override
    public boolean addFitnessData(JSONObject jsonObject) {

        String sql = "INSERT INTO \"Fitness_History\" (email, capture_date, step_count, calories_expended) "
                + "VALUES (?,?,?,?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, jsonObject.get("email").toString());
            preparedStatement.setDate(2, Date.valueOf(jsonObject.get("captureDate").toString()));
            preparedStatement.setInt(3, Integer.parseInt(jsonObject.get("stepCount").toString()));
            preparedStatement.setInt(4, Integer.parseInt(jsonObject.get("caloriesExpended").toString()));

            preparedStatement.execute();
        } catch (SQLException e) {
            logger.error("Failed to add fitness data");
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public FitnessHistory getFitnessDataByEmail(String email) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -7);
        Date lastWeekDate = new Date(calendar.getTimeInMillis());


        String sql = "SELECT email as email, MAX(capture_date) as capture_date, SUM(step_count)/7 as step_count, " +
                "SUM(calories_expended)/7 as calories_expended FROM \"Fitness_History\" WHERE email = \'" + email +
                "\' AND capture_date >= \'" + lastWeekDate + "\' GROUP BY email;";



        FitnessHistory fitnessHistory = null;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            fitnessHistory = new FitnessHistory();

            while (resultSet.next()) {

                fitnessHistory.setEmail(resultSet.getString("email"));
                fitnessHistory.setCaptureDate(resultSet.getDate("capture_date"));
                fitnessHistory.setStepCount(resultSet.getInt("step_count"));
                fitnessHistory.setCaloriesExpended(resultSet.getInt("calories_expended"));
            }
        } catch (SQLException e) {
            logger.error("Failed to add fitness data");
            e.printStackTrace();
        }
        return fitnessHistory;
    }

    @Override
    public boolean deleteFitnessData(FitnessHistory fitnessHistory) {
        String sql = "DELETE FROM \"Fitness_History\" WHERE " +
                "email= \'" + fitnessHistory.getEmail() +
                "\' AND capture_date=\'" + fitnessHistory.getCaptureDate() +
                "\' AND step_count=" + fitnessHistory.getStepCount() +
                "AND calories_expended=" + fitnessHistory.getCaloriesExpended();

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
}
