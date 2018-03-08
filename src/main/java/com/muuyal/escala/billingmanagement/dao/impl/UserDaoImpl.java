package com.muuyal.escala.billingmanagement.dao.impl;

import com.muuyal.escala.billingmanagement.dao.DBConnection;
import com.muuyal.escala.billingmanagement.dao.interfaces.UserDao;
import com.muuyal.escala.billingmanagement.entities.User;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
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

        System.out.println("---" + this.getClass().getName() +  " findAll clicked. ---");
        System.out.println("--- SELECT * FROM user; ---");
        Connection connection = null;
        Statement statement   = null;
        PreparedStatement preparedStatement;

        Set<User> result =  new HashSet<User>();
        try {

            connection = this.setConnection();
            System.out.println("--- Database opened successfully ---");
            statement = connection.createStatement();
            System.out.println("--- Connection: " + connection.getMetaData()+ " ---");

            preparedStatement = connection.prepareStatement("SELECT * FROM user");
            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("--- Result set: " + resultSet.getCursorName()+ " ---");

            while (resultSet.next()){
                User temp = new User();
                temp.setId(resultSet.getInt("id"));
                temp.setUserName(resultSet.getString("username"));
                temp.setPassword(resultSet.getString("password"));
                temp.setRol(resultSet.getString("rol"));
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
    public Set<User> findAll(User user) {
        return null;
    }

    @Override
    public Set<User> findAll(String search) {

        System.out.println("---" + this.getClass().getName() +  " findAll clicked. ---");
        System.out.println("--- SELECT * FROM user WHERE id = '"+ search +"', username = '"+ search +"', " +
                "password = '"+ search +"', rol = '"+ search +"'; ---");
        Connection connection = null;
        Statement statement   = null;
        PreparedStatement preparedStatement;

        Set<User> result =  new HashSet<User>();

        try {
            connection = this.setConnection();
            System.out.println("--- Database opened successfully ---");
            statement = connection.createStatement();
            System.out.println("--- Connection: " + connection.getMetaData()+ " ---");

            if (search.matches(".+[a-zA-Z]+.?")){
                //TODO search for string fields

                preparedStatement = connection.prepareStatement("SELECT * FROM user " +
                        "WHERE username LIKE '%"+ search +"%' OR password LIKE '%"+ search +"%' OR " +
                        "rol LIKE '%"+ search +"%' ;");
                ResultSet resultSet = preparedStatement.executeQuery();

                System.out.println("--- Result set: " + resultSet.getCursorName()+ " ---");

                while (resultSet.next()){
                    User user = new User();
                    user.setId(resultSet.getInt("id"));
                    user.setUserName(resultSet.getString("username"));
                    user.setPassword(resultSet.getString("password"));
                    user.setRol(resultSet.getString("rol"));
                    result.add(user);
                }

                resultSet.close();
//            statement.close();
//            connection.close();
                this.closeConnection();

            } else {
                Integer temp = Integer.valueOf(search);
                //TODO search for integer fields

                preparedStatement = connection.prepareStatement("SELECT * FROM user " +
                        "WHERE id = '"+ temp +"' OR username LIKE '%"+ search +"%' OR password LIKE '%"+ search +"%' OR " +
                        "rol LIKE '%"+ search +"%' ;");
                ResultSet resultSet = preparedStatement.executeQuery();

                System.out.println("--- Result set: " + resultSet.getCursorName()+ " ---");

                while (resultSet.next()){
                    User user = new User();
                    user.setId(resultSet.getInt("id"));
                    user.setUserName(resultSet.getString("username"));
                    user.setPassword(resultSet.getString("password"));
                    user.setRol(resultSet.getString("rol"));
                    result.add(user);
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
    public Set <User> findOne(User user) {

        System.out.println("---" + this.getClass().getName() +  " findAll clicked. ---");
        System.out.println("--- SELECT * FROM user WHERE username = '"+ user +"'; ---");
        Connection connection = null;
        Statement statement   = null;
        PreparedStatement preparedStatement;

        Set<User> result =  new HashSet<User>();
        try {

            connection = this.setConnection();
            System.out.println("--- Database opened successfully ---");
            statement = connection.createStatement();
            System.out.println("--- Connection: " + connection.getMetaData()+ " ---");

            preparedStatement = connection.prepareStatement("SELECT * FROM user WHERE username = '"+ user +"';");
            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("--- Result set: " + resultSet.getCursorName()+ " ---");

            while (resultSet.next()){
                User temp = new User();
                temp.setId(resultSet.getInt("id"));
                temp.setUserName(resultSet.getString("username"));
                temp.setPassword(resultSet.getString("password"));
                temp.setRol(resultSet.getString("rol"));
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
}
