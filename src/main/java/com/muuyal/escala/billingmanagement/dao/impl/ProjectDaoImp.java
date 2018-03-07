package com.muuyal.escala.billingmanagement.dao.impl;

import com.muuyal.escala.billingmanagement.dao.DBConnection;
import com.muuyal.escala.billingmanagement.dao.interfaces.ProjectDao;
import com.muuyal.escala.billingmanagement.entities.Project;
import org.springframework.stereotype.Repository;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;

@Repository
public class ProjectDaoImp extends DBConnection implements ProjectDao {

    public ProjectDaoImp(){
    }

    @Override
    public boolean save(Project project) {

        boolean saved = false;

        System.out.println("--- INSERT INTO project(name, description, price, eta, deadline) VALUES" +
                " ('" + project.getName() + "','" + project.getDescription() + "','" + project.getPrice() +
                 "','" + project.getEta() + project.getDeadline() +  "')" + " ---");
        Connection connection = null;
        Statement statement   = null;
        PreparedStatement preparedStatement;

        try {

            connection = this.setConnection();
            System.out.println("--- Database opened successfully ---");
            statement = connection.createStatement();
            System.out.println("--- Connection: " + connection.getMetaData()+ " ---");

            preparedStatement = connection.prepareStatement("INSERT INTO project" +
                    "(name, description, price, eta, deadline) " +
                    "VALUES(?,?,?,?,?)");
            preparedStatement.setString(1, project.getName());
            preparedStatement.setString(2, project.getDescription());
            preparedStatement.setInt(3, project.getPrice());
            preparedStatement.setString(4, project.getEta().toString());
            preparedStatement.setString(5, project.getDeadline().toString());
            preparedStatement.executeUpdate();

            connection.commit();
            saved = true;
            statement.close();

        } catch (SQLException e){
            System.err.println(e.getMessage());
        } finally {
            this.closeConnection();
        }
        return saved;
    }

    @Override
    public Set<Project> findAll() {

        System.out.println("---" + this.getClass().getName() +  " findAll clicked. ---");
        System.out.println("--- SELECT * FROM project; ---");
        Connection connection = null;
        Statement statement   = null;
        PreparedStatement preparedStatement;

        Set<Project> result =  new HashSet<Project>();
        try {

            connection = this.setConnection();
            System.out.println("--- Database opened successfully ---");
            statement = connection.createStatement();
            System.out.println("--- Connection: " + connection.getMetaData()+ " ---");

            preparedStatement = connection.prepareStatement("SELECT * FROM project");
            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("--- Result set: " + resultSet.getCursorName()+ " ---");

            while (resultSet.next()){
                Project temp = new Project();
                temp.setId(resultSet.getInt("id"));
                temp.setName(resultSet.getString("name"));
                temp.setDescription(resultSet.getString("description"));
                temp.setPrice(resultSet.getInt("price"));
                temp.setEta(new SimpleDateFormat("yyyy-mm-dd").parse(resultSet.getString("eta")));
                temp.setDeadline(new SimpleDateFormat("yyyy-mm-dd").parse(resultSet.getString("deadline")));
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
    public Project findById(Integer id) {
        return null;
    }

    @Override
    public boolean update(Project project) {

        boolean updated = false;

        System.out.println("--- UPDATE project " +
                "SET id="+project.getId()+", name="+project.getName()+", description="+project.getDescription()+", " +
                "eta="+project.getEta()+", price="+project.getPrice()+", deadline=" + project.getDeadline() +
                "WHERE id="+project.getId()+" ---");
        Connection connection = null;
        Statement statement   = null;
        PreparedStatement preparedStatement;

        try {

            connection = this.setConnection();
            System.out.println("--- Database opened successfully ---");
            statement = connection.createStatement();
            System.out.println("--- Connection: " + connection.getMetaData()+ " ---");

            preparedStatement = connection.prepareStatement("UPDATE project " +
                    "SET id=?, name=?, description=?, price=?, eta=?, deadline=? " +
                    "WHERE id=?");
            preparedStatement.setInt(1, project.getId());
            preparedStatement.setString(2, project.getName());
            preparedStatement.setString(3, project.getDescription());
            preparedStatement.setInt(4, project.getPrice());
            preparedStatement.setString(5, project.getEta().toString());
            preparedStatement.setString(6, project.getDeadline().toString());
            preparedStatement.setInt(8, project.getId());
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
    public boolean delete(Project project) {

        boolean deleted = false;

        System.out.println("--- DELETE FROM project WHERE id="+project.getId()+" ---");
        Connection connection = null;
        Statement statement   = null;
        PreparedStatement preparedStatement;

        try {

            connection = this.setConnection();
            System.out.println("--- Database opened successfully ---");
            statement = connection.createStatement();
            System.out.println("--- Connection: " + connection.getMetaData()+ " ---");

            preparedStatement = connection.prepareStatement("DELETE FROM project WHERE id=?");
            preparedStatement.setInt(1, project.getId());
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
    public Set<Project> findAll(String search) {
        return null;
    }
}
