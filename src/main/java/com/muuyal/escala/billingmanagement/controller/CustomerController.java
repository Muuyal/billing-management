package com.muuyal.escala.billingmanagement.controller;


import com.muuyal.escala.billingmanagement.dao.impl.CustomerDaoImpl;
import com.muuyal.escala.billingmanagement.dao.interfaces.CustomerDao;
import com.muuyal.escala.billingmanagement.entities.Customer;
import com.muuyal.escala.billingmanagement.entities.Project;
import com.muuyal.escala.billingmanagement.entities.Staff;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import org.springframework.stereotype.Controller;

@Controller
public class CustomerController implements Initializable {

    public Button buttonUpdate;

    @FXML
    private TextField name;
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
    private TableView<Customer> customerList = new TableView<>();
    @FXML
    private TextArea notes;
    @FXML
    private Label   message;

    private Customer customer = new Customer();

    CustomerDao customerDao = new CustomerDaoImpl();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        initCustomerList();

    }

    private void initCustomerList(){


        if (!customerDao.findAll().isEmpty()){

            final ObservableList<Customer> customerItems = FXCollections.observableArrayList(
                    customerDao.findAll()
            );
            System.out.println("------- " + customerItems.toString() + " ----------");

            customerList.setEditable(false);

            TableColumn columnA = new TableColumn("Nombre"); // customer name
            TableColumn columnB = new TableColumn("Telefono"); //custome phone
            TableColumn columnC = new TableColumn("eMail"); //customer email

            columnA.setCellValueFactory(
                    new PropertyValueFactory<Customer,String>("name")
            );
            columnB.setCellValueFactory(
                    new PropertyValueFactory<Customer,String>("phone")
            );
            columnC.setCellValueFactory(
                    new PropertyValueFactory<Customer,String>("email")
            );


            customerList.getColumns().addAll(columnA, columnB, columnC);
            customerList.setItems(customerItems);
        }

    }

    @FXML
    public void goToNew(ActionEvent actionEvent) throws IOException{

        System.out.println("-- " + this.getClass().getName() + ": go to new customer --");
        Parent homePageParent = FXMLLoader.load(getClass().getResource("/views/customer/customerNew.fxml"));
        Scene homePageScene = new Scene(homePageParent);
        Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        appStage.setScene(homePageScene);
        appStage.show();

    }

    @FXML
    public void goToDelete(ActionEvent actionEvent) throws  IOException{

        if (customerList.getSelectionModel().getSelectedItem() == null ){
            Alert alertNotSelected = new Alert(Alert.AlertType.CONFIRMATION, "No hay registro seleccionado?", ButtonType.CANCEL);
            alertNotSelected.showAndWait();
        }else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Quieres borrar registro seleccionado?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.YES) {

                Customer customer = customerList.getSelectionModel().getSelectedItem();
                if (customerDao.delete(customer)) {
                    customerList.getItems().remove(customer);
                }
            }
        }

    }

    @FXML
    public void goToFindPassenger(ActionEvent actionEvent) throws IOException{

        System.out.println("-- " + this.getClass().getName() + ": go to find customer --");
        Parent homePageParent = FXMLLoader.load(getClass().getResource("/views/customer/customerFind.fxml"));
        Scene homePageScene = new Scene(homePageParent);
        Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        appStage.setScene(homePageScene);
        appStage.show();

    }

    // Borrar
    @FXML
    public void goToDetails(ActionEvent actionEvent) throws IOException {

        System.out.println("-- " + this.getClass().getName() + ": go to customer details --");
        Parent homePageParent = FXMLLoader.load(getClass().getResource("/views/customer/customerDetails.fxml"));
        Scene homePageScene = new Scene(homePageParent);
        Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        appStage.setScene(homePageScene);
        appStage.show();

    }

    @FXML
    public void save(ActionEvent actionEvent) throws IOException {
        System.out.println("-- " + this.getClass().getName() + ": save customer --");

        customer.setName(name.getText());
        customer.setPhone(phone.getText());
        customer.setEmail(email.getText());
        customer.setAddressStreet(addressStreet.getText());
        customer.setAddressColony(addressColony.getText());
        customer.setAddressCity(addressCity.getText());
        customer.setAddressPC(addressPC.getText());
        customer.setNotes(notes.getText());

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
    public void update(ActionEvent actionEvent) throws IOException {
        System.out.println("-- " + this.getClass().getName() + ": update customer --");

        customer.setName(name.getText());


        System.out.println("-- " + this.getClass().getName() + ": updated clicked --");
        Parent homePageParent = FXMLLoader.load(getClass().getResource("/views/common/success.fxml"));
        Scene homePageScene = new Scene(homePageParent);
        Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        if  (customerDao.update(customer)){
            appStage.hide();
            appStage.setScene(homePageScene);
            appStage.show();
        } else {
            message.setText("Error updateing project");
        }
    }

    @FXML
    public void delete(ActionEvent actionEvent) throws IOException {
        System.out.println("-- " + this.getClass().getName() + ": delete customer --");

        customer.setName(name.getText());


        System.out.println("-- " + this.getClass().getName() + ": deleted clicked --");
        Parent homePageParent = FXMLLoader.load(getClass().getResource("/views/common/success.fxml"));
        Scene homePageScene = new Scene(homePageParent);
        Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        if  (customerDao.delete(customer)){
            appStage.hide();
            appStage.setScene(homePageScene);
            appStage.show();
        } else {
            message.setText("Error deleting project");
        }

    }
}
