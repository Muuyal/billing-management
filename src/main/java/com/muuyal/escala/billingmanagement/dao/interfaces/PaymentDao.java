package com.muuyal.escala.billingmanagement.dao.interfaces;

import com.muuyal.escala.billingmanagement.entities.Payment;
import java.util.Set;

public interface PaymentDao {

    boolean save(Payment payment);

    Set<Payment> findAll();

    Set <Payment> findAll(Payment payment);

    Payment findOne(Payment payment);

    void update(Payment payment);

    void delete(Payment payment);
}
