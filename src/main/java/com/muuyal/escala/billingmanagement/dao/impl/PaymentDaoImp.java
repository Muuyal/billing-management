package com.muuyal.escala.billingmanagement.dao.impl;

import com.muuyal.escala.billingmanagement.dao.DBConnection;
import com.muuyal.escala.billingmanagement.dao.interfaces.PaymentDao;
import com.muuyal.escala.billingmanagement.dao.interfaces.ProjectDao;
import com.muuyal.escala.billingmanagement.entities.Payment;
import com.muuyal.escala.billingmanagement.entities.Project;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;

@Repository
public class PaymentDaoImp extends DBConnection implements PaymentDao {

    public PaymentDaoImp(){
    }

    @Override
    public boolean save(Payment payment) {
        return false;
    }

    @Override
    public Set<Payment> findAll() {
        return null;
    }

    @Override
    public Set<Payment> findByProject(Integer projectId) {
        return null;
    }

    @Override
    public Set<Payment> findByCustomer(Integer customerId) {
        return null;
    }

    @Override
    public Payment findById(Integer id) {
        return null;
    }

    @Override
    public boolean update(Payment payment) {

        boolean updated = false;

        System.out.println("--- UPDATE payment " +
                "SET id = '"+ payment.getId() +"', customerId = '"+ payment.getCustomerId() +"', " +
                "contractId = '"+ payment.getContractId() +"', paymentAmount = '"+ payment.getPaymentAmount() +"', " +
                "paymentDate = '"+ payment.getPaymentDate() +"' " +
                "WHERE id = '"+ payment.getId() +"' ---");
        Connection connection = null;
        Statement statement   = null;
        PreparedStatement preparedStatement;

        try {

            connection = this.setConnection();
            System.out.println("--- Database opened successfully ---");
            statement = connection.createStatement();
            System.out.println("--- Connection: " + connection.getMetaData()+ " ---");

            preparedStatement = connection.prepareStatement("UPDATE payment " +
                            "SET id = ?, customerId = ?, contractId = ?, paymentAmount = ?, paymentDate = ? " +
                            "WHERE id = ?");
            preparedStatement.setInt(1, payment.getId());
            preparedStatement.setInt(2, payment.getCustomerId());
            preparedStatement.setInt(3, payment.getContractId());
            preparedStatement.setDouble(4, payment.getPaymentAmount());
            preparedStatement.setString(5, payment.getPaymentDate().toString());
            preparedStatement.setInt(6, payment.getId());
            preparedStatement.executeUpdate();

            connection.commit();
            statement.close();

        } catch (SQLException e){
            System.err.println(e.getMessage());
        } finally {
            updated = true;
            this.closeConnection();
        }
        return updated;
    }

    @Override
    public boolean delete(Payment payment) {

        boolean deleted = false;

        System.out.println("--- DELETE FROM payment WHERE id="+payment.getId()+" ---");
        Connection connection = null;
        Statement statement   = null;
        PreparedStatement preparedStatement;

        try {

            connection = this.setConnection();
            System.out.println("--- Database opened successfully ---");
            statement = connection.createStatement();
            System.out.println("--- Connection: " + connection.getMetaData()+ " ---");

            preparedStatement = connection.prepareStatement("DELETE FROM payment WHERE id=?");
            preparedStatement.setInt(1, payment.getId());
            preparedStatement.executeUpdate();

            connection.commit();
            statement.close();

        } catch (SQLException e){
            System.err.println(e.getMessage());
        } finally {
            deleted = true;
            this.closeConnection();
        }
        return deleted;
    }
}
