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
    private  TableView<String> projectList = new TableView<String>();
    @FXML
    private TableView<String> customerList = new TableView<String>();
    @FXML
    private TableView<String> customerDetails =  new TableView<String>();;



    @Override
    public void initialize(URL location, ResourceBundle resources) {

//        label.setText("Loaded");
//
//       Set<Travel> travelSet = travelDao.findAll();
//       Travel travel1 = new Travel();
//       travel1.setId(1);
//       travel1.setDestination("asdasd");

//       projectView.setItems(observableList);

        ObservableList<String> projectItems = FXCollections.observableArrayList ("Single", "Double", "Suite", "Family App");
        ObservableList<String> customerItems = FXCollections.observableArrayList ("Single", "Double", "Suite", "Family App");
        ObservableList<String> customerDetailsItems = FXCollections.observableArrayList ("Single", "Double", "Suite", "Family App");

        projectList.setEditable(false);
        customerList.setEditable(false);
        customerDetails.setEditable(false);

        TableColumn columnA = new TableColumn("Nombre");
        TableColumn columnB = new TableColumn("Precio");
        TableColumn columnC = new TableColumn("Fecha de salida");
        TableColumn columnD = new TableColumn("");

        projectList.getColumns().addAll(columnA, columnB, columnC);

        columnB.setText("Estado");
        columnC.setText("Adeudo");
        customerList.getColumns().addAll(columnA, columnB, columnC);

        columnB.setText("Adeudo");
        columnC.setText("Pagado");
        columnD.setText("Habitación");
        customerDetails.getColumns().addAll(columnA, columnB, columnC, columnD);

        projectList.setItems(projectItems);


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
