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

public class NotificationDaoImpl implements NotificationDao {
    Connection connection;

    public NotificationDaoImpl() {
        connection = DbConnector.getConnection();
    }

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

    @Override
    public boolean updateNotification(JSONObject jsonObject) throws SQLException {
        String sql = "UPDATE \"Notification\" " +
                "SET \"start\" = " + Integer.parseInt(jsonObject.get("start").toString()) +
                ", \"interval\" = " + Integer.parseInt(jsonObject.get("interval").toString()) +
                ", \"end\"= " + Integer.parseInt(jsonObject.get("end").toString()) +
                " WHERE label = \'" + jsonObject.get("label").toString() + "\'";

        Statement statement;
        statement = connection.createStatement();
        statement.executeUpdate(sql);
        return true;
    }
}
