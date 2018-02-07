package com.muuyal.escala.billingmanagement.controller;

import com.muuyal.escala.billingmanagement.dao.impl.ContractDaoImp;
import com.muuyal.escala.billingmanagement.dao.impl.CustomerDaoImpl;
import com.muuyal.escala.billingmanagement.dao.impl.ProjectDaoImp;
import com.muuyal.escala.billingmanagement.dao.interfaces.ContractDao;
import com.muuyal.escala.billingmanagement.dao.interfaces.CustomerDao;
import com.muuyal.escala.billingmanagement.dao.interfaces.ProjectDao;
import com.muuyal.escala.billingmanagement.entities.Customer;
import com.muuyal.escala.billingmanagement.entities.Project;
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
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ProjectController implements Initializable {

    private Button buttonUpdate;
    private Project project = new Project();
    private ProjectDao projectDao = new ProjectDaoImp();

    @FXML
    private TextField name;
    @FXML
    private TextField description;
    @FXML
    private TextField price;
    @FXML
    private DatePicker eta;
    @FXML
    private DatePicker deadline;
    @FXML
    private Label message;
    @FXML
    private  TableView<Project> projectList = new TableView<Project>();
    @FXML
    private TableView<String> customerList = new TableView<String>();
    @FXML
    private TableView<String> customerDetails =  new TableView<String>();;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        initProjectList();

    }

    private void initProjectList(){

        if (!projectDao.findAll().isEmpty()){

            final ObservableList<Project> projectItems = FXCollections.observableArrayList(
                    projectDao.findAll()
            );
//            System.out.println("------- " + projectItems.get + " ----------");

            projectList.setEditable(false);
            customerList.setEditable(false);
            customerDetails.setEditable(false);

            TableColumn columnA = new TableColumn("Nombre"); // project name
            TableColumn columnB = new TableColumn("Destino"); //project
            TableColumn columnC = new TableColumn("Precio"); //project
            TableColumn columnD = new TableColumn("Salida"); //project
            TableColumn columnE = new TableColumn("Estado"); //customer
            TableColumn columnF = new TableColumn("Adeudo"); //customer
            TableColumn columnG = new TableColumn("Pagado"); //customer
            TableColumn columnH = new TableColumn("Habitación"); //customer
            TableColumn columnI = new TableColumn("Nombre"); //customer

            columnA.setCellValueFactory(
                    new PropertyValueFactory<Project,String>("name")
            );
            columnB.setCellValueFactory(
                    new PropertyValueFactory<Project,String>("description")
            );
            columnC.setCellValueFactory(
                    new PropertyValueFactory<Project,String>("price")
            );
            columnD.setCellValueFactory(
                    new PropertyValueFactory<Project,String>("eta")
            );

            projectList.getColumns().addAll(columnA, columnB, columnC, columnD);
            customerList.getColumns().addAll(columnI, columnE, columnF);
            customerDetails.getColumns().addAll(columnI,columnE, columnF, columnG, columnH );

            projectList.setItems(projectItems);
        }

    }

    @FXML
    public void showCustomers(){
        System.out.println("-- " + this.getClass().getName() + ": showCustomers clicked--");
        ContractDao contractDao = new ContractDaoImp();
        CustomerDao customerDao = new CustomerDaoImpl();
        Set<Customer> customers = new HashSet<>();

        if (projectList.getSelectionModel().getSelectedItem() != null ) {
            Project clickedProject = projectList.getSelectionModel().getSelectedItem();
            System.out.println("-- " + this.getClass().getName() + ": item selected is: " + clickedProject.getId() + "--");
            Set<Integer> customerIds = contractDao.findCustomerIdsByProyect(clickedProject.getId());
            for (Integer customerId : customerIds){
             customers = customerDao.findById(customerId);
            }

            for (Customer customer : customers){
                System.out.println(customer.getName());
            }

        }

    }

    @FXML
    public void showDetails(){
        System.out.println("-- " + this.getClass().getName() + ": showDetails clicked--");
    }

    @FXML
    public void goToNewTravel(ActionEvent actionEvent) throws IOException{

        System.out.println("-- " + this.getClass().getName() + ": go to new project --");
        Parent homePageParent = FXMLLoader.load(getClass().getResource("/views/project/projectNew.fxml"));
        Scene homePageScene = new Scene(homePageParent);
        Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        appStage.setScene(homePageScene);
        appStage.show();

    }

    @FXML
    public void goToFindTravel(ActionEvent actionEvent) throws IOException{

        System.out.println("-- " + this.getClass().getName() + ": go to find project --");
        Parent homePageParent = FXMLLoader.load(getClass().getResource("/views/project/projectFind.fxml"));
        Scene homePageScene = new Scene(homePageParent);
        Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        appStage.setTitle("Busqueda");
        appStage.setScene(homePageScene);
        appStage.show();

    }

    @FXML
    public void goToDetails(ActionEvent actionEvent) throws IOException {

        System.out.println("-- " + this.getClass().getName() + ": go to project details --");
        Parent homePageParent = FXMLLoader.load(getClass().getResource("/views/project/projectDetails.fxml"));
        Scene homePageScene = new Scene(homePageParent);
        Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        appStage.setScene(homePageScene);
        appStage.show();

    }

    @FXML
    public void save(ActionEvent actionEvent) throws IOException {

        LocalDate localEta = eta.getValue();
        LocalDate localDeadline = deadline.getValue();
        System.out.println("-- " + this.getClass().getName() + ": save project with name: " + name.getText() + "price: "+ price.getText() +"--");
        project.setName(name.getText());
        project.setDescription(description.getText());
        project.setPrice(Integer.valueOf(price.getText()));
        project.setEta(Date.valueOf(localEta));
        project.setDeadline(Date.valueOf(localDeadline));


        System.out.println("-- " + this.getClass().getName() + ": saved clicked --");
        Parent homePageParent = FXMLLoader.load(getClass().getResource("/views/common/success.fxml"));
        Scene homePageScene = new Scene(homePageParent);
        Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        if  (projectDao.save(project)){
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
        System.out.println("-- " + this.getClass().getName() + ": go to project home --");
        Parent homePageParent = FXMLLoader.load(getClass().getResource("/views/project/projectHome.fxml"));
        Scene homePageScene = new Scene(homePageParent);
        Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        appStage.setTitle("Proyectos");
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
    public void update(ActionEvent actionEvent) throws IOException{

        LocalDate localEta = eta.getValue();
        LocalDate localDeadline = deadline.getValue();
        System.out.println("-- " + this.getClass().getName() + ": update project with name: " + name.getText() + "price: "+ price.getText() +"--");
        project.setName(name.getText());
        project.setDescription(description.getText());
        project.setPrice(Integer.valueOf(price.getText()));
        project.setEta(Date.valueOf(localEta));
        project.setDeadline(Date.valueOf(localDeadline));
        project.setId(1);


        System.out.println("-- " + this.getClass().getName() + ": updated clicked --");
        Parent homePageParent = FXMLLoader.load(getClass().getResource("/views/common/success.fxml"));
        Scene homePageScene = new Scene(homePageParent);
        Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        if  (projectDao.update(project)){
            appStage.hide();
            appStage.setScene(homePageScene);
            appStage.show();
        } else {
            message.setText("Error updating" + name.getText() + "project");
        }
    }

    @FXML
    public void delete(ActionEvent actionEvent) throws IOException{

        LocalDate localEta = eta.getValue();
        LocalDate localDeadline = deadline.getValue();
        System.out.println("-- " + this.getClass().getName() + ": delete project with name: " + name.getText() + "price: "+ price.getText() +"--");
        project.setName(name.getText());
        project.setDescription(description.getText());
        project.setPrice(Integer.valueOf(price.getText()));
        project.setEta(Date.valueOf(localEta));
        project.setDeadline(Date.valueOf(localDeadline));
        project.setId(1);


        System.out.println("-- " + this.getClass().getName() + ": deleted clicked --");
        Parent homePageParent = FXMLLoader.load(getClass().getResource("/views/common/success.fxml"));
        Scene homePageScene = new Scene(homePageParent);
        Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        if  (projectDao.delete(project)){
            appStage.hide();
            appStage.setScene(homePageScene);
            appStage.show();
        } else {
            message.setText("Error deleting" + name.getText() + "project");
        }
    }
}
