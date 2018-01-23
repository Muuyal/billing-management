package com.muuyal.escala.billingmanagement.dao.impl;

import com.muuyal.escala.billingmanagement.dao.DBConnection;
import com.muuyal.escala.billingmanagement.dao.interfaces.TravelDao;
import com.muuyal.escala.billingmanagement.entities.Travel;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Repository
public class TravelDaoImp extends DBConnection implements TravelDao{


    public TravelDaoImp(){

    }

    @Override
    public boolean save(Travel travel) {

        boolean saved = false;

        System.out.println("--- insert into travel(name, destination, price, payments, departure, deadline) values" +
                " ('" + travel.getName() + "','" + travel.getDestination() + "','" + travel.getPrice() +
                "','" + travel.getPayments() + "','" + travel.getDeparture() + "','" + travel.getDeadline() + "')" + " ---");
        Connection connection = null;
        Statement statement   = null;

        try {

            connection = this.setConnection();
            System.out.println("--- Database opened successfully ---");
            statement = connection.createStatement();
            System.out.println("--- Connection: " + connection.getMetaData()+ " ---");

            statement.executeUpdate("insert into travel(name, destination, price, payments, departure, deadline) values"  +
                    "('" + travel.getName() + "','" + travel.getDestination() + "','" + travel.getPrice() +
                    "','" + travel.getPayments() + "','" + travel.getDeparture() + "','" + travel.getDeadline() + "');");

            connection.commit();
            saved = true;
            statement.close();

        } catch (SQLException e){
            System.err.println(e.getMessage());
        } finally {
            this.closeConnection();
        }



        return saved;
    }

    @Override
    public void findAll(Travel travel) {

    }

    @Override
    public void findOne(Travel travel) {

    }

    @Override
    public void update(Travel travel) {

    }
}
