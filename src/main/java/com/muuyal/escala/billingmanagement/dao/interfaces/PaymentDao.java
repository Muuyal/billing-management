package com.muuyal.escala.billingmanagement.dao.interfaces;

import com.muuyal.escala.billingmanagement.entities.Payment;
import java.util.Set;

public interface PaymentDao {

    boolean save(Payment payment);

    Set<Payment> findAll();

    Set<Payment> findByProject(Integer projectId);

    Set<Payment> findByCustomer(Integer customerId);

    Payment findById(Integer id);

    boolean update(Payment payment);

    boolean delete(Payment payment);


}
