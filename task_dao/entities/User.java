package Lesson4.entities;

/**
 * Created by Rapid Blaze on 09.10.2015.
 */
public class User {
    int id;
    String lastname;
    String firstname;
    int age;

    public void setId(int id) { this.id = id; }

    public int getId(){
        return id;
    }

    public void setLastname(String lastname){
        this.lastname = lastname;
    }

    public String getLastname(){
        return lastname;
    }

    public void setFirstname(String firstname){
        this.firstname = firstname;
    }

    public String getFirstname(){
        return firstname;
    }

    public void setAge(int age){
        this.age = age;
    }

    public int getAge(){return age;}

    @Override
    public String toString(){
        return id + " " + lastname + " " + firstname + " " + age;
    }
}
