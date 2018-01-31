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

        System.out.println("--- INSERT INTO contract(customer_id, project_id, discount, createdOn, terminatedOn) " +
                "VALUES ('"+ contract.getCustomer_id() +"','"+ contract.getProject_id() +"'," +
                "'"+ contract.getDiscount() +"','"+ contract.getCreatedOn() +"','"+ contract.getTerminatedOn() +"') ---");

        Connection connection = null;
        Statement statement   = null;
        PreparedStatement preparedStatement;

        try {

            connection = this.setConnection();
            System.out.println("--- Database opened successfully ---");
            statement = connection.createStatement();
            System.out.println("--- Connection: " + connection.getMetaData()+ " ---");

            preparedStatement = connection.prepareStatement("INSERT INTO contract(" +
                    "customer_id, project_id, discount, createdOn, terminatedOn) " +
                    "VALUES (?,?,?,?,?)");
            preparedStatement.setString(1, contract.getCustomer_id());
            preparedStatement.setInt(2, contract.getProject_id() );
            preparedStatement.setString(3, contract.getDiscount().toString());
            preparedStatement.setString(4, contract.getCreatedOn().toString());
            preparedStatement.setString(5, contract.getTerminatedOn().toString());
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
                temp.setCustomer_id(resultSet.getString("customer_id("));
                temp.setProject_id(resultSet.getInt("project_id("));
                temp.setDiscount(resultSet.getInt("discount"));
                temp.setCreatedOn(new SimpleDateFormat("yyyy-mm-dd").parse(resultSet.getString("createdOn")));
                temp.setCreatedOn(new SimpleDateFormat("yyyy-mm-dd").parse(resultSet.getString("terminatedOn")));
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
    public Set<Contract> findByProject(Contract contract) {

        System.out.println("---" + this.getClass().getName() +  " findByProject clicked. ---");
        System.out.println("--- SELECT * FROM contract WHERE project_id = '"+ contract.getProject_id() +"'; ---");
        Connection connection = null;
        Statement statement   = null;
        PreparedStatement preparedStatement;

        Set<Contract> result =  new HashSet<Contract>();
        try {

            connection = this.setConnection();
            System.out.println("--- Database opened successfully ---");
            statement = connection.createStatement();
            System.out.println("--- Connection: " + connection.getMetaData()+ " ---");

            preparedStatement = connection.prepareStatement("SELECT * FROM contract WHERE project_id = ?");
            preparedStatement.setInt(1, contract.getProject_id());

            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("--- Result set: " + resultSet.getCursorName()+ " ---");

            while (resultSet.next()){
                Contract temp = new Contract();
                temp.setId(resultSet.getString("id"));
                temp.setCustomer_id(resultSet.getString("customer_id("));
                temp.setProject_id(resultSet.getInt("project_id("));
                temp.setDiscount(resultSet.getInt("discount"));
                temp.setCreatedOn(new SimpleDateFormat("yyyy-mm-dd").parse(resultSet.getString("createdOn")));
                temp.setCreatedOn(new SimpleDateFormat("yyyy-mm-dd").parse(resultSet.getString("terminatedOn")));
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
    public Set<Contract> findByCustomer(Contract contract) {

        System.out.println("---" + this.getClass().getName() +  " findByCustomer clicked. ---");
        System.out.println("--- SELECT * FROM contract WHERE customer_id = '"+ contract.getCustomer_id() +"'; ---");
        Connection connection = null;
        Statement statement   = null;
        PreparedStatement preparedStatement;

        Set<Contract> result =  new HashSet<Contract>();
        try {

            connection = this.setConnection();
            System.out.println("--- Database opened successfully ---");
            statement = connection.createStatement();
            System.out.println("--- Connection: " + connection.getMetaData()+ " ---");

            preparedStatement = connection.prepareStatement("SELECT * FROM contract WHERE customer_id = ?");
            preparedStatement.setString(1, contract.getCustomer_id());

            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("--- Result set: " + resultSet.getCursorName()+ " ---");

            while (resultSet.next()){
                Contract temp = new Contract();
                temp.setId(resultSet.getString("id"));
                temp.setCustomer_id(resultSet.getString("customer_id("));
                temp.setProject_id(resultSet.getInt("project_id("));
                temp.setDiscount(resultSet.getInt("discount"));
                temp.setCreatedOn(new SimpleDateFormat("yyyy-mm-dd").parse(resultSet.getString("createdOn")));
                temp.setCreatedOn(new SimpleDateFormat("yyyy-mm-dd").parse(resultSet.getString("terminatedOn")));
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
    public Set<Contract> findById(Contract contract) {

        System.out.println("---" + this.getClass().getName() +  " findById clicked. ---");
        System.out.println("--- SELECT * FROM contract WHERE id = '"+ contract.getId() +"'; ---");
        Connection connection = null;
        Statement statement   = null;
        PreparedStatement preparedStatement;

        Set<Contract> result =  new HashSet<Contract>();
        try {

            connection = this.setConnection();
            System.out.println("--- Database opened successfully ---");
            statement = connection.createStatement();
            System.out.println("--- Connection: " + connection.getMetaData()+ " ---");

            preparedStatement = connection.prepareStatement("SELECT * FROM contract WHERE id = ?");
            preparedStatement.setString(1, contract.getId());

            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("--- Result set: " + resultSet.getCursorName()+ " ---");

            while (resultSet.next()){
                Contract temp = new Contract();
                temp.setId(resultSet.getString("id"));
                temp.setCustomer_id(resultSet.getString("customer_id("));
                temp.setProject_id(resultSet.getInt("project_id("));
                temp.setDiscount(resultSet.getInt("discount"));
                temp.setCreatedOn(new SimpleDateFormat("yyyy-mm-dd").parse(resultSet.getString("createdOn")));
                temp.setCreatedOn(new SimpleDateFormat("yyyy-mm-dd").parse(resultSet.getString("terminatedOn")));
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

        boolean updated = false;

        System.out.println("--- UPDATE contract " +
                "SET id="+contract.getId()+", customer_id="+contract.getCustomer_id()+", " +
                "project_id="+contract.getProject_id()+", discount="+contract.getDiscount()+", " +
                "createdOn="+contract.getCreatedOn()+", terminatedOn="+contract.getTerminatedOn()+" " +
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
                    "SET id=?, customer_id=?, project_id=?, discount=?, createdOn=?, terminatedOn=? " +
                    "WHERE id=?");
            preparedStatement.setString(1, contract.getId());
            preparedStatement.setString(2, contract.getCustomer_id());
            preparedStatement.setInt(3, contract.getProject_id());
            preparedStatement.setInt(4, contract.getDiscount());
            preparedStatement.setString(5, contract.getCreatedOn().toString());
            preparedStatement.setString(6, contract.getTerminatedOn().toString());
            preparedStatement.setString(7, contract.getId());
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
    public Set<Contract> findAll(Contract contract) {

        System.out.println("---" + this.getClass().getName() +  " findAll clicked. ---");
        System.out.println("--- SELECT * FROM contract WHERE ; ---");
        Connection connection = null;
        Statement statement   = null;
        PreparedStatement preparedStatement;

        Set<Contract> result =  new HashSet<Contract>();
        try {

            connection = this.setConnection();
            System.out.println("--- Database opened successfully ---");
            statement = connection.createStatement();
            System.out.println("--- Connection: " + connection.getMetaData()+ " ---");

            preparedStatement = connection.prepareStatement("SELECT * FROM contract WHERE ");
            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("--- Result set: " + resultSet.getCursorName()+ " ---");

            while (resultSet.next()){
                Contract temp = new Contract();
                temp.setId(resultSet.getString("id"));
                temp.setCustomer_id(resultSet.getString("customer_id("));
                temp.setProject_id(resultSet.getInt("project_id("));
                temp.setDiscount(resultSet.getInt("discount"));
                temp.setCreatedOn(new SimpleDateFormat("yyyy-mm-dd").parse(resultSet.getString("createdOn")));
                temp.setCreatedOn(new SimpleDateFormat("yyyy-mm-dd").parse(resultSet.getString("terminatedOn")));
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
}
