/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ticketstore.models;

import java.io.Serializable;

/**
 *
 * @author Kevin
 */
public class Ticket implements Comparable<Ticket>, Serializable{

    public String id;
    public String username;
    public String date;
    public String artist;
    public Double price;
    public int seats;
    
    public Ticket(String id,String username, String concert, 
                  String artist, Double price, int seats){
        this.id = id;
        this.username = username;
        this.date = concert;
        this.artist = artist;
        this.price = price;
        this.seats = seats;
    }
    
    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }
    
    @Override
    public int compareTo(Ticket o) {
        if (o.getId()!=null){
            return id.compareTo(o.getId()+"");
        }
        else if(o.getUsername()!=null){
            return username.compareTo(o.getUsername()+"");
        }
        else{
            return -1;
        }
    }
    
    @Override
    public String toString() {
        return "Id: "+id+" Date: " + getDate();
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
     * @return the artist
     */
    public String getArtist() {
        return artist;
    }

    /**
     * @param artist the artist to set
     */
    public void setArtist(String artist) {
        this.artist = artist;
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
        /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
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
        this.seats = seats;
    }
    
}
