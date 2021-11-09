package com.revature.persistence;

import com.revature.model.Person;
import com.revature.util.ConnectionSingleton;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class PersonDao implements Dao<Person>{

    @Override
    public void create(Person person) {
        String sql = "insert into person(first_name, last_name) values(?,?)";

        try(Connection connection = ConnectionSingleton.getInstance()){
            assert connection != null;
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, person.getFirstName());
            stmt.setString(2, person.getLastName());

            stmt.executeUpdate();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Person getByID(int id) {
        String sql = "select * from person where person_id=?";
        Person person = null;
        try(Connection connection = ConnectionSingleton.getInstance()){
            assert connection != null;
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                person = new Person();
                person.setPersonID(rs.getInt(1));
                person.setFirstName(rs.getString(2));
                person.setLastName(rs.getString(3));
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return person;
    }

    @Override
    public List<Person> getAll() {
        return null;
    }

    @Override
    public boolean update(Person person) {
        String sql = "update person set first_name=?, last_name=? where person_id=?";

        try(Connection connection = ConnectionSingleton.getInstance()){
            assert connection != null;
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, person.getFirstName());
            stmt.setString(2, person.getLastName());
            stmt.setInt(3, person.getPersonID());
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
