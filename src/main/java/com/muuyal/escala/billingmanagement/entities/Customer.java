package com.muuyal.escala.billingmanagement.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="customer")
public class Customer {

    @Id
    @NotNull
    private Integer id;
    @NotNull
    private String name;
    @NotNull
    private String phone;
    private String email;
    private String addressStreet;
    private String addressCity;
    private String addressColony;
    private String addressPC;
    private String notes;

    public Customer(String name, String phone, String eMail) {
        this.setName(name);
        this.setPhone(phone);
        this.setEmail(email);
    }

    public Customer(){
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddressStreet() {
        return addressStreet;
    }

    public void setAddressStreet(String addressStreet) {
        this.addressStreet = addressStreet;
    }

    public String getAddressCity() {
        return addressCity;
    }

    public void setAddressCity(String addressCity) {
        this.addressCity = addressCity;
    }

    public String getAddressColony() {
        return addressColony;
    }

    public void setAddressColony(String addressColony) {
        this.addressColony = addressColony;
    }

    public String getAddressPC() {
        return addressPC;
    }

    public void setAddressPC(String addressPC) {
        this.addressPC = addressPC;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return this.getName();
    }

}