package com.muuyal.escala.billingmanagement.dao.impl;

import com.muuyal.escala.billingmanagement.dao.DBConnection;
import com.muuyal.escala.billingmanagement.dao.interfaces.ProjectDao;
import com.muuyal.escala.billingmanagement.dao.interfaces.StaffDao;
import com.muuyal.escala.billingmanagement.entities.Project;
import com.muuyal.escala.billingmanagement.entities.Staff;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;

@Repository
public class StaffDaoImp extends DBConnection implements StaffDao {


    public StaffDaoImp(){
    }

    @Override
    public boolean save(Staff staff) {
        boolean saved = false;

        System.out.println("--- insert into staff " +
                "(name, phone, email, address_street, address_city, address_colony, address_pc, rol, salary)" +
                "values ('"+ staff.getName() +"','"+ staff.getPhone() +"', " +
                "'"+ staff.getEmail() +"','"+ staff.getAddressStreet() +"', " +
                " '"+ staff.getAddressCity() +"', '"+ staff.getAddressColony() +"', " +
                " '"+ staff.getAddressPC() +"', '"+ staff.getRol() +"', '"+ staff.getSalary() +"' " +
                "') ---");
        Connection connection = null;
        Statement statement   = null;
        PreparedStatement preparedStatement;

        try {

            connection = this.setConnection();
            System.out.println("--- Database opened successfully ---");
            statement = connection.createStatement();
            System.out.println("--- Connection: " + connection.getMetaData()+ " ---");

            preparedStatement = connection.prepareStatement("INSERT INTO staff (" +
                    "name, phone, email, address_street, address_city, address_colony, address_pc, rol, salary) " +
                    "values (?,?,?,?,?,?,?,?,?)");
            preparedStatement.setString(1, staff.getName());
            preparedStatement.setString(2, staff.getPhone());
            preparedStatement.setString(3, staff.getEmail());
            preparedStatement.setString(4, staff.getAddressStreet());
            preparedStatement.setString(5, staff.getAddressCity());
            preparedStatement.setString(6, staff.getAddressColony());
            preparedStatement.setString(7, staff.getAddressPC());
            preparedStatement.setString(8, staff.getRol());
            preparedStatement.setDouble(9, staff.getSalary());
            preparedStatement.executeUpdate();

            connection.commit();
            statement.close();

        } catch (SQLException e){
            System.err.println(e.getMessage());
        } finally {
            saved = true;
            this.closeConnection();
        }
        return saved;
    }

