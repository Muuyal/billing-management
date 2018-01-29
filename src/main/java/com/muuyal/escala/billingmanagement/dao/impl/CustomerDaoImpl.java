package com.muuyal.escala.billingmanagement.dao.impl;

import com.muuyal.escala.billingmanagement.dao.DBConnection;
import com.muuyal.escala.billingmanagement.dao.interfaces.CustomerDao;
import com.muuyal.escala.billingmanagement.entities.Customer;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Set;

@Repository
public class CustomerDaoImpl extends DBConnection implements CustomerDao {


    @Override
    public boolean save(Customer customer) {

        boolean saved = false;

        System.out.println("--- insert into project(name, phone, email, address_street,  address_city,  address_colony,  address_pc, notes) values" +
                " ('" + customer.getName() + "','" + customer.getPhone() + "','" + customer.geteMail() +
                "','" + customer.getAddressStreet() + "','" + customer.getAddressCity() + "','" + customer.getAddressColony() +
                "','" + customer.getAddressPC() + "','" + customer.getNotes() +
                "')" + " ---");

        Connection connection = null;
        Statement statement   = null;
        PreparedStatement preparedStatement;

        try {

            connection = this.setConnection();
            System.out.println("--- Database opened successfully ---");
            statement = connection.createStatement();
            System.out.println("--- Connection: " + connection.getMetaData()+ " ---");

            preparedStatement = connection.prepareStatement("insert into customer(name, phone, email, address_street,  address_city,  address_colony,  address_pc, notes) values(?,?,?,?,?,?,?,?)");
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2, customer.getPhone() );
            preparedStatement.setString(3, customer.geteMail());
            preparedStatement.setString(4, customer.getAddressStreet());
            preparedStatement.setString(5, customer.getAddressCity());
            preparedStatement.setString(6, customer.getAddressColony());
            preparedStatement.setString(7, customer.getAddressPC());
            preparedStatement.setString(8, customer.getNotes());
            preparedStatement.executeUpdate();

            connection.commit();
            saved = true;
//            statement.close();

        } catch (SQLException e){
            System.err.println(e.getMessage());
        } finally {
            this.closeConnection();
        }
        return saved;
    }

    @Override
    public Set<Customer> findAll() {

        return null;

    }

    @Override
    public Customer findOne(Integer id) {

        return null;

    }
}
