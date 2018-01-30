package com.muuyal.escala.billingmanagement.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="contract")
public class Contract {

    private String id;
    private String customer_id;
    private String project_id;
    private Integer discount;
    private Date createdOn;
    private Date terminatedOn;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getProject_id() {
        return project_id;
    }

    public void setProject_id(String project_id) {
        this.project_id = project_id;
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

    public Date getTerminatedOn() {
        return terminatedOn;
    }

    public void setTerminatedOn(Date terminatedOn) {
        this.terminatedOn = terminatedOn;
    }
}
