package com.postop.helper;

import com.postop.model.FitnessHistory;
import com.postop.model.Patient;
import org.testng.annotations.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

public class CallbackLogicTest {

    @Test
    public void notificationCountCheck() throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dob = sdf.parse("1990-10-15");
        Date visitDate = sdf.parse("2017-11-10");
        Date captureDate = sdf.parse("2017-11-11");
        Patient patient = new Patient("", "", "", "", "F", dob, "", "", "", 0, false, false, visitDate);
        FitnessHistory fitnessHistory = new FitnessHistory("", new Date(), 5000, 1326);
        assertEquals(4, new NotificationLogic(patient, fitnessHistory).getNumberOfNotifications());

    }
}