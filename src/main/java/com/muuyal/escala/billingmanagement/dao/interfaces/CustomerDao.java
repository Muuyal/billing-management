package com.muuyal.escala.billingmanagement.dao.interfaces;

import com.muuyal.escala.billingmanagement.entities.Customer;
import java.util.Set;

public interface CustomerDao {

    boolean save(Customer customer);

    Set<Customer> findAll();

    Customer findById(Integer id);

    boolean update(Customer customer);

    boolean delete(Customer customer);

}
