package com.revature.persistence;

import com.revature.model.AccountTypes;
import com.revature.util.ConnectionSingleton;

import javax.security.auth.login.AccountException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class AccountTypesDao implements Dao<AccountTypes>{

    @Override
    public void create(AccountTypes accountTypes) {
        String sql = "insert into account_types(account_type_id, account_type_name) values(?,?)";

        try(Connection connection = ConnectionSingleton.getInstance()){
            assert connection != null;
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, accountTypes.getAccountTypeID());
            stmt.setString(2, accountTypes.getAccountTypeName());

            stmt.executeUpdate();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public AccountTypes getByID(int id) {
        String sql = "select * from account_types where account_type_id=?";
        AccountTypes accountTypes = null;
        try(Connection connection = ConnectionSingleton.getInstance()){
            assert connection != null;
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                accountTypes = new AccountTypes();
                accountTypes.setAccountTypeID(rs.getInt(1));
                accountTypes.setAccountTypeName(rs.getString(2));
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return accountTypes;
    }

    public AccountTypes getByAccountTypeName(String account_type_name){
        String sql = "select * from account_types where account_type_name=?";
        AccountTypes accountTypes = null;
        try(Connection connection = ConnectionSingleton.getInstance()){
            assert connection != null;
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, account_type_name);

            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                accountTypes = new AccountTypes();
                accountTypes.setAccountTypeID(rs.getInt(1));
                accountTypes.setAccountTypeName(rs.getString(2));
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return accountTypes;
    }

    @Override
    public List<AccountTypes> getAll() {
        return null;
    }

    @Override
    public boolean update(AccountTypes accountTypes) {
        String sql = "update account_types set account_type_name=? where account_type_id=?";

        try(Connection connection = ConnectionSingleton.getInstance()){
            assert connection != null;
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, accountTypes.getAccountTypeName());
            stmt.setInt(3, accountTypes.getAccountTypeID());

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
