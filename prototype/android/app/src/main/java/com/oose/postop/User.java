package com.oose.postop;

/**
 * Created by Omotola on 10/12/2017.
 */

class User {
    private String email, password;

    public User(String email, String password) {
        this.email = email;
        this.password = password;

    }


    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
