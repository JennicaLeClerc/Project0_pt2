package com.revature.model;

import java.io.Serializable;

public class User implements Serializable {
    private int userID;

    private String username;
    private String pincode;

    public User(){
    }

    public User(String username, String pincode){
        super(username, pincode);
    }

    public User(int userID, String username, String pincode){
        super(userID, username, pincode);
    }

    public User(int account_number, String firstName, String lastName, String username, String pincode, double checking_balance, double savings_balance){
        super( account_number, firstName, lastName, username, pincode, checking_balance, savings_balance);
    }

    /*public User(int userID, String firstName, String lastName, String username, String pincode, int account_number, double checking_balance, double savings_balance){
        super( userID, firstName, lastName, username, pincode, account_number, checking_balance, savings_balance);
    }*/

    public int getUserID() {
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
 
    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userID +
                ", username='" + username + '\'' +
                ", pin='" + pincode +
                '}';
    }
}