package com.muuyal.escala.billingmanagement.entities;



import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="contract")
public class Passenger {


    @NotNull
    private String name;

    private String travel;

    @NotNull
    private Integer phone;
    @Id
    @NotNull
    private String id;
    private String eMail;
    private String addressStreet;
    private String addressCity;
    private String addressColony;
    private Integer addressPC;
    private String notes;
    private String contract;


    Passenger(String name, int phone, String eMail) {
        this.setName(name);
        this.setPhone(phone);
        this.seteMail(eMail);
    }


    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getTravel() {

        return travel;
    }

    public void setTravel(String travel) {
        this.travel = travel;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {

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

    public Integer getAddressPC() {

        return addressPC;
    }

    public void setAddressPC(Integer addressPC) {

        this.addressPC = addressPC;
    }

    public String getNotes() {

        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}