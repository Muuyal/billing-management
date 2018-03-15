package com.muuyal.escala.billingmanagement.controller;

import com.muuyal.escala.billingmanagement.dao.impl.StaffDaoImp;
import com.muuyal.escala.billingmanagement.dao.interfaces.StaffDao;
import com.muuyal.escala.billingmanagement.entities.Staff;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.control.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class StaffController implements Initializable {

    private Button buttonUpdate;
    private StaffDao staffDao = new StaffDaoImp();
    private Staff staff = new Staff();

    @FXML
    private TextField name;
    @FXML
    private TextField phone;
    @FXML
    private TextField email;
    @FXML
    private TextField addressStreet;
    @FXML
    private TextField addressCity;
    @FXML
    private TextField addressColony;
    @FXML
    private TextField addressPC;
    @FXML
    private ChoiceBox<String> rol =  new ChoiceBox<>();
    @FXML
    private TextField salary;
    @FXML
    private Label message;
    @FXML
    private TableView<Staff> staffList = new TableView<>();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initRolChoiceBox();
    }

    private void initRolChoiceBox(){
        rol.setItems(FXCollections.observableArrayList(
                "Admin", "Staff", "Ventas")
        );
    }

    @FXML
    public void goToNew(ActionEvent actionEvent) throws IOException {

        System.out.println("-- " + this.getClass().getName() + ": go to new staff --");
        Parent homePageParent = FXMLLoader.load(getClass().getResource("/views/staff/staffNew.fxml"));
        Scene homePageScene = new Scene(homePageParent);
        Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        appStage.setScene(homePageScene);
        appStage.show();

    }

    @FXML
    public void goToFind(ActionEvent actionEvent) throws IOException{

        System.out.println("-- " + this.getClass().getName() + ": go to find staff --");
        Parent homePageParent = FXMLLoader.load(getClass().getResource("/views/staff/staffFind.fxml"));
        Scene homePageScene = new Scene(homePageParent);
        Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        appStage.setScene(homePageScene);
        appStage.show();

    }

    // Borrar
    @FXML
    public void goToDetails(ActionEvent actionEvent) throws IOException {

        System.out.println("-- " + this.getClass().getName() + ": go to staff details --");
        Parent homePageParent = FXMLLoader.load(getClass().getResource("/views/staff/staffDetails.fxml"));
        Scene homePageScene = new Scene(homePageParent);
        Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        appStage.setScene(homePageScene);
        appStage.show();

    }

    @FXML
    public void save(ActionEvent actionEvent) throws IOException {
        System.out.println("-- " + this.getClass().getName() + ": save staff --");
        staff.setName(name.getText());
        staff.setPhone(phone.getText());
        staff.setEmail(email.getText());
        staff.setAddressStreet(addressStreet.getText());
        staff.setAddressColony(addressColony.getText());
        staff.setAddressCity(addressCity.getText());
        staff.setAddressPC(addressPC.getText());
        staff.setSalary(Double.valueOf(salary.getText()));

        System.out.println("-- " + this.getClass().getName() + ": saved clicked --");
        Parent homePageParent = FXMLLoader.load(getClass().getResource("/views/common/success.fxml"));
        Scene homePageScene = new Scene(homePageParent);
        Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        if  (staffDao.save(staff)){
            appStage.hide();
            appStage.setScene(homePageScene);
            appStage.show();
        } else {
            message.setText("Error saving new staff");
        }

    }

   @FXML
   public void goToDelete(ActionEvent actionEvent) throws IOException {

       if (staffList.getSelectionModel().getSelectedItem() == null ){
           Alert alertNotSelected = new Alert(Alert.AlertType.CONFIRMATION, "No hay registro seleccionado", ButtonType.CANCEL);
           alertNotSelected.showAndWait();
       }else {
           Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "¿Quieres borrar registro seleccionado?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
           alert.showAndWait();
           if (alert.getResult() == ButtonType.YES) {

               Staff staff = staffList.getSelectionModel().getSelectedItem();
               if (staffDao.delete(staff)) {
                   staffList.getItems().remove(staff);
               }
           }
       }
   }

    @FXML
    public void goToHome(ActionEvent actionEvent) throws IOException {
        System.out.println("-- " + this.getClass().getName() + ": go to staff home --");
        Parent homePageParent = FXMLLoader.load(getClass().getResource("/views/staff/staffHome.fxml"));
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

        System.out.println("-- " + this.getClass().getName() + ": update staff --");

        staff.setId(staff.getId());
        staff.setName(name.getText());
        staff.setPhone(phone.getText());
        staff.setEmail(email.getText());
        staff.setAddressStreet(addressStreet.getText());
        staff.setAddressCity(addressCity.getText());
        staff.setAddressColony(addressColony.getText());
        staff.setAddressPC(addressPC.getText());
        staff.setRol(rol.getValue());
        staff.setSalary(Double.valueOf(salary.getText()));

        System.out.println("-- " + this.getClass().getName() + ": updated clicked --");
        Parent homePageParent = FXMLLoader.load(getClass().getResource("/views/common/success.fxml"));
        Scene homePageScene = new Scene(homePageParent);
        Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        if  (staffDao.update(staff)){
            appStage.hide();
            appStage.setScene(homePageScene);
            appStage.show();
        } else {
            message.setText("Error updating staff");
        }
    }

    @FXML
    public void delete(ActionEvent actionEvent) throws IOException {
        System.out.println("-- " + this.getClass().getName() + ": delete staff --");

        staff.setId(staff.getId());

        System.out.println("-- " + this.getClass().getName() + ": updated clicked --");
        Parent homePageParent = FXMLLoader.load(getClass().getResource("/views/common/success.fxml"));
        Scene homePageScene = new Scene(homePageParent);
        Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        if  (staffDao.delete(staff)){
            appStage.hide();
            appStage.setScene(homePageScene);
            appStage.show();
        } else {
            message.setText("Error deleting staff");
        }
    }

}
