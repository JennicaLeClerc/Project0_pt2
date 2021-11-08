package com.revature.service;

import com.revature.model.BankingTypes;
import com.revature.util.ConnectionSingleton;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Project0Service {

    public List<BankingTypes> getAllBankingTypes() {
        String sql = "select * from banking_types";
        List<BankingTypes> bankingtypeslist = new ArrayList<>();

        try(Connection connection = ConnectionSingleton.getInstance()){
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                bankingtypeslist.add( new BankingTypes(rs.getInt(1), rs.getString(2)));
                System.out.println("name " + rs.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bankingtypeslist;
    }

    /*public Users getArtistsByName(String name) {
        String sql = "select * from \"Artist\" where \"Name\" = '" + name + "'";
        Artist artist = null;

        try(Connection connection = ConnectionSingleton.getInstance()){
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                artist = new Artist(
                        rs.getInt(1),
                        rs.getString(2));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return artist;
    }*/
}
