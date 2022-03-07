/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.JavaPIDEV.entities;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;

/**
 *
 * @author MSI
 */
public class Client {
    
    private int cin;
    private String nomPrenom;
    private String surnom;
    private String sexe;

    
    private String mdp;
    private String email;
    private Date dateNaissance;
    private String adresse;
    private String image;

    public Client() {
    }
    
    public Client(int cin, String nomPrenom, String surnom,String sexe,String email ,String mdp, Date dateNaissance, String adresse,String image) {
        this.cin = cin;
        this.nomPrenom = nomPrenom;
        this.surnom = surnom;
        this.sexe = sexe;
        this.email = email;        
        this.mdp = mdp;
        this.dateNaissance = dateNaissance;
        this.adresse = adresse;
        this.image = image;
    }
    
    public Client(String nomPrenom, String surnom,String sexe, String email, String mdp, Date dateNaissance, String adresse,String image) {
        this.nomPrenom = nomPrenom;
        this.surnom = surnom;
        this.sexe = sexe;
        this.email = email;        
        this.mdp = mdp;
        this.dateNaissance = dateNaissance;
        this.adresse = adresse;
        this.image = image;
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
    
    public String getSexe() {
        return sexe;
    }
    
    public String getEmail() {
        return email;
    }

    public String getMdp() {
        return mdp;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public String getAdresse() {
        return adresse;
    }
    
    public String getImage() {
        return image;
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
    
    public void setSexe(String sexe) {
        this.sexe = sexe;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
    
    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Client{" + "cin=" + cin + ", nomPrenom=" + nomPrenom + ", surnom=" + surnom +", sexe=" + sexe + ", mdp=" + mdp + ", email=" + email + ", dateNaissance=" + dateNaissance + ", adresse=" + adresse + ", image=" + image + '}';
    }
   
    
//    public void CalculAge(){
//        String[] arrayString = this.dateNaissance.split("/");
//        int  arrayInt [] = {0,0,0};
//        for(int i=0 ; i<arrayString.length;i++){
//            arrayInt[i] = Integer.parseInt(arrayString[i]);
//        }
//        LocalDate date = LocalDate.of(arrayInt[0], arrayInt[1], arrayInt[2]);
//        LocalDate now = LocalDate.now();
//        Period period = Period.between(date, now);
//        System.out.println("année: "+period.getYears()+" and mois: "+period.getMonths());
//    }
    
}
