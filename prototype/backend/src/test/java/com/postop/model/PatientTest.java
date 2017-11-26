package com.postop.model;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class PatientTest {
    @Test
    public void setLastVisitDate() throws Exception {
        Patient patient=new Patient();
        patient.setLastVisitDate(new Date());
        assertEquals(new Date(),patient.getLastVisitDate());
    }

    @Test
    public void setDob() throws Exception {
        Patient patient=new Patient();
        patient.setDob(new Date());
        assertEquals(new Date(),patient.getDob());
    }

}