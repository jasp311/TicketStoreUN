/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ticketstore.controllers;

import com.mycompany.ticketstore.MainApp;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


/**
 *
 * @author Kevin
 */
public class AllDoneController implements Initializable {
    @FXML
    private Label label;
    public Button loginBtn;

    @FXML
    private void handleHome(ActionEvent event) throws IOException {
        MainApp m = new MainApp();
        m.changeScene(2);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
}
