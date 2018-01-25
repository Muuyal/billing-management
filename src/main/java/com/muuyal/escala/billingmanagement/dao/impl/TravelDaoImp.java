package com.muuyal.escala.billingmanagement.dao.impl;

import com.muuyal.escala.billingmanagement.dao.DBConnection;
import com.muuyal.escala.billingmanagement.dao.interfaces.TravelDao;
import com.muuyal.escala.billingmanagement.entities.Travel;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;

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
        PreparedStatement preparedStatement;

        try {

            connection = this.setConnection();
            System.out.println("--- Database opened successfully ---");
            statement = connection.createStatement();
            System.out.println("--- Connection: " + connection.getMetaData()+ " ---");

            preparedStatement = connection.prepareStatement("insert into travel(name, destination, price, payments, departure, deadline) values(?,?,?,?,?,?)");
            preparedStatement.setString(1, travel.getName());
            preparedStatement.setString(2, travel.getDestination());
            preparedStatement.setInt(3, travel.getPrice());
            preparedStatement.setString(4, travel.getPayments());
            preparedStatement.setString(5, travel.getDeparture().toString());
            preparedStatement.setString(6, travel.getDeadline().toString());
            preparedStatement.executeUpdate();

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
    public Set<Travel> findAll() {

        System.out.println("---" + this.getClass().getName() +  " findAll clicked. ---");
        System.out.println("--- select * from travel; ---");
        Connection connection = null;
        Statement statement   = null;
        PreparedStatement preparedStatement;

        Set<Travel> result =  new HashSet<Travel>();
        try {

            connection = this.setConnection();
            System.out.println("--- Database opened successfully ---");
            statement = connection.createStatement();
            System.out.println("--- Connection: " + connection.getMetaData()+ " ---");

            preparedStatement = connection.prepareStatement("select * from travel");
            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("--- Result set: " + resultSet.getCursorName()+ " ---");

            while (resultSet.next()){
                Travel temp = new Travel();
                temp.setId(resultSet.getInt("id"));
                temp.setName(resultSet.getString("name"));
                temp.setDestination(resultSet.getString("destination"));
                temp.setPrice(resultSet.getInt("price"));
                temp.setPayments(resultSet.getString("payments"));
                temp.setDeparture(new SimpleDateFormat("yyyy-mm-dd").parse(resultSet.getString("departure")));
                temp.setDeadline(new SimpleDateFormat("yyyy-mm-dd").parse(resultSet.getString("deadline")));
                result.add(temp);
            }

            resultSet.close();
//            statement.close();
//            connection.close();
            this.closeConnection();


        } catch (Exception e){
            System.err.println("--- Error found " + e.getClass().getName() + ":" + e.getMessage());
//            System.exit(0);
        }
        return result;
    }

    @Override
    public Set<Travel> findAll(Travel travel) {

        Set<Travel> travelSet = new HashSet<>();
        return travelSet;
    }

    @Override
    public Travel findOne(Travel travel) {
        return travel;
    }

    @Override
    public void update(Travel travel) {

    }

    @Override
    public void delete(Travel travel) {

    }
}
