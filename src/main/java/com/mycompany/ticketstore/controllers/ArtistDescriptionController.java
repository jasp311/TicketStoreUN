package com.mycompany.ticketstore.controllers;

import com.mycompany.ticketstore.MainApp;
import com.mycompany.ticketstore.managers.DBManager;
import com.mycompany.ticketstore.models.Artist;
import com.mycompany.ticketstore.models.Concert;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class ArtistDescriptionController implements Initializable {
    
    @FXML
    public Button loginBtn;
    
    @FXML
    private ListView list_concerts;
    
    @FXML
    private TextArea description;

    @FXML
    private TextField artistname;

    @FXML
    public Button backbtn;

    @FXML
    public Button updatebtn;

    @FXML
    public Button erasebtn;

    @FXML
    public Button savebtn;
    
    
    @FXML
    private void handleBack(ActionEvent event) throws IOException {
        MainApp m = new MainApp();
        m.changeScene(2);
    }

    @FXML
    private void handleUpdate(ActionEvent event) throws IOException {
        MainApp m = new MainApp();
        DBManager dbm = DBManager.getInstance();
        Artist a = dbm.getSelectedArtist();
        a.setName(artistname.getText());
        a.setDescription(description.getText());
        dbm.updateArtist(a);
        dbm.getArtists();        
        m.changeScene(2);
    }
    
    @FXML
    private void handleErase(ActionEvent event) throws IOException {
        MainApp m = new MainApp();
        DBManager dbm = DBManager.getInstance();
        dbm.eraseArtist(dbm.getSelectedArtist());
        m.changeScene(2);
    }
    
    @FXML
    private void handleSave(ActionEvent event) throws IOException {
        MainApp m = new MainApp();
        DBManager dbm = DBManager.getInstance();
        Artist a = dbm.getSelectedArtist();
        a.setName(artistname.getText());
        a.setDescription(description.getText());
        dbm.saveArtist(a);
        dbm.getArtists();        
        m.changeScene(2);
    }
    
    @FXML public void handleConcertClick(MouseEvent arg0) {
        MainApp m = new MainApp();
        DBManager db = DBManager.getInstance();
        db.setSelectConcert((Concert)list_concerts.getSelectionModel().getSelectedItem());
        m.changeScene(4);       
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DBManager db = DBManager.getInstance();
        if(db.getSelectedArtist().getName()==null)
            artistname.setText("Insertar nombre artista");
        else
            artistname.setText(""+db.getSelectedArtist().getName());

        description.setText(""+db.getSelectedArtist().getDescription());                
        List<Concert> values = Arrays.asList(db.getSelectedArtist().getConcerts());
        list_concerts.setItems(FXCollections.observableArrayList(values));
        updatebtn.setVisible(db.isSaveArtist());
        updatebtn.setVisible(!db.isSaveArtist());            
        savebtn.setVisible(!db.isSaveArtist());
        savebtn.setVisible(db.isSaveArtist());
        list_concerts.setVisible(!db.isSaveArtist());

    }    
}
