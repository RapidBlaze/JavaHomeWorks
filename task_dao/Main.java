package Lesson4;

import Lesson4.entities.Order;
import Lesson4.entities.User;
import Lesson4.repositories.OrderRepositoryImpl;
import Lesson4.repositories.UserRepositoryImpl;

import java.sql.*;
import java.util.List;

/**
 * Created by Rapid Blaze on 09.10.2015.
 */
public class Main {

    public static UserRepositoryImpl usi = new UserRepositoryImpl();
    public static OrderRepositoryImpl ori = new OrderRepositoryImpl();

    public static void main(String[] args) {

        createTableUsers();
        createTableOrders();

        addUsers();
        addOrders();

        testUserRepositoryImpl();
        testOrderRepositoryImpl();

    }

    private static void testUserRepositoryImpl(){
        System.out.println("UserRepositoryImpl tests:");
        System.out.println("----------------------------------------------------------------");
        System.out.println("1)");
        usi.writeUserCount();

        System.out.println("----------------------------------------------------------------");
        System.out.println("2)");
        showUsers();

        System.out.println("----------------------------------------------------------------");
        System.out.println("3)");
        add2Users();

        System.out.println("----------------------------------------------------------------");
        System.out.println("4)");
        usi.writeUserCount();

        System.out.println("----------------------------------------------------------------");
        System.out.println("5)");
        User test = usi.findUserByFirstname("Damir");

        System.out.println("----------------------------------------------------------------");
        System.out.println("6)");
        usi.deleteUser(test.getId());

        System.out.println("----------------------------------------------------------------");
        System.out.println("7) Edited first user");
        test.setId(1);
        test.setFirstname("Sasha");
        test.setAge(20);
        test.setLastname("Grishkov");
        usi.updateUser(test);

        System.out.println("----------------------------------------------------------------");
        System.out.println("8)");
        showUsers();

        System.out.println("----------------------------------------------------------------");
        System.out.println("9)");
        usi.writeUserCount();
        System.out.println("----------------------------------------------------------------");
    }

    private static void testOrderRepositoryImpl(){
        System.out.println("OrderRepositoryImpl tests:");
        System.out.println("----------------------------------------------------------------");
        System.out.println("1)");
        ori.writeOrderCount();

        System.out.println("----------------------------------------------------------------");
        System.out.println("2)");
        showOrders();

        System.out.println("----------------------------------------------------------------");
        System.out.println("3)");
        Order order = new Order();
        order.setId(3);
        order.setCustomerId(3);
        order.setSalesPersonId(1);
        ori.addOrder(order);

        System.out.println("----------------------------------------------------------------");
        System.out.println("4)");
        showOrders();

        System.out.println("----------------------------------------------------------------");
        System.out.println("5)");
        Order test = ori.findOrderByCustomerId(2);

        System.out.println("----------------------------------------------------------------");
        System.out.println("6)");
        ori.deleteOrder(test.getId());

        System.out.println("----------------------------------------------------------------");
        System.out.println("7) Edited first order");
        test.setId(1);
        test.setCustomerId(3);
        test.setSalesPersonId(2);
        ori.updateOrder(test);

        System.out.println("----------------------------------------------------------------");
        System.out.println("8)");
        showOrders();

        System.out.println("----------------------------------------------------------------");
        System.out.println("9)");
        ori.writeOrderCount();
        System.out.println("----------------------------------------------------------------");

    }

    private static void createTableUsers(){
        try {
            String url = "jdbc:derby:db;create=true;";

            Connection con = DriverManager.getConnection(url);

            Statement stmt = con.createStatement();

            String sql = "CREATE TABLE USERS " +
                    "(id INTEGER not NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), " +
                    " firstname VARCHAR(255), " +
                    " lastname VARCHAR(255), " +
                    " age INTEGER, " +
                    " PRIMARY KEY ( id ))";
            try {
                stmt.executeUpdate(sql);
            } catch (SQLException e) {
                if (e.getSQLState().equals("X0Y32")) {
                } else {
                    throw e;
                }
            }
            con.close();
        }   catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void createTableOrders(){
        try {
            String url = "jdbc:derby:db;create=true;";

            Connection con = DriverManager.getConnection(url);

            Statement stmt = con.createStatement();

            String sql = "CREATE TABLE ORDERS " +

                    "(id INTEGER not NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), " +

                    " customerId INTEGER, " +

                    " salesPersonId INTEGER, " +

                    " PRIMARY KEY ( id ))";

            try {
                stmt.executeUpdate(sql);
            } catch (SQLException e) {
                if (e.getSQLState().equals("X0Y32")) {
                    // OK
                } else {
                    throw e;
                }
            }
            con.close();
        }   catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void addUsers(){
        User user = new User();
        user.setId(1);
        user.setLastname("Grishkov");
        user.setFirstname("Pavel");
        user.setAge(19);
        usi.addUser(user);

        user = new User();
        user.setId(2);
        user.setLastname("Lushnikov");
        user.setFirstname("Aleksey");
        user.setAge(19);
        usi.addUser(user);

        user = new User();
        user.setId(3);
        user.setLastname("Minkaev");
        user.setFirstname("Damir");
        user.setAge(19);
        usi.addUser(user);
    }

    private static void add2Users(){
        User user = new User();
        user.setId(1);
        user.setLastname("Grishkov");
        user.setFirstname("Pavel");
        user.setAge(19);
        usi.addUser(user);
        System.out.println(user);

        user = new User();
        user.setId(2);
        user.setLastname("Lushnikov");
        user.setFirstname("Aleksey");
        user.setAge(19);
        usi.addUser(user);
        System.out.println(user);
    }

    private static void addOrders(){
        Order order = new Order();
        order.setId(1);
        order.setCustomerId(1);
        order.setSalesPersonId(2);
        ori.addOrder(order);

        order = new Order();
        order.setId(2);
        order.setCustomerId(2);
        order.setSalesPersonId(3);
        ori.addOrder(order);
    }

    private static void showUsers(){
        List<User> users = usi.getAllUsers();
        for (int i = 0; i < users.size(); i++){
            System.out.println(users.get(i));
        }
    }

    private static void showOrders(){
        List<Order> orders = ori.getAllOrders();
        for (int i = 0; i < orders.size(); i++){
            System.out.println(orders.get(i));
        }
    }
}
