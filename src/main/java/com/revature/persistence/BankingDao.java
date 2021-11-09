package com.revature.persistence;

import com.revature.model.Banking;
import com.revature.util.ConnectionSingleton;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class BankingDao implements Dao<Banking>{

    @Override
    public void create(Banking banking) {
        String sql = "insert into banking(transaction_id, invoice_date, amount, banking_type_id, account_type_id) values(?,?,?,?,?)";

        try(Connection connection = ConnectionSingleton.getInstance()){
            assert connection != null;
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, banking.getTransactionID());
            stmt.setDate(2, (Date) banking.getInvoiceDate());
            stmt.setDouble(3, banking.getAmount());
            stmt.setInt(4, banking.getBankingTypeID());
            stmt.setInt(5, banking.getAccountTypeID());

            stmt.executeUpdate();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        Link(banking.getAccountNo(),banking.getTransactionID());
    }

    public void Link(int accountNo,int transactionID){
        String sql = "insert into transaction(account_no,transaction_id) VALUES (?,?)";
        try (Connection connection = ConnectionSingleton.getInstance()) {
            assert connection != null;
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, accountNo);
            stmt.setInt(2, transactionID);

            stmt.executeUpdate();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Banking getByID(int id) {
        String sql = "select * from banking where transaction_id=?";
        Banking banking = null;
        try(Connection connection = ConnectionSingleton.getInstance()){
            assert connection != null;
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                banking = new Banking();
                banking.setTransactionID(rs.getInt(1));
                banking.setInvoiceDate(rs.getDate(2));
                banking.setAmount(rs.getDouble(3));
                banking.setBankingTypeID(rs.getInt(4));
                banking.setAccountTypeID(rs.getInt(5));
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return banking;
    }

    public Banking getByDate(Date date){
        String sql = "select * from banking where invoice_date=?";
        Banking banking = null;
        try(Connection connection = ConnectionSingleton.getInstance()){
            assert connection != null;
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setDate(1, date);

            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                banking = new Banking();
                banking.setTransactionID(rs.getInt(1));
                banking.setInvoiceDate(rs.getDate(2));
                banking.setAmount(rs.getDouble(3));
                banking.setBankingTypeID(rs.getInt(4));
                banking.setAccountTypeID(rs.getInt(5));
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return banking;
    }

    @Override
    public List<Banking> getAll() {
        return null;
    }

    @Override
    public boolean update(Banking banking) {
        String sql = "update banking set invoice_date=?, amount=?, banking_type_id=?, account_type_id=? where transaction_id=?";

        try(Connection connection = ConnectionSingleton.getInstance()){
            assert connection != null;
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setDate(1, (Date) banking.getInvoiceDate());
            stmt.setDouble(2, banking.getAmount());
            stmt.setInt(3, banking.getBankingTypeID());
            stmt.setInt(4, banking.getAccountTypeID());
            stmt.setInt(5, banking.getTransactionID());

            return stmt.executeUpdate() != 0;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean deleteByID(int id) {
        return false;
    }
}
