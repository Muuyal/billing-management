package com.muuyal.escala.billingmanagement.dao.interfaces;

import com.muuyal.escala.billingmanagement.entities.Project;
import java.util.Set;

public interface TravelDao {

    boolean save(Project project);

    Set<Project> findAll();

    Set<Project> findAll(Project project);

    Project findOne(Project project);

    void update(Project project);

    void delete(Project project);

}
