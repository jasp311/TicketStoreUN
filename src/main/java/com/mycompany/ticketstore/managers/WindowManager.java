/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ticketstore.managers;

import com.mycompany.ticketstore.models.Artist;
import com.mycompany.ticketstore.models.Concert;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Kevin
 */
public class WindowManager {
    public static WindowManager windowM;
    public Stage  window;
    public Scene login, artists, artistdetail, concert, tickets,
            concertuserdetail, checkout, alldone, ticketsdetail;
    public Artist artistSelected;
    public Concert concertSelected;
    
    
    static{
        try {
            windowM = new WindowManager();
        } catch (IOException ex) {
            Logger.getLogger(WindowManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private WindowManager() throws IOException{

    }
    
    public void changeScene(int scene){
        WindowManager m  = WindowManager.getInstance();
        m.window.setTitle("JavaFX and Maven");
        
        switch (scene){
            case 1:
                m.window.setScene(m.login);
            break;
            case 2:
                m.window.setScene(m.artists);
            break;
            case 3:
                m.window.setScene(m.artistdetail);
            break;
            case 4:
                m.window.setScene(m.concert);
            break;
            case 5:
                m.window.setScene(m.checkout);
            break;
            case 6:
                m.window.setScene(m.alldone);
            break;
            case 7:
                m.window.setScene(m.tickets);
            break;
            case 8:
                m.window.setScene(m.concertuserdetail);
            break;
        }
        m.window.show();  

    }
    
    public static WindowManager getInstance(){
        return windowM;
    }

}
