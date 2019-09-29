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
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;


/**
 *
 * @author Kevin
 */
public class TicketsController implements Initializable {
    
        
    @FXML
    private ListView list_tickets;
        
    @FXML
    private TextField idtxt;
    
    @FXML
    private Text warning;
        
    
    @FXML private void handleBack(ActionEvent event) throws IOException {
        MainApp m = new MainApp();
        m.changeScene(2);
    }
    
    @FXML private void searchTicket(ActionEvent event) throws IOException {
        MainApp m = new MainApp();
        DBManager dbm = DBManager.getInstance();
        Ticket t = dbm.searchTicket(idtxt.getText());
        if(t!=null){
            dbm.setSelectedTicket(t);
            m.changeScene(7);
        }
        else{
            warning.setVisible(true);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DBManager dbm = DBManager.getInstance();
        List<Ticket> values = Arrays.asList(dbm.getTickets());
        list_tickets.setItems(FXCollections.observableArrayList(values));
        list_tickets.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    MainApp m = new MainApp();
                    DBManager db = DBManager.getInstance();
                    db.setSelectedTicket((Ticket)list_tickets.getSelectionModel().getSelectedItem());
                    System.out.println(list_tickets.getSelectionModel().getSelectedItem());
                    m.changeScene(7);  
                }
        });
    }    
}
