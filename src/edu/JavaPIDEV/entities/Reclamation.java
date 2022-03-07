/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.JavaPIDEV.entities;

import java.sql.Date;

/**
 *
 * @author Jasser BOUKRAYA
 */
public class Reclamation {
    private int id_rec;
    private String type_rec;
    private String description_rec;
    private Date date_rec;
    private int cin;
    private String screenshot;
    private String mail;
    
    
    public Reclamation(){}

    public Reclamation(int id_rec, String type_rec, String description_rec, Date date_rec, int cin, String screenshot,String mail) {
        this.id_rec = id_rec;
        this.type_rec = type_rec;
        this.description_rec = description_rec;
        this.date_rec = date_rec;
        this.cin = cin;
        this.screenshot = screenshot;
        this.mail=mail;
      
    }

    public Reclamation(String type_rec, String description_rec, Date date_rec, String screenshot) {
        this.type_rec = type_rec;
        this.description_rec = description_rec;
        this.date_rec = date_rec;
        this.screenshot = screenshot;
    }

  

    public Reclamation(int id_rec, String type_rec, String description_rec, Date date_rec, int cin) {
        this.id_rec = id_rec;
        this.type_rec = type_rec;
        this.description_rec = description_rec;
        this.date_rec = date_rec;
        this.cin = cin;
    }

   

    public Reclamation(int id_rec) {
        this.id_rec = id_rec;
    }

    public Reclamation(String type_rec, String description_rec, Date date_rec, String screenshot, String mail) {
        this.type_rec = type_rec;
        this.description_rec = description_rec;
        this.date_rec = date_rec;
        this.screenshot = screenshot;
        this.mail = mail;
    }

    public Reclamation(int id_rec, String type_rec, String description_rec, Date date_rec) {
        this.id_rec = id_rec;
        this.type_rec = type_rec;
        this.description_rec = description_rec;
        this.date_rec = date_rec;
    }

    public Reclamation(String type_rec, String description_rec, Date date_rec, int cin, String screenshot, String mail) {
        this.type_rec = type_rec;
        this.description_rec = description_rec;
        this.date_rec = date_rec;
        this.cin = cin;
        this.screenshot = screenshot;
        this.mail = mail;
    }
    

   

   
    
 
    public int getId_rec() {
        return id_rec;
    }

    public void setId_rec(int id_rec) {
        this.id_rec = id_rec;
    }

    public String getType_rec() {
        return type_rec;
    }

    public void setType_rec(String type_rec) {
        this.type_rec = type_rec;
    }

    public String getDescription_rec() {
        return description_rec;
    }

    public void setDescription_rec(String description_rec) {
        this.description_rec = description_rec;
    }

    public Date getDate_rec() {
        return date_rec;
    }

    public void setDate_rec(Date date_rec) {
        this.date_rec = date_rec;
    }

   

    public int getCin() {
        return cin;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    public String getScreenshot() {
        return screenshot;
    }
    

    public void setScreenshot(String screenshot) {
        this.screenshot = screenshot;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id_rec=" + id_rec + ", type_rec=" + type_rec + ", description_rec=" + description_rec + ", date_rec=" + date_rec + ", cin=" + cin + ", screenshot=" + screenshot + ", mail=" + mail + '}';
    }

    
    

    


   


    
}
