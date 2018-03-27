package com.muuyal.escala.billingmanagement.dao.interfaces;

import com.muuyal.escala.billingmanagement.entities.Payment;
import java.util.Set;

public interface PaymentDao {

    boolean save(Payment payment);

    Set<Payment> findAll();

    Set<Payment> findByProject(Integer projectId);

    Set<Payment> findByCustomer(Integer customerId);

    Set<Payment> findById(Integer id);

    Set<Payment> findAll(String search);

    boolean update(Payment payment);

    boolean delete(Payment payment);

    boolean FindStatusOk (Integer customerId, Integer contractId, Long limit); //limit = 7, 15 o 30
}
