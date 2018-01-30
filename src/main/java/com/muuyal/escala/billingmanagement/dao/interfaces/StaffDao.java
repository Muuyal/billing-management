package com.muuyal.escala.billingmanagement.dao.interfaces;

import com.muuyal.escala.billingmanagement.entities.Staff;
import java.util.Set;

public interface StaffDao {

    boolean save(Staff staff);

    Set<Staff> findAll();

    Set<Staff> findAll(Staff staff);

    Staff findOne(Staff staff);

    void update(Staff staff);

    void delete(Staff staff);
}