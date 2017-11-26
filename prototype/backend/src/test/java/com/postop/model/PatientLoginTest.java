package com.postop.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class PatientLoginTest {

    @Test
    public void getEmail() throws Exception {
        PatientLogin pl=new PatientLogin();

        assertNull(pl.getEmail());
    }

    @Test
    public void setEmail() throws Exception {
        PatientLogin pl=new PatientLogin();
        pl.setEmail("testing");
        assertEquals("testing", pl.getEmail());
    }

    @Test
    public void getPassword() throws Exception {
        PatientLogin pl=new PatientLogin();
        assertEquals(null,pl.getPassword());
    }

    @Test
    public void setPassword() throws Exception {
        PatientLogin pl=new PatientLogin();
        pl.setPassword("testing");
        assertEquals("testing", pl.getPassword());

    }

}