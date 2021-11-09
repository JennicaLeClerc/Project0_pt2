package com.revature.model;

import java.io.Serializable;
import java.util.Objects;

public class AccountTypes implements Serializable {
    private int accountTypeID;
    private String accountTypeName;

    public AccountTypes(){}

    public AccountTypes(int accountTypeID, String accountTypeName) {
        this.accountTypeID = accountTypeID;
        this.accountTypeName = accountTypeName;
    }

    public int getAccountTypeID() {
        return accountTypeID;
    }

    public void setAccountTypeID(int accountTypeID) {
        this.accountTypeID = accountTypeID;
    }

    public String getAccountTypeName() {
        return accountTypeName;
    }

    public void setAccountTypeName(String accountTypeName) {
        this.accountTypeName = accountTypeName;
    }

    @Override
    public String toString() {
        return "AccountType{" +
                "accountTypeID =" + accountTypeID +
                ", accountTypeName ='" + accountTypeName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountTypes accountTypes = (AccountTypes) o;
        return accountTypeID == accountTypes.accountTypeID && Objects.equals(accountTypeName, accountTypes.accountTypeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountTypeID, accountTypeName);
    }
}
