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
public class SuccessController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }

    @FXML
    public void goToHome(ActionEvent actionEvent) throws IOException {
        System.out.println("-- " + this.getClass().getName() + ": go to project home --");
        Parent homePageParent = FXMLLoader.load(getClass().getResource("/views/home.fxml"));
        Scene homePageScene = new Scene(homePageParent);
        Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        appStage.setTitle("Proyectos");
        appStage.setScene(homePageScene);
        appStage.show();
    }

}
