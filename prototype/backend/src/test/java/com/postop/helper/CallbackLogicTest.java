package com.postop.helper;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import java.text.ParseException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class CallbackLogicTest {

    @Test
    public void severityCheck1() throws ParseException {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("hasPain", true);
        jsonObject.put("hasNausea", true);
        jsonObject.put("hasFever", true);
        jsonObject.put("hasFatigue", true);
        jsonObject.put("urineColor", "Cloudy");
        assertEquals(6, new CallbackLogic(jsonObject).getSeverity());

    }

    @Test
    public void severityCheck2() {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("hasPain", false);
        jsonObject.put("hasNausea", true);
        jsonObject.put("hasFever", true);
        jsonObject.put("hasFatigue", true);
        jsonObject.put("urineColor", "Cloudy");
        assertNotEquals(4, new CallbackLogic(jsonObject).getSeverity());
    }

    @Test
    public void severityCheck3() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("hasPain", false);
        jsonObject.put("hasNausea", true);
        jsonObject.put("hasFever", true);
        jsonObject.put("hasFatigue", false);
        jsonObject.put("urineColor", "Cloudy");
        assertEquals(4, new CallbackLogic(jsonObject).getSeverity());
    }
}