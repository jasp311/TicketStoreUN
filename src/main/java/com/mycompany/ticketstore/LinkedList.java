/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ticketstore;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.BulkWriteOperation;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mycompany.ticketstore.managers.DBManager;
import com.mycompany.ticketstore.models.Concert;
import java.lang.reflect.Field;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kevin
 * @param <T>
 */
public class LinkedList <T extends Comparable<T>>{
    private Node head;
    private int size;
    
    public LinkedList(){
        head=null;
        size=0;
    }
    
    public void push(T data){
        
        setSize(getSize() + 1);
        if(getHead()==null){
            setHead(new Node(data,null));
        } 
        else{
            Node aux = getHead();
            while(aux.getNext()!=null){
                aux = aux.getNext();
            }
            aux.setNext(new Node(data,null));
        }
    }
    
    public Node update(T data){
        Node aux = getHead();
        while(aux!=null && aux.getData().compareTo(data)!=0){
            aux = aux.getNext();
        }
        if(aux!=null)
            aux.setData(data);
        return aux;
    }
    
    void sort() 
    { 
        if (getHead() != null && size>1) {
            boolean sorted = false;
            while(!sorted){
                Node aux = head;
                sorted = true;
                while(aux.getNext()!=null){
                    if(aux.getData().compareTo(aux.getNext().getData())>0){
                        Node n = new Node(aux.getData(),null);
                        aux.setData(aux.getNext().getData());
                        aux.getNext().setData(n.getData());
                        sorted = false;
                    }
                    aux=aux.getNext();
                }                
            }
        }
    }

    public Node delete(T data){
        Node deleted = null;
        Node aux = getHead();
        Node prev = getHead();
        while(aux!=null){
            if(aux.getData().compareTo(data)==0){
                deleted = aux;
                prev.setNext(aux.getNext());
                break;
            }
            prev = aux;
            aux = aux.getNext();
        }
        return deleted;
    }
    
    public void loadData(String collectionName,  Class<T> nclass)
    {    
        Gson g = new Gson();
        DBManager dbm = DBManager.getInstance();

        DBCollection collection = dbm.db.getCollection(collectionName);
        DBCursor iterable = collection.find();
        
        while(iterable.hasNext()) {
            push((T)g.fromJson(g.toJson(iterable.next()), nclass));
	}
        
    }
    
    
    public void loadDataLimit(String collectionName,  Class<T> nclass, int limit, int skip)
    {    
        Gson g = new Gson();
        DBManager dbm = DBManager.getInstance();
        DBCollection collection = dbm.db.getCollection(collectionName);
        DBCursor iterable = collection.find().limit(limit).skip(skip);
        
        while(iterable.hasNext()) {
            push((T)g.fromJson(g.toJson(iterable.next()), nclass));
	}        
     }
    
    public Node search(T data){
        Node aux = getHead();
        while(aux!=null && aux.getData().compareTo(data)!=0){
            aux = aux.getNext();
        }
        return aux;
    }
    
    public LinkedList partialSearch(T data){
        LinkedList sublist = new LinkedList();        
        Node aux = getHead();
        while(aux!=null){
            if(aux.getData()!=null){
                if(aux.getData().compareTo(data)==0)
                    sublist.push(aux.getData());
            }
            aux = aux.getNext();
        }
        return sublist;
    }
    
    public void save(String collectionName, Class<T> nclass){
        Gson gson=new Gson();                
        DBManager dbm = DBManager.getInstance();
        DBCollection collection = dbm.db.getCollection(collectionName);
        Node aux = head;
        BulkWriteOperation bulk = collection.initializeUnorderedBulkOperation();

        while(aux!=null){
            String json = gson.toJson(aux.getData());   
            T c = gson.fromJson(json, nclass);
            Field[] fields = c.getClass().getDeclaredFields();
            
            BasicDBObject document = new BasicDBObject(); 
            for(Field f: fields) {
                try {
                    document.append(f.getName(),f.get(c) );                                        
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(LinkedList.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            bulk.insert(document);
            aux = aux.getNext();
        }

        bulk.execute();    
        
    }
    
    public void print(Node aux){
        if(aux!=null){
            System.out.println(aux.getData());
            print(aux.getNext());
        }
    }

    /**
     * @return the head
     */
    public Node getHead() {
        return head;
    }

    /**
     * @param head the head to set
     */
    public void setHead(Node head) {
        this.head = head;
    }

    /**
     * @return the size
     */
    public int getSize() {
        return size;
    }

    /**
     * @param size the size to set
     */
    public void setSize(int size) {
        this.size = size;
    }
    
}
