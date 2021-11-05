package com.revature.model;

import java.io.Serializable;

public class Users implements Serializable {

    private int userID;
    private String username;
    private String pincode;

    public Users(int userID, String username, String pincode) {
        this.userID = userID;
        this.username = username;
        this.pincode = pincode;
    }

    public int UserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String lastName) {
        this.pincode = pincode;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userID +
                ", username='" + username + '\'' +
                ", pincode='" + pincode + '}';
    }
}
