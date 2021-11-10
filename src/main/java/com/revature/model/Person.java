package com.revature.model;

import java.io.Serializable;

public class Person implements Serializable {
    private int personID;
    private String firstName;
    private String lastName;

    private int accountNo;

    public Person(){}

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Person(int personID, String firstName, String lastName) {
        this.personID = personID;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Person(int personID, String firstName, String lastName, int accountNo) {
        this.personID = personID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.accountNo = accountNo;
    }

    public int getPersonID() {
        return personID;
    }

    public void setPersonID(int personID) {
        this.personID = personID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(int accountNo) {
        this.accountNo = accountNo;
    }

    @Override
    public String toString() {
        return "Person{" +
                "personID =" + personID +
                ", firstName ='" + firstName + '\'' +
                ", lastName =' " + lastName + '\'' +
                '}';
    }
}
