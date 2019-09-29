package com.mycompany.ticketstore;

import com.mycompany.ticketstore.managers.DBManager;
import com.mycompany.ticketstore.managers.WindowManager;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MainApp extends Application {
   
    @Override
    public void start(Stage stage) throws Exception {
        WindowManager m  = WindowManager.getInstance();
        m.window = stage;
        m.window.setTitle("TicketStore");

        Parent root = FXMLLoader.load(getClass().getResource("/fxml/allDone.fxml"));    
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        m.alldone = scene;        


        root = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));    
        scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        m.login = scene;
        
        m.window.setScene(m.login);
        m.window.show();        
        
    }
    
    public void changeScene(int scene) {
        WindowManager m  = WindowManager.getInstance();
        m.window.setTitle("TicketStore");
        
        switch (scene){
            case 1:
                m.window.setScene(m.login);
            break;
            case 2:
            {
            try {
                m.artists = new Scene((Parent)FXMLLoader.load(getClass().getResource("/fxml/artistList.fxml")));
            } catch (IOException ex) {
                Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
            }
                m.window.setScene(m.artists);
            }
            break;

            case 3:
                try{
                    m.artistdetail = new Scene((Parent)FXMLLoader.load(getClass().getResource("/fxml/artistDetail.fxml")));
                    m.window.setScene(m.artistdetail);
                }
                catch(Exception e){
                    System.out.print(e);
                }
            break;
            case 4:
                try{
                    m.checkout = new Scene((Parent)FXMLLoader.load(getClass().getResource("/fxml/checkout.fxml")));
                    m.window.setScene(m.checkout);
                }
                catch(Exception e){
                    System.out.print(e);
                }
            break;
            case 5:
                m.window.setScene(m.alldone);
            break;            
            case 6:
                try{
                    m.tickets = new Scene((Parent)FXMLLoader.load(getClass().getResource("/fxml/tickets.fxml")));
                    m.window.setScene(m.tickets);
                }
                catch(Exception e){
                    System.out.print(e);
                }
            break;
            case 7:
                try{
                    m.ticketsdetail = new Scene((Parent)FXMLLoader.load(getClass().getResource("/fxml/ticketsDetail.fxml")));
                    m.window.setScene(m.ticketsdetail);
                }
                catch(Exception e){
                    System.out.print(e);
                }
            break;
        }
        m.window.show();  

    }
    
    public static void main(String[] args) throws Exception {
        DBManager dbm = DBManager.getInstance();
        //dbm.populateUserDB();
        //dbm.populateArtistsDB();
        //dbm.populateTicketDB();
        dbm.populateTicketForRootDB();
        launch(args);
    }

}
