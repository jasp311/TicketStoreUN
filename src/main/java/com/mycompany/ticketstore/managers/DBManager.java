/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ticketstore.managers;

import com.mongodb.BasicDBObject;
import com.mongodb.BulkWriteOperation;
import com.mongodb.*;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mycompany.ticketstore.LinkedList;
import com.mycompany.ticketstore.Node;
import com.mycompany.ticketstore.models.Artist;
import com.mycompany.ticketstore.models.Concert;
import com.mycompany.ticketstore.models.Ticket;
import com.mycompany.ticketstore.models.User;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kevin
 */
public class DBManager {

    /**
     * @return the SelectedUser
     */
    public User getSelectedUser() {
        return SelectedUser;
    }

    /**
     * @param SelectedUser the SelectedUser to set
     */
    public void setSelectedUser(User SelectedUser) {
        this.SelectedUser = SelectedUser;
    }
    private static DBManager dbManager;
    public MongoClient mongoClient = new MongoClient("localhost", 27017);
    public MongoDatabase mdb = mongoClient.getDatabase("ticketstore");
    public DB db = (DB) mongoClient.getDB("ticketstore");
    LinkedList artistlist = new LinkedList();
    LinkedList ticketlist = new LinkedList();
    private boolean saveArtist = false;    
    String[] genres = {"Rock","Pop","Punk","Rap","Vaporwave"};
    private User SelectedUser = new User(null,null);
    Artist SelectedArtist = new Artist(null,null,0,null);
    private Concert SelectConcert = new Concert(null,null,null,0,0.0);
    int LIMIT_QUERY = 50000;
    private Ticket selectedTicket = new Ticket(null,null,null,null,0.0,0);
    
    static{
        dbManager = new DBManager();
    }
    
    private DBManager(){
        
    }
    
    public static DBManager getInstance(){
        return dbManager;
    }
    
    public Artist getSelectedArtist(){
        return SelectedArtist;
    }
        
    public void setSelectedArtist(Artist artist){
        this.SelectedArtist = artist;
    }
    
    public Artist[] searchArtist(String artistname){
        LinkedList partialList = artistlist.partialSearch(new Artist(artistname,null,0,null));
        Node aux = partialList.getHead();
        Artist [] artists = new Artist[partialList.getSize()];
        int i = 0;
        while(aux!=null){
            artists[i] = (Artist)aux.getData();
            aux = aux.getNext();
            i++;
        }    
        return artists;
    }
    
    public Artist[] getArtists(){
        artistlist.loadData("artists",Artist.class);
        Artist [] artists = new Artist[artistlist.getSize()];
        Node aux = artistlist.getHead();
        int i = 0;
        while(aux!=null){
            artists[i] = (Artist)aux.getData();
            aux = aux.getNext();
            i++;
        }        
        return artists;
    }
    
    public Artist saveArtist(Artist artist){
        artistlist.loadData("artists",Artist.class);
        artistlist.push(artist);
                
        DBCollection collection = db.getCollection("artists");
        List<BasicDBObject> concerts = new ArrayList<>();
        DBObject auxdoc = new BasicDBObject
                         ("name", artist.getName())
                  .append("concerts",concerts)
                  .append("genre",genres[4])
                  .append("description",artist.getDescription());
        collection.save(auxdoc);
        return artist;
    }
    
    public void eraseArtist(Artist artist){
        artistlist.loadData("artists",Artist.class);
        artistlist.push(artist);
        artistlist.delete(artist);

        DBCollection collection = db.getCollection("tickets");
        BasicDBObject query = new BasicDBObject();
        query.append("name", artist.getName());
        collection.remove(query);
    }
    
    public void updateArtist(Artist artist){
        DBCollection collection = db.getCollection("tickets");
        BasicDBObject query = new BasicDBObject();
        query.append("name", artist.getName());
        
        List<BasicDBObject> concerts = new ArrayList<>();

        concerts.add(new BasicDBObject("date","01 04 200"+4)
                .append("seats",40)
                .append("price",1+45.5)
                .append("location","BOG"));
        concerts.add(new BasicDBObject("date","01 04 200"+6)
                .append("seats",40)
                .append("price", 2+45.5)
                .append("location","MED"));
        concerts.add(new BasicDBObject("date","01 04 200"+8)
                .append("seats",4)
                .append("price", 3+45.5)
                .append("location","NYC"));
                
                
        DBObject auxdoc = new BasicDBObject
                         ("name", artist.getName())
                  .append("concerts",concerts)
                  .append("genre",genres[4])
                  .append("description",artist.getDescription());
        collection.update(query,auxdoc);
    }
    
    public Artist setSelectedArtist(){
        return SelectedArtist;
    }
    
    public void populateUserDB(){
        MongoClient mongo = new MongoClient("localhost", 27017);
        DB db = (DB) mongo.getDB("ticketstore");
        DBCollection collection = db.getCollection("users");

        for(int j=0;j<100;j++){
            BulkWriteOperation bulk = collection.initializeUnorderedBulkOperation();
            for(int i=0;i<10;i++){
                DBObject document = new BasicDBObject
                                 ("username", "user"+(i+(j*10)))
                          .append("password","password"+(i+(j*10)))
                          .append("id",""+(i+(j*10)));
                bulk.insert(document);
            }
            bulk.execute();    
        }
    }
    
