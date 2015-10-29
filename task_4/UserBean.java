package com.itis;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rapid Blaze on 20.10.2015.
 */
public class UserBean {

    public UserBean(){
        addUsers();
    }

    public List<User> userList = new ArrayList<User>();

    public static class User{
        String firstname;
        String lastname;
        int age;

        public User(String firstname, String lastname, int age){
            this.firstname = firstname;
            this.lastname = lastname;
            this.age = age;
        }

        public void setFirstname(String firstname){
            this.firstname = firstname;
        }

        public void setLastname(String lastname){
            this.lastname = lastname;
        }

        public void setAge(int age){
            this.age = age;
        }

        public String getFirstname(){
            return firstname;
        }

        public String getLastname(){
            return lastname;
        }

        public int getAge(){
            return age;
        }

    }

    public void addUsers(){
        User user = new User("Grishkov","Pavel",19);
        userList.add(user);
        user = new User("Lushnikov", "Aleksey", 20);
        userList.add(user);
        user = new User("Mercury", "Freddie", 60);
        userList.add(user);
        user = new User("Depp", "Johnny", 50);
        userList.add(user);
        user = new User("Pitt", "Bradley", 50);
        userList.add(user);
    }

    public List<User> getUserList(){
        return userList;
    }

    public void setUserList(List<User> usersList){
        userList = usersList;
    }

    public static void main(String[] args) {

    }

}
/* <jsp:useBean id="userBean" class="com.itis.UserBean" />
*
*/
