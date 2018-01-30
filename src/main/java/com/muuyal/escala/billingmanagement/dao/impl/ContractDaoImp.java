package com.muuyal.escala.billingmanagement.dao.impl;

import com.muuyal.escala.billingmanagement.dao.DBConnection;
import com.muuyal.escala.billingmanagement.dao.interfaces.ContractDao;
import com.muuyal.escala.billingmanagement.dao.interfaces.ProjectDao;
import com.muuyal.escala.billingmanagement.entities.Contract;
import com.muuyal.escala.billingmanagement.entities.Project;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;

@Repository
public class ContractDaoImp extends DBConnection implements ContractDao {


    public ContractDaoImp(){

    }

    @Override
    public boolean save(Contract contract) {
        return false;
    }

    @Override
    public Set<Contract> findAll() {
        return null;
    }

    @Override
    public Set<Contract> findByProject(Integer projectId) {
        return null;
    }

    @Override
    public Set<Contract> findByCustomer(Integer customerId) {
        return null;
    }

    @Override
    public Contract findById(Integer id) {
        return null;
    }

    @Override
    public boolean update(Contract contract) {
        return false;
    }

    @Override
    public boolean delete(Contract contract) {
        return false;
    }
}
