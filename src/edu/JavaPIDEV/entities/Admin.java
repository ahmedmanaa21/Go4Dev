/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.JavaPIDEV.entities;

/**
 *
 * @author MSI
 */
public class Admin {

    private int id;
    private String nom;
    private String prenom;
    private String email;
    private String mdp;
    private int numtel;

    public Admin() {
    }

    public Admin(int id, String nom, String prenom, String email, String mdp, int numtel) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.mdp = mdp;
        this.numtel = numtel;
    }

    public Admin(String nom, String prenom, String email, String mdp, int numtel) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.mdp = mdp;
        this.numtel = numtel;
    }
    
    public Admin(String nom, String prenom, String email, String mdp) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.mdp = mdp;
    }
    
    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public String getMdp() {
        return mdp;
    }

    public int getNumtel() {
        return numtel;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public void setNumtel(int numtel) {
        this.numtel = numtel;
    }
    
        @Override
    public String toString() {
        return "Admin{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", mdp=" + mdp + ", numtel=" + numtel + '}';
    }
    
}
