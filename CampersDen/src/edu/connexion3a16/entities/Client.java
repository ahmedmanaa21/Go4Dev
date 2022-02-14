/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.connexion3a16.entities;

/**
 *
 * @author MSI
 */
public class Client {
    
    private int cin;
    private String nomPrenom;
    private String surnom;
    private String mdp;
    private String email;
    private String adresse;

    public Client() {
    }

    public Client(int cin, String nomPrenom, String surnom, String mdp, String email, String adresse) {
        this.cin = cin;
        this.nomPrenom = nomPrenom;
        this.surnom = surnom;
        this.mdp = mdp;
        this.email = email;
        this.adresse = adresse;
    }

    public Client(String nomPrenom, String surnom, String mdp, String email, String adresse) {
        this.nomPrenom = nomPrenom;
        this.surnom = surnom;
        this.mdp = mdp;
        this.email = email;
        this.adresse = adresse;
    }
    
    

    public int getCin() {
        return cin;
    }

    public String getNomPrenom() {
        return nomPrenom;
    }

    public String getSurnom() {
        return surnom;
    }

    public String getMdp() {
        return mdp;
    }

    public String getEmail() {
        return email;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    public void setNomPrenom(String nomPrenom) {
        this.nomPrenom = nomPrenom;
    }

    public void setSurnom(String surnom) {
        this.surnom = surnom;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    @Override
    public String toString() {
        return "Client{" + "Cin=" + cin + ", Nom & Prenom=" + nomPrenom + ", Surnom=" + surnom + ", Mot de passe=" + mdp + ", Email=" + email + ", Adresse=" + adresse + '}';
    }
    
}
