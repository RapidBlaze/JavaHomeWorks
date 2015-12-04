package Lesson4.repositories;

import Lesson4.entities.Order;

import java.util.List;

/**
 * Created by Rapid Blaze on 09.10.2015.
 */
public interface OrderRepository {

    List<Order> getAllOrders();

    void addOrder(Order order);

    void updateOrder(Order order);

    void deleteOrder(int id);

    Order findOrderByCustomerId (int customerId);
}
