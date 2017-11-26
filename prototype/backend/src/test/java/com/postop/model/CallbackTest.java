package com.postop.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class CallbackTest {

    @Test
    public void testCallBack(){
        Callback c = new Callback();
        c.setEmail("oosegroup19@gmail.com");
        c.setCallbackDate("11-11-2011");
        c.setHasFatigue(true);
        c.setHasFever(true);
        c.setHasPain(true);
        c.setIsResolved(false);
        c.setHasNausea(false);
        c.setUrineColor("Cloudy");
        c.setSeverity(10);

        c.getHasFatigue();
        c.getHasFever();
        c.getHasNausea();
        c.getHasPain();
        c.getCallbackDate();
        c.getDateString();
        c.getEmail();
        c.getIsResolved();
        c.getLogger();
        c.getSeverity();
        c.getUrineColor();

    }


}