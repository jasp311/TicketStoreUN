/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ticketstore.controllers;

import com.mycompany.ticketstore.LinkedList;
import com.mycompany.ticketstore.MainApp;
import com.mycompany.ticketstore.managers.DBManager;
import com.mycompany.ticketstore.models.Ticket;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;


/**
 *
 * @author Kevin
 */
public class CheckoutController implements Initializable {
    @FXML
    public Button loginBtn;
    
    @FXML
    public TextField text1;

    @FXML
    public TextField text2;

    @FXML
    public TextField text3;

    @FXML
    public TextField text4;
    
    @FXML
    public TextField text5;

    @FXML
    private void HandleCheckOut(ActionEvent event) throws IOException {
        DBManager db = DBManager.getInstance();
        MainApp m = new MainApp();
        LinkedList <Ticket> tickets = new <Ticket> LinkedList();
        for(int i=0;i<Integer.valueOf(text3.getText());i++){
            Ticket t = new Ticket("777",db.getSelectedArtist().getName(),db.getSelectConcert().getDate(),
                    db.getSelectedArtist().getName(), db.getSelectConcert().getPrice(), db.getSelectConcert().getSeats());
            tickets.push(t);
        }        
        tickets.save("tickets",Ticket.class);
        m.changeScene(5);
    }

    @FXML
    private void HandleBackOut(ActionEvent event) throws IOException {
        MainApp m = new MainApp();
        m.changeScene(3);
    }    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DBManager db = DBManager.getInstance();        
        text1.setText(db.getSelectConcert().getPrice().toString());
        text2.setText(db.getSelectedArtist().getName());                
        text3.setText("1");                
        text4.setText(db.getSelectConcert().getDate());  
        text5.setText(db.getSelectedUser().getUsername());  

     }    
}
