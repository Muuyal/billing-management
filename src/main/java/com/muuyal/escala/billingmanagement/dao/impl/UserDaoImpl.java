package com.muuyal.escala.billingmanagement.dao.impl;

import com.muuyal.escala.billingmanagement.dao.DBConnection;
import com.muuyal.escala.billingmanagement.dao.interfaces.UserDao;
import com.muuyal.escala.billingmanagement.entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Set;

public class UserDaoImpl extends DBConnection implements UserDao {

    @Override
    public boolean save(User user) {
        boolean saved = false;

        System.out.println("--- INSERT INTO user(id, username, password, rol) " +
                "VALUES ('"+ user.getId() +"','"+ user.getUserName() +"'," +
                "'"+ user.getPassword() +"','"+ user.getRol() +"') ---");

        Connection connection = null;
        Statement statement   = null;
        PreparedStatement preparedStatement;

        try {

            connection = this.setConnection();
            System.out.println("--- Database opened successfully ---");
            statement = connection.createStatement();
            System.out.println("--- Connection: " + connection.getMetaData()+ " ---");

            preparedStatement = connection.prepareStatement("INSERT INTO user(" +
                    "id, username, password, rol) " +
                    "VALUES (?,?,?,?)");
            preparedStatement.setInt(1, user.getId() );
            preparedStatement.setString(2, user.getUserName() );
            preparedStatement.setString(3, user.getPassword() );
            preparedStatement.setString(4, user.getRol() );
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
    public Set<User> findAll() {
        return null;
    }

    @Override
    public Set<User> findAll(User user) {
        return null;
    }

    @Override
    public User findOne(User user) {
        return null;
    }

    @Override
    public boolean update(User user) {

        boolean updated = false;

        System.out.println("--- UPDATE user " +
                "SET id = '"+ user.getId() +"', userName = '"+ user.getUserName() +"', " +
                "password = '"+ user.getPassword() +"', rol = '"+ user.getRol() +"'  " +
                "WHERE id = '"+ user.getId() +"' ---");
        Connection connection = null;
        Statement statement   = null;
        PreparedStatement preparedStatement;

        try {

            connection = this.setConnection();
            System.out.println("--- Database opened successfully ---");
            statement = connection.createStatement();
            System.out.println("--- Connection: " + connection.getMetaData()+ " ---");

            preparedStatement = connection.prepareStatement("UPDATE user " +
                    "SET id = ?, userName = ?, password = ?, rol = ?  " +
                    "WHERE id = ?");
            preparedStatement.setInt(1, user.getId());
            preparedStatement.setString(2, user.getUserName());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getRol());
            preparedStatement.setInt(5, user.getId());
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
    public boolean delete(User user) {

        boolean deleted = false;

        System.out.println("--- DELETE FROM user WHERE id="+user.getId()+" ---");
        Connection connection = null;
        Statement statement   = null;
        PreparedStatement preparedStatement;

        try {

            connection = this.setConnection();
            System.out.println("--- Database opened successfully ---");
            statement = connection.createStatement();
            System.out.println("--- Connection: " + connection.getMetaData()+ " ---");

            preparedStatement = connection.prepareStatement("DELETE FROM user WHERE id=?");
            preparedStatement.setInt(1, user.getId());
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
    public Set<User> findAll(String search) {
        return null;
    }
}
