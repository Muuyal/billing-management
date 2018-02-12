package com.muuyal.escala.billingmanagement.dao.impl;

import com.muuyal.escala.billingmanagement.dao.DBConnection;
import com.muuyal.escala.billingmanagement.dao.interfaces.ContractDao;
import com.muuyal.escala.billingmanagement.entities.Contract;
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

        System.out.println("--- INSERT INTO contract(customerId, projectId, discount, createdOn, deadline, customer_name, project_name) " +
                "VALUES ('"+ contract.getCustomerId() +"','"+ contract.getProjectId() +"'," +
                "'"+ contract.getDiscount() +"','"+ contract.getCreatedOn() +"','"+ contract.getDeadline() +
                "','" + contract.getCustomerName() + "','" + contract.getProjectName() +
                "') ---");

        Connection connection = null;
        Statement statement   = null;
        PreparedStatement preparedStatement;

        try {

            connection = this.setConnection();
            System.out.println("--- Database opened successfully ---");
            statement = connection.createStatement();
            System.out.println("--- Connection: " + connection.getMetaData()+ " ---");

            preparedStatement = connection.prepareStatement("INSERT INTO contract(" +
                    "customer_id, project_id, discount, createdOn, deadline, paymentSchedule, customer_name, project_name) " +
                    "VALUES (?,?,?,?,?,?,?,?)");
            preparedStatement.setInt(1, contract.getCustomerId());
            preparedStatement.setInt(2, contract.getProjectId() );
            preparedStatement.setInt(3, contract.getDiscount());
            preparedStatement.setString(4, contract.getCreatedOn().toString());
            preparedStatement.setString(5, contract.getDeadline().toString());
            preparedStatement.setString(6, contract.getPaymentSchedule());
            preparedStatement.setString(7, contract.getCustomerName());
            preparedStatement.setString(8, contract.getProjectName());
            preparedStatement.executeUpdate();

            connection.commit();
            saved = true;
        } catch (SQLException e){

            System.err.println(e.getMessage());

        } finally {

            this.closeConnection();

        }
        return saved;
    }

    @Override
    public Set<Contract> findAll() {

        System.out.println("---" + this.getClass().getName() +  " findAll clicked. ---");
        System.out.println("--- SELECT * FROM contract; ---");
        Connection connection = null;
        Statement statement   = null;
        PreparedStatement preparedStatement;

        Set<Contract> result =  new HashSet<Contract>();
        try {

            connection = this.setConnection();
            System.out.println("--- Database opened successfully ---");
            statement = connection.createStatement();
            System.out.println("--- Connection: " + connection.getMetaData()+ " ---");

            preparedStatement = connection.prepareStatement("SELECT * FROM contract");
            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("--- Result set: " + resultSet.getCursorName()+ " ---");

            while (resultSet.next()){
                Contract temp = new Contract();
                temp.setId(resultSet.getString("id"));
                temp.setCustomerId(resultSet.getInt("customer_id"));
                temp.setProjectId(resultSet.getInt("project_id"));
                temp.setDiscount(resultSet.getInt("discount"));
                temp.setCreatedOn(new SimpleDateFormat("yyyy-mm-dd").parse(resultSet.getString("createdOn")));
                temp.setCreatedOn(new SimpleDateFormat("yyyy-mm-dd").parse(resultSet.getString("deadline")));
                temp.setProjectName(resultSet.getString("project_name"));
                temp.setCustomerName(resultSet.getString("customer_name"));
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
    public Set<Contract> findByProject(Integer contractId) {

        System.out.println("---" + this.getClass().getName() +  " findByProject clicked. ---");
        System.out.println("--- SELECT * FROM contract WHERE projectId = '"+ contractId +"'; ---");
        Connection connection = null;
        Statement statement   = null;
        PreparedStatement preparedStatement;

        Set<Contract> result =  new HashSet<Contract>();
        try {

            connection = this.setConnection();
            System.out.println("--- Database opened successfully ---");
            statement = connection.createStatement();
            System.out.println("--- Connection: " + connection.getMetaData()+ " ---");

            preparedStatement = connection.prepareStatement("SELECT * FROM contract WHERE projectId = ?");
            preparedStatement.setInt(1, contractId);

            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("--- Result set: " + resultSet.getCursorName()+ " ---");

            while (resultSet.next()){
                Contract temp = new Contract();
                temp.setId(resultSet.getString("id"));
                temp.setCustomerId(resultSet.getInt("customer_id"));
                temp.setProjectId(resultSet.getInt("project_id"));
                temp.setDiscount(resultSet.getInt("discount"));
                temp.setCreatedOn(new SimpleDateFormat("yyyy-mm-dd").parse(resultSet.getString("createdOn")));
                temp.setCreatedOn(new SimpleDateFormat("yyyy-mm-dd").parse(resultSet.getString("deadline")));
                temp.setProjectName(resultSet.getString("project_name"));
                temp.setCustomerName(resultSet.getString("customer_name"));
                result.add(temp);
            }

            resultSet.close();
            this.closeConnection();


        } catch (Exception e){
            System.err.println("--- Error found " + e.getClass().getName() + ":" + e.getMessage());
        }
        return result;
    }

    @Override
    public Contract findById(Integer id) {
        return null;
    }

    @Override
    public Set<Contract> findByCustomer(Integer customerId) {

        System.out.println("---" + this.getClass().getName() +  " findByCustomer clicked. ---");
        System.out.println("--- SELECT * FROM contract WHERE customerId = '"+ customerId +"'; ---");
        Connection connection = null;
        Statement statement   = null;
        PreparedStatement preparedStatement;

        Set<Contract> result =  new HashSet<Contract>();
        try {

            connection = this.setConnection();
            System.out.println("--- Database opened successfully ---");
            statement = connection.createStatement();
            System.out.println("--- Connection: " + connection.getMetaData()+ " ---");

            preparedStatement = connection.prepareStatement("SELECT * FROM contract WHERE customerId = ?");
            preparedStatement.setString(1, customerId.toString());

            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("--- Result set: " + resultSet.getCursorName()+ " ---");

            while (resultSet.next()){
                Contract temp = new Contract();
                temp.setId(resultSet.getString("id"));
                temp.setCustomerId(resultSet.getInt("customer_id"));
                temp.setProjectId(resultSet.getInt("project_id"));
                temp.setDiscount(resultSet.getInt("discount"));
                temp.setCreatedOn(new SimpleDateFormat("yyyy-mm-dd").parse(resultSet.getString("createdOn")));
                temp.setCreatedOn(new SimpleDateFormat("yyyy-mm-dd").parse(resultSet.getString("deadline")));
                temp.setCustomerName(resultSet.getString("customer_name"));
                temp.setProjectName(resultSet.getString("project_name"));
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
    public boolean update(Contract contract) {

        boolean updated;

        System.out.println("--- UPDATE contract " +
                "SET id="+contract.getId()+", customer_id="+contract.getCustomerId()+", " +
                "project_id="+contract.getProjectId()+", discount="+contract.getDiscount()+", " +
                "createdOn="+contract.getCreatedOn()+", deadline="+contract.getDeadline()+" " +
                "customer_name="+contract.getCustomerName()+", project_name="+contract.getProjectName()+" " +
                "WHERE id="+contract.getId()+" ---");
        Connection connection = null;
        Statement statement   = null;
        PreparedStatement preparedStatement;

        try {

            connection = this.setConnection();
            System.out.println("--- Database opened successfully ---");
            statement = connection.createStatement();
            System.out.println("--- Connection: " + connection.getMetaData()+ " ---");

            preparedStatement = connection.prepareStatement("UPDATE contract " +
                    "SET id=?, customer_id=?, project_id=?, discount=?, createdOn=?, deadline=?, customer_name=?, project_name = ? " +
                    "WHERE id=?");
            preparedStatement.setString(1, contract.getId());
            preparedStatement.setInt(2, contract.getCustomerId());
            preparedStatement.setInt(3, contract.getProjectId());
            preparedStatement.setInt(4, contract.getDiscount());
            preparedStatement.setString(5, contract.getCreatedOn().toString());
            preparedStatement.setString(6, contract.getDeadline().toString());
            preparedStatement.setString(7, contract.getId());
            preparedStatement.setString(8, contract.getCustomerName());
            preparedStatement.setString(9, contract.getProjectName());
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
    public boolean delete(Contract contract) {

        boolean deleted = false;

        System.out.println("--- DELETE FROM contract WHERE id="+contract.getId()+" ---");
        Connection connection = null;
        Statement statement   = null;
        PreparedStatement preparedStatement;

        try {

            connection = this.setConnection();
            System.out.println("--- Database opened successfully ---");
            statement = connection.createStatement();
            System.out.println("--- Connection: " + connection.getMetaData()+ " ---");

            preparedStatement = connection.prepareStatement("DELETE FROM contract WHERE id=?");
            preparedStatement.setString(1, contract.getId());
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
    public Set<Integer> findCustomerIdsByProyect(Integer projectId){

        System.out.println("---" + this.getClass().getName() +  " findCustomersByProyect clicked. ---");
        System.out.println("--- SELECT customer_id FROM contract WHERE project_id = '"+ projectId +"'; ---");
        Connection connection = null;
        Statement statement   = null;
        PreparedStatement preparedStatement;

        Set<Integer> result =  new HashSet<>();
        try {

            connection = this.setConnection();
            System.out.println("--- Database opened successfully ---");
            statement = connection.createStatement();
            System.out.println("--- Connection: " + connection.getMetaData()+ " ---");

            preparedStatement = connection.prepareStatement("SELECT customer_id FROM contract WHERE project_id = ?");
            preparedStatement.setString(1, projectId.toString());

            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("--- Result set: " + resultSet.getCursorName()+ " ---");

            while (resultSet.next()){
                Integer temp = resultSet.getInt("customer_id");
                result.add(temp);
            }

            resultSet.close();
            this.closeConnection();

        } catch (Exception e){
            System.err.println("--- Error found " + e.getClass().getName() + ":" + e.getMessage());
        }
        return  result;
    }

    @Override
    public Set<Contract> findAll(String search){
        Set<Contract> result = new HashSet<>();

        if (search.matches("[a-zA-Z]+.?")){
            // TODO search for string fields
        } else {
            Integer temp = Integer.valueOf(search);
            // TODO search for integer fields

        }

        return result;
    }

}
