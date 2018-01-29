package com.muuyal.escala.billingmanagement.controller;


import com.muuyal.escala.billingmanagement.dao.impl.CustomerDaoImpl;
import com.muuyal.escala.billingmanagement.dao.interfaces.CustomerDao;
import com.muuyal.escala.billingmanagement.entities.Customer;
import com.muuyal.escala.billingmanagement.entities.Project;
import com.muuyal.escala.billingmanagement.entities.Staff;
import com.muuyal.escala.billingmanagement.services.PassengerService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class ContractController implements Initializable {

    public Button buttonUpdate;

    @FXML
    private TextField name;
    @FXML
    private ChoiceBox<Project> project;
    @FXML
    private TextField phone;
    @FXML
    private TextField email;
    @FXML
    private TextField addressStreet;
    @FXML
    private TextField addressColony;
    @FXML
    private TextField addressCity;
    @FXML
    private TextField addressPC;
    @FXML
    private ChoiceBox<Staff> staff;
    @FXML
    private TextArea notes;
    @FXML
    private Label   message;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    PassengerService passengerService;

    private Customer customer = new Customer();

    CustomerDao customerDao = new CustomerDaoImpl();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void goToNewPassenger(ActionEvent actionEvent) throws IOException{

        System.out.println("-- " + this.getClass().getName() + ": go to new customer --");
        Parent homePageParent = FXMLLoader.load(getClass().getResource("/views/customer/customerNew.fxml"));
        Scene homePageScene = new Scene(homePageParent);
        Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        appStage.setScene(homePageScene);
        appStage.show();

    }

    @FXML
    public void goToFindPassenger(ActionEvent actionEvent) throws IOException{

        System.out.println("-- " + this.getClass().getName() + ": go to find customer --");
        Parent homePageParent = FXMLLoader.load(getClass().getResource("/views/customer/passengerFind.fxml"));
        Scene homePageScene = new Scene(homePageParent);
        Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        appStage.setScene(homePageScene);
        appStage.show();

    }

    // Borrar
    @FXML
    public void goToDetails(ActionEvent actionEvent) throws IOException {

        System.out.println("-- " + this.getClass().getName() + ": go to customer details --");
        Parent homePageParent = FXMLLoader.load(getClass().getResource("/views/customer/passengerDetails.fxml"));
        Scene homePageScene = new Scene(homePageParent);
        Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        appStage.setScene(homePageScene);
        appStage.show();

    }

    @FXML
    public void save(ActionEvent actionEvent) throws IOException {
        System.out.println("-- " + this.getClass().getName() + ": save customer --");

        customer.setName(name.getText());


        System.out.println("-- " + this.getClass().getName() + ": saved clicked --");
        Parent homePageParent = FXMLLoader.load(getClass().getResource("/views/common/success.fxml"));
        Scene homePageScene = new Scene(homePageParent);
        Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        if  (customerDao.save(customer)){
            appStage.hide();
            appStage.setScene(homePageScene);
            appStage.show();
        } else {
            message.setText("Error saving new project");
        }

    }

    @FXML
    public void clearScreen(ActionEvent actionEvent) {
        System.out.println("-- " + this.getClass().getName() + ": clear screen --");
//        int[] arr = {1,2,3};
//        for (int x = 0; x == arr.length; x++){
//            x;
//        }


    }

    @FXML
    public void goToHome(ActionEvent actionEvent) throws IOException {
        System.out.println("-- " + this.getClass().getName() + ": go to customer home --");
        Parent homePageParent = FXMLLoader.load(getClass().getResource("/views/customer/customerHome.fxml"));
        Scene homePageScene = new Scene(homePageParent);
        Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        appStage.setScene(homePageScene);
        appStage.show();
    }

    @FXML
    public void goBack(ActionEvent actionEvent) throws IOException {
        System.out.println("-- " + this.getClass().getName() + ": go to customer home --");
        Parent homePageParent = FXMLLoader.load(getClass().getResource("/views/home.fxml"));
        Scene homePageScene = new Scene(homePageParent);
        Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        appStage.setScene(homePageScene);
        appStage.show();
    }

    @FXML
    public void goToFound(ActionEvent actionEvent){
        System.out.println("-- " + this.getClass().getName() + ": go to found list --");


    }

    @FXML
    public void update(ActionEvent actionEvent){
        System.out.println("-- " + this.getClass().getName() + ": go to found list --");

        buttonUpdate.setDisable(false);


    }
}
