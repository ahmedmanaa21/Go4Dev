/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.JavaPIDEV.entities;

import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

/**
 *
 * @author 21627
 */
public class Commande {
   
  private int id_cmd;
  private String nom;
  private String prenom;
  private int num_tel;
  private int codepostal;
  private String Etat;
  private String adressmail;
  private String mode_paiment;
 
    public Commande() { }

    public Commande(int id_cmd, String nom, String prenom, int num_tel, String mode_paiment, int codepostal, String Etat, String adressmail) {
        this.id_cmd = id_cmd;
        this.nom = nom;
        this.prenom = prenom;
        this.num_tel = num_tel;
        this.codepostal = codepostal;
        this.Etat = Etat;
        this.adressmail = adressmail;
        this.mode_paiment=mode_paiment;
    }

    public Commande(String nom, String prenom, int num_tel, int codepostal, String Etat, String adressmail, String mode_paiment) {
        this.nom = nom;
        this.prenom = prenom;
        this.num_tel = num_tel;
        this.codepostal = codepostal;
        this.Etat = Etat;
        this.adressmail = adressmail;
        this.mode_paiment = mode_paiment;
    }

    public Commande(String nom, String prenom, int num_tel, String mode_paiment, int codepostal, String Etat, String adressmail) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  
    public int getId_cmd() {
        return id_cmd;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public int getNum_tel() {
        return num_tel;
    }

    public String getMode_paiment() {
        return mode_paiment;
    }

    public int getCodepostal() {
        return codepostal;
    }

    public String getEtat() {
        return Etat;
    }

    public String getAdressmail() {
        return adressmail;
    }

    public void setId_cmd(int id_cmd) {
        this.id_cmd = id_cmd;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setNum_tel(int num_tel) {
        this.num_tel = num_tel;
    }

    public void setMode_paiment(String mode_paiment) {
        this.mode_paiment = mode_paiment;
    }

    public void setCodepostal(int codepostal) {
        this.codepostal = codepostal;
    }

    public void setEtat(String Etat) {
        this.Etat = Etat;
    }

    public void setAdressmail(String adressmail) {
        this.adressmail = adressmail;
    }

    @Override
    public String toString() {
        return "Commande{" + "id_cmd=" + id_cmd + ", nom=" + nom + ", prenom=" + prenom + ", num_tel=" + num_tel + ", mode_paiment=" + mode_paiment + ", codepostal=" + codepostal + ", Etat=" + Etat + ", AdressMail=" + adressmail + '}';
    }

   
}

