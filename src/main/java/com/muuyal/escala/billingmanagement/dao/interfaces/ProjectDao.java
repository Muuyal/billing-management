package com.muuyal.escala.billingmanagement.dao.interfaces;

import com.muuyal.escala.billingmanagement.entities.Project;
import java.util.Set;

public interface ProjectDao {

    boolean save(Project project);

    Set<Project> findAll();

    Project findById(Integer id);

    boolean update(Project project);

    boolean delete(Project project);

}
