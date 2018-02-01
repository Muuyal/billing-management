package com.muuyal.escala.billingmanagement.entities;

import javax.persistence.*;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import java.util.Date;

@Entity
@Table (name="project")
public class Project {

    private Integer id;
    @NotNull(message = "Nombre no puede ir vacio")
    private String name;
    private String destination;
    private Date eta;
    private Integer price;

    public Project(Integer id, String name, String destination, Integer price) {
        this.id = id;
        this.name = name;
        this.destination = destination;
        this.price = price;
    }

    public Project(String name, String destination, Integer price, Date eta) {
        this.name = name;
        this.destination = destination;
        this.price = price;
        this.eta = eta;
    }

    public Project(String name, String destination, Date eta, Date deadline, Integer price, String paymentSchedule){

    }

    public Project(){

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId() { return id; }


    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Date getEta() {
        return eta;
    }

    public void setEta(Date eta) {
        this.eta = eta;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}