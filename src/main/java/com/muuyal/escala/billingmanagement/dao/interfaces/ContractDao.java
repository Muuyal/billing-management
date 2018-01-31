package com.muuyal.escala.billingmanagement.dao.interfaces;

import com.muuyal.escala.billingmanagement.entities.Contract;
import java.util.Set;

public interface ContractDao {

    boolean save(Contract contract);

    Set<Contract> findAll();

    Set<Contract> findAll(Contract contract);

    Set<Contract> findByProject(Contract contract);

    Set<Contract> findByCustomer(Contract contract);

    Set<Contract> findById(Contract contract);

    boolean update(Contract contract);

    boolean delete(Contract contract);
}