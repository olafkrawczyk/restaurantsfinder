package com.okrawczy.restaurantsfinder.service.requestwrapper;

/**
 * Created by Olaf on 2017-10-17.
 */


public class CredentialsWrapper {
    private String emailAddress;
    private String password;

    public CredentialsWrapper() {
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
