package com.muuyal.escala.billingmanagement.dao.impl;

import com.muuyal.escala.billingmanagement.dao.DBConnection;
import com.muuyal.escala.billingmanagement.dao.interfaces.ContractDao;
import com.muuyal.escala.billingmanagement.dao.interfaces.ProjectDao;
import com.muuyal.escala.billingmanagement.entities.Contract;
import com.muuyal.escala.billingmanagement.entities.Project;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;

@Repository
public class ContractDaoImp extends DBConnection implements ContractDao {


    public ContractDaoImp(){

    }

    @Override
    public boolean save(Contract contract) {


        boolean saved = false;

        System.out.println("--- insert into project(name, phone, email, address_street,  address_city,  address_colony,  address_pc, notes) values" +
                " ('" + contract.getCustomer_id() + "','" + contract.getProject_id()+ "','" +
                "','" + contract.getDeadline() + "','" + contract.getDiscount()+ "','" + contract.getCreatedOn() +
                "')" + " ---");

        Connection connection = null;
        Statement statement   = null;
        PreparedStatement preparedStatement;

        try {

            connection = this.setConnection();
            System.out.println("--- Database opened successfully ---");
            statement = connection.createStatement();
            System.out.println("--- Connection: " + connection.getMetaData()+ " ---");

            preparedStatement = connection.prepareStatement("insert into contract(customerId, projectId, deadline, discount,  createdOn) values(?,?,?,?,?)");
            preparedStatement.setString(1, contract.getCustomer_id());
            preparedStatement.setString(2, contract.getProject_id());
            preparedStatement.setString(3, contract.getDeadline().toString());
            preparedStatement.setInt(4, contract.getDiscount());
            preparedStatement.setString(5, contract.getCreatedOn().toString());
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
    public Set<Contract> findAll() {
        return null;
    }

    @Override
    public Set<Contract> findByProject(Integer projectId) {
        return null;
    }

    @Override
    public Contract findById(Integer id) {
        return null;
    }

    @Override
    public boolean update(Contract contract) {
        return false;
    }

    @Override
    public boolean delete(Contract contract) {
        return false;
    }

    @Override
    public Set<Contract> findAll(Contract contract) {
        return null;
    }

    @Override
    public Set<Contract> findByCustomer(Integer customerId) {
        return null;
    }
}
