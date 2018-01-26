package com.muuyal.escala.billingmanagement.dao.impl;

import com.muuyal.escala.billingmanagement.dao.DBConnection;
import com.muuyal.escala.billingmanagement.dao.interfaces.LoginDao;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

@Repository
public class LoginDaoImpl extends DBConnection implements LoginDao {


    @Override
    public boolean isValidCredentals(TextField username, TextField password){

        boolean let_in = false;
        System.out.println("--- select * from users where username = " + "'"+ username.getText() + "'" + " and password = "
                + "'" + password.getText() + "' ---");
        Connection connection = null;
        Statement statement   = null;
        try {

            connection = this.setConnection();
            System.out.println("--- Database opened successfully ---");
            statement = connection.createStatement();
            System.out.println("--- Connection: " + connection.getMetaData()+ " ---");

            ResultSet resultSet = statement.executeQuery("select * from users where username = " + "'"+ username.getText() + "'" + " and password = " + "'" + password.getText() + "'");
            System.out.println("--- Result set: " + resultSet.getCursorName()+ " ---");

            while (resultSet.next()){
                if (resultSet.getString("USERNAME") != null && resultSet.getString("PASSWORD") != null){
                    String user = resultSet.getString("USERNAME");
                    String pass= resultSet.getString("PASSWORD");
                    System.out.println("--- Username: " + user + " Password: " + pass + "---");
                    let_in = true;
                }
            }

            resultSet.close();
//          connection.close();
            this.closeConnection();


        } catch (Exception e){
            System.err.println("--- Error found " + e.getClass().getName() + ":" + e.getMessage());
            System.exit(0);
        }
        return let_in;
    }

}

