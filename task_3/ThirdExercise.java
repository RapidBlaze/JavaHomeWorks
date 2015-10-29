package Lesson3;

import java.sql.*;

/**
 * Created by Rapid Blaze on 07.10.2015.
 */
public class ThirdExercise {
    public static void main(String[] args) {
        createTableOrders();
        createTableStudents();
        addOrders();
        addStudents();
        crossJoin();
        leftJoin();
        rightJoin();
        System.out.println("------------------------------------------------------");
    }

    private static void createTableStudents(){
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

    private static void createTableOrders(){
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

            String sql = "CREATE TABLE ORDERS " +

                    "(id INTEGER not NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), " +

                    " customerId INTEGER, " +

                    " salesPersonId INTEGER, " +

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
            sql = "INSERT INTO STUDENTS (firstname, lastname, age) " + "VALUES ('Petr', 'Ivanov', 95)";
            stmt.executeUpdate(sql);

        }   catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void addOrders(){
        try {
            String url = "jdbc:derby:memory:db;create=true";

            Connection con = DriverManager.getConnection(url);

            Statement stmt = con.createStatement();

            String sql = "INSERT INTO ORDERS (customerId, salesPersonId) " + "VALUES ( 1, 2)";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO ORDERS (customerId, salesPersonId) " + "VALUES ( 3, 1)";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO ORDERS (customerId, salesPersonId) " + "VALUES ( 3, 2)";
            stmt.executeUpdate(sql);

        }   catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void crossJoin(){
        try {
            System.out.println("------------------------------------------------------");
            System.out.println("CROSSJOIN:");
            String sqlSelect = "SELECT s.firstname, o.id FROM STUDENTS s CROSS JOIN ORDERS o";
            String url = "jdbc:derby:memory:db;create=true";
            Connection con = DriverManager.getConnection(url);
            Statement stmt = con.createStatement();
            ResultSet results = stmt.executeQuery(sqlSelect);

            while (results.next()) {
                String firstname = results.getString(1);
                Integer id = results.getInt(2);
                StringBuilder sb = new StringBuilder("");
                sb.append(firstname);
                sb.append(" -> ");
                sb.append(id);
                System.out.println(sb.toString());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void leftJoin(){
        try {
            System.out.println("------------------------------------------------------");
            System.out.println("LEFTJOIN:");
            String sqlSelect = "SELECT s.firstName as Customer, o.customerId FROM STUDENTS s LEFT JOIN ORDERS o ON s.id = o.customerId";
            String url = "jdbc:derby:memory:db;create=true";
            Connection con = DriverManager.getConnection(url);
            Statement stmt = con.createStatement();
            ResultSet results = stmt.executeQuery(sqlSelect);

            while (results.next()) {
                String firstname = results.getString(1);
                String id = results.getString(2);
                StringBuilder sb = new StringBuilder("");
                sb.append(firstname);
                sb.append(" -> ");
                sb.append(id);
                System.out.println(sb.toString());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void rightJoin(){
        try {
            System.out.println("------------------------------------------------------");
            System.out.println("RIGHTJOIN:");
            String sqlSelect = "SELECT s.firstName as Customer, o.customerId FROM STUDENTS s RIGHT JOIN ORDERS o ON s.id = o.customerId";
            String url = "jdbc:derby:memory:db;create=true";
            Connection con = DriverManager.getConnection(url);
            Statement stmt = con.createStatement();
            ResultSet results = stmt.executeQuery(sqlSelect);

            while (results.next()) {
                String firstname = results.getString(1);
                String id = results.getString(2);
                StringBuilder sb = new StringBuilder("");
                sb.append(firstname);
                sb.append(" -> ");
                sb.append(id);
                System.out.println(sb.toString());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
