package com.revature.persistence;

import com.revature.model.BankingTypes;
import com.revature.util.ConnectionSingleton;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class BankingTypesDao implements Dao<BankingTypes>{

    @Override
    public void create(BankingTypes bankingTypes) {
        String sql = "insert into banking_types(banking_type_id, banking_type_name) values(?,?)";

        try(Connection connection = ConnectionSingleton.getInstance()){
            assert connection != null;
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, bankingTypes.getBankingTypeID());
            stmt.setString(2, bankingTypes.getBankingTypeName());

            stmt.executeUpdate();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public BankingTypes getByID(int id) {
        String sql = "select * from banking_types where banking_type_id=?";
        BankingTypes bankingTypes = null;
        try(Connection connection = ConnectionSingleton.getInstance()){
            assert connection != null;
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                bankingTypes = new BankingTypes();
                bankingTypes.setBankingTypeID(rs.getInt(1));
                bankingTypes.setBankingTypeName(rs.getString(2));
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return bankingTypes;
    }

    public BankingTypes getByBankingTypeName(String username){
        String sql = "select * from user where banking_type_name=?";
        BankingTypes bankingTypes = null;
        try(Connection connection = ConnectionSingleton.getInstance()){
            assert connection != null;
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, username);

            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                bankingTypes = new BankingTypes();
                bankingTypes.setBankingTypeID(rs.getInt(1));
                bankingTypes.setBankingTypeName(rs.getString(2));
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return bankingTypes;
    }

    @Override
    public List<BankingTypes> getAll() {
        return null;
    }

    @Override
    public boolean update(BankingTypes bankingTypes) {
        String sql = "update users set banking_type_name=? where banking_type_id=?";

        try(Connection connection = ConnectionSingleton.getInstance()){
            assert connection != null;
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, bankingTypes.getBankingTypeName());
            stmt.setInt(3, bankingTypes.getBankingTypeID());

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