package Lesson4.repositories;

import Lesson4.entities.Order;
import Lesson4.entities.User;

import java.util.List;

/**
 * Created by Rapid Blaze on 09.10.2015.
 */
public interface UserRepository {

    List<User> getAllUsers();

    void addUser(User user);

    void updateUser(User user);

    void deleteUser(int id);

    User findUserByFirstname(String firstname);

}