    public void populateTicketDB(){
        MongoClient mongo = new MongoClient("localhost", 27017);
        DB db = (DB) mongo.getDB("ticketstore");
        DBCollection collection = db.getCollection("tickets");

        for(int j=0;j<100;j++){
            BulkWriteOperation bulk = collection.initializeUnorderedBulkOperation();
            for(int i=0;i<10;i++){
                DBObject document = new BasicDBObject
                         ("username", "user"+(j*10))
                        .append("date","01 04 200"+j%10)
                        .append("artist","artist"+(j*10))
                        .append("price",j+45.5)
                        .append("id",""+(i+(j*10)))
                        .append("seats",""+(i+(j*10)));
              bulk.insert(document);
            }
            bulk.execute();    
        }
    }

    public void populateTicketForRootDB(){
        MongoClient mongo = new MongoClient("localhost", 27017);
        DB db = (DB) mongo.getDB("ticketstore");
        DBCollection collection = db.getCollection("tickets");

        for(int j=0;j<1000;j++){
            BulkWriteOperation bulk = collection.initializeUnorderedBulkOperation();
            for(int i=0;i<10000;i++){
                DBObject document = new BasicDBObject
                         ("username", "root")
                        .append("date","01 04 200"+j%10)
                        .append("artist","artist"+(j*10))
                        .append("price",j+45.5)
                        .append("id",""+(i+(j*10000)))
                        .append("seats",""+(i+(j*10)));
              bulk.insert(document);
            }
            bulk.execute();    
        }
    }
    
    public void populateArtistsDB(){
        MongoClient mongo = new MongoClient("localhost", 27017);
        DB db = (DB) mongo.getDB("ticketstore");
        DBCollection collection = db.getCollection("artists");

        for(int j=0;j<100;j++){
            BulkWriteOperation bulk = collection.initializeUnorderedBulkOperation();
            for(int i=0;i<10;i++){
                List<BasicDBObject> concerts = new ArrayList<>();
                List<BasicDBObject> seats = new ArrayList<>();
                
                concerts.add(new BasicDBObject("date","01 04 200"+j%10)
                        .append("seats",40)
                        .append("price",j+45.5)
                        .append("location","BOG"));
                concerts.add(new BasicDBObject("date","01 04 200"+j%10)
                        .append("seats",40)
                        .append("price", j+45.5)
                        .append("location","MED"));
                concerts.add(new BasicDBObject("date","01 04 200"+j%10)
                        .append("seats",4)
                        .append("price", j+45.5)
                        .append("location","NYC"));
                concerts.add(new BasicDBObject("date","01 04 200"+j%10)
                        .append("seats",4)
                        .append("price", j+45.5)
                        .append("location","BOG"));
                concerts.add(new BasicDBObject("date","01 04 200"+j%10)
                        .append("seats",4)
                        .append("price", j+45.5)
                        .append("location","MED"));
                
                DBObject document = new BasicDBObject
                                 ("name", "artist"+(i+(j*10)))
                          .append("concerts",concerts)
                          .append("genre",genres[i%5])
                          .append("description","Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat");
              bulk.insert(document);
            }
            bulk.execute();    
        }
    }

    public User searchUser(String user, String password){
        int skip = 0;
        int limit = LIMIT_QUERY;
        User userSearch = new User(user,password);
        Node userFound = null;
        
        LinkedList <User> users = new LinkedList();        
        users.loadDataLimit("users", User.class, limit, skip);
        while(users.getHead()!=null){
            userFound = users.search(userSearch);
            if(userFound!=null){
                break;
            }
            users = new LinkedList();
            skip = skip+limit;
            users.loadDataLimit("users", User.class, limit, skip);
        }
        if(userFound==null){
            userFound = new Node(null,null);
        }
        return (User)userFound.getData();
    }
    
