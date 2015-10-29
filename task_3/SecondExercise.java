package Lesson3;

import java.sql.*;

/**
 * Created by Rapid Blaze on 07.10.2015.
 */
public class SecondExercise {

    public static void main(String[] args) {
        createTableStudents();
        addStudents();
        createTableOrders();
        addOrders();
        showOrders();
        showOutput();
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

    private static void addOrders(){
        try {
            String url = "jdbc:derby:memory:db;create=true";

            Connection con = DriverManager.getConnection(url);

            Statement stmt = con.createStatement();

            String sql = "INSERT INTO ORDERS (customerId, salesPersonId) " + "VALUES ( 1, 8)";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO ORDERS (customerId, salesPersonId) " + "VALUES ( 3, 4)";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO ORDERS (customerId, salesPersonId) " + "VALUES ( 5, 6)";
            stmt.executeUpdate(sql);

        }   catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void showOrders(){
        try {
            String sqlSelect = "SELECT id, customerId, salesPersonId FROM ORDERS";
            String url = "jdbc:derby:memory:db;create=true";

            Connection con = DriverManager.getConnection(url);
            Statement stmt = con.createStatement();

            ResultSet results = stmt.executeQuery(sqlSelect);

            while (results.next()) {
                String id = results.getString(1);
                String customerId = results.getString(2);
                Integer salesPersonId = results.getInt(3);
                StringBuilder sb = new StringBuilder("");
                sb.append(id);
                sb.append("|");
                sb.append(customerId);
                sb.append("|");
                sb.append(salesPersonId);
                System.out.println(sb.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void showOutput(){
        try {
            System.out.println("Output:");
            String sqlSelect = "SELECT o.id, s.lastname || ' ' || s.firstname AS CustomerName, p.lastname || ' ' || p.firstname AS SalesPersonName FROM  ORDERS o, STUDENTS s, STUDENTS p WHERE s.id = o.customerId AND p.id = o.salesPersonId ORDER BY s.id ASC";
            String url = "jdbc:derby:memory:db;create=true";

            Connection con = DriverManager.getConnection(url);
            Statement stmt = con.createStatement();

            ResultSet results = stmt.executeQuery(sqlSelect);

            while (results.next()) {
                Integer id = results.getInt(1);
                String CustomerName = results.getString(2);
                String SalesPersonName = results.getString(3);

                StringBuilder sb = new StringBuilder("");
                sb.append(id);
                sb.append(" ");
                sb.append(CustomerName);
                sb.append(" -> ");
                sb.append(SalesPersonName);
                System.out.println(sb.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
