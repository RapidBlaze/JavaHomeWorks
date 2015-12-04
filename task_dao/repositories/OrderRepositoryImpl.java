package Lesson4.repositories;

import Lesson4.entities.Order;
import Lesson4.entities.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rapid Blaze on 15.10.2015.
 */
public class OrderRepositoryImpl implements OrderRepository{

    @Override
    public List<Order> getAllOrders(){
        List<Order> orders = new ArrayList<Order>();
        try {
            String sqlSelect = "SELECT id, customerId, salesPersonId FROM ORDERS";
            String url = "jdbc:derby:db;create=true;";

            Connection con = DriverManager.getConnection(url);
            Statement stmt = con.createStatement();

            ResultSet results = stmt.executeQuery(sqlSelect);

            while (results.next()) {
                Integer id = results.getInt(1);
                Integer customerId = results.getInt(2);
                Integer salesPersonId = results.getInt(3);

                Order order = new Order();
                order.setId(id);
                order.setCustomerId(customerId);
                order.setSalesPersonId(salesPersonId);
                orders.add(order);
            }
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return orders;
    };

    @Override
    public void addOrder(Order order) {
        try {
            String url = "jdbc:derby:db;create=true;";

            Connection con = DriverManager.getConnection(url);

            Statement stmt = con.createStatement();

            String sql = "INSERT INTO ORDERS (customerId, salesPersonId) VALUES (" + order.getCustomerId() + ", " + order.getSalesPersonId() + ")";
            stmt.executeUpdate(sql);

        }   catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateOrder(Order order) {
        try {
            String url = "jdbc:derby:db;create=true;";

            Connection con = DriverManager.getConnection(url);

            Statement stmt = con.createStatement();

            String sql = "UPDATE ORDERS SET customerId = " + order.getCustomerId() +" WHERE id = "+ order.getId();
            stmt.executeUpdate(sql);

        }   catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteOrder(int id) {
        try {
            String url = "jdbc:derby:db;create=true;";
            Connection con = DriverManager.getConnection(url);
            Statement stmt = con.createStatement();

            String sql = "SELECT * FROM ORDERS WHERE id=" + id;
            ResultSet results = stmt.executeQuery(sql);
            results.next();
            Integer id1 = results.getInt(1);
            Integer customerId = results.getInt(2);
            Integer salesPersonId = results.getInt(3);

            Order order = new Order();
            order.setId(id1);
            order.setCustomerId(customerId);
            order.setSalesPersonId(salesPersonId);

            System.out.println("Deleted: " + order);

            sql = "DELETE FROM ORDERS WHERE id = " + id;
            stmt.executeUpdate(sql);

        }   catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Order findOrderByCustomerId(int customerId) {
        Order order = new Order();
        try {
            String url = "jdbc:derby:db;create=true;";

            Connection con = DriverManager.getConnection(url);

            Statement stmt = con.createStatement();

            String sql = "SELECT * FROM ORDERS WHERE customerId = " + customerId;

            ResultSet results = stmt.executeQuery(sql);
            results.next();
            Integer id1 = results.getInt(1);
            Integer customerId1 = results.getInt(2);
            Integer salesPersonId = results.getInt(3);

            order.setId(id1);
            order.setCustomerId(customerId1);
            order.setSalesPersonId(salesPersonId);
            System.out.println(order);

        }   catch (Exception e) {
            e.printStackTrace();
        }
        return order;
    }

    public void writeOrderCount(){
        System.out.println("Order count = " + getAllOrders().size());
    }
}
