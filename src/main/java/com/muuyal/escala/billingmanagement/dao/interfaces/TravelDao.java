package com.muuyal.escala.billingmanagement.dao.interfaces;

import com.muuyal.escala.billingmanagement.entities.Travel;

public interface TravelDao {

    boolean save(Travel travel);

    void findAll(Travel travel);

    void findOne(Travel travel);

    void update(Travel travel);

}
