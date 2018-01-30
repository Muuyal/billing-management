package com.muuyal.escala.billingmanagement.dao.interfaces;

import com.muuyal.escala.billingmanagement.entities.Contract;
import java.util.Set;

public interface ContractDao {

    boolean save(Contract contract);

    Set<Contract> findAll();

    Set <Contract> findAll(Contract contract);

    Contract findOne(Contract contract);

    void update(Contract contract);

    void delete(Contract contract);
}
