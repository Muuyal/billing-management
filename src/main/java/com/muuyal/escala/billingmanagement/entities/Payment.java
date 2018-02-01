package com.muuyal.escala.billingmanagement.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="payment")
public class Payment {

    private Integer id;
    private Integer customerId;
    private Integer contractId;
    private Double paymentAmount;
    private Date paymentDate;
    
    //Constructor
    public Payment(Integer customerId, Integer projectId,  Date date) {
        this.customerId = customerId;
        this.contractId = projectId;
        this.paymentDate = date;
    }

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

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getContractId() {
        return contractId;
    }

    public void setContractId(Integer contractId) {
        this.contractId = contractId;
    }

    public Double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(Double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }
}