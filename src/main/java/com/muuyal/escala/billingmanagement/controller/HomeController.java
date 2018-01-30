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
public class HomeController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void goToTravel(ActionEvent actionEvent) throws IOException{
        System.out.println("-- " + this.getClass().getName() + ": go to travels home --");
        Parent homePageParent = FXMLLoader.load(getClass().getResource("/views/travel/projectHome.fxml"));
        Scene homePageScene = new Scene(homePageParent);
        Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        appStage.setScene(homePageScene);
        appStage.show();
    }

    @FXML
    public void goToPassenger(ActionEvent actionEvent) throws IOException{
        System.out.println("-- " + this.getClass().getName() + ": go to customer home --");
        Parent homePageParent = FXMLLoader.load(getClass().getResource("/views/customer/customerHome.fxml"));
        Scene homePageScene = new Scene(homePageParent);
        Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        appStage.setScene(homePageScene);
        appStage.show();
    }

    @FXML
    public void goToPayment(ActionEvent actionEvent) throws IOException{
        System.out.println("-- " + this.getClass().getName() + ": go to payment home --");
        Parent homePageParent = FXMLLoader.load(getClass().getResource("/views/payment/paymentHome.fxml"));
        Scene homePageScene = new Scene(homePageParent);
        Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        appStage.setScene(homePageScene);
        appStage.show();
    }

    @FXML
    public void goToStaff(ActionEvent actionEvent) throws IOException{
        System.out.println("-- " + this.getClass().getName() + ": go to staff home --");
        Parent homePageParent = FXMLLoader.load(getClass().getResource("/views/staff/staffHome.fxml"));
        Scene homePageScene = new Scene(homePageParent);
        Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        appStage.setScene(homePageScene);
        appStage.show();
    }

    @FXML
    public void goToContract(ActionEvent actionEvent) throws IOException{
        System.out.println("-- " + this.getClass().getName() + ": go to contract home --");
        Parent homePageParent = FXMLLoader.load(getClass().getResource("/views/contract/contractHome.fxml"));
        Scene homePageScene = new Scene(homePageParent);
        Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        appStage.setScene(homePageScene);
        appStage.show();
    }

}
