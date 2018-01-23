package com.muuyal.escala.billingmanagement.dao.interfaces;

import com.muuyal.escala.billingmanagement.entities.Travel;
import javafx.scene.control.TextField;

public interface LoginDao {

     boolean isValidCredentals(TextField username, TextField password);

}
