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
public class Artist implements Comparable<Artist>, Serializable{
    private String name;
    private Concert[] concerts;
    private String genre;
    private String description;
    
    public Artist(String name,String genre, int concerts, String description){
        this.name = name;
        this.genre = genre;
        this.concerts = new Concert[concerts];
        this.description = description;
    }

    public Artist() {
        this.name = null;
        this.genre = null;
        this.concerts = new Concert[10];
        this.description = null;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the concerts
     */
    public Concert[] getConcerts() {
        return concerts;
    }

    /**
     * @param concerts the concerts to set
     */
    public void setConcerts(Concert[] concerts) {
        this.setConcerts(concerts);
    }

    /**
     * @return the genre
     */
    public String getGenre() {
        return genre;
    }

    /**
     * @param genre the genre to set
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public int compareTo(Artist o) {
        int comparison = -1;
        if(o.getName()!=null && this.name!=null){
            if (this.name.contains(o.getName())){
                comparison = 0;
            }
        }
        return comparison;
    }
    
    @Override
    public String toString() {
        return getName()+" "+getGenre()+"\n"; 
    }  

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
