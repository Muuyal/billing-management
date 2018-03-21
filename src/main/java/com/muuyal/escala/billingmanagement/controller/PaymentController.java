package com.muuyal.escala.billingmanagement.controller;

import com.muuyal.escala.billingmanagement.dao.impl.ContractDaoImp;
import com.muuyal.escala.billingmanagement.dao.impl.CustomerDaoImpl;
import com.muuyal.escala.billingmanagement.dao.interfaces.ContractDao;
import com.muuyal.escala.billingmanagement.dao.interfaces.CustomerDao;
import com.muuyal.escala.billingmanagement.entities.Contract;
import com.muuyal.escala.billingmanagement.entities.Customer;
import com.muuyal.escala.billingmanagement.entities.Project;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

import com.muuyal.escala.billingmanagement.entities.Payment;
import com.muuyal.escala.billingmanagement.dao.interfaces.PaymentDao;
import com.muuyal.escala.billingmanagement.dao.impl.PaymentDaoImp;

@Controller
public class PaymentController implements Initializable {

    private Button buttonUpdate;
    private Payment payment = new Payment();
    private PaymentDao paymentDao = new PaymentDaoImp();
    private Customer customer = new Customer();
    private CustomerDao customerDao = new CustomerDaoImpl();
    private Contract contract = new Contract();
    private ContractDao contractDao = new ContractDaoImp();

    @FXML
    private ChoiceBox<Customer> customerId = new ChoiceBox<>();
    @FXML
    private ChoiceBox<Contract> contractId =  new ChoiceBox<>();
    @FXML
    private TextField amount;
    @FXML
    private Label message;
    @FXML
    private TableView<Payment> paymentList = new TableView<>();

    private LocalDate paymentDate;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initCustomerChoiceBox();
        initContractChoiceBox();
        initCustomerChoiceBoxListener();
    }

    private void initCustomerChoiceBoxListener(){


        ZoneId defaultZoneId = ZoneId.systemDefault();

        ChangeListener<Customer> changeListener =  new ChangeListener<Customer>() {
            @Override
            public void changed(ObservableValue<? extends Customer> observable, Customer oldValue, Customer newValue) {
                initContractChoiceBox();
            }
        };

        customerId.getSelectionModel().selectedItemProperty().addListener(changeListener);

    }

    private void initCustomerChoiceBox(){

        Set<Customer> tempCustomer = customerDao.findAll();
        System.out.println("---- Getting All Customers");
        customerId.setItems( FXCollections.observableArrayList( tempCustomer ));
        customerId.selectionModelProperty().get().select(0);
    }

    private void initContractChoiceBox(){
        Set<Contract> tempContract = new HashSet<>();
        if (customerId.getValue().getId() >1) {
            tempContract = contractDao.findByCustomer(Integer.valueOf(customerId.getValue().getId()));
        }
        System.out.println("---- Getting All Contracts ----");
        System.out.println("-------------- Total contracts: " + tempContract.size() + "---------------");
        contractId.setItems( FXCollections.observableArrayList( tempContract ));
    }

    @FXML
    public void goToNew(ActionEvent actionEvent) throws IOException {

        System.out.println("-- " + this.getClass().getName() + ": go to new payment --");
        Parent homePageParent = FXMLLoader.load(getClass().getResource("/views/payment/paymentNew.fxml"));
        Scene homePageScene = new Scene(homePageParent);
        Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        appStage.setScene(homePageScene);
        appStage.show();

    }

    @FXML
    public void goToFind(ActionEvent actionEvent) throws IOException{

        System.out.println("-- " + this.getClass().getName() + ": go to find payment --");
        Parent homePageParent = FXMLLoader.load(getClass().getResource("/views/payment/paymentFind.fxml"));
        Scene homePageScene = new Scene(homePageParent);
        Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        appStage.setScene(homePageScene);
        appStage.show();

    }

    // Borrar
    @FXML
    public void goToDetails(ActionEvent actionEvent) throws IOException {

        System.out.println("-- " + this.getClass().getName() + ": go to payment details --");
        Parent homePageParent = FXMLLoader.load(getClass().getResource("/views/payment/paymentDetails.fxml"));
        Scene homePageScene = new Scene(homePageParent);
        Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        appStage.setScene(homePageScene);
        appStage.show();

    }

    @FXML
    public void goToDelete(ActionEvent actionEvent){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "¿Quieres borrar registro seleccionado?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            //do stuff
           Payment payment = paymentList.getSelectionModel().getSelectedItem();
           paymentDao.delete(payment);
           paymentList.getItems().remove(payment);
        }
    }

    @FXML
    public void save(ActionEvent actionEvent) throws  IOException {
        System.out.println("-- " + this.getClass().getName() + ": save payment --");
        LocalDate today = LocalDate.now();
        System.out.println("Today is: " + today);

        Payment payment = new Payment();
        payment.setPaymentDate(Date.valueOf(today));
        payment.setPaymentAmount(Double.valueOf(amount.getText()));
        payment.setContractId(contractId.getSelectionModel().getSelectedItem().getId());
        payment.setCustomerId(customerId.getSelectionModel().getSelectedItem().getId());


        Parent homePageParent = FXMLLoader.load(getClass().getResource("/views/common/success.fxml"));
        Scene homePageScene = new Scene(homePageParent);
        Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        if (paymentDao.save(payment)){
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
        System.out.println("-- " + this.getClass().getName() + ": go to payment home --");
        Parent homePageParent = FXMLLoader.load(getClass().getResource("/views/payment/paymentHome.fxml"));
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
    public void find(ActionEvent actionEvent){
        System.out.println("-- " + this.getClass().getName() + ": go to found list --");


    }

    @FXML
    public void update(ActionEvent actionEvent) throws IOException {

        LocalDate localPaymentDate = paymentDate;
        System.out.println("-- " + this.getClass().getName() + ": update payment of " + customerId.getValue() + "--");
        payment.setCustomerId(customerId.getValue().getId());
        payment.setContractId(contractId.getValue().getId());
        payment.setPaymentAmount(Double.valueOf(amount.getText()));
        payment.setPaymentDate(Date.valueOf(localPaymentDate));
        payment.setId(1);


        System.out.println("-- " + this.getClass().getName() + ": updated clicked --");
        Parent homePageParent = FXMLLoader.load(getClass().getResource("/views/common/success.fxml"));
        Scene homePageScene = new Scene(homePageParent);
        Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        if  (paymentDao.update(payment)){
            appStage.hide();
            appStage.setScene(homePageScene);
            appStage.show();
        } else {
            message.setText("Error updating " + customerId.getValue() + " payment");
        }
    }

    @FXML
    public void delete(ActionEvent actionEvent) throws IOException {

        LocalDate localPaymentDate = paymentDate;
        System.out.println("-- " + this.getClass().getName() + ": delete payment of " + customerId.getValue() + "--");
        payment.setCustomerId(customerId.getValue().getId());
        payment.setContractId(contractId.getValue().getId());
        payment.setPaymentAmount(Double.valueOf(amount.getText()));
        payment.setPaymentDate(Date.valueOf(localPaymentDate));
        payment.setId(1);


        System.out.println("-- " + this.getClass().getName() + ": deleted clicked --");
        Parent homePageParent = FXMLLoader.load(getClass().getResource("/views/common/success.fxml"));
        Scene homePageScene = new Scene(homePageParent);
        Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        if  (paymentDao.delete(payment)){
            appStage.hide();
            appStage.setScene(homePageScene);
            appStage.show();
        } else {
            message.setText("Error deleting " + customerId.getValue() + " payment");
        }
    }
    
}
