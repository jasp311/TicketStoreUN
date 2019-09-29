/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ticketstore.models;

import com.mycompany.ticketstore.managers.DBManager;
import java.io.Serializable;
import java.util.*;
import java.text.*;

/**
 *
 * @author Kevin
 */
public class Concert implements Comparable<Concert>, Serializable {
    public String date;
    public int seats; 
    public String location;
    public Double price;

    public Concert(String date, String location, String artist,int seats, Double price){
        this.date = date;
        this.location = location;
        this.seats = 0;
        this.price = price;
    }
    
    @Override
    public int compareTo(Concert b){ 
        return this.getDate().compareTo(b.getDate());
    }

    
    public static Calendar parseReturnDate(String returnDate) 
        throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd MM yyyy");
        Date date = formatter.parse(returnDate);
        Calendar cal=Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }       
    
    @Override
    public String toString() { 
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return getLocation()+" "+date+"\n"; 
    }  

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return the seats
     */
    public int getSeats() {
        return seats;
    }

    /**
     * @param seats the seats to set
     */
    public void setSeats(int seats) {
        this.setSeats(seats);
    }

    /**
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(String location) {
        this.location = location;
    }
    
    private void SaveDB(){
        DBManager dbm = DBManager.getInstance();
}

    /**
     * @return the price
     */
    public Double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(Double price) {
        this.price = price;
    }
}
