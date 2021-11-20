package com.revature.model;

import java.io.Serializable;

public class User implements Serializable {
    private int accountNo;
    private String username;
    private String password;
    private double checkingBalance;
    private double savingsBalance;

    public User(){}

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    public User(int accountNo, String username, String password){
        this.accountNo = accountNo;
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, double checkingBalance, double savingsBalance){
        this.username = username;
        this.password = password;
        this.checkingBalance = checkingBalance;
        this.savingsBalance = savingsBalance;
    }

    public User(int accountNo, String username, String password, double checkingBalance, double savingsBalance){
        this.accountNo = accountNo;
        this.username = username;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
        return "User{" +
                "accountNo=" + accountNo +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", checkingBalance=' $'" + String.format("%.2f", checkingBalance) +
                ", savingsBalance=' $'" + String.format("%.2f", savingsBalance) +
                '}';
    }
}