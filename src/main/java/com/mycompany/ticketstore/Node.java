/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ticketstore;

/**
 *
 * @author Kevin
 */
public class Node <T extends Comparable<T>>{
    private Node next;
    private T data;

    public Node(T data,Node next){
        this.next = next;
        this.data = data;
    }
    /**
     * @return the next
     */
    public Node getNext() {
        return next;
    }

    /**
     * @param next the next to set
     */
    public void setNext(Node next) {
        this.next = next;
    }

    /**
     * @return the data
     */
    public T getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(T data) {
        this.data = data;
    }
    
    @Override
    public String toString() {
        return data.toString();
    } 
}
