package com.postop.model;

import java.util.ArrayList;

public class Nurse {
    String name;
    ArrayList<Patient> patients;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Patient> getPatients() {
        return patients;
    }

    public void setPatients(ArrayList<Patient> patients) {
        this.patients = patients;
    }
    public void addPatient(Patient patient){

    }
}
