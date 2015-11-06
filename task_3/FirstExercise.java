package Lesson3;

import java.sql.*;

/**
 * Created by Rapid Blaze on 02.10.2015.
 */
public class FirstExercise {
    public static void main(String[] args) {
        try {

            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");

        } catch (ClassNotFoundException e) {

            e.printStackTrace();

        }
        createTable();
        addStudents();
        showStudents();
        System.out.println("------------------------------------------------------");
        selectStudents();
        System.out.println("------------------------------------------------------");
        selectDStudents();
    }

    private static void createTable(){
        try {
            String url = "jdbc:derby:memory:db;create=true";

            Connection con = DriverManager.getConnection(url);

            DatabaseMetaData dbmd = con.getMetaData();

            System.out.println("\nConnected with " +

                    dbmd.getDriverName() + " " + dbmd.getDriverVersion() + "{ " +

                    dbmd.getDriverMajorVersion() + ",” " +

                    +dbmd.getDriverMinorVersion() + " }" + " to "

                    + dbmd.getDatabaseProductName() + " "

                    + dbmd.getDatabaseProductVersion() + "\n");

            Statement stmt = con.createStatement();

            String sql = "CREATE TABLE STUDENTS " +

                    "(id INTEGER not NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), " +

                    " firstname VARCHAR(255), " +

                    " lastname VARCHAR(255), " +

                    " middlename VARCHAR(255), " +

                    " age INTEGER, " +

                    " PRIMARY KEY ( id ))";

            stmt.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void addStudents(){
        try {
            String url = "jdbc:derby:memory:db;create=true";

            Connection con = DriverManager.getConnection(url);

            Statement stmt = con.createStatement();

            String sql = "INSERT INTO STUDENTS (firstname, lastname, age) " + "VALUES ('Jora', 'Grishkov', 18)";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO STUDENTS (firstname, lastname, age) " + "VALUES ('Ayrat', 'Natfullin', 31)";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO STUDENTS (firstname, lastname, age) " + "VALUES ('Pavel', 'Ivanov', 95)";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO STUDENTS (firstname, lastname, age) " + "VALUES ('Alex', 'Lushnikov', 19)";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO STUDENTS (firstname, lastname, age) " + "VALUES ('Pavel', 'Aprockiy', 25)";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO STUDENTS (firstname, lastname, age) " + "VALUES ('Vovan', 'Namba', 1)";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO STUDENTS (firstname, lastname, age) " + "VALUES ('Yan', 'Aprockiy', 19)";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO STUDENTS (firstname, lastname, age) " + "VALUES ('Levap', 'Vokhsirgd', 18)";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO STUDENTS (firstname, lastname, age) " + "VALUES ('Grandpa', 'Maxim', 105)";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO STUDENTS (firstname, lastname, age) " + "VALUES ('Vladimir', 'Dutin', 65)";
            stmt.executeUpdate(sql);

        }   catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void showStudents(){
        try {
            String sqlSelect = "Select firstname, lastname, age from STUDENTS";
            String url = "jdbc:derby:memory:db;create=true";

            Connection con = DriverManager.getConnection(url);
            Statement stmt = con.createStatement();

            ResultSet results = stmt.executeQuery(sqlSelect);

            while (results.next()) {
                String name = results.getString(1);
                String lastname = results.getString(2);
                Integer age = results.getInt(3);
                StringBuilder sb = new StringBuilder("name=");
                sb.append(name);
                sb.append(", lastname=");
                sb.append(lastname);
                sb.append(", age=");
                sb.append(age);
                System.out.println(sb.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void selectStudents(){
        try {
            String sqlSelect = "SELECT firstname, lastname, age FROM STUDENTS WHERE mod(id, 2) = 1 ORDER BY lastname ASC, firstname DESC";
            String url = "jdbc:derby:memory:db;create=true";

            Connection con = DriverManager.getConnection(url);
            Statement stmt = con.createStatement();

            ResultSet results = stmt.executeQuery(sqlSelect);

            while (results.next()) {
                String name = results.getString(1);
                String lastname = results.getString(2);
                Integer age = results.getInt(3);
                StringBuilder sb = new StringBuilder("name=");
                sb.append(name);
                sb.append(", lastname=");
                sb.append(lastname);
                sb.append(", age=");
                sb.append(age);
                System.out.println(sb.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void selectDStudents(){
        try {
            String sqlSelect = "SELECT firstname, lastname, age FROM STUDENTS WHERE Upper(lastname) LIKE '%D%'";
            String url = "jdbc:derby:memory:db;create=true";

            Connection con = DriverManager.getConnection(url);
            Statement stmt = con.createStatement();

            ResultSet results = stmt.executeQuery(sqlSelect);

            while (results.next()) {
                String name = results.getString(1);
                String lastname = results.getString(2);
                Integer age = results.getInt(3);
                StringBuilder sb = new StringBuilder("name=");
                sb.append(name);
                sb.append(", lastname=");
                sb.append(lastname);
                sb.append(", age=");
                sb.append(age);
                System.out.println(sb.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
