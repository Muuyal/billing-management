package com.muuyal.escala.billingmanagement.controller;

import com.muuyal.escala.billingmanagement.dao.impl.TravelDaoImp;
import com.muuyal.escala.billingmanagement.dao.interfaces.TravelDao;
import com.muuyal.escala.billingmanagement.entities.Travel;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
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
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;


@Controller
public class TravelController implements Initializable {

    @Autowired
    private UserDetailsService userDetailsService;

    private Button buttonUpdate;

    private Travel travel =  new Travel();


    private TravelDao travelDao = new TravelDaoImp();

    @FXML
    private TextField name;
    @FXML
    private TextField destination;
    @FXML
    private TextField price;
    @FXML
    private TextField paymentSchedule;
    @FXML
    private DatePicker departure;
    @FXML
    private DatePicker deadline;
    @FXML
    private Label message;
    @FXML
    private Label label;
    @FXML
    private  TableView<Travel> projectList = new TableView<Travel>();
    @FXML
    private TableView<String> customerList = new TableView<String>();
    @FXML
    private TableView<String> customerDetails =  new TableView<String>();;



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        final ObservableList<Travel> projectItems = FXCollections.observableArrayList(
                travelDao.findAll()
        );
        System.out.println("------- " + projectItems.get(1) + " ----------");

        projectList.setEditable(false);
        customerList.setEditable(false);
        customerDetails.setEditable(false);

        TableColumn columnA = new TableColumn("Nombre"); // project name
        TableColumn columnB = new TableColumn("Destino"); //project
        TableColumn columnC = new TableColumn("Salida"); //project
        TableColumn columnD = new TableColumn("Precio"); //project
        TableColumn columnE = new TableColumn("Estado"); //customer
        TableColumn columnF = new TableColumn("Adeudo"); //customer
        TableColumn columnG = new TableColumn("Pagado"); //customer
        TableColumn columnH = new TableColumn("Habitación"); //customer
        TableColumn columnI = new TableColumn("Nombre"); //customer

        columnA.setCellValueFactory(
                new PropertyValueFactory<Travel,String>("name")
        );
        columnB.setCellValueFactory(
                new PropertyValueFactory<Travel,String>("destination")
        );
        columnC.setCellValueFactory(
                new PropertyValueFactory<Travel,String>("price")
        );
        columnD.setCellValueFactory(
                new PropertyValueFactory<Travel,String>("departure")
        );

        projectList.getColumns().addAll(columnA, columnB, columnC, columnD);
        customerList.getColumns().addAll(columnI, columnE, columnF);
        customerDetails.getColumns().addAll(columnI,columnE, columnF, columnG, columnH );

        projectList.setItems(projectItems);

    }

    @FXML
    public void showCustomers(){
        System.out.println("-- " + this.getClass().getName() + ": showCustomers clicked--");
        if (projectList.getSelectionModel().getSelectedItem() != null ) {
            Travel clickedTravel = projectList.getSelectionModel().getSelectedItem();
            System.out.println("-- " + this.getClass().getName() + ": item selected is: " + clickedTravel.getId() + "--");
        }

    }

    @FXML
    public void showDetails(){
        System.out.println("-- " + this.getClass().getName() + ": showDetails clicked--");
    }

    @FXML
    public void goToNewTravel(ActionEvent actionEvent) throws IOException{

        System.out.println("-- " + this.getClass().getName() + ": go to new travel --");
        Parent homePageParent = FXMLLoader.load(getClass().getResource("/views/travel/travelNew.fxml"));
        Scene homePageScene = new Scene(homePageParent);
        Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        appStage.setScene(homePageScene);
        appStage.show();

    }

    @FXML
    public void goToFindTravel(ActionEvent actionEvent) throws IOException{

        System.out.println("-- " + this.getClass().getName() + ": go to find travel --");
        Parent homePageParent = FXMLLoader.load(getClass().getResource("/views/travel/travelFind.fxml"));
        Scene homePageScene = new Scene(homePageParent);
        Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        appStage.setTitle("Busqueda");
        appStage.setScene(homePageScene);
        appStage.show();

    }

    @FXML
    public void goToDetails(ActionEvent actionEvent) throws IOException {

        System.out.println("-- " + this.getClass().getName() + ": go to travel details --");
        Parent homePageParent = FXMLLoader.load(getClass().getResource("/views/travel/travelDetails.fxml"));
        Scene homePageScene = new Scene(homePageParent);
        Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        appStage.setScene(homePageScene);
        appStage.show();

    }

    @FXML
    public void save(ActionEvent actionEvent) throws IOException {

        LocalDate localDeparture = departure.getValue();
        LocalDate localDeadline = deadline.getValue();
        System.out.println("-- " + this.getClass().getName() + ": save travel with name: " + name.getText() + "price: "+ price.getText() +"--");
        travel.setName(name.getText());
        travel.setDestination(destination.getText());
        travel.setPrice(Integer.valueOf(price.getText()));
        travel.setPayments(paymentSchedule.getText());
        travel.setDeparture(Date.valueOf(localDeparture));
        travel.setDeadline(Date.valueOf(localDeadline));
        travel.setId(1);


        System.out.println("-- " + this.getClass().getName() + ": saved clicked --");
        Parent homePageParent = FXMLLoader.load(getClass().getResource("/views/common/success.fxml"));
        Scene homePageScene = new Scene(homePageParent);
        Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        if  (travelDao.save(travel)){
            appStage.hide();
            appStage.setScene(homePageScene);
            appStage.show();
        } else {
            message.setText("Error saving new travel");
        }


    }

    @FXML
    public void clearScreen(ActionEvent actionEvent) {
        System.out.println("-- " + this.getClass().getName() + ": clear screen --");

    }

    @FXML
    public void goToHome(ActionEvent actionEvent) throws IOException {
        System.out.println("-- " + this.getClass().getName() + ": go to travel home --");
        Parent homePageParent = FXMLLoader.load(getClass().getResource("/views/travel/proyectHome.fxml"));
        Scene homePageScene = new Scene(homePageParent);
        Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        appStage.setTitle("Proyectos");
        appStage.setScene(homePageScene);
        appStage.show();
    }

    @FXML
    public void goBack(ActionEvent actionEvent) throws IOException {
        System.out.println("-- " + this.getClass().getName() + ": go to passenger home --");
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
    public void update(ActionEvent actionEvent){
        System.out.println("-- " + this.getClass().getName() + ": go to found list --");

        buttonUpdate.setDisable(false);


    }

}
