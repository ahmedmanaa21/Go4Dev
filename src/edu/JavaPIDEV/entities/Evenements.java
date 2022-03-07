/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.JavaPIDEV.entities;

import java.sql.Date;

/**
 *
 * @author melki
 */
public class Evenements {
    private int id ; 
    private String nom ;
    private String description ;
    private Date date_deb ; 
    private Date date_fin ; 
    private String image ; 

    public Evenements() {
    }

    public Evenements(int id) {
        this.id = id;
    }

    public Evenements(int id, String nom, String description, Date date_deb, Date date_fin, String image) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.date_deb = date_deb;
        this.date_fin = date_fin;
        this.image = image;
    }

    public Evenements(String nom, String description, Date date_deb, Date date_fin, String image) {
        this.nom = nom;
        this.description = description;
        this.date_deb = date_deb;
        this.date_fin = date_fin;
        this.image = image;
    }

  
    
    

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getDescription() {
        return description;
    }

    public Date getDate_deb() {
        return date_deb;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public String getImage() {
        return image;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate_deb(Date date_deb) {
        this.date_deb = date_deb;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Evenements{" + "id=" + id + ", nom=" + nom + ", description=" + description + ", date_deb=" + date_deb + ", date_fin=" + date_fin + ", image=" + image + '}';
    }
    
   

  

 
    
  
    
    
}
