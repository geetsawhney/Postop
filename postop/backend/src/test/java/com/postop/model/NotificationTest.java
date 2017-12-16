package com.postop.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class NotificationTest {

    @Test
    public void getLabel() {
        Notification notification=new Notification();
        notification.setLabel("C");
        assertEquals(notification.getLabel(),"C");
    }

    @Test
    public void getStart() {
        Notification notification=new Notification();
        notification.setStart(1);
        assertEquals(notification.getStart(),1);
    }

    @Test
    public void getEnd() {

        Notification notification=new Notification();
        notification.setEnd(1);
        assertEquals(notification.getEnd(),1);
    }

    @Test
    public void getInterval() {

        Notification notification=new Notification();
        notification.setInterval(1);
        assertEquals(notification.getInterval(),1);

    }
}