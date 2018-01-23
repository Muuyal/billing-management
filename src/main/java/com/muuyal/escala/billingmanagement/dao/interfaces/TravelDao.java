package com.muuyal.escala.billingmanagement.dao.interfaces;

import com.muuyal.escala.billingmanagement.entities.Travel;

import java.util.Set;

public interface TravelDao {

    boolean save(Travel travel);

    Set<Travel> findAll(Travel travel);

    Travel findOne(Travel travel);

    void update(Travel travel);

    void delete(Travel travel);


}
