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
    private String date_deb ; 
    private String date_fin ; 

    public Evenements() {
    }

    public Evenements(String nom, String description, String date_deb, String date_fin) {
        this.nom = nom;
        this.description = description;
        this.date_deb = date_deb;
        this.date_fin = date_fin;
    }

    public Evenements(int id, String nom, String description, String date_deb, String date_fin) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.date_deb = date_deb;
        this.date_fin = date_fin;
    }

    public Evenements(int id) {
        this.id = id;
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

    public String getDate_deb() {
        return date_deb;
    }

    public String getDate_fin() {
        return date_fin;
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

    public void setDate_deb(String date_deb) {
        this.date_deb = date_deb;
    }

    public void setDate_fin(String date_fin) {
        this.date_fin = date_fin;
    }

    @Override
    public String toString() {
        return "Evenements{" + "id=" + id + ", nom=" + nom + ", description=" + description + ", date_deb=" + date_deb + ", date_fin=" + date_fin + '}';
    }

 
    
  
    
    
}
