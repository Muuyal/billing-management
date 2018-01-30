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

        System.out.println("--- insert into project(name, destination, price, payments, departure, deadline) values" +
                " ('" + project.getName() + "','" + project.getDestination() + "','" + project.getPrice() +
                "','" + project.getPaymentSchedule() + "','" + project.getEta() + "','" + project.getDeadline() + "')" + " ---");
        Connection connection = null;
        Statement statement   = null;
        PreparedStatement preparedStatement;

        try {

            connection = this.setConnection();
            System.out.println("--- Database opened successfully ---");
            statement = connection.createStatement();
            System.out.println("--- Connection: " + connection.getMetaData()+ " ---");

            preparedStatement = connection.prepareStatement("insert into travel(name, destination, price, payments, departure, deadline) values(?,?,?,?,?,?)");
            preparedStatement.setString(1, project.getName());
            preparedStatement.setString(2, project.getDestination());
            preparedStatement.setInt(3, project.getPrice());
            preparedStatement.setString(4, project.getPaymentSchedule());
            preparedStatement.setString(5, project.getEta().toString());
            preparedStatement.setString(6, project.getDeadline().toString());
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
        System.out.println("--- select * from travel; ---");
        Connection connection = null;
        Statement statement   = null;
        PreparedStatement preparedStatement;

        Set<Project> result =  new HashSet<Project>();
        try {

            connection = this.setConnection();
            System.out.println("--- Database opened successfully ---");
            statement = connection.createStatement();
            System.out.println("--- Connection: " + connection.getMetaData()+ " ---");

            preparedStatement = connection.prepareStatement("select * from travel");
            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("--- Result set: " + resultSet.getCursorName()+ " ---");

            while (resultSet.next()){
                Project temp = new Project();
                temp.setId(resultSet.getInt("id"));
                temp.setName(resultSet.getString("name"));
                temp.setDestination(resultSet.getString("destination"));
                temp.setPrice(resultSet.getInt("price"));
                temp.setPaymentSchedule(resultSet.getString("payments"));
                temp.setEta(new SimpleDateFormat("yyyy-mm-dd").parse(resultSet.getString("departure")));
                temp.setDeadline(new SimpleDateFormat("yyyy-mm-dd").parse(resultSet.getString("deadline")));
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
    public Set<Project> findAll(Project project) {

        Set<Project> projectSet = new HashSet<>();
        return projectSet;
    }

    @Override
    public Project findById(Integer id) {
        return null;
    }

    @Override
    public boolean update(Project project) {
        boolean updated = false;
        System.out.println("--- UPDATE project " +
                "SET id="+project.getId()+", name="+project.getName()+", destination="+project.getDestination()+", " +
                "departure="+project.getEta()+", deadline="+project.getDeadline()+", price="+project.getPrice()+", " +
                "payments="+project.getPaymentSchedule()+" " +
                "WHERE id=? ---");
        Connection connection = null;
        Statement statement   = null;
        PreparedStatement preparedStatement;

        try {

            connection = this.setConnection();
            System.out.println("--- Database opened successfully ---");
            statement = connection.createStatement();
            System.out.println("--- Connection: " + connection.getMetaData()+ " ---");

            preparedStatement = connection.prepareStatement("UPDATE travel " +
                    "SET id=?, name=?, destination=?, departure=?, deadline=?, price=?, payments=? " +
                    "WHERE id=?");
            preparedStatement.setInt(1, project.getId());
            preparedStatement.setString(2, project.getName());
            preparedStatement.setString(3, project.getDestination());
            preparedStatement.setString(4, project.getEta().toString());
            preparedStatement.setString(5, project.getDeadline().toString());
            preparedStatement.setInt(6, project.getPrice());
            preparedStatement.setString(7, project.getPaymentSchedule());
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

            preparedStatement = connection.prepareStatement("DELETE FROM travel WHERE id=?");
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
}
