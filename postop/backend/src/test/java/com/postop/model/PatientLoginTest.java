package com.postop.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class PatientLoginTest {

    @Test
    public void getEmail() {
        PatientLogin patientLogin=new PatientLogin();
        patientLogin.setEmail("test1@test.com");
        assertEquals("test1@test.com",patientLogin.getEmail());
    }

    @Test
    public void getPassword() {
        PatientLogin patientLogin=new PatientLogin();
        patientLogin.setPassword("test1@test.com");
        assertEquals("test1@test.com",patientLogin.getPassword());

    }
}