package com.muuyal.escala.billingmanagement.dao;

import java.sql.*;

public class DBConnection {

    Connection connection = null;
    Statement statement   = null;

    public Connection setConnection(){
    try {
        connection = DriverManager.getConnection("jdbc:sqlite:billing-management.db");
        connection.setAutoCommit(false);

        System.out.println("--- Database opened successfully ---");
        statement = connection.createStatement();


    } catch (Exception e){
        System.err.println("--- Error found " + e.getClass().getName() + ":" + e.getMessage());
        System.exit(0);
      }
      return connection;
    }

    public void closeConnection(){
       try {
           statement.close();
           connection.close();
           System.out.println("--- Databse closed successfully ---");

       } catch( SQLException e){
           System.err.println("--- Error found " + e.getClass().getName() + ":" + e.getMessage());
       }

    }
}
