package com.muuyal.escala.billingmanagement.controller;

import com.muuyal.escala.billingmanagement.dao.impl.ContractDaoImp;
import com.muuyal.escala.billingmanagement.dao.impl.CustomerDaoImpl;
import com.muuyal.escala.billingmanagement.dao.interfaces.ContractDao;
import com.muuyal.escala.billingmanagement.dao.interfaces.CustomerDao;
import com.muuyal.escala.billingmanagement.entities.Contract;
import com.muuyal.escala.billingmanagement.entities.Customer;
import com.muuyal.escala.billingmanagement.entities.Project;
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
    private ChoiceBox<String> customerId = new ChoiceBox<>();
    @FXML
    private ChoiceBox<String> contractId =  new ChoiceBox<>();
    @FXML
    private TextField amount;
    @FXML
    private Label message;

    private LocalDate paymentDate;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initCustomerChoiceBox();
        initContractChoiceBox();
    }

    private void initCustomerChoiceBox(){


        Set<String> customerNames = new HashSet<>();
        Set<Customer> tempCustomer = customerDao.findAll();
        System.out.println("---- Getting All Projects");
        for (Customer customer : tempCustomer){
            customerNames.add(customer.getName());
        }
        System.out.println(customerNames.toString());
        customerId.setItems( FXCollections.observableArrayList( customerNames ));
    }

    private void initContractChoiceBox(){
        Set<String> contractNames = new HashSet<>();
        Set<Contract> tempContract = contractDao.findAll();
        System.out.println("---- Getting All Projects");
        for (Contract contract : tempContract){
            contractNames.add(contract.getId());
        }
        System.out.println(contractNames.toString());
        contractId.setItems( FXCollections.observableArrayList( contractNames ));
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
    public void save(ActionEvent actionEvent) {
        System.out.println("-- " + this.getClass().getName() + ": save payment --");
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
        payment.setCustomerId(Integer.valueOf(customerId.getValue()));
        payment.setContractId(Integer.valueOf(contractId.getValue()));
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
        payment.setCustomerId(Integer.valueOf(customerId.getValue()));
        payment.setContractId(Integer.valueOf(contractId.getValue()));
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
