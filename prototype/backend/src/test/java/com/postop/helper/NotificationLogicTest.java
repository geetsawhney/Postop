package com.postop.helper;

import org.testng.annotations.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class NotificationLogicTest {

    @Test
    public void testSomething(){

        assertEquals(new NotificationLogic().ageStatus("H",60),"M");
    }

    @Test
    public void  testNotificationLogic(){

        assertEquals(new NotificationLogic().ageStatus("L",20), "L");
    }

    @Test
    public void testShouldFail(){
        assertNotEquals(new NotificationLogic().ageStatus("L",20), "M");
    }

}