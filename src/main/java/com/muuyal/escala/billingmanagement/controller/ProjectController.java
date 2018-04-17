package com.muuyal.escala.billingmanagement.controller;

import com.muuyal.escala.billingmanagement.Utils.CustomerHelper;
import com.muuyal.escala.billingmanagement.dao.impl.ContractDaoImp;
import com.muuyal.escala.billingmanagement.dao.impl.CustomerDaoImpl;
import com.muuyal.escala.billingmanagement.dao.impl.ProjectDaoImp;
import com.muuyal.escala.billingmanagement.dao.interfaces.ContractDao;
import com.muuyal.escala.billingmanagement.dao.interfaces.CustomerDao;
import com.muuyal.escala.billingmanagement.dao.interfaces.ProjectDao;
import com.muuyal.escala.billingmanagement.entities.*;
//import com.sun.org.apache.xpath.internal.operations.String;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.util.Callback;
import org.springframework.stereotype.Controller;

@Controller
public class ProjectController implements Initializable {

    private Button buttonUpdate;
    private Project project = new Project();
    private ProjectDao projectDao = new ProjectDaoImp();
    TableColumn columnE = new TableColumn("Estado"); // customer status

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
    private TableView<CustomerDetails> customerList = new TableView<CustomerDetails>();


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        initProjectList();
       // customerName.setText("asdasdasd222");

        //Preparing customer details
//        TableColumn columnA = new TableColumn("Nombre"); // customer name
//        TableColumn columnB = new TableColumn("Telefono"); // customer phone
//        TableColumn columnC = new TableColumn("Correo"); // customer email
//        columnA.setCellValueFactory(
//                new PropertyValueFactory<Customer,String>("name")
//        );
//        columnB.setCellValueFactory(
//                new PropertyValueFactory<Customer,String>("phone")
//        );
//        columnC.setCellValueFactory(
//                new PropertyValueFactory<Customer,Integer>("email")
//        );
//        customerDetails.getColumns().addAll(columnA, columnB, columnC);

        // Preparing customer List
        TableColumn columnD = new TableColumn("Nombre"); // customer name
//        TableColumn columnE = new TableColumn("Estado"); // customer status
        TableColumn columnF = new TableColumn("Adeudo"); // customer debt
        columnD.setCellValueFactory( new PropertyValueFactory<CustomerDetails,String>("name") );
        columnE.setCellValueFactory( new PropertyValueFactory<CustomerDetails,String>("status") );
        columnF.setCellValueFactory( new PropertyValueFactory<CustomerDetails,Integer>("debt") );

