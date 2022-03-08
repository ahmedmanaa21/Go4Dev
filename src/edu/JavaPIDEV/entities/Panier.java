/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.connexion3a16.entities;

/**
 *
 * @author 21627
 */
public class Panier {
          int id_panier;
        int  ref_equipement;
    
           double total_panier;
           int nbr_equipement;
           int prix_equipement;
           int cin;

    public Panier() {
    }

    public Panier(int id_panier, int ref_equipement,  double total_panier, int nbr_equipement,int prix_equipement,int cin) {
        this.id_panier = id_panier;
        this.ref_equipement = ref_equipement;
        this.total_panier = total_panier;
        this.nbr_equipement = nbr_equipement;
        
        this.prix_equipement=prix_equipement;
        this.cin=cin;
    }

    public int getCin() {
        return cin;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    public int getId_panier() {
        return id_panier;
    }

    public void setId_panier(int id_panier) {
        this.id_panier = id_panier;
    }

    public int getRef_equipement() {
        return ref_equipement;
    }

    public void setRef_equipement(int ref_equipement) {
        this.ref_equipement = ref_equipement;
    }

    public double getTotal_panier() {
        return total_panier;
    }

    public void setTotal_panier(double total_panier) {
        this.total_panier = total_panier;
    }

    public int getNbr_equipement() {
        return nbr_equipement;
    }

    public void setNbr_equipement(int nbr_equipement) {
        this.nbr_equipement = nbr_equipement;
    }

    public int getPrix_equipement() {
        return prix_equipement;
    }

    public void setPrix_equipement(int prix_equipement) {
        this.prix_equipement = prix_equipement;
    }

    @Override
    public String toString() {
        return "Panier{" + "id_panier=" + id_panier + ", ref_equipement=" + ref_equipement + ", total_panier=" + total_panier + ", nbr_equipement=" + nbr_equipement + ", prix_equipement=" + prix_equipement + ", cin=" + cin + '}';
    }

  
    

   

  
           
           
    
}
