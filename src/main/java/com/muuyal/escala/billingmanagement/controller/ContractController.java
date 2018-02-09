package com.muuyal.escala.billingmanagement.controller;


import com.muuyal.escala.billingmanagement.dao.impl.ContractDaoImp;
import com.muuyal.escala.billingmanagement.dao.impl.CustomerDaoImpl;
import com.muuyal.escala.billingmanagement.dao.impl.ProjectDaoImp;
import com.muuyal.escala.billingmanagement.dao.interfaces.ContractDao;
import com.muuyal.escala.billingmanagement.dao.interfaces.CustomerDao;
import com.muuyal.escala.billingmanagement.dao.interfaces.ProjectDao;
import com.muuyal.escala.billingmanagement.entities.Contract;
import com.muuyal.escala.billingmanagement.entities.Customer;
import com.muuyal.escala.billingmanagement.entities.Project;
import com.muuyal.escala.billingmanagement.entities.Staff;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

@Controller
public class ContractController implements Initializable {

    public Button buttonUpdate;

    @FXML
    private TextField name;
    @FXML
    private ChoiceBox<Project> projectId  = new ChoiceBox<>();
    @FXML
    private ChoiceBox<Customer> customerId = new ChoiceBox<>();
    @FXML
    private TextField discount;
    @FXML
    private DatePicker deadline;
    @FXML
    private ChoiceBox<String> paymentSchedule =  new ChoiceBox<>();
    @FXML
    private TableView<Contract> contractList = new TableView<>();
    @FXML
    private Label   message;

    private Contract contract = new Contract();
    private Customer customer = new Customer();
    private ContractDao contractDao = new ContractDaoImp();
    private CustomerDao customerDao = new CustomerDaoImpl();
    private ProjectDao projectDao   = new ProjectDaoImp();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        System.out.println("Initializing contract controller");
        initPaymentChoiceBox();
        initProjectChoiceBox();
        initCustomerChoiceBox();

    }

    private void initPaymentChoiceBox(){
        paymentSchedule.setItems(FXCollections.observableArrayList(
                "Semanal", "Quincenal", "Mensual")
        );
    }


    private void initProjectChoiceBox(){

        Set<String> projectNames = new HashSet<>();
        Set<Project> tempProject = projectDao.findAll();
        System.out.println("---- Getting All Projects");
        for (Project project : tempProject){
            projectNames.add(project.getName());
        }
        System.out.println(projectNames.toString());
        projectId.setItems( FXCollections.observableArrayList( tempProject ));
    }

    private void initCustomerChoiceBox(){

        Set<String> customerNames = new HashSet<>();
        Set<Customer> tempCustomer = customerDao.findAll();
        System.out.println("---- Getting All Customers");
        for (Customer customer :  tempCustomer) {
            System.out.println("---- Customer founds ----");
            customerNames.add(customer.getName());
            System.out.println("---- " + customer.getName());
        }
        System.out.println(customerNames.toString());
        customerId.setItems( FXCollections.observableArrayList(tempCustomer));
    }

    @FXML
    public void goToNew(ActionEvent actionEvent) throws IOException{

        System.out.println("-- " + this.getClass().getName() + ": go to new contract --");
        Parent homePageParent = FXMLLoader.load(getClass().getResource("/views/contract/contractNew.fxml"));
        Scene homePageScene = new Scene(homePageParent);
        Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        appStage.setScene(homePageScene);
        appStage.show();

    }

    @FXML
    public void goToFind(ActionEvent actionEvent) throws IOException{

        System.out.println("-- " + this.getClass().getName() + ": go to find contract --");
        Parent homePageParent = FXMLLoader.load(getClass().getResource("/views/contract/contractFind.fxml"));
        Scene homePageScene = new Scene(homePageParent);
        Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        appStage.setScene(homePageScene);
        appStage.show();

    }

    // Borrar
    @FXML
    public void goToDetails(ActionEvent actionEvent) throws IOException {

        System.out.println("-- " + this.getClass().getName() + ": go to contract details --");
        Parent homePageParent = FXMLLoader.load(getClass().getResource("/views/contract/contractDetails.fxml"));
        Scene homePageScene = new Scene(homePageParent);
        Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        appStage.setScene(homePageScene);
        appStage.show();

    }

    @FXML
    public void save(ActionEvent actionEvent) throws IOException {
        System.out.println("-- " + this.getClass().getName() + ": save contract --");

        LocalDate localDeadline = deadline.getValue();
//        customerId.getSelectionModel().getSelectedItem().
        System.out.println("------- selected item: " + paymentSchedule.getSelectionModel().getSelectedItem());
        System.out.println("------- selected item: " + customerId.getValue().getName());
        System.out.println("------- selected item: " + projectId.getValue().getName());

        contract.setPaymentSchedule(paymentSchedule.getSelectionModel().getSelectedItem());
        contract.setCustomerId(customerId.getValue().getId());
        contract.setProjectId(projectId.getValue().getId());
        contract.setDiscount(Integer.valueOf(discount.getText()));
        contract.setDeadline(Date.valueOf(localDeadline));
        contract.setCreatedOn(Date.valueOf( LocalDate.now()));


        System.out.println("-- " + this.getClass().getName() + ": saved clicked --");
        Parent homePageParent = FXMLLoader.load(getClass().getResource("/views/common/success.fxml"));
        Scene homePageScene = new Scene(homePageParent);
        Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        if  (contractDao.save(contract)){
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
        System.out.println("-- " + this.getClass().getName() + ": go to contract home --");
        Parent homePageParent = FXMLLoader.load(getClass().getResource("/views/contract/contractHome.fxml"));
        Scene homePageScene = new Scene(homePageParent);
        Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        appStage.setScene(homePageScene);
        appStage.show();
    }

    @FXML
    public void goBack(ActionEvent actionEvent) throws IOException {
        System.out.println("-- " + this.getClass().getName() + ": go to contract home --");
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

        System.out.println("-- " + this.getClass().getName() + ": update contract --");

        contract.setPaymentSchedule(paymentSchedule.getValue());


        System.out.println("-- " + this.getClass().getName() + ": updated clicked --");
        Parent homePageParent = FXMLLoader.load(getClass().getResource("/views/common/success.fxml"));
        Scene homePageScene = new Scene(homePageParent);
        Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        if  (contractDao.update(contract)){
            appStage.hide();
            appStage.setScene(homePageScene);
            appStage.show();
        } else {
            message.setText("Error updating contract");
        }
    }

    @FXML
    public void delete(ActionEvent actionEvent) throws IOException {

        System.out.println("-- " + this.getClass().getName() + ": delete contract --");

        contract.setId(paymentSchedule.getValue());


        System.out.println("-- " + this.getClass().getName() + ": deleted clicked --");
        Parent homePageParent = FXMLLoader.load(getClass().getResource("/views/common/success.fxml"));
        Scene homePageScene = new Scene(homePageParent);
        Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        if  (contractDao.delete(contract)){
            appStage.hide();
            appStage.setScene(homePageScene);
            appStage.show();
        } else {
            message.setText("Error deleting contract");
        }
    }

}
