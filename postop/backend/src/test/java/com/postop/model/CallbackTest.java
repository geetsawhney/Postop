package com.postop.model;

import org.junit.Test;

import java.sql.Date;

import static org.junit.Assert.*;


public class CallbackTest {

    @Test
    public void getEmail() {
        Callback callback=new Callback();
        callback.setEmail("test1@test.com");
        assertEquals(callback.getEmail(),"test1@test.com");
    }


    @Test
    public void getCallbackDate() {
        Callback callback=new Callback();
        long time=System.currentTimeMillis();
        callback.setCallbackDate(new Date(time));
        assertEquals(callback.getCallbackDate(), new Date(time));
    }

    @Test
    public void getSeverity() {
        Callback callback=new Callback();
        callback.setSeverity(5);
        assertEquals(5,callback.getSeverity());
    }

    @Test
    public void getHasPain() {
        Callback callback=new Callback();
        callback.setHasPain(true);
        assertFalse(!callback.getHasPain());
    }

    @Test
    public void getHasNausea() {
        Callback callback=new Callback();
        callback.setHasNausea(true);
        assertFalse(!callback.getHasNausea());
    }


    @Test
    public void getHasFever() {
        Callback callback=new Callback();
        callback.setHasFever(true);
        assertTrue(callback.getHasFever());
    }

    @Test
    public void getHasFatigue() {
        Callback callback=new Callback();
        callback.setHasFatigue(true);
        assertTrue(callback.getHasFatigue());
    }

    @Test
    public void getUrineColor() {
        Callback callback=new Callback();
        callback.setUrineColor("Cloudy");
        assertEquals(callback.getUrineColor(),"Cloudy");
    }


    @Test
    public void getIsResolved() {
        Callback callback=new Callback();
        callback.setIsResolved(false);
        assertFalse(callback.getIsResolved());
    }

}