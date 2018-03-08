package com.muuyal.escala.billingmanagement.entities;

import javax.persistence.*;

@Entity
@Table(name="user")
public class User{

    private Integer id;
    @Column(name="username")
    private String userName;
    private String password;
    private String rol;

    /*public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }*/

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getRol() {
        return rol;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

}