package com.muuyal.escala.billingmanagement.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="customer")
public class Customer {

    @Id
    @NotNull
    private String id;
    @NotNull
    private String name;
    @NotNull
    private String phone;
    private String eMail;
    private String addressStreet;
    private String addressCity;
    private String addressColony;
    private String addressPC;
    private String notes;

    public Customer(String name, String phone, String eMail) {
        this.setName(name);
        this.setPhone(phone);
        this.seteMail(eMail);
    }

    public Customer(){
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
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

}