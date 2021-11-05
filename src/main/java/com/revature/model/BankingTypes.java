package com.revature.model;

import java.io.Serializable;
import java.util.Objects;

public class BankingTypes implements Serializable {

    private int bankingTypeID;
    private String bankingTypeName;

    public BankingTypes(int bankingTypeID, String bankingTypeName) {
        this.bankingTypeID = bankingTypeID;
        this.bankingTypeName = bankingTypeName;
    }

    public int getBankingTypeID() {
        return bankingTypeID;
    }

    public void setBankingTypeID(int bankingTypeID) {
        this.bankingTypeID = bankingTypeID;
    }

    public String getBankingTypeName() {
        return bankingTypeName;
    }

    public void setBankingTypeName(String bankingTypeName) {
        this.bankingTypeName = bankingTypeName;
    }

    @Override
    public String toString() {
        return "BankingType{" +
                "bankingTypeID =" + bankingTypeID +
                ", bankingTypeName ='" + bankingTypeName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankingTypes bankingTypes = (BankingTypes) o;
        return bankingTypeID == bankingTypes.bankingTypeID && Objects.equals(bankingTypeName, bankingTypes.bankingTypeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bankingTypeID, bankingTypeName);
    }
}
