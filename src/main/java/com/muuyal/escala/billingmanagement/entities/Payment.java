package com.muuyal.escala.billingmanagement.entities;

import java.util.Date;

public class Payment {

    private String user;
    private String travel;
    private Integer paymentAmount ;
    private Integer total;
    private Integer amountLeft;
    private Date date;
    
    //Constructor
    public Payment(String user, String travel, Integer total, Date date) {
        this.user = user;
        this.travel = travel;
        this.total = total;
        this.date = date;
    }
    
    //getters
    public String getUser() {
        return user;
    }

    public String getTravel() {
        return travel;
    }

    public Integer getPaymentAmount() {
        return paymentAmount;
    }

    public Integer getTotal() {
        return total;
    }

    public Integer getAmountLeft() {
        return amountLeft;
    }

    public Date getDate() {
        return date;
    }

    //Setters
    public void setUser(String user) {
        this.user = user;
    }

    public void setTravel(String travel) {
        this.travel = travel;
    }

    public void setPaymentAmount(Integer paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public void setAmountLeft(Integer amountLeft) {
        this.amountLeft = amountLeft;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    

}