        customerList.getColumns().addAll(columnD, columnE, columnF);

    }

    private void initProjectList(){


        projectList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 2) {
                    //Use ListView's getSelected Item
                    showCustomers();
                    //use this to do whatever you want to. Open Link etc.
                }
            }
        });


        if (!projectDao.findAll().isEmpty()){

            final ObservableList<Project> projectItems = FXCollections.observableArrayList( projectDao.findAll() );
            projectList.setEditable(false);
            customerList.setEditable(false);

            TableColumn columnA = new TableColumn("Nombre"); // project name
            TableColumn columnB = new TableColumn("Destino"); //project
            TableColumn columnC = new TableColumn("Precio"); //project
            TableColumn columnD = new TableColumn("Salida"); //project

            columnA.setCellValueFactory( new PropertyValueFactory<Project,String>("name") );
            columnB.setCellValueFactory( new PropertyValueFactory<Project,String>("description") );
            columnC.setCellValueFactory( new PropertyValueFactory<Project,String>("price") );
            columnD.setCellValueFactory( new PropertyValueFactory<Project,String>("eta") );
            projectList.getColumns().addAll(columnA, columnB, columnC, columnD);
            projectList.setItems(projectItems);
        }

    }

    @FXML
    public void showCustomers(){
        //TODO falta revisar
        System.out.println("-- " + this.getClass().getName() + ": showCustomers clicked--");
        ContractDao contractDao = new ContractDaoImp();
        CustomerDao customerDao = new CustomerDaoImpl();
        Set<Customer> customers = new HashSet<>();

        CustomerDetails customerDetails = new CustomerDetails();
        Set<CustomerDetails> customerDetailsList = new HashSet<>();
        CustomerHelper customerHelper = new CustomerHelper();

        if (projectList.getSelectionModel().getSelectedItem() != null ) {
            Project clickedProject = projectList.getSelectionModel().getSelectedItem();
            System.out.println("-- " + this.getClass().getName() + ": item selected is: " + clickedProject.getId() + "--");
            Set<Integer> customerIds = contractDao.findCustomerIdsByProyect(clickedProject.getId());
            System.out.println(" *******----- Customers: " + customerIds.toString() +" -----");

            for (Integer customerId : customerIds){
                System.out.println(" ----- Customer: " + customerId +" -----");
                customers.add(customerDao.findById(customerId));
            }

            for (Customer customer : customers){
                System.out.println(customer.getName());
                customerDetails.setName(customer.getName());
                customerDetails.setDebt(customerHelper.getCustomerDebt(customer));
                try {

                    //java.lang.String info = customerHelper.getCustomerStatus(customer);

                    columnE.setCellFactory(new Callback<TableColumn<Object, java.lang.String>, TableCell<Object, java.lang.String>>() {
                        @Override
                        public TableCell<Object, java.lang.String> call(TableColumn<Object, java.lang.String> tableColumn) {
                            return new TableCell<Object, java.lang.String>(){
                                @Override
                                protected void updateItem(java.lang.String item, boolean empty) {

                                    if( item != null  ){
                                        if( item.equals("A tiempo") ){

                                            setStyle("-fx-background-color: green;");
                                            this.setText(item);

                                        }else if( item.equals("Atrasado") ){

                                            setStyle("-fx-background-color: red;");
                                            this.setText(item);

                                        }
                                    }
                                }
                            };
                        }
                    });

                    customerDetails.setStatus(customerHelper.getCustomerStatus(customer));

                } catch (ParseException parseException){
                    System.out.println(parseException);
                }
                customerDetails.setCustomerId(customer.getId());

                customerDetailsList.add(customerDetails);

            }
        }

        final ObservableList<CustomerDetails> customerDetailsObservableList = FXCollections.observableArrayList( customerDetailsList );
        customerList.setItems(customerDetailsObservableList);
    }

    @FXML
    public void showDetails(MouseEvent actionEvent) throws IOException{

//        System.out.println("------------------ " + this.getClass().getName() + ": showDetails clicked--");
        CustomerDao customerDao = new CustomerDaoImpl();
        Customer customer = new Customer();
//
//        if (customerList.getSelectionModel().getSelectedItem() != null ) {
//            CustomerDetails clickedCustomer = customerList.getSelectionModel().getSelectedItem();
//            System.out.println("--++++++++++++++++++++++++++++ " + this.getClass().getName() + ": item selected is: " + clickedCustomer.getCustomerId() + "--");
//            customer = customerDao.findById(clickedCustomer.getCustomerId());
//        }
//        final ObservableList<Customer> customerObservableList = FXCollections.observableArrayList(
//                customer
//        );
//        customerDetails.setItems(customerObservableList);

        if (customerList.getSelectionModel().getSelectedItem() != null ) {
            CustomerDetails clickedCustomer = customerList.getSelectionModel().getSelectedItem();
            System.out.println("--++++++++++++++++++++++++++++ " + this.getClass().getName() + ": item selected is: " + clickedCustomer.getCustomerId() + "--");
            customer = customerDao.findById(clickedCustomer.getCustomerId());
        }
        ProjectCustomerController projectCustomerController = new ProjectCustomerController();
        projectCustomerController.setCustomer(customer);

        System.out.println("---------------------------------- " + this.getClass().getName() + ": go to project customer details --");
        Parent homePageParent = FXMLLoader.load(getClass().getResource("/views/project/projectCustomer.fxml"));
        Scene homePageScene = new Scene(homePageParent);
        Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        appStage.setScene(homePageScene);
//        customerName.setText("asdasdasd!!!");
        appStage.show();

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
    public void goToUpdate(ActionEvent actionEvent) throws IOException{

        System.out.println("-- " + this.getClass().getName() + ": go to update project --");
        /*Parent homePageParent = FXMLLoader.load(getClass().getResource("/views/project/projectNew.fxml"));
        Scene homePageScene = new Scene(homePageParent);
        Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        appStage.setScene(homePageScene);
        appStage.show();*/

    }

    @FXML
    public void goToDelete (ActionEvent actionEvent) throws IOException{

        if (projectList.getSelectionModel().getSelectedItem() == null ){
            Alert alertNotSelected = new Alert(Alert.AlertType.CONFIRMATION, "No hay registro seleccionado", ButtonType.CANCEL);
            alertNotSelected.showAndWait();
        }else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "¿Quieres borrar registro seleccionado?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.YES) {

                Project project = projectList.getSelectionModel().getSelectedItem();
                if (projectDao.delete(project)) {
                    projectList.getItems().remove(project);
                }
            }
        }

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
