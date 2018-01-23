package com.muuyal.escala.billingmanagement.controller;


import javafx.scene.control.Label;
import javafx.event.ActionEvent;

@org.springframework.stereotype.Controller
public class Controller {

    public Label helloWorld;


    public void sayHelloWorld(ActionEvent actionEvent){

        helloWorld.setText("alo monde");

    }

    public void login(ActionEvent actionEvent){



    }

}
