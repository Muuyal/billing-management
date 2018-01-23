package com.muuyal.escala.billingmanagement.controller;

import com.muuyal.escala.billingmanagement.dao.impl.LoginDaoImpl;
import com.muuyal.escala.billingmanagement.dao.interfaces.LoginDao;
import com.muuyal.escala.billingmanagement.entities.Travel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

@Controller
public class LoginController implements Initializable {

    @Autowired
    private UserDetailsService userDetailsService;


    @Autowired
    LoginDao loginDao;

    @FXML
    private TextField username;

    @FXML
    private TextField password;

    @FXML
     private Label message;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void login(ActionEvent actionEvent) throws IOException{


        System.out.println("-- " + this.getClass().getName() + ": login clicked --");
        Parent homePageParent = FXMLLoader.load(getClass().getResource("/views/home.fxml"));
        Scene homePageScene = new Scene(homePageParent);
        Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        if  ( loginDao.isValidCredentals(username, password)){
            appStage.hide();
            appStage.setScene(homePageScene);
            appStage.show();
        } else {
            username.clear();
            password.clear();
            message.setText("Invalid credentials");
        }


    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    private boolean isValidCredentals(){
//
//        boolean let_in = false;
//        System.out.println("--- select * from users where username = " + "'"+ username.getText() + "'" + " and password = "
//                + "'" + password.getText() + "' ---");
//        Connection connection = null;
//        Statement statement   = null;
//        try {
//            connection = DriverManager.getConnection("jdbc:sqlite:billing-management.db");
//            connection.setAutoCommit(false);
//
//            System.out.println("--- Database opened successfully ---");
//            statement = connection.createStatement();
//
//            ResultSet resultSet = statement.executeQuery("select * from users where username = " + "'"+ username.getText() + "'" + " and password = " + "'" + password.getText() + "'");
//
//            while (resultSet.next()){
//                if (resultSet.getString("USERNAME") != null && resultSet.getString("PASSWORD") != null){
//                    String username = resultSet.getString("USERNAME");
//                    String password = resultSet.getString("PASSWORD");
//                    System.out.println("--- Username: " + username + " Password: " + password + "---");
//                    let_in = true;
//                }
//            }
//
//            resultSet.close();
//            statement.close();
//            connection.close();
//
//
//        } catch (Exception e){
//            System.err.println("--- Error found " + e.getClass().getName() + ":" + e.getMessage());
//            System.exit(0);
//        }
//        return let_in;
//    }

//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
//    }

}
