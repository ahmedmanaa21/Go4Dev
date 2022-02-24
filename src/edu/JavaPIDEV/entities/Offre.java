/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.JavaPIDEV.entities;

/**
 *
 * @author dell
 */
public class Offre {
   private int Id_promotion;
  private int ref_equipement ;
  private String date_debutpromo ;
  private String date_finpromo;
  private String Pourcentagepromo;
 ;
  
   public Offre(){}

    public Offre( int ref_equipement, String date_debutpromo, String date_finpromo, String Pourcentagepromo) {
       // this.Id_promotion = Id_promotion;
        this.ref_equipement = ref_equipement;
        this.date_debutpromo = date_debutpromo;
        this.date_finpromo = date_finpromo;
        this.Pourcentagepromo = Pourcentagepromo;
   
    }

   
     
     
      public Offre(int Id_promotion ,int ref_equipement, String date_debutpromo, String date_finpromo, String Pourcentagepromo) {
        this.Id_promotion = Id_promotion;
        this.ref_equipement = ref_equipement;
        this.date_debutpromo = date_debutpromo;
        this.date_finpromo = date_finpromo;
        this.Pourcentagepromo = Pourcentagepromo;
    

    } 
     
     
     
     
     
     
     
    
     
     
     
     
     
     
     
    public String getPourcentagepromo() {
        return Pourcentagepromo;
    }


    public void setPourcentagepromo(String Pourcentagepromo) {
        this.Pourcentagepromo = Pourcentagepromo;
    }

 

  

    
    

    public int getRef_equipement() {
        return ref_equipement;
    }

    public void setRef_equipement(int ref_equipement) {
        this.ref_equipement = ref_equipement;
    }

    public String getDate_debutpromo() {
        return date_debutpromo;
    }

    public void setDate_debutpromo(String date_debutpromo) {
        this.date_debutpromo = date_debutpromo;
    }

    public String getDate_finpromo() {
        return date_finpromo;
    }

    public void setDate_finpromo(String date_finpromo) {
        this.date_finpromo = date_finpromo;
    }

    @Override
    public String toString() {
        return "Offre{" + "Id_promotion=" + Id_promotion + ", ref_equipement=" + ref_equipement + ", date_debutpromo=" + date_debutpromo + ", date_finpromo=" + date_finpromo + ", Pourcentagepromo=" + Pourcentagepromo  + '}';
    }

    public int getId_promotion() {
        return Id_promotion;
    }

    public void setId_promotion(int Id_promotion) {
        this.Id_promotion = Id_promotion;
    }

   

    
}

