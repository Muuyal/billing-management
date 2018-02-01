package com.muuyal.escala.billingmanagement.dao.impl;

import com.muuyal.escala.billingmanagement.dao.DBConnection;
import com.muuyal.escala.billingmanagement.dao.interfaces.ProjectDao;
import com.muuyal.escala.billingmanagement.dao.interfaces.StaffDao;
import com.muuyal.escala.billingmanagement.entities.Project;
import com.muuyal.escala.billingmanagement.entities.Staff;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;

@Repository
public class StaffDaoImp extends DBConnection implements StaffDao {


    public StaffDaoImp(){

    }

    @Override
    public boolean save(Staff staff) {
        return false;
    }

    @Override
    public Set<Staff> findAll() {
        return null;
    }

    @Override
    public Staff findById(Integer id) {
        return null;
    }

    @Override
    public boolean update(Staff staff) {
        return false;
    }

    @Override
    public boolean delete(Staff staff) {
        return false;
    }
}
