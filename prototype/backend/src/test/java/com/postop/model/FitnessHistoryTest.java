package com.postop.model;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class FitnessHistoryTest {
    @Test
    public void getEmail() throws Exception {
        FitnessHistory fh=new FitnessHistory();
        assertNull(fh.getEmail());
    }

    @Test
    public void getCaptureDate() throws Exception {
        FitnessHistory fh=new FitnessHistory();
        assertNull(fh.getCaptureDate());
    }

    @Test
    public void setCaptureDate() throws Exception {
        FitnessHistory fh=new FitnessHistory();
        fh.setCaptureDate(new Date());
        assertNotEquals("",fh.getCaptureDate());
    }

    @Test
    public void getCaloriesExpended() throws Exception {
        FitnessHistory fh=new FitnessHistory();
        assertEquals(0,fh.getCaloriesExpended());
    }

}