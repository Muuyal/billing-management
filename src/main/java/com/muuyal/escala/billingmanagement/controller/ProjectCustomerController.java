package com.muuyal.escala.billingmanagement.controller;

import com.muuyal.escala.billingmanagement.dao.impl.CustomerDaoImpl;
import com.muuyal.escala.billingmanagement.dao.interfaces.CustomerDao;
import com.muuyal.escala.billingmanagement.entities.Customer;
import com.muuyal.escala.billingmanagement.entities.CustomerDetails;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProjectCustomerController implements Initializable {

    @FXML
    private TextField customerName          =  new TextField();
    @FXML
    private TextField customerPhone         =  new TextField();
    @FXML
    private TextField customerEmail         =  new TextField();
    @FXML
    private TextField customerAddressStreet =  new TextField();
    @FXML
    private TextField customerAddressColony =  new TextField();
    @FXML
    private TextField customerAddressCity   =  new TextField();
    @FXML
    private TextField customerAddressPC     =  new TextField();
    @FXML
    private TextArea customerNotes          =  new TextArea();

    static Customer customer;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO revisar x
        customerName.setText(customer.getName());
        customerPhone.setText(customer.getPhone());
        customerEmail.setText(customer.getEmail());
        customerAddressStreet.setText(customer.getAddressStreet());
        customerAddressColony.setText(customer.getAddressColony());
        customerAddressCity.setText(customer.getAddressCity());
        customerAddressPC.setText(customer.getAddressPC());
        customerNotes.setText(customer.getNotes());

    }

    @FXML
    public void goBack(ActionEvent actionEvent) throws IOException {
        System.out.println("-- " + this.getClass().getName() + ": go to customer home --");
        Parent homePageParent = FXMLLoader.load(getClass().getResource("/views/project/projectHome.fxml"));
        Scene homePageScene = new Scene(homePageParent);
        Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        appStage.setScene(homePageScene);
        appStage.show();
    }

    @FXML
    public void goToNewProject(ActionEvent actionEvent) throws IOException{

        System.out.println("-- " + this.getClass().getName() + ": go to new project --");
        Parent homePageParent = FXMLLoader.load(getClass().getResource("/views/project/projectNew.fxml"));
        Scene homePageScene = new Scene(homePageParent);
        Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        appStage.setScene(homePageScene);
        appStage.show();

    }

    @FXML
    public void goToFindProject(ActionEvent actionEvent) throws IOException{

        System.out.println("-- " + this.getClass().getName() + ": go to find project --");
        Parent homePageParent = FXMLLoader.load(getClass().getResource("/views/project/projectFind.fxml"));
        Scene homePageScene = new Scene(homePageParent);
        Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        appStage.setTitle("Busqueda");
        appStage.setScene(homePageScene);
        appStage.show();

    }

}
