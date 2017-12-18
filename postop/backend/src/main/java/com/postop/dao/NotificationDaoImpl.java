package com.postop.dao;


import com.postop.dao.interfaces.NotificationDao;
import com.postop.model.Notification;
import com.postop.utils.DbConnector;
import org.json.simple.JSONObject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Implements NotificationDao and defines methods for
 * fetching a list of Notification counts for each label
 * @author: Rohit Aakash, Geet Sawhney
 */
public class NotificationDaoImpl implements NotificationDao {
    private Connection connection;


    /**
     * Initializes a database connection
     */
    public NotificationDaoImpl() {
        connection = DbConnector.getConnection();
    }


    /**
     * Fetches a list of notification count for each label
     * @return List<Notification>
     * @throws SQLException
     */
    @Override
    public List<Notification> getNotifications() throws SQLException {
        List<Notification> allNotifications = new ArrayList<>();

        String sql = "SELECT * FROM \"Notification\"";

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        Notification notification;
        while (resultSet.next()) {

            notification = new Notification();

            notification.setLabel(resultSet.getString("label"));
            notification.setStart(Integer.parseInt(resultSet.getString("start")));
            notification.setEnd(Integer.parseInt(resultSet.getString("end")));
            notification.setInterval(Integer.parseInt(resultSet.getString("interval")));
            allNotifications.add(notification);
        }
        resultSet.close();
        statement.close();

        return allNotifications;
    }

    /**
     * Updates the start, end and interval for a criticality label
     * @param jsonObject: JSON containing key value pairs for notification count
     * @return true if update was successful else false
     * @throws SQLException
     */
    @Override
    public boolean updateNotification(JSONObject jsonObject) throws SQLException {
        String sql = "UPDATE \"Notification\" " +
                "SET \"start\" = " + Integer.parseInt(jsonObject.get("start").toString()) +
                ", \"interval\" = " + Integer.parseInt(jsonObject.get("interval").toString()) +
                ", \"end\"= " + Integer.parseInt(jsonObject.get("end").toString()) +
                " WHERE label = \'" + jsonObject.get("label").toString() + "\'";

        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);
        return true;
    }

    /**
     * Retrieves notification count parameters from the table for a particular label
     * @param label: criticality label for which the start, end and interval values are to be fetched
     * @return a Notification instance
     * @throws SQLException
     */
    @Override
    public Notification getNotification(String label) throws SQLException {
        String sql = "SELECT * FROM \"Notification\" WHERE label = \'" +label +"\'";

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        Notification notification = null;
        while(resultSet.next()){
            notification = new Notification();
            notification.setLabel(resultSet.getString("label"));
            notification.setStart(Integer.parseInt(resultSet.getString("start")));
            notification.setEnd(Integer.parseInt(resultSet.getString("end")));
            notification.setInterval(Integer.parseInt(resultSet.getString("interval")));
        }
        return notification;
    }
}
