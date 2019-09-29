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
public class User implements Comparable<User>, Serializable{
    private String password;
    private String username;

    public User(String username , String password ){
        this.username = username;
        this.password = password;
    }
    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
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
    public int compareTo(User o) {
        int res = -1;
        if(password!=null && username!=null && o!=null){
            if((password.compareTo(o.password))==0
                    && (username.compareTo(o.username)==0)){
                res = 0;
            }
            else{
                res = -1;
            }
        }
        return res;
    }
    
    @Override
    public String toString() {
        return getUsername()+" pass: "+getPassword()+"\n"; 
    }  

}
