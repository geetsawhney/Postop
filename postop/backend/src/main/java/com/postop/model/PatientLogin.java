package com.postop.model;

/**
 *This class represents a model of a patients login
 * @author Geet Sawhney, Rohit Aakash
 */
public class PatientLogin {
    private String email;
    private String password;

    /**
     * @return email of the patient
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email : email to be set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return current password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password : password to be set
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