    public Ticket[] getTickets(){
        int skip = 0;        
        LinkedList <Ticket> reslist = new LinkedList(); 
        LinkedList <Ticket> auxlist = new LinkedList(); 
        
        ticketlist.loadDataLimit("tickets", Ticket.class, LIMIT_QUERY, skip);
        System.out.println(SelectedUser.getUsername());
        
        while(ticketlist.getHead()!=null && reslist.getSize()<LIMIT_QUERY){
            auxlist = ticketlist.partialSearch(new Ticket(null,SelectedUser.getUsername(),null,null,0.0,0));
            Node aux = auxlist.getHead();
            
            while(aux!=null){
                reslist.push((Ticket)aux.getData());
                aux = aux.getNext();
            }
            ticketlist = new LinkedList();
            ticketlist.loadDataLimit("tickets", Ticket.class, LIMIT_QUERY, skip);
            skip = skip+LIMIT_QUERY;
        }
        
        
        Ticket [] ticketsarr = new Ticket[reslist.getSize()];
        Node aux = reslist.getHead();
        int i = 0;
        while(aux!=null){
            ticketsarr[i] = (Ticket)aux.getData();
            aux = aux.getNext();
            i++;
        }    
        
        return ticketsarr;
    }
    
    
    public void deleteTicket(Ticket T){
        int skip = 0;        
        Node deleted = null;
        LinkedList <Ticket> auxlist = new LinkedList(); 
                
        System.out.println();
        System.out.println("********Deletion TEST**********");        
        Instant start = Instant.now();

        auxlist.loadDataLimit("tickets", Ticket.class, LIMIT_QUERY, skip);
        while(auxlist.getHead()!=null){
            deleted = auxlist.delete(T);
            if(deleted!=null){
                System.out.println("Deleted!");
                System.out.println(deleted);
                break;
            }
            auxlist.loadDataLimit("tickets", Ticket.class, LIMIT_QUERY, skip);            
            skip = skip+LIMIT_QUERY;
            
        }
        
        Instant end = Instant.now();
        Duration timeElapsed = Duration.between(start, end);
        
        System.out.println("Deletion Time: "+ timeElapsed.toMillis() +" milliseconds");                
        System.out.println("********Deletion TEST**********");
        System.out.println();

        DBCollection collection = db.getCollection("tickets");
        BasicDBObject query = new BasicDBObject();
        query.append("id", T.getId());
        collection.remove(query);

    }
    
    public Ticket searchTicket(String id){
        int skip = 0;
        int limit = LIMIT_QUERY;
        Ticket ticketSearch = new Ticket(id,null,null,null,0.0,0);
        Node userFound = null;
        
        System.out.println();        
        System.out.println("********Search TEST**********");
        Instant start = Instant.now();

        LinkedList <Ticket> listtickets = new LinkedList();        
        listtickets.loadDataLimit("tickets", Ticket.class, limit, skip);
        while(listtickets.getHead()!=null){
            userFound = listtickets.search(ticketSearch);
            if(userFound!=null){
                break;
            }
            skip = skip+limit;
            listtickets = new LinkedList();
            listtickets.loadDataLimit("tickets", Ticket.class, limit, skip);
        }
        if(userFound==null){
            userFound = new Node(null,null);
        }
        
        Instant end = Instant.now();        
        System.out.println();
        
        Duration timeElapsed = Duration.between(start, end);
        System.out.println("Search Time: "+ timeElapsed.toMillis() +" milliseconds");        
        System.out.println("********Search TEST**********");
        System.out.println();

        return (Ticket)userFound.getData();
    }
     
    public void updateTicket(Ticket T){
        int skip = 0;        
        Node updated = null;
                
        System.out.println();        
        System.out.println("********Update TEST**********");        
        Instant start = Instant.now();
        
        LinkedList <Ticket> auxlist = new LinkedList(); 
        auxlist.loadDataLimit("tickets", Ticket.class, LIMIT_QUERY, skip);
        while(auxlist.getHead()!=null){
            updated = auxlist.update(T);
            if(updated!=null){
                System.out.println("Updated!");
                System.out.println(updated);
                break;
            }
            auxlist.loadDataLimit("tickets", Ticket.class, LIMIT_QUERY, skip);            
            skip = skip+LIMIT_QUERY;
        }
        
        Instant end = Instant.now();
        Duration timeElapsed = Duration.between(start, end);
        
        System.out.println("Update Time: "+ timeElapsed.toMillis() +" milliseconds");        
        System.out.println("********Update TEST**********");
        System.out.println();        

        if(updated!=null){
            System.out.println("Updated!");
            DBCollection collection = db.getCollection("tickets");
            BasicDBObject query = new BasicDBObject();
            query.append("id", T.getId());
            Ticket taux = (Ticket) updated.getData(); 
            DBObject document = new BasicDBObject
                ("username", SelectedUser.getUsername())
               .append("date",taux.getDate())
               .append("artist",taux.getArtist())
               .append("price",taux.getPrice())
               .append("id",""+taux.getId())
               .append("seats",taux.getSeats());

            collection.update(query,document);
        }
    }
    
    
    /**
     * @return the SelectConcert
     */
    public Concert getSelectConcert() {
        return SelectConcert;
    }

    /**
     * @param SelectConcert the SelectConcert to set
     */
    public void setSelectConcert(Concert SelectConcert) {
        this.SelectConcert = SelectConcert;
    }

    /**
     * @return the selectedTicket
     */
    public Ticket getSelectedTicket() {
        return selectedTicket;
    }

    /**
     * @param selectedTicket the selectedTicket to set
     */
    public void setSelectedTicket(Ticket selectedTicket) {
        this.selectedTicket = selectedTicket;
    }

    /**
     * @return the saveArtist
     */
    public boolean isSaveArtist() {
        return saveArtist;
    }

    /**
     * @param saveArtist the saveArtist to set
     */
    public void setSaveArtist(boolean saveArtist) {
        this.saveArtist = saveArtist;
    }

}