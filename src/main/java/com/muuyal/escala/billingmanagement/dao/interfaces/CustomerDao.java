package com.muuyal.escala.billingmanagement.dao.interfaces;

import com.muuyal.escala.billingmanagement.entities.Customer;

import java.util.Set;

public interface CustomerDao {

    public boolean save(Customer customer);

    public Set<Customer> findAll();

    public Customer findOne(Integer id);

}
