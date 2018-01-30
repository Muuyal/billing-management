package com.muuyal.escala.billingmanagement.dao.impl;

import com.muuyal.escala.billingmanagement.dao.DBConnection;
import com.muuyal.escala.billingmanagement.dao.interfaces.PaymentDao;
import com.muuyal.escala.billingmanagement.dao.interfaces.ProjectDao;
import com.muuyal.escala.billingmanagement.entities.Payment;
import com.muuyal.escala.billingmanagement.entities.Project;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;

@Repository
public class PaymentDaoImp extends DBConnection implements PaymentDao {


    public PaymentDaoImp(){

    }

    @Override
    public boolean save(Payment payment) {
        return false;
    }

    @Override
    public Set<Payment> findAll() {
        return null;
    }

    @Override
    public Set<Payment> findByProject(Integer projectId) {
        return null;
    }

    @Override
    public Set<Payment> findByCustomer(Integer customerId) {
        return null;
    }

    @Override
    public Payment findById(Integer id) {
        return null;
    }

    @Override
    public boolean update(Payment payment) {
        return false;
    }

    @Override
    public boolean delete(Payment payment) {
        return false;
    }
}
