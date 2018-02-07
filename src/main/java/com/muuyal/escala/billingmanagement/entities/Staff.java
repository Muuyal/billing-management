package com.muuyal.escala.billingmanagement.entities;


import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;

@Entity
@Table(name="staff")
public class Staff {

    private Integer id;
    private String name;
    private String phone;
    private String eMail;
    private String addressStreet;
    private String addressCity;
    private String addressColony;
    private String addressPC;
    private String rol;
    private Double salary;

    public Staff (String name, String eMail, Double salary){
        this.name = name;
        this.eMail = eMail;
        this.salary = salary;
    }

    public Staff (){

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

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }
}