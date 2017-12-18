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
 * Implements FitnessHistoryDao and defines methods for
 * inserting, deleting and fetching fitness data
 *
 * @author Rohit Aakash, Geet Sawhney
 */
public class FitnessHistoryDaoImpl implements FitnessHistoryDao {

    private final Logger logger = LoggerFactory.getLogger(FitnessHistoryDaoImpl.class);
    private Connection connection;

    /**
     * Initializes the database connection
     */
    public FitnessHistoryDaoImpl() {
        connection = DbConnector.getConnection();
    }

    /**
     * Adds fitness data to the table
     *
     * @param jsonObject: JSON consisting of required key value pairs of fitness data
     * @return true if insert was successful else false
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
     * Retrieves average fitness data over a period of 7 days based on the email
     * @param email: email id of the patient
     * @return FitnessHistory object
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
     * Deletes fitness data for a given fitness history object
     * @param fitnessHistory: FitnessHistory instance with the values to be deleted from the table
     * @return true if delete was successful else false
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
