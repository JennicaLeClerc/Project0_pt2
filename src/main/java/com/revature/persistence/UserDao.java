package com.revature.persistence;

import com.revature.model.User;
import com.revature.util.ConnectionSingleton;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class UserDao implements Dao<User>{

    @Override
    public void create(User user) {
        String sql = "insert into user(account_no, username, pincode, checking_balance, savings_balance) values(?,?,?,?,?)";

        try(Connection connection = ConnectionSingleton.getInstance()){
            assert connection != null;
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, user.getAccountNo());
            stmt.setString(2, user.getUsername());
            stmt.setString(3, user.getPincode());
            stmt.setDouble(4, user.getCheckingBalance());
            stmt.setDouble(5, user.getSavingsBalance());

            stmt.executeUpdate();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public User getByID(int id) {
        String sql = "select * from user where account_no=?";
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
                user.setPincode(rs.getString(3));
                user.setCheckingBalance(rs.getDouble(4));
                user.setSavingsBalance(rs.getDouble(5));
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return user;
    }

    public User getByUsername(String username){
        String sql = "select * from user where username=?";
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
                user.setPincode(rs.getString(3));
                user.setCheckingBalance(rs.getDouble(4));
                user.setSavingsBalance(rs.getDouble(5));
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return user;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public boolean update(User user) {
        String sql = "update users set username=?, pincode=? where account_no=?";

        try(Connection connection = ConnectionSingleton.getInstance()){
            assert connection != null;
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPincode());
            stmt.setInt(3, user.getAccountNo());
//            if (stmt.executeUpdate() != 0) {
//                return true;
//            }

            // shorthand combination returning a conditional operation
            return stmt.executeUpdate() != 0;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean updateBalance(User user){
        String sql = "update users set checking_balance=?, savings_balance=? where account_no=?";

        try(Connection connection = ConnectionSingleton.getInstance()){
            assert connection != null;
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setDouble(1, user.getCheckingBalance());
            stmt.setDouble(2, user.getSavingsBalance());
            stmt.setInt(3, user.getAccountNo());

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
