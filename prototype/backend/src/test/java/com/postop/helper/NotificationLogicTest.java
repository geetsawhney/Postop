package com.postop.helper;

import com.postop.model.FitnessHistory;
import com.postop.model.Patient;
import org.testng.annotations.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class NotificationLogicTest {

    @Test
    public void testSomething() throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dob = sdf.parse("1990-10-15");
        Date visitDate = sdf.parse("2017-11-10");
        Date captureDate = sdf.parse("2017-11-11");
        Patient patient = new Patient("", "", "", "", "F", dob, "", "", "", 0, false, false, visitDate);
        FitnessHistory fitnessHistory = new FitnessHistory("", new Date(), 5000, 1326);
        assertEquals(4, new NotificationLogic(patient, fitnessHistory).getNumberOfNotifications());

    }
//
//    @Test
//    public void  testNotificationLogic(){
//
//        assertEquals(new NotificationLogic().ageStatus("L",20), "L");
//    }
//
//    @Test
//    public void testShouldFail(){
//        assertNotEquals(new NotificationLogic().ageStatus("L",20), "M");
//    }

}