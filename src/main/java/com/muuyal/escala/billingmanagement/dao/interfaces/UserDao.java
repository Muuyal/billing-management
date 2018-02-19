package com.muuyal.escala.billingmanagement.dao.interfaces;

import com.muuyal.escala.billingmanagement.entities.User;
import java.util.Set;

public interface UserDao {

    boolean save(User user);

    Set <User> findAll();

    Set <User> findAll(String search);

    Set <User> findAll(User user);

    User findOne(User user);

    boolean update(User user);

    boolean delete(User user);
}
