package com.postop.helper;

import com.postop.model.FitnessHistory;
import com.postop.model.Patient;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class NotificationLogicTest {

    @Test
    public void notificationCountCheck1() throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dob = sdf.parse("1990-10-15");
        Date visitDate = sdf.parse("2017-11-10");
        Date captureDate = sdf.parse("2017-11-11");
        Patient patient = new Patient("", "", "", "", "F", dob, "", "", "", 0, false, false, visitDate);
        FitnessHistory fitnessHistory = new FitnessHistory("", new Date(), 5000, 1326);
        assertEquals(4, new NotificationLogic(patient, fitnessHistory).getNumberOfNotifications());

    }

    @Test
    public void notificationCountCheck2() throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dob = sdf.parse("1989-10-15");
        Date visitDate = sdf.parse("2017-11-09");
        Date captureDate = sdf.parse("2017-11-11");
        Patient patient = new Patient("", "", "", "", "F", dob, "", "", "", 0, false, false, visitDate);
        FitnessHistory fitnessHistory = new FitnessHistory("", new Date(), 5000, 1326);
        assertNotEquals(5, new NotificationLogic(patient, fitnessHistory).getNumberOfNotifications());

    }

    @Test
    public void notificationCountCheck3() throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dob = sdf.parse("1970-09-15");
        Date visitDate = sdf.parse("2017-11-10");
        Date captureDate = sdf.parse("2017-11-11");
        Patient patient = new Patient("", "", "", "", "F", dob, "", "", "", 3, true, true, visitDate);
        FitnessHistory fitnessHistory = new FitnessHistory("", new Date(), 8000, 1326);
        assertEquals(8, new NotificationLogic(patient, fitnessHistory).getNumberOfNotifications());

    }

}