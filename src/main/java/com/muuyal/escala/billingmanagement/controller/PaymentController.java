package com.muuyal.escala.billingmanagement.controller;

import com.muuyal.escala.billingmanagement.entities.Project;
import javafx.scene.control.*;
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

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.muuyal.escala.billingmanagement.entities.Payment;
import com.muuyal.escala.billingmanagement.dao.interfaces.PaymentDao;
import com.muuyal.escala.billingmanagement.dao.impl.PaymentDaoImp;

@Controller
public class PaymentController implements Initializable {

    private Button buttonUpdate;
    private Payment payment = new Payment();
    private PaymentDao paymentDao = new PaymentDaoImp();

    @FXML
    private TextField customerId;
    @FXML
    private TextField contractId;
    @FXML
    private TextField paymentAmount;
    @FXML
    private DatePicker paymentDate;
    @FXML
    private Label message;
    /*@FXML
    private TableView<Project> projectList = new TableView<Project>();
    @FXML
    private TableView<String> customerList = new TableView<String>();
    @FXML
    private TableView<String> customerDetails =  new TableView<String>();;*/

    @Override
    public void initialize(URL location, ResourceBundle resources) {

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

        LocalDate localPaymentDate = paymentDate.getValue();
        System.out.println("-- " + this.getClass().getName() + ": update payment of " + customerId.getText() + "--");
        payment.setCustomerId(Integer.valueOf(customerId.getText()));
        payment.setContractId(Integer.valueOf(contractId.getText()));
        payment.setPaymentAmount(Double.valueOf(paymentAmount.getText()));
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
            message.setText("Error updating " + customerId.getText() + " payment");
        }
    }

    @FXML
    public void delete(ActionEvent actionEvent) throws IOException {

        LocalDate localPaymentDate = paymentDate.getValue();
        System.out.println("-- " + this.getClass().getName() + ": delete payment of " + customerId.getText() + "--");
        payment.setCustomerId(Integer.valueOf(customerId.getText()));
        payment.setContractId(Integer.valueOf(contractId.getText()));
        payment.setPaymentAmount(Double.valueOf(paymentAmount.getText()));
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
            message.setText("Error deleting " + customerId.getText() + " payment");
        }
    }
    
}
