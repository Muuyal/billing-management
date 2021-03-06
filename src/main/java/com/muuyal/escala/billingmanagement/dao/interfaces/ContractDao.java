package com.muuyal.escala.billingmanagement.dao.interfaces;

import com.muuyal.escala.billingmanagement.entities.Contract;
import java.util.Set;

public interface ContractDao {

    boolean save(Contract contract);

    Set<Contract> findAll();

    Set<Contract> findAll(String search);

    Set<Contract> findByProject(Integer projectId);

    Set<Contract> findByCustomer(Integer customerId);

    Set<Contract> findById(Integer Id);

    boolean update(Contract contract);

    boolean delete(Contract contract);

    public Set<Integer> findCustomerIdsByProyect(Integer projectId);

    Set<Contract> findByProjectAndCustomer (Integer projectId, Integer customerId);
}