package com.muuyal.escala.billingmanagement.dao.impl;

import com.muuyal.escala.billingmanagement.dao.DBConnection;
import com.muuyal.escala.billingmanagement.dao.interfaces.ContractDao;
import com.muuyal.escala.billingmanagement.entities.Contract;
import com.muuyal.escala.billingmanagement.entities.Customer;
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

    SimpleDateFormat DateFormat = new SimpleDateFormat("YYYY-MM-DD");

    @Override
    public boolean save(Contract contract) {

        boolean saved = false;

        System.out.println("--- INSERT INTO contract( customer_id, project_id, discount, createdOn, " +
                "deadline, customerName, projectName, paymentSchedule, finalPrice ) " +
                "VALUES ("+contract.getCustomerId()+","+contract.getProjectId()+","+contract.getDiscount()+"," +
                ""+contract.getCreatedOn()+","+contract.getDeadline()+","+contract.getCustomerName()+"," +
                ""+contract.getProjectName()+","+contract.getPaymentSchedule()+","+contract.getFinalPrice()+") ---");

        Connection connection = null;
        Statement statement   = null;
        PreparedStatement preparedStatement;

        try {

            connection = this.setConnection();
            System.out.println("--- Database opened successfully ---");
            statement = connection.createStatement();
            System.out.println("--- Connection: " + connection.getMetaData()+ " ---");

            preparedStatement = connection.prepareStatement("INSERT INTO contract(" +
                    "customer_id, project_id, discount, createdOn, deadline, paymentSchedule, customer_name, project_name, final_price) " +
                    "VALUES (?,?,?,?,?,?,?,?,?)");
            preparedStatement.setInt(1, contract.getCustomerId());
            preparedStatement.setInt(2, contract.getProjectId() );
            preparedStatement.setInt(3, contract.getDiscount());
            preparedStatement.setString(4, contract.getCreatedOn().toString());
            preparedStatement.setString(5, contract.getDeadline().toString());
            preparedStatement.setString(6, contract.getPaymentSchedule());
            preparedStatement.setString(7, contract.getCustomerName());
            preparedStatement.setString(8, contract.getProjectName());
            preparedStatement.setDouble(9, contract.getFinalPrice());
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
                temp.setId(resultSet.getInt("id"));
                temp.setCustomerId(resultSet.getInt("customer_id"));
                temp.setProjectId(resultSet.getInt("project_id"));
                temp.setDiscount(resultSet.getInt("discount"));
                temp.setCreatedOn(DateFormat.parse(resultSet.getString("createdOn")));
                temp.setCreatedOn(DateFormat.parse(resultSet.getString("deadline")));
                temp.setPaymentSchedule(resultSet.getString("paymentSchedule"));
                temp.setProjectName(resultSet.getString("project_name"));
                temp.setCustomerName(resultSet.getString("customer_name"));
                temp.setFinalPrice(resultSet.getDouble("final_price"));
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
    public Set<Contract> findAll(String search){

        //No se pude poner formato a la fecha.

        System.out.println("---" + this.getClass().getName() +  " findAll clicked. ---");
        System.out.println("--- SELECT * FROM contract WHERE id = '" + search + "', customer_id = '"+ search +"' OR " +
                "projectId = '"+ search +"' OR discount = '"+ search +"' OR createdOn LIKE '%"+ search +"%' OR " +
                "deadline LIKE '%"+ search +"%' OR payment_schedule LIKE '%"+ search +"%' OR customer_name LIKE '%"+ search +"%' OR " +
                "project_name LIKE '%"+ search +"%' OR final_price = '"+ search +"'; ---");
        Connection connection = null;
        Statement statement   = null;
        PreparedStatement preparedStatement;

        Set<Contract> result =  new HashSet<Contract>();

        try {
            connection = this.setConnection();
            System.out.println("--- Database opened successfully ---");
            statement = connection.createStatement();
            System.out.println("--- Connection: " + connection.getMetaData()+ " ---");

            if (search.matches(".+[a-zA-Z]+.?")){
                // TODO search for string fields

                preparedStatement = connection.prepareStatement("SELECT * FROM contract " +
                        "WHERE createdOn LIKE '%"+ search +"%' OR deadline LIKE '%"+ search +"%' OR " +
                        "paymentSchedule LIKE '%"+ search +"%' OR customerName LIKE '%"+ search +"%' OR " +
                        "projectName LIKE '%"+ search +"%';");
                ResultSet resultSet = preparedStatement.executeQuery();

                System.out.println("--- Result set: " + resultSet.getCursorName()+ " ---");

                while (resultSet.next()){
                    Contract contract = new Contract();
                    contract.setId(resultSet.getInt("id"));
                    contract.setCustomerId(resultSet.getInt("customer_id"));
                    contract.setProjectId(resultSet.getInt("project_id"));
                    contract.setDiscount(resultSet.getInt("discount"));
                    contract.setCreatedOn(resultSet.getDate("createdOn"));
                    contract.setCreatedOn(resultSet.getDate("deadline"));
                    contract.setPaymentSchedule(resultSet.getString("paymentSchedule"));
                    contract.setProjectName(resultSet.getString("project_name"));
                    contract.setCustomerName(resultSet.getString("customer_name"));
                    contract.setFinalPrice(resultSet.getDouble("final_price"));
                    result.add(contract);
                }

                resultSet.close();
//            statement.close();
//            connection.close();
                this.closeConnection();

            } else {
                Integer temp = Integer.valueOf(search);
                // TODO search for integer fields

                preparedStatement = connection.prepareStatement("SELECT * FROM contract " +
                        "WHERE id = '" + temp + "', customer_id = '"+ temp +"' OR projectId = '"+ temp +"' OR " +
                        "discount = '"+ temp +"' OR createdOn LIKE '%"+ search +"%' OR deadline LIKE '%"+ search +"%' OR " +
                        "paymentSchedule LIKE '%"+ search +"%' OR customerName LIKE '%"+ search +"%' OR " +
                        "projectName LIKE '%"+ search +"%' OR finalPrice = '"+ temp +"';");
                ResultSet resultSet = preparedStatement.executeQuery();

                System.out.println("--- Result set: " + resultSet.getCursorName()+ " ---");

                while (resultSet.next()){
                    Contract contract = new Contract();
                    contract.setId(resultSet.getInt("id"));
                    contract.setCustomerId(resultSet.getInt("customer_id"));
                    contract.setProjectId(resultSet.getInt("project_id"));
                    contract.setDiscount(resultSet.getInt("discount"));
                    contract.setCreatedOn(resultSet.getDate("createdOn"));
                    contract.setCreatedOn(resultSet.getDate("deadline"));
                    contract.setPaymentSchedule(resultSet.getString("paymentSchedule"));
                    contract.setProjectName(resultSet.getString("project_name"));
                    contract.setCustomerName(resultSet.getString("customer_name"));
                    contract.setFinalPrice(resultSet.getDouble("final_price"));
                    result.add(contract);
                }

                resultSet.close();
//            statement.close();
//            connection.close();
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
                temp.setId(resultSet.getInt("id"));
                temp.setCustomerId(resultSet.getInt("customer_id"));
                temp.setProjectId(resultSet.getInt("project_id"));
                temp.setDiscount(resultSet.getInt("discount"));
                temp.setCreatedOn(DateFormat.parse(resultSet.getString("createdOn")));
                temp.setCreatedOn(DateFormat.parse(resultSet.getString("deadline")));
                temp.setPaymentSchedule(resultSet.getString("paymentSchedule"));
                temp.setProjectName(resultSet.getString("project_name"));
                temp.setCustomerName(resultSet.getString("customer_name"));
                temp.setFinalPrice(resultSet.getDouble("final_price"));
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
    public Set<Contract> findById(Integer id) {

        System.out.println("---" + this.getClass().getName() +  " findByProject clicked. ---");
        System.out.println("--- SELECT * FROM contract WHERE id = '"+ id +"'; ---");
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
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("--- Result set: " + resultSet.getCursorName()+ " ---");

            while (resultSet.next()){
                Contract temp = new Contract();
                temp.setId(resultSet.getInt("id"));
                temp.setCustomerId(resultSet.getInt("customer_id"));
                temp.setProjectId(resultSet.getInt("project_id"));
                temp.setDiscount(resultSet.getInt("discount"));
                temp.setCreatedOn(DateFormat.parse(resultSet.getString("createdOn")));
                temp.setCreatedOn(DateFormat.parse(resultSet.getString("deadline")));
                temp.setPaymentSchedule(resultSet.getString("paymentSchedule"));
                temp.setProjectName(resultSet.getString("project_name"));
                temp.setCustomerName(resultSet.getString("customer_name"));
                temp.setFinalPrice(resultSet.getDouble("final_price"));
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
    public Set<Contract> findByCustomer(Integer customerId) {

        System.out.println("---" + this.getClass().getName() +  " findByCustomer clicked. ---");
        System.out.println("--- SELECT * FROM contract WHERE customer_id = '"+ customerId +"'; ---");
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
            preparedStatement.setString(1, customerId.toString());

            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("--- Result set: " + resultSet.getCursorName()+ " ---");

            while (resultSet.next()){
                Contract temp = new Contract();
                temp.setId(resultSet.getInt("id"));
                temp.setCustomerId(resultSet.getInt("customer_id"));
                temp.setProjectId(resultSet.getInt("project_id"));
                temp.setDiscount(resultSet.getInt("discount"));
                temp.setCreatedOn(DateFormat.parse(resultSet.getString("createdOn")));
                temp.setCreatedOn(DateFormat.parse(resultSet.getString("deadline")));
                temp.setPaymentSchedule(resultSet.getString("paymentSchedule"));
                temp.setCustomerName(resultSet.getString("customer_name"));
                temp.setProjectName(resultSet.getString("project_name"));
                temp.setFinalPrice(resultSet.getDouble("final_price"));
                result.add(temp);
            }

            resultSet.close();
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
                "SET id="+contract.getId()+", customer_id="+contract.getCustomerId()+", project_id="+contract.getProjectId()+", " +
                "discount="+contract.getDiscount()+", createdOn="+contract.getCreatedOn()+", deadline="+contract.getDeadline()+", " +
                "paymentSchedule="+contract.getPaymentSchedule()+", customer_name="+contract.getCustomerName()+", " +
                "project_name="+contract.getProjectName()+ ", finalPrice="+ contract.getFinalPrice() +"  " +
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
                    "SET id=?, customer_id=?, project_id=?, discount=?, createdOn=?, " +
                    "deadline=?, paymentSchedule=?, customer_name=?, project_name=?, finalPrice=? " +
                    "WHERE id=?");
            preparedStatement.setInt(1, contract.getId());
            preparedStatement.setInt(2, contract.getCustomerId());
            preparedStatement.setInt(3, contract.getProjectId());
            preparedStatement.setInt(4, contract.getDiscount());
            preparedStatement.setString(5, contract.getCreatedOn().toString());
            preparedStatement.setString(6, contract.getDeadline().toString());
            preparedStatement.setString(7, contract.getPaymentSchedule());
            preparedStatement.setString(8, contract.getCustomerName());
            preparedStatement.setString(9, contract.getProjectName());
            preparedStatement.setDouble(10, contract.getFinalPrice());
            preparedStatement.setInt(11, contract.getId());

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
            preparedStatement.setInt(1, contract.getId());
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
            preparedStatement.setInt(1, projectId);

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
    public Set<Contract> findByProjectAndCustomer(Integer projectId, Integer customerId) {

        System.out.println("---" + this.getClass().getName() +  " findByProjectAndCustomer clicked. ---");
        System.out.println("--- SELECT * FROM contract " +
                "WHERE customerId = '"+ customerId +"' AND projectId = '"+ projectId +"'; ---");
        Connection connection = null;
        Statement statement   = null;
        PreparedStatement preparedStatement;

        Set<Contract> result =  new HashSet<Contract>();
        try {

            connection = this.setConnection();
            System.out.println("--- Database opened successfully ---");
            statement = connection.createStatement();
            System.out.println("--- Connection: " + connection.getMetaData()+ " ---");

            preparedStatement = connection.prepareStatement("SELECT * FROM contract " +
                    "WHERE customer_id = ? AND project_id = ?");
            preparedStatement.setInt(1, customerId);
            preparedStatement.setInt(2, projectId);

            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("--- Result set: " + resultSet.getCursorName()+ " ---");

            while (resultSet.next()){
                Contract temp = new Contract();
                temp.setId(resultSet.getInt("id"));
                temp.setCustomerId(resultSet.getInt("customer_id"));
                temp.setProjectId(resultSet.getInt("project_id"));
                temp.setDiscount(resultSet.getInt("discount"));
                temp.setCreatedOn(DateFormat.parse(resultSet.getString("createdOn")));
                temp.setCreatedOn(DateFormat.parse(resultSet.getString("deadline")));
                temp.setPaymentSchedule(resultSet.getString("paymentSchedule"));
                temp.setCustomerName(resultSet.getString("customer_name"));
                temp.setProjectName(resultSet.getString("project_name"));
                temp.setFinalPrice(resultSet.getDouble("final_price"));
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
