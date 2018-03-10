package com.muuyal.escala.billingmanagement.dao.impl;

import com.muuyal.escala.billingmanagement.dao.DBConnection;
import com.muuyal.escala.billingmanagement.dao.interfaces.CustomerDao;
import com.muuyal.escala.billingmanagement.entities.Customer;
import org.springframework.stereotype.Repository;
import java.sql.*;
import java.util.HashSet;
import java.util.Set;

@Repository
public class CustomerDaoImpl extends DBConnection implements CustomerDao {


    @Override
    public boolean save(Customer customer) {

        boolean saved = false;

        System.out.println("--- INSERT INTO " +
                "customer(name, phone, email, address_street,  address_city,  address_colony,  address_pc, notes) VALUES" +
                " ('" + customer.getName() + "','" + customer.getPhone() + "','" + customer.getEmail() +
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

            preparedStatement = connection.prepareStatement("INSERT INTO customer" +
                    "(name, phone, email, address_street,  address_city,  address_colony,  address_pc, notes) " +
                    "VALUES(?,?,?,?,?,?,?,?)");
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2, customer.getPhone() );
            preparedStatement.setString(3, customer.getEmail());
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

        System.out.println("---" + this.getClass().getName() +  " findAll clicked. ---");
        System.out.println("--- SELECT * FROM customer; ---");
        Connection connection = null;
        Statement statement   = null;
        PreparedStatement preparedStatement;

        Set<Customer> result =  new HashSet<Customer>();
        try {

            connection = this.setConnection();
            System.out.println("--- Database opened successfully ---");
            statement = connection.createStatement();
            System.out.println("--- Connection: " + connection.getMetaData()+ " ---");

            preparedStatement = connection.prepareStatement("SELECT * FROM customer");
            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("--- Result set: " + resultSet.getCursorName()+ " ---");

            while (resultSet.next()){
                Customer temp = new Customer();
                temp.setId(resultSet.getInt("id"));
                temp.setName(resultSet.getString("name"));
                temp.setPhone(resultSet.getString("phone"));
                temp.setEmail(resultSet.getString("email"));
                temp.setAddressStreet(resultSet.getString("address_street"));
                temp.setAddressCity(resultSet.getString("address_city"));
                temp.setAddressColony(resultSet.getString("address_colony"));
                temp.setAddressPC(resultSet.getString("address_pc"));
                temp.setNotes(resultSet.getString("notes"));
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
        System.out.println("---- Results: " + result.toString() + " Class: " + this.getClass() + " ----");
        return result;

    }

    @Override
    public Set<Customer> findById(Customer customer) {

        System.out.println("---" + this.getClass().getName() +  " findById clicked. ---");
        System.out.println("--- SELECT * FROM customer WHERE id="+ customer.getId() +"; ---");
        Connection connection = null;
        Statement statement   = null;
        PreparedStatement preparedStatement;

        Set<Customer> result =  new HashSet<Customer>();
        try {

            connection = this.setConnection();
            System.out.println("--- Database opened successfully ---");
            statement = connection.createStatement();
            System.out.println("--- Connection: " + connection.getMetaData()+ " ---");

            preparedStatement = connection.prepareStatement("SELECT * FROM customer WHERE id=?");
            preparedStatement.setInt(1, customer.getId());
            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("--- Result set: " + resultSet.getCursorName()+ " ---");

            while (resultSet.next()){
                Customer temp = new Customer();
                temp.setId(resultSet.getInt("id"));
                temp.setName(resultSet.getString("name"));
                temp.setPhone(resultSet.getString("phone"));
                temp.setEmail(resultSet.getString("email"));
                temp.setAddressStreet(resultSet.getString("AddressStreet"));
                temp.setAddressCity(resultSet.getString("AddressCity"));
                temp.setAddressColony(resultSet.getString("AddressColony"));
                temp.setAddressPC(resultSet.getString("AddressPC"));
                temp.setNotes(resultSet.getString("notes"));
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
    public Customer findById(Integer customerId) {

        System.out.println("---" + this.getClass().getName() +  " findById integer clicked. ---");
        System.out.println("--- SELECT * FROM customer WHERE id="+ customerId +"; ---");
        Connection connection = null;
        Statement statement   = null;
        PreparedStatement preparedStatement;

        Customer result = new Customer();
        try {

            connection = this.setConnection();
            System.out.println("--- Database opened successfully ---");
            statement = connection.createStatement();
            System.out.println("--- Connection: " + connection.getMetaData()+ " ---");

            preparedStatement = connection.prepareStatement("SELECT * FROM customer WHERE id=?");
            preparedStatement.setString(1, customerId.toString());
            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("--- Result set: " + resultSet.getCursorName()+ " ---");

            while (resultSet.next()){
                Customer temp = new Customer();
                temp.setId(resultSet.getInt("id"));
                temp.setName(resultSet.getString("name"));
                temp.setPhone(resultSet.getString("phone"));
                temp.setEmail(resultSet.getString("email"));
                temp.setAddressStreet(resultSet.getString("address_street"));
                temp.setAddressCity(resultSet.getString("address_city"));
                temp.setAddressColony(resultSet.getString("address_colony"));
                temp.setAddressPC(resultSet.getString("address_pc"));
                temp.setNotes(resultSet.getString("notes"));
                result = temp;
            }

            resultSet.close();
            this.closeConnection();

        } catch (Exception e){
            System.err.println("--- Error found " + e.getClass().getName() + ":" + e.getMessage());
        }
        return result;
    }

    @Override
    public boolean update(Customer customer) {

        boolean updated = false;

        System.out.println("--- UPDATE customer " +
                "SET id="+ customer.getId() +", name="+ customer.getName() +", phone="+ customer.getPhone() +", " +
                "email="+ customer.getEmail() +", address_street="+ customer.getAddressStreet() +", " +
                "address_city="+ customer.getAddressCity() +",  address_colony="+ customer.getAddressColony() +", " +
                "address_pc="+ customer.getAddressPC() +", notes="+ customer.getNotes() +" " +
                "WHERE id="+ customer.getId() +" ---");

        Connection connection = null;
        Statement statement   = null;
        PreparedStatement preparedStatement;

        try {

            connection = this.setConnection();
            System.out.println("--- Database opened successfully ---");
            statement = connection.createStatement();
            System.out.println("--- Connection: " + connection.getMetaData()+ " ---");

            preparedStatement = connection.prepareStatement("UPDATE customer " +
                    "SET id=?, name=?, phone=?, email=?, address_street=?, address_city=?, " +
                    "address_colony=?, address_pc=?, notes=? " +
                    "WHERE id=?");
            preparedStatement.setInt(1, customer.getId());
            preparedStatement.setString(2, customer.getName());
            preparedStatement.setString(3, customer.getPhone() );
            preparedStatement.setString(4, customer.getEmail());
            preparedStatement.setString(5, customer.getAddressStreet());
            preparedStatement.setString(6, customer.getAddressCity());
            preparedStatement.setString(7, customer.getAddressColony());
            preparedStatement.setString(8, customer.getAddressPC());
            preparedStatement.setString(9, customer.getNotes());
            preparedStatement.setInt(10, customer.getId());
            preparedStatement.executeUpdate();

            connection.commit();
            updated = true;
//            statement.close();

        } catch (SQLException e){
            System.err.println(e.getMessage());
        } finally {
            this.closeConnection();
        }
        return updated;
    }

    @Override
    public boolean delete(Customer customer) {

        boolean deleted = false;

        System.out.println("--- DELETE FROM customer WHERE id="+ customer.getId() +" ---");

        Connection connection = null;
        Statement statement   = null;
        PreparedStatement preparedStatement;

        try {

            connection = this.setConnection();
            System.out.println("--- Database opened successfully ---");
            statement = connection.createStatement();
            System.out.println("--- Connection: " + connection.getMetaData()+ " ---");

            preparedStatement = connection.prepareStatement("DELETE FROM customer WHERE id=?");
            preparedStatement.setInt(1, customer.getId());
            preparedStatement.executeUpdate();

            connection.commit();
            deleted = true;
//            statement.close();

        } catch (SQLException e){
            System.err.println(e.getMessage());
        } finally {
            this.closeConnection();
        }
        return deleted;
    }

    @Override
    public Set<Customer> findAll(String search) {
        return null;
    }
}