    @Override
    public Set<Staff> findAll() {

        System.out.println("---" + this.getClass().getName() +  " findAll clicked. ---");
        System.out.println("--- SELECT * FROM staff ---");
        Connection connection = null;
        Statement statement   = null;
        PreparedStatement preparedStatement;

        Set<Staff> result =  new HashSet<Staff>();
        try {

            connection = this.setConnection();
            System.out.println("--- Database opened successfully ---");
            statement = connection.createStatement();
            System.out.println("--- Connection: " + connection.getMetaData()+ " ---");

            preparedStatement = connection.prepareStatement("SELECT * FROM staff");
            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("--- Result set: " + resultSet.getCursorName()+ " ---");

            while (resultSet.next()){
                Staff staff = new Staff();
                staff.setId(resultSet.getInt("id"));
                staff.setName(resultSet.getString("name"));
                staff.setPhone(resultSet.getString("phone"));
                staff.setEmail(resultSet.getString("email"));
                staff.setAddressStreet(resultSet.getString("address_street"));
                staff.setAddressCity(resultSet.getString("address_city"));
                staff.setAddressColony(resultSet.getString("address_colony"));
                staff.setAddressPC(resultSet.getString("address_pc"));
                staff.setRol(resultSet.getString("rol"));
                staff.setSalary(resultSet.getDouble("salary"));
                result.add(staff);
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
    public Set<Staff> findById(Integer id) {

        System.out.println("---" + this.getClass().getName() +  " findAll clicked. ---");
        System.out.println("--- SELECT * FROM staff WHERE id = '"+ id +"' ---");
        Connection connection = null;
        Statement statement   = null;
        PreparedStatement preparedStatement;

        Set<Staff> result =  new HashSet<Staff>();
        try {

            connection = this.setConnection();
            System.out.println("--- Database opened successfully ---");
            statement = connection.createStatement();
            System.out.println("--- Connection: " + connection.getMetaData()+ " ---");

            preparedStatement = connection.prepareStatement("SELECT * FROM staff WHERE id = '"+ id +"';");
            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("--- Result set: " + resultSet.getCursorName()+ " ---");

            while (resultSet.next()){
                Staff staff = new Staff();
                staff.setId(resultSet.getInt("id"));
                staff.setName(resultSet.getString("name"));
                staff.setPhone(resultSet.getString("phone"));
                staff.setEmail(resultSet.getString("email"));
                staff.setAddressStreet(resultSet.getString("addressStreet"));
                staff.setAddressCity(resultSet.getString("addressCity"));
                staff.setAddressColony(resultSet.getString("addressColony"));
                staff.setAddressPC(resultSet.getString("addressPC"));
                staff.setRol(resultSet.getString("rol"));
                staff.setSalary(resultSet.getDouble("salary"));
                result.add(staff);
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
    public boolean update(Staff staff) {

        boolean updated = false;

        System.out.println("--- UPDATE staff " +
                "SET id = '"+ staff.getId() +"', name = '"+ staff.getName() +"', phone = '"+ staff.getPhone() +"', " +
                "eMail = '"+ staff.getEmail() +"', addressStreet = '"+ staff.getAddressStreet() +"', " +
                "addressCity = '"+ staff.getAddressCity() +"', addressColony = '"+ staff.getAddressColony() +"', " +
                "addressPC = '"+ staff.getAddressPC() +"', rol = '"+ staff.getRol() +"', salary = '"+ staff.getSalary() +"' " +
                "WHERE id = '"+ staff.getId() +"' ---");
        Connection connection = null;
        Statement statement   = null;
        PreparedStatement preparedStatement;

        try {

            connection = this.setConnection();
            System.out.println("--- Database opened successfully ---");
            statement = connection.createStatement();
            System.out.println("--- Connection: " + connection.getMetaData()+ " ---");

            preparedStatement = connection.prepareStatement("UPDATE staff " +
                    "SET id = ?, name = ?, phone = ?, eMail = ?, addressStreet = ?, addressCity = ?, addressColony = ?, " +
                    "addressPC = ?, rol = ?, salary = ? " +
                    "WHERE id = ?");
            preparedStatement.setInt(1, staff.getId());
            preparedStatement.setString(2, staff.getName());
            preparedStatement.setString(3, staff.getPhone());
            preparedStatement.setString(4, staff.getEmail());
            preparedStatement.setString(5, staff.getAddressStreet());
            preparedStatement.setString(6, staff.getAddressCity());
            preparedStatement.setString(7, staff.getAddressColony());
            preparedStatement.setString(8, staff.getAddressPC());
            preparedStatement.setString(9, staff.getRol());
            preparedStatement.setDouble(10, staff.getSalary());
            preparedStatement.setInt(11, staff.getId());
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
    public boolean delete(Staff staff) {

        boolean deleted = false;

        System.out.println("--- DELETE FROM staff WHERE id="+staff.getId()+" ---");
        Connection connection = null;
        Statement statement   = null;
        PreparedStatement preparedStatement;

        try {

            connection = this.setConnection();
            System.out.println("--- Database opened successfully ---");
            statement = connection.createStatement();
            System.out.println("--- Connection: " + connection.getMetaData()+ " ---");

            preparedStatement = connection.prepareStatement("DELETE FROM staff WHERE id=?");
            preparedStatement.setInt(1, staff.getId());
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
    public Set<Staff> findAll(String search) {

        System.out.println("---" + this.getClass().getName() +  " findAll clicked. ---");
        System.out.println("--- SELECT * FROM staff " +
                "WHERE id = '"+ search +"' OR name LIKE '"+ search +"' OR phone LIKE '"+ search +"' OR " +
                "email LIKE '"+ search +"' OR addressStreet LIKE '"+ search +"' OR addressCity LIKE '"+ search +"' OR " +
                "addressColony LIKE '"+ search +"' OR addressPC LIKE '"+ search +"' OR rol LIKE '"+ search +"' OR " +
                "salary = '"+ search +"'; ---");
        Connection connection = null;
        Statement statement   = null;
        PreparedStatement preparedStatement;

        Set<Staff> result =  new HashSet<Staff>();

        try {
            connection = this.setConnection();
            System.out.println("--- Database opened successfully ---");
            statement = connection.createStatement();
            System.out.println("--- Connection: " + connection.getMetaData()+ " ---");

            if (search.matches(".+[a-zA-Z]+.?")){

                // TODO search for string fields
                preparedStatement = connection.prepareStatement("SELECT * FROM staff " +
                        "WHERE name LIKE '"+ search +"' OR phone LIKE '"+ search +"' OR email LIKE '"+ search +"' OR " +
                        "addressStreet LIKE '"+ search +"' OR addressCity LIKE '"+ search +"' OR addressColony LIKE '"+ search +"' OR " +
                        "addressPC LIKE '"+ search +"' OR rol LIKE '"+ search +"';");
                ResultSet resultSet = preparedStatement.executeQuery();

                System.out.println("--- Result set: " + resultSet.getCursorName()+ " ---");

                while (resultSet.next()){
                    Staff staff = new Staff();
                    staff.setId(resultSet.getInt("id"));
                    staff.setName(resultSet.getString("name"));
                    staff.setPhone(resultSet.getString("phone"));
                    staff.setEmail(resultSet.getString("email"));
                    staff.setAddressStreet(resultSet.getString("addressStreet"));
                    staff.setAddressCity(resultSet.getString("addressCity"));
                    staff.setAddressColony(resultSet.getString("addressColony"));
                    staff.setAddressPC(resultSet.getString("addressPC"));
                    staff.setRol(resultSet.getString("rol"));
                    staff.setSalary(resultSet.getDouble("salary"));
                    result.add(staff);
                }

                resultSet.close();
//            statement.close();
//            connection.close();
                this.closeConnection();

            } else {
                Integer temp = Integer.valueOf(search);
                // TODO search for integer fields
                preparedStatement = connection.prepareStatement("SELECT * FROM staff " +
                        "WHERE id = '"+ temp +"' OR  phone LIKE '"+ temp +"' OR email LIKE '"+ temp +"' OR " +
                        "addressStreet LIKE '"+ temp +"' OR addressColony LIKE '"+ temp +"' OR " +
                        "addressPC LIKE '"+ temp +"' OR rol LIKE '"+ temp +"' OR salary = '"+ temp +"';");
                ResultSet resultSet = preparedStatement.executeQuery();

                System.out.println("--- Result set: " + resultSet.getCursorName()+ " ---");

                while (resultSet.next()){
                    Staff staff = new Staff();
                    staff.setId(resultSet.getInt("id"));
                    staff.setName(resultSet.getString("name"));
                    staff.setPhone(resultSet.getString("phone"));
                    staff.setEmail(resultSet.getString("email"));
                    staff.setAddressStreet(resultSet.getString("addressStreet"));
                    staff.setAddressCity(resultSet.getString("addressCity"));
                    staff.setAddressColony(resultSet.getString("addressColony"));
                    staff.setAddressPC(resultSet.getString("addressPC"));
                    staff.setRol(resultSet.getString("rol"));
                    staff.setSalary(resultSet.getDouble("salary"));
                    result.add(staff);
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
}
