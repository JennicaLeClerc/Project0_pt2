package com.revature.persistence;

import com.revature.model.*;
import com.revature.util.ConnectionSingleton;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDao implements Dao<User>{

    @Override
    public void create(User user) {
        String sql = "insert into users(account_no, username, password, checking_balance, savings_balance) values(?,?,?,?,?)";

        try(Connection connection = ConnectionSingleton.getInstance()){
            assert connection != null;
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, user.getAccountNo());
            stmt.setString(2, user.getUsername());
            stmt.setString(3, user.getPassword());
            stmt.setDouble(4, user.getCheckingBalance());
            stmt.setDouble(5, user.getSavingsBalance());

            stmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public User getByID(int id) {
        String sql = "select * from users where account_no=?";
        User user = null;
        try(Connection connection = ConnectionSingleton.getInstance()){
            assert connection != null;
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                user = new User();
                user.setAccountNo(rs.getInt(1));
                user.setUsername(rs.getString(2));
                user.setPassword(rs.getString(3));
                user.setCheckingBalance(rs.getDouble(4));
                user.setSavingsBalance(rs.getDouble(5));
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return user;
    }

    public void printAllTransactions(User user){
        String sql = "select * from transaction where account_no=?";

        Banking banking;
        try(Connection connection = ConnectionSingleton.getInstance()){
            assert connection != null;
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, user.getAccountNo());

            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                int temp_transactionID = rs.getInt(2);
                String sql_ID = "select * from banking where transaction_id =?";
                try(Connection connection_ID = ConnectionSingleton.getInstance()) {
                    assert connection != null;
                    PreparedStatement stmt_ID = connection.prepareStatement(sql_ID);
                    stmt_ID.setInt(1, temp_transactionID);

                    ResultSet rs_ID = stmt_ID.executeQuery();

                    if(rs_ID.next()){
                        banking = new Banking();
                        banking.setTransactionID(rs.getInt(1));
                        banking.setInvoiceDate(rs.getDate(2));
                        banking.setAmount(rs.getDouble(3));
                        banking.setBankingTypeID(rs.getInt(4));
                        banking.setAccountTypeID(rs.getInt(5));

                        System.out.println(banking);
                    }

                }catch(Exception e){
                    System.out.println(e.getMessage());
                }
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public User getByUsername(String username){
        String sql = "select * from users where username=?";
        User user = null;
        try(Connection connection = ConnectionSingleton.getInstance()){
            assert connection != null;
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, username);

            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                user = new User();
                user.setAccountNo(rs.getInt(1));
                user.setUsername(rs.getString(2));
                user.setPassword(rs.getString(3));
                user.setCheckingBalance(rs.getDouble(4));
                user.setSavingsBalance(rs.getDouble(5));
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return user;
    }

    @Override
    public void update(User user) {
        String sql = "update users set username=?, password=? where account_no=?";

        try(Connection connection = ConnectionSingleton.getInstance()){
            assert connection != null;
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setInt(3, user.getAccountNo());

            stmt.executeUpdate();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void updateBalance(User user){
        String sql = "update users set checking_balance=?, savings_balance=? where account_no=?";

        try(Connection connection = ConnectionSingleton.getInstance()){
            assert connection != null;
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setDouble(1, user.getCheckingBalance());
            stmt.setDouble(2, user.getSavingsBalance());
            stmt.setInt(3, user.getAccountNo());

            stmt.executeUpdate();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteByID(int id) {
    }
}
