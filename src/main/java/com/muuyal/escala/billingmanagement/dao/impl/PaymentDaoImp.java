package com.muuyal.escala.billingmanagement.dao.impl;

import com.muuyal.escala.billingmanagement.dao.DBConnection;
import com.muuyal.escala.billingmanagement.dao.interfaces.PaymentDao;
import com.muuyal.escala.billingmanagement.entities.Payment;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Repository
public class PaymentDaoImp extends DBConnection implements PaymentDao {

    public PaymentDaoImp(){
    }

    @Override
    public boolean save(Payment payment) {

        boolean saved = false;

        System.out.println("--- INSERT INTO payment" +
                "(customerId, contractId, paymentAmount, paymentDate) " +
                "VALUES ('" + payment.getCustomerId() + "','" + payment.getContractId() + "'," +
                "'" + payment.getPaymentAmount() + "','" + payment.getPaymentDate() + "')" + " ---");

        Connection connection = null;
        Statement statement   = null;
        PreparedStatement preparedStatement;

        try {
            connection = this.setConnection();
            System.out.println("--- Database opened successfully ---");
            statement = connection.createStatement();
            System.out.println("--- Connection: " + connection.getMetaData()+ " ---");

            preparedStatement = connection.prepareStatement("INSERT INTO payment" +
                    "(customerId, contractId, paymentAmount, paymentDate) " +
                    "VALUES (?, ?, ?, ?)");
            preparedStatement.setInt(1, payment.getCustomerId() );
            preparedStatement.setInt(2, payment.getContractId() );
            preparedStatement.setDouble(3, payment.getPaymentAmount() );
            preparedStatement.setString(4, payment.getPaymentDate().toString() );
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
    public Set<Payment> findAll() {

        System.out.println("--- SELECT * FROM payment ---");
        Set<Payment> result = new HashSet<>();
        Connection connection = null;
        Statement statement   = null;
        PreparedStatement preparedStatement;

        try {

            connection = this.setConnection();
            System.out.println("--- Database opened successfully ---");
            statement = connection.createStatement();
            System.out.println("--- Connection: " + connection.getMetaData()+ " ---");

            preparedStatement = connection.prepareStatement("SELECT * FROM payment");
            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("--- Result set: " + resultSet.getCursorName()+ " ---");

            while (resultSet.next()){
                Payment temp = new Payment();
                temp.setId(resultSet.getInt("id"));
                temp.setCustomerId(resultSet.getInt("customerId"));
                temp.setContractId(resultSet.getInt("contractId"));
                temp.setPaymentAmount(resultSet.getDouble("paymentAmount"));
                temp.setPaymentDate(new SimpleDateFormat("yyyy-mm-dd").parse(resultSet.getString("paymentDate")));
                result.add(temp);
            }

            resultSet.close();
            this.closeConnection();

        } catch (SQLException e){
            System.err.println(e.getMessage());
        } catch (ParseException e){
            System.err.println(e.getMessage());
        } finally {
            this.closeConnection();
        }

        return result;
    }

    @Override
    public Set<Payment> findByProject(Integer projectId) {

        System.out.println("--- SELECT * FROM payment " +
                "WHERE contractId = '"+ projectId +"' ---");
        Set<Payment> result = new HashSet<>();
        Connection connection = null;
        Statement statement   = null;
        PreparedStatement preparedStatement;

        try {

            connection = this.setConnection();
            System.out.println("--- Database opened successfully ---");
            statement = connection.createStatement();
            System.out.println("--- Connection: " + connection.getMetaData()+ " ---");

            preparedStatement = connection.prepareStatement("SELECT * FROM  payment " +
                    "WHERE contractId = ?");
            preparedStatement.setInt(1, projectId);
            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("--- Result set: " + resultSet.getCursorName()+ " ---");

            while (resultSet.next()){
                Payment temp = new Payment();
                temp.setId(resultSet.getInt("id"));
                temp.setCustomerId(resultSet.getInt("customerId"));
                temp.setContractId(resultSet.getInt("contractId"));
                temp.setPaymentAmount(resultSet.getDouble("paymentAmount"));
                temp.setPaymentDate(new SimpleDateFormat("yyyy-mm-dd").parse(resultSet.getString("paymentDate")));
                result.add(temp);
            }

            resultSet.close();
            this.closeConnection();

        } catch (SQLException e){
            System.err.println(e.getMessage());
        } catch (ParseException e){
            System.err.println(e.getMessage());
        } finally {
            this.closeConnection();
        }

        return result;
    }

    @Override
    public Set<Payment> findByCustomer(Integer customerId) {

        System.out.println("--- SELECT * FROM payment " +
                "WHERE customerId = '"+ customerId +"' ---");
        Set<Payment> result = new HashSet<>();
        Connection connection = null;
        Statement statement   = null;
        PreparedStatement preparedStatement;

        try {

            connection = this.setConnection();
            System.out.println("--- Database opened successfully ---");
            statement = connection.createStatement();
            System.out.println("--- Connection: " + connection.getMetaData()+ " ---");

            preparedStatement = connection.prepareStatement("SELECT * FROM  payment " +
                    "WHERE customerId = ?");
            preparedStatement.setInt(1, customerId);
            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("--- Result set: " + resultSet.getCursorName()+ " ---");

            while (resultSet.next()){
                Payment temp = new Payment();
                temp.setId(resultSet.getInt("id"));
                temp.setCustomerId(resultSet.getInt("customerId"));
                temp.setContractId(resultSet.getInt("contractId"));
                temp.setPaymentAmount(resultSet.getDouble("paymentAmount"));
                temp.setPaymentDate(new SimpleDateFormat("yyyy-mm-dd").parse(resultSet.getString("paymentDate")));
                result.add(temp);
            }

            resultSet.close();
            this.closeConnection();

        } catch (SQLException e){
            System.err.println(e.getMessage());
        } catch (ParseException e){
            System.err.println(e.getMessage());
        } finally {
            this.closeConnection();
        }

        return result;
    }

    @Override
    public Set<Payment> findById(Integer id) {

        System.out.println("--- SELECT * FROM payment " +
                "WHERE id = '"+ id +"' ---");
        Set<Payment> result = new HashSet<>();
        Connection connection = null;
        Statement statement   = null;
        PreparedStatement preparedStatement;

        try {

            connection = this.setConnection();
            System.out.println("--- Database opened successfully ---");
            statement = connection.createStatement();
            System.out.println("--- Connection: " + connection.getMetaData()+ " ---");

            preparedStatement = connection.prepareStatement("SELECT * FROM  payment " +
                    "WHERE id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("--- Result set: " + resultSet.getCursorName()+ " ---");

            while (resultSet.next()){
                Payment temp = new Payment();
                temp.setId(resultSet.getInt("id"));
                temp.setCustomerId(resultSet.getInt("customerId"));
                temp.setContractId(resultSet.getInt("contractId"));
                temp.setPaymentAmount(resultSet.getDouble("paymentAmount"));
                temp.setPaymentDate(new SimpleDateFormat("yyyy-mm-dd").parse(resultSet.getString("paymentDate")));
                result.add(temp);
            }

            resultSet.close();
            this.closeConnection();

        } catch (SQLException e){
            System.err.println(e.getMessage());
        } catch (ParseException e){
            System.err.println(e.getMessage());
        } finally {
            this.closeConnection();
        }

        return result;
    }

    @Override
    public Set<Payment> findAll(String search) {

        System.out.println("---" + this.getClass().getName() +  " findAll clicked. ---");
        System.out.println("--- SELECT * FROM payment " +
                "WHERE id = '"+ search +"' OR customerId LIKE '%"+ search +"%' OR contractId LIKE '%"+ search +"%' OR " +
                "paymentAmount LIKE '%"+ search +"%' OR paymentDate LIKE '%"+ search +"%'; ---");
        Connection connection = null;
        Statement statement   = null;
        PreparedStatement preparedStatement;

        Set<Payment> result =  new HashSet<Payment>();

        try {
            connection = this.setConnection();
            System.out.println("--- Database opened successfully ---");
            statement = connection.createStatement();
            System.out.println("--- Connection: " + connection.getMetaData()+ " ---");

            if (search.matches(".+[a-zA-Z]+.?")){
                // TODO search for string fields
                preparedStatement = connection.prepareStatement("SELECT * FROM payment " +
                        "WHERE paymentAmount = '"+ search +"' OR paymentDate LIKE '%"+ search +"%';");
                ResultSet resultSet = preparedStatement.executeQuery();

                System.out.println("--- Result set: " + resultSet.getCursorName()+ " ---");

                while (resultSet.next()){
                    Payment payment = new Payment();
                    payment.setId(resultSet.getInt("id"));
                    payment.setCustomerId(resultSet.getInt("customerId"));
                    payment.setContractId(resultSet.getInt("contractId"));
                    payment.setPaymentAmount(resultSet.getDouble("paymentAmount"));
                    payment.setPaymentDate(resultSet.getDate("paymentDate"));
                    result.add(payment);
                }

                resultSet.close();
//              statement.close();
//              connection.close();
                this.closeConnection();

            } else {
                Integer temp = Integer.valueOf(search);
                // TODO search for integer fields
                preparedStatement = connection.prepareStatement("SELECT * FROM payment " +
                        "WHERE id = '"+ temp +"' OR customerId = '"+ temp +"' OR contractId = '"+ temp +"' OR " +
                        "paymentAmount = '"+ temp +"' OR paymentDate LIKE '%"+ search +"%';");
                ResultSet resultSet = preparedStatement.executeQuery();

                System.out.println("--- Result set: " + resultSet.getCursorName()+ " ---");

                while (resultSet.next()){
                    Payment payment = new Payment();
                    payment.setId(resultSet.getInt("id"));
                    payment.setCustomerId(resultSet.getInt("customerId"));
                    payment.setContractId(resultSet.getInt("contractId"));
                    payment.setPaymentAmount(resultSet.getDouble("paymentAmount"));
                    payment.setPaymentDate(resultSet.getDate("paymentDate"));
                    result.add(payment);
                }

                resultSet.close();
//              statement.close();
//              connection.close();
                this.closeConnection();
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            this.closeConnection();
        }
        return result;
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

    @Override
    public boolean FindStatusOk(Integer customerId, Integer contractId, Long limit) { //limit = 7, 15 o 30

        boolean find = false;

        System.out.println("--- SELECT * FROM payment " +
                "WHERE customerId = "+ customerId +" AND contractId = "+ contractId +" AND paymentDate < "+ limit +"---");
        Connection connection = null;
        Statement statement   = null;
        PreparedStatement preparedStatement;

        try {

            connection = this.setConnection();
            System.out.println("--- Database opened successfully ---");
            statement = connection.createStatement();
            System.out.println("--- Connection: " + connection.getMetaData()+ " ---");

            preparedStatement = connection.prepareStatement("SELECT * FROM payment " +
                    "WHERE customerId = ? AND contractId = ? AND paymentDate < (SELECT DATETIME('now', '-"+ limit +" day')");
            preparedStatement.setInt(1, customerId);
            preparedStatement.setInt(2, contractId);
            preparedStatement.executeUpdate();

            connection.commit();
            statement.close();

        } catch (SQLException e){
            System.err.println(e.getMessage());
        } finally {
            find = true;
            this.closeConnection();
        }
        return find;
    }
}
