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
    public void severityCheck() throws ParseException {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("hasPain", false);
        jsonObject.put("hasNausea", true);
        jsonObject.put("hasFever", true);
        jsonObject.put("hasFatigue", true);
        jsonObject.put("urineColor", "Cloudy");
        assertEquals(5, new CallbackLogic(jsonObject).getSeverity());

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