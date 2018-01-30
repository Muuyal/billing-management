package com.muuyal.escala.billingmanagement.dao.interfaces;

import com.muuyal.escala.billingmanagement.entities.Staff;

import java.util.Set;

public interface StaffDao {

    boolean save(Staff staff);

    Set<Staff> findAll();

    Set<Staff> findAll(Staff staff);

    Staff findById(Integer id);

    boolean update(Staff staff);

    boolean delete(Staff staff);


}
