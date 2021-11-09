package com.revature.model;

import java.io.Serializable;

public class User implements Serializable {
    private int accountNo;
    private String username;
    private String pincode;
    private double checkingBalance;
    private double savingsBalance;

    public User(){}

    public User(String username, String pincode){
        this.username = username;
        this.pincode = pincode;
    }

    public User(int accountNo, String username, String pincode){
        this.accountNo = accountNo;
        this.username = username;
        this.pincode = pincode;
    }

    public User(int accountNo, String username, String pincode, double checkingBalance, double savingsBalance){
        this.accountNo = accountNo;
        this.username = username;
        this.pincode = pincode;
        this.checkingBalance = checkingBalance;
        this.savingsBalance = savingsBalance;
    }

    public int getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(int accountNo) {
        this.accountNo = accountNo;
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

    public double getCheckingBalance() {
        return checkingBalance;
    }

    public void setCheckingBalance(double checkingBalance) {
        this.checkingBalance = checkingBalance;
    }

    public double getSavingsBalance() {
        return savingsBalance;
    }

    public void setSavingsBalance(double savingsBalance) {
        this.savingsBalance = savingsBalance;
    }

    @Override
    public String toString() {
        //String checking = String.format("%.2f", checkingBalance));
        //String savings = String.format("%.2f", savingsBalance))
        return "User{" +
                "accountNo=" + accountNo +
                ", username='" + username + '\'' +
                ", pin='" + pincode + '\'' +
                ", checkingBalance=' $'" + /*checking*/ checkingBalance +
                ", savingsBalance=' $'" + /*savings*/ savingsBalance +
                '}';
    }
}