package com.mycompany.ticketstore.controllers;

import com.mycompany.ticketstore.MainApp;
import com.mycompany.ticketstore.managers.DBManager;
import com.mycompany.ticketstore.models.Artist;
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

public class ArtistListController implements Initializable {
    
    @FXML
    private ListView list_artist;
    
    @FXML
    private TextField searchtext;
    
    @FXML
    private void searchArtist(ActionEvent event) {
        DBManager dbm = DBManager.getInstance();
        List<Artist> values = Arrays.asList(dbm.searchArtist(searchtext.getText()));
        list_artist.setItems(FXCollections.observableArrayList(values));
    }
   
    @FXML 
    private void openTickets(ActionEvent event){
        MainApp m = new MainApp();
        m.changeScene(6);
    }
    
    @FXML 
    private void addArtist(ActionEvent event){
        MainApp m = new MainApp();
        DBManager dbm = DBManager.getInstance();
        dbm.setSelectedArtist(new Artist(null,null,0,null));
        dbm.setSaveArtist(true);
        m.changeScene(3);
    }
        
    @FXML public void handleMouseClick(MouseEvent arg0) {
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {        
        DBManager dbm = DBManager.getInstance();
        List<Artist> values = Arrays.asList(dbm.getArtists());
        list_artist.setItems(FXCollections.observableArrayList(values));
        list_artist.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    DBManager dbm = DBManager.getInstance();
                    MainApp m = new MainApp();
                    dbm.setSelectedArtist((Artist)list_artist.getSelectionModel().getSelectedItem());
                    dbm.setSaveArtist(false);
                    m.changeScene(3);
                }
        });
        
    }    
}
