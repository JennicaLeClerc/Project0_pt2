package com.revature.model;

import java.io.Serializable;
import java.util.Date;

public class Banking implements Serializable {
    private int transactionID;
    private Date invoiceDate;
    private double amount;
    private int bankingTypeID;
    private int accountTypeID;

    private int accountNo;

    public Banking(){}

    public Banking(int transactionID, Date invoiceDate, double amount, int bankingTypeID, int accountTypeID) {
        this.transactionID = transactionID;
        this.invoiceDate = invoiceDate;
        this.amount = amount;
        this.bankingTypeID = bankingTypeID;
        this.accountTypeID = accountTypeID;
    }

    public Banking(int transactionID, Date invoiceDate, double amount, int bankingTypeID, int accountTypeID, int accountNo) {
        this.transactionID = transactionID;
        this.invoiceDate = invoiceDate;
        this.amount = amount;
        this.bankingTypeID = bankingTypeID;
        this.accountTypeID = accountTypeID;
        this.accountNo = accountNo;
    }

    public int getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getBankingTypeID() {
        return bankingTypeID;
    }

    public void setBankingTypeID(int bankingTypeID) {
        this.bankingTypeID = bankingTypeID;
    }

    public int getAccountTypeID() {
        return accountTypeID;
    }

    public void setAccountTypeID(int accountTypeID) {
        this.accountTypeID = accountTypeID;
    }

    public int getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(int accountNo) {
        this.accountNo = accountNo;
    }

    @Override
    public String toString() {
        return "Banking{" +
                "transactionID=" + transactionID +
                ", invoiceDate='" + invoiceDate + '\'' +
                ", amount='" + String.format("%.2f", amount) + '\'' +
                ", bankingTypeID='" + bankingTypeID + '\'' +
                ", accountTypeID='" + accountTypeID +
                '}';
    }
}