/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ticketstore.controllers;

import com.mycompany.ticketstore.MainApp;
import com.mycompany.ticketstore.managers.DBManager;
import com.mycompany.ticketstore.models.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;


/**
 *
 * @author Kevin
 */
public class LoginController implements Initializable {
    @FXML
    public Button loginBtn;

    @FXML
    public PasswordField passtext;
            
    @FXML            
    public TextField usertext;
    
    @FXML
    public Text userNotFoundTXT;
            
    @FXML
    private void handleLogin(ActionEvent event) throws IOException {
        MainApp m = new MainApp();
        DBManager dbm = DBManager.getInstance();
        User user = dbm.searchUser(usertext.getText(), passtext.getText());
        if(user!=null){
            dbm.setSelectedUser(user);
            m.changeScene(2);        
        }
        else{
            userNotFoundTXT.setVisible(true);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
}
