/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ticketstore.controllers;

import com.mycompany.ticketstore.MainApp;
import com.mycompany.ticketstore.managers.DBManager;
import com.mycompany.ticketstore.models.Ticket;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;


/**
 *
 * @author Kevin
 */
public class TicketDescription implements Initializable {

    @FXML
    TextField text1;

    @FXML
    TextField text2;
    
    @FXML
    TextField text3;
    
    @FXML
    TextField text4;
    
    @FXML
    TextField text5;
    
    @FXML
    TextField idtxt;
    
    @FXML private void handleBack(ActionEvent event) throws IOException {
        MainApp m = new MainApp();
        m.changeScene(6);
    }
    
    @FXML private void handleErase(ActionEvent event) throws IOException {
        MainApp m = new MainApp();
        DBManager dbm = DBManager.getInstance();
        dbm.deleteTicket(dbm.getSelectedTicket());
        dbm.getTickets();
        m.changeScene(6);
    }

    @FXML private void handleSave(ActionEvent event) throws IOException {
        MainApp m = new MainApp();
        DBManager dbm = DBManager.getInstance();
        Ticket t = dbm.getSelectedTicket();
        t.setSeats(Integer.parseInt(text5.getText()));
        dbm.setSelectedTicket(t);
        dbm.updateTicket(dbm.getSelectedTicket());
        dbm.getTickets();
        m.changeScene(6);
    }
    


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DBManager dbm = DBManager.getInstance();
        System.out.println("FAS NJIKFDNN");
        System.out.println(dbm.getSelectedTicket());
        text1.setText(dbm.getSelectedTicket().getDate());
        text2.setText(dbm.getSelectedTicket().getArtist());
        text3.setText(""+(dbm.getSelectedTicket().getSeats()*dbm.getSelectedTicket().getPrice()));
        text4.setText(dbm.getSelectedTicket().getId());
        text5.setText(""+dbm.getSelectedTicket().getSeats());

    }    
}
