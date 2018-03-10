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

        System.out.println("--- insert into staff (name, phone, email, addressStreet, addressCity, addressColony, addressPC, rol, salary)" +
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

            preparedStatement = connection.prepareStatement("INSERT INTO staff (name, phone, email, addressStreet, addressCity, addressColony, addressPC, rol, salary) " +
                    "values (?,?,?,?,?,?,?,?,?)");
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
            saved = true;
            this.closeConnection();
        }
        return saved;
    }

    @Override
    public Set<Staff> findAll() {
        return null;
    }

    @Override
    public Staff findById(Integer id) {
        return null;
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
        return null;
    }
}
