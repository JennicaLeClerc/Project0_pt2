package com.revature.persistence;

import com.revature.model.Banking;
import com.revature.util.ConnectionSingleton;

import java.sql.*;
import java.util.List;

public class BankingDao implements Dao<Banking>{

    @Override
    public void create(Banking banking) {
        String sql = "insert into banking(invoice_date, amount, banking_type_id, account_type_id) values(?,?,?,?)";
        java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        
        try(Connection connection = ConnectionSingleton.getInstance()){
            assert connection != null;
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setDate(1, sqlDate);
            stmt.setDouble(2, banking.getAmount());
            stmt.setInt(3, banking.getBankingTypeID());
            stmt.setInt(4, banking.getAccountTypeID());

            stmt.executeUpdate();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        Link(banking.getAccountNo(),getLastTransactionID());
    }

    public int getLastTransactionID(){
        String sql = "select * from banking order by transaction_id desc limit 1;";
        try (Connection connection= ConnectionSingleton.getInstance()) {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()){
                return resultSet.getInt(1);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
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

    @Override
    public void update(Banking banking) {
        String sql = "update banking set invoice_date=?, amount=?, banking_type_id=?, account_type_id=? where transaction_id=?";
        java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

        try(Connection connection = ConnectionSingleton.getInstance()){
            assert connection != null;
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setDate(1, sqlDate);
            stmt.setDouble(2, banking.getAmount());
            stmt.setInt(3, banking.getBankingTypeID());
            stmt.setInt(4, banking.getAccountTypeID());
            stmt.setInt(5, banking.getTransactionID());

            stmt.executeUpdate();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteByID(int id) {}
}
