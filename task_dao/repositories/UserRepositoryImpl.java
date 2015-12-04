package Lesson4.repositories;

import Lesson4.entities.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rapid Blaze on 09.10.2015.
 */
public class UserRepositoryImpl implements UserRepository{

    @Override
    public List<User> getAllUsers(){
        List<User> users = new ArrayList<User>();
        try {
            String sqlSelect = "SELECT id, lastname, firstname, age FROM USERS";
            String url = "jdbc:derby:db;create=true;";

            Connection con = DriverManager.getConnection(url);
            Statement stmt = con.createStatement();

            ResultSet results = stmt.executeQuery(sqlSelect);

            while (results.next()) {
                Integer id = results.getInt(1);
                String lastName = results.getString(2);
                String firstName = results.getString(3);
                Integer age = results.getInt(4);

                User user = new User();
                user.setId(id);
                user.setLastname(lastName);
                user.setFirstname(firstName);
                user.setAge(age);
                users.add(user);
            }
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    };

    @Override
    public void addUser(User user){
        try {
            String url = "jdbc:derby:db;create=true;";

            Connection con = DriverManager.getConnection(url);

            Statement stmt = con.createStatement();

            String sql = "INSERT INTO USERS (firstname, lastname, age) VALUES ('" + user.getFirstname() + "', '" + user.getLastname() + "', " + user.getAge() + ")";
            stmt.executeUpdate(sql);

        }   catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateUser(User user){
        try {
            String url = "jdbc:derby:db;create=true;";

            Connection con = DriverManager.getConnection(url);

            Statement stmt = con.createStatement();

            String sql = "UPDATE USERS SET firstname='" + user.getFirstname() + "', lastname='" + user.getLastname() + "', age=" + user.getAge() +" WHERE id="+ user.getId();
            stmt.executeUpdate(sql);

        }   catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUser(int id){
        try {
            String url = "jdbc:derby:db;create=true;";
            Connection con = DriverManager.getConnection(url);
            Statement stmt = con.createStatement();

            User user = new User();
            String sql = "SELECT * FROM USERS WHERE id=" + id;
            ResultSet results = stmt.executeQuery(sql);
            results.next();
            Integer id1 = results.getInt(1);
            String lastName = results.getString(2);
            String firstName = results.getString(3);
            Integer age = results.getInt(4);
            user.setId(id1);
            user.setLastname(lastName);
            user.setFirstname(firstName);
            user.setAge(age);
            System.out.println("Deleted: " + user);

            sql = "DELETE FROM USERS WHERE id=" + id;
            stmt.executeUpdate(sql);

        }   catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public User findUserByFirstname(String firstname){
        User user = new User();
        try {
            String url = "jdbc:derby:db;create=true;";

            Connection con = DriverManager.getConnection(url);

            Statement stmt = con.createStatement();

            String sql = "SELECT * FROM USERS WHERE firstname = '" + firstname + "'";

            ResultSet results = stmt.executeQuery(sql);
            results.next();
            Integer id = results.getInt(1);
            String lastName = results.getString(2);
            String firstName = results.getString(3);
            Integer age = results.getInt(4);

            user.setId(id);
            user.setLastname(lastName);
            user.setFirstname(firstName);
            user.setAge(age);
            System.out.println(user);

        }   catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public void writeUserCount(){
        System.out.println("User count = " + getAllUsers().size());
    }




}
