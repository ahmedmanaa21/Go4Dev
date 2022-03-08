/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.JavaPIDEV.entities;

import java.sql.Date;
import java.sql.Timestamp;
import javafx.scene.control.TextField;

/**
 *
 * @author dell
 */
public class Offre extends Equipement{
   private int Id_promotion;
  private int ref_equipement ;
  private Date date_debutpromo ;
  private Date date_finpromo;
  private String Pourcentagepromo;
  
 ;
  
   public Offre(){
   super();
   }

    public Offre( int ref_equipement, Date date_debutpromo, Date date_finpromo, String Pourcentagepromo) {
       // this.Id_promotion = Id_promotion;
        this.ref_equipement = ref_equipement;
        this.date_debutpromo = date_debutpromo;
        this.date_finpromo = date_finpromo;
        this.Pourcentagepromo = Pourcentagepromo;
   
    }

    public Offre(int Id_promotion, int ref_equipement, Date date_debutpromo, Date date_finpromo, String Pourcentagepromo, Double Prix_equipement,String Nom_equipement) {
        super(Prix_equipement,Nom_equipement);
        this.Id_promotion = Id_promotion;
        this.ref_equipement = ref_equipement;
        this.date_debutpromo = date_debutpromo;
        this.date_finpromo = date_finpromo;
        this.Pourcentagepromo = Pourcentagepromo;
    }

   
     
     
      public Offre(int Id_promotion ,int ref_equipement, Date date_debutpromo, Date date_finpromo, String Pourcentagepromo) {
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

    public Date getDate_debutpromo() {
        return date_debutpromo;
    }

    public void setDate_debutpromo(Date date_debutpromo) {
        this.date_debutpromo = date_debutpromo;
    }

    public Date getDate_finpromo() {
        return date_finpromo;
    }

    public void setDate_finpromo(Date date_finpromo) {
        this.date_finpromo = date_finpromo;
    }

    @Override
    public String toString() {
        return "Offre{" + "Id_promotion=" + Id_promotion + ", ref_equipement=" + ref_equipement + ", date_debutpromo=" + date_debutpromo + ", date_finpromo=" + date_finpromo + ", Pourcentagepromo=" + Pourcentagepromo + '}'+super.toString();
    }

   

    public int getId_promotion() {
        return Id_promotion;
    }

    public void setId_promotion(int Id_promotion) {
        this.Id_promotion = Id_promotion;
    }

   

    
}

