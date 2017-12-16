package com.postop.dao.interfaces;


import com.postop.model.Notification;
import org.json.simple.JSONObject;

import java.sql.SQLException;
import java.util.List;

public interface NotificationDao {

    List<Notification> getNotifications() throws SQLException;
    boolean updateNotification(JSONObject jsonObject) throws SQLException;
}
