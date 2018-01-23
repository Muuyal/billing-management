package com.muuyal.escala.billingmanagement.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class SaveController implements Initializable {


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void save(ActionEvent actionEvent) {
        System.out.println("-- " + this.getClass().getName() + ": save clicked --");

    }

    @FXML
    public void clearScreen(ActionEvent actionEvent) {
        System.out.println("-- " + this.getClass().getName() + ": clear screen clicked--");

    }

    @FXML
    public void goToHome(ActionEvent actionEvent){
        System.out.println("-- " + this.getClass().getName() + ": go to home clicked --");

    }

}
