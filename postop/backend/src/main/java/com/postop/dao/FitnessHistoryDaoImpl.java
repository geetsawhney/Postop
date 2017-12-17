package com.postop.dao;

import com.postop.dao.interfaces.FitnessHistoryDao;
import com.postop.model.FitnessHistory;
import com.postop.utils.DbConnector;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.Calendar;

/**
 *
 */
public class FitnessHistoryDaoImpl implements FitnessHistoryDao {

    private Connection connection;
    private final Logger logger = LoggerFactory.getLogger(FitnessHistoryDaoImpl.class);

    /**
     *
     */
    public FitnessHistoryDaoImpl() {
        connection = DbConnector.getConnection();
    }

    /**
     * @param jsonObject
     * @return
     * @throws SQLException
     */
    @Override
    public boolean addFitnessData(JSONObject jsonObject) throws SQLException {

        String sql = "INSERT INTO \"Fitness_History\" (email, capture_date, step_count, calories_expended) "
                + "VALUES (?,?,?,?)";


            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, jsonObject.get("email").toString().toLowerCase().trim());
            preparedStatement.setDate(2, Date.valueOf(jsonObject.get("captureDate").toString().trim()));
            preparedStatement.setInt(3, Integer.parseInt(jsonObject.get("stepCount").toString().trim()));
            preparedStatement.setInt(4, Integer.parseInt(jsonObject.get("caloriesExpended").toString().trim()));

            preparedStatement.execute();

        return true;
    }

    /**
     * @param email
     * @return
     * @throws SQLException
     */
    @Override
    public FitnessHistory getFitnessDataByEmail(String email) throws SQLException {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -7);
        Date lastWeekDate = new Date(calendar.getTimeInMillis());


        String sql = "SELECT email as email, MAX(capture_date) as capture_date, SUM(step_count)/7 as step_count, " +
                "SUM(calories_expended)/7 as calories_expended FROM \"Fitness_History\" WHERE email = \'" + email +
                "\' AND capture_date >= \'" + lastWeekDate + "\' GROUP BY email;";



        FitnessHistory fitnessHistory = null;

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            fitnessHistory = new FitnessHistory();

            while (resultSet.next()) {

                fitnessHistory.setEmail(resultSet.getString("email"));
                fitnessHistory.setCaptureDate(resultSet.getDate("capture_date"));
                fitnessHistory.setStepCount(resultSet.getInt("step_count"));
                fitnessHistory.setCaloriesExpended(resultSet.getInt("calories_expended"));
            }

        return fitnessHistory;
    }

    /**
     * @param fitnessHistory
     * @return
     * @throws SQLException
     */
    @Override
    public boolean deleteFitnessData(FitnessHistory fitnessHistory) throws SQLException {
        String sql = "DELETE FROM \"Fitness_History\" WHERE " +
                "email= \'" + fitnessHistory.getEmail().toLowerCase().trim() +
                "\' AND capture_date=\'" + fitnessHistory.getCaptureDate() +
                "\' AND step_count=" + fitnessHistory.getStepCount() +
                "AND calories_expended=" + fitnessHistory.getCaloriesExpended();


            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);

        return true;
    }
}
