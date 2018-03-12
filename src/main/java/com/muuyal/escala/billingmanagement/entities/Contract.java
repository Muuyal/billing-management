package com.muuyal.escala.billingmanagement.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="contract")
public class Contract {

    private Integer id;
    private Integer customerId;
    private Integer projectId;
    private Integer discount;
    private Date createdOn;
    private Date deadline;
    private String paymentSchedule;
    private String customerName;
    private String projectName;
    private Double finalPrice;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customer_id) {
        this.customerId = customer_id;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer project_id) {
        this.projectId = project_id;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public String getPaymentSchedule() {
        return paymentSchedule;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void setPaymentSchedule(String paymentSchedule) {
        this.paymentSchedule = paymentSchedule;
    }

    public Double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(Double finalPrice) {
        this.finalPrice = finalPrice;
    }

    @Override
    public String toString() {
        return this.getProjectName();
    }

}
