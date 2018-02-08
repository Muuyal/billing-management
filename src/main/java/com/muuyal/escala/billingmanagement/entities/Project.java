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
    private String description;
    private Date eta;
    private Date deadline;
    private Integer price;

    public Project(Integer id, String name, String description, Integer price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Project(String name, String description, Integer price, Date eta) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.eta = eta;
    }

    public Project(String name, String description, Date eta, Date deadline, Integer price, String paymentSchedule){

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return this.getName();
    }
}