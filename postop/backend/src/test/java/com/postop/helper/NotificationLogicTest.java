package com.postop.helper;

import com.postop.model.FitnessHistory;
import com.postop.model.Patient;

import org.junit.*;
import static org.junit.Assert.*;

import java.sql.Date;
import java.sql.SQLException;

public class NotificationLogicTest {

    @Test
    public void notificationCountCheck1() throws SQLException {

        Date dob = Date.valueOf("1990-10-15");
        Date visitDate = Date.valueOf("2017-11-10");
        Date captureDate = Date.valueOf("2017-11-11");
        Patient patient = new Patient("", "", "", "", "F", dob, "", "", "", 0, false, false, visitDate);
        FitnessHistory fitnessHistory = new FitnessHistory("", captureDate, 1000, 1326);
        assertNotEquals(-8, new NotificationLogic(patient, fitnessHistory).getNumberOfNotifications());

    }

    @Test
    public void notificationCountCheck2() throws SQLException {

        Date dob =Date.valueOf("1989-10-15");
        Date visitDate = Date.valueOf("2017-05-09");
        Date captureDate = Date.valueOf("2017-11-11");
        Patient patient = new Patient("", "", "", "", "F", dob, "", "", "", 0, false, false, visitDate);
        FitnessHistory fitnessHistory = new FitnessHistory("", captureDate, 5000, 1326);
        assertNotEquals(-1, new NotificationLogic(patient, fitnessHistory).getNumberOfNotifications());

    }

    @Test
    public void notificationCountCheck3() throws SQLException {

        Date dob = Date.valueOf("1970-09-15");
        Date visitDate = Date.valueOf("2017-11-10");
        Date captureDate = Date.valueOf("2017-11-11");
        Patient patient = new Patient("", "", "", "", "F", dob, "", "", "", 3, true, true, visitDate);
        FitnessHistory fitnessHistory = new FitnessHistory("", captureDate, 8000, 1326);
        assertNotEquals(-12, new NotificationLogic(patient, fitnessHistory).getNumberOfNotifications());

    }

    @Test
    public void notificationCountCheck4() throws SQLException {

        Date dob = Date.valueOf("1950-09-15");
        Date visitDate = Date.valueOf("2017-04-10");
        Date captureDate = Date.valueOf("2017-05-11");
        Patient patient = new Patient("", "", "", "", "F", dob, "", "", "", 5, true, true, visitDate);
        FitnessHistory fitnessHistory = new FitnessHistory("", captureDate, 10000, 2356);
        assertNotEquals(-1, new NotificationLogic(patient, fitnessHistory).getNumberOfNotifications());

    }

    @Test
    public void notificationCountCheck5() throws SQLException {

        Date dob = Date.valueOf("1950-09-15");
        Date visitDate = Date.valueOf("2017-10-10");
        Date captureDate = Date.valueOf("2017-05-11");
        Patient patient = new Patient("", "", "", "", "M", dob, "", "", "", 5, false, true, visitDate);
        FitnessHistory fitnessHistory = new FitnessHistory("", captureDate, 4000, 2356);
        assertNotEquals('a', new NotificationLogic(patient, fitnessHistory).getNumberOfNotifications());

    }

    @Test
    public void notificationCountCheck6() throws SQLException {

        Date dob = Date.valueOf("1995-10-15");
        Date visitDate = Date.valueOf("2017-10-09");
        Date captureDate = Date.valueOf("2017-11-11");
        Patient patient = new Patient("", "", "", "", "F", dob, "", "", "", 0, true, true, visitDate);
        FitnessHistory fitnessHistory = new FitnessHistory("", captureDate, 2000, 1326);
        assertNotEquals(-1, new NotificationLogic(patient, fitnessHistory).getNumberOfNotifications());

    }

    @Test
    public void notificationCountCheck7() throws SQLException {

        Date dob = Date.valueOf("1982-10-15");
        Date visitDate = Date.valueOf("2017-10-09");
        Date captureDate = Date.valueOf("2017-11-11");
        Patient patient = new Patient("", "", "", "", "F", dob, "", "", "", 3, true, false, visitDate);
        FitnessHistory fitnessHistory = new FitnessHistory("", captureDate, 4000, 1326);
        assertNotEquals(4, new NotificationLogic(patient, fitnessHistory).getNumberOfNotifications());
    }

    @Test
    public void notificationCountCheck8() throws SQLException {

        Date dob = Date.valueOf("1980-10-15");
        Date visitDate = Date.valueOf("2017-12-11");
        Date captureDate = Date.valueOf("2017-12-11");
        Patient patient = new Patient("", "", "", "", "M", dob, "", "", "", 8, true, false, visitDate);
        FitnessHistory fitnessHistory = new FitnessHistory("", captureDate, 4000, 1326);
        assertNotEquals(4, new NotificationLogic(patient, fitnessHistory).getNumberOfNotifications());
    }

    @Test
    public void notificationCountCheck9() throws SQLException {

        Date dob = Date.valueOf("1980-10-15");
        Date visitDate = Date.valueOf("2017-12-11");
        Date captureDate = Date.valueOf("2017-12-11");
        Patient patient = new Patient("", "", "", "", "M", dob, "", "", "", 8, true, false, visitDate);
        FitnessHistory fitnessHistory = new FitnessHistory("", captureDate, 2000, 1326);
        assertNotEquals(4, new NotificationLogic(patient, fitnessHistory).getNumberOfNotifications());
    }

}