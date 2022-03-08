/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.JavaPIDEV.services;

import edu.JavaPIDEV.entities.Equipement;
import edu.JavaPIDEV.entities.Offre;
import edu.JavaPIDEV.utils.MyConnection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author dell
 */
public class OffreCRUD {
    

    
    
      public void ajouteroffre(Offre o){
    
     try {
             String requete= "INSERT INTO Offre(ref_equipement,date_debutpromo,date_finpromo,Pourcentagepromo) VALUES (?,?,?,?)";
           
            PreparedStatement pst = new MyConnection().getCnx().prepareStatement(requete);
           //pst.setShort(1, (short) o.getId_promotion());
               pst.setShort(1, (short) o.getRef_equipement());
               pst.setDate(2, o.getDate_debutpromo());
               pst.setDate(3, o.getDate_finpromo());
               pst.setString(4, o.getPourcentagepromo());
             
               
            pst.executeUpdate();
            System.out.println("promo ajoute!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

      }
      
      
      
       public  ObservableList<Offre> affichagepromotion(){
      
        List<Offre>myList=new ArrayList();
       int prix;
        try {
            
          
             String requete ="SELECT a.Prix_equipement as 'Prix',a.nom_equipement as nom   ,o.id_promotion,o.ref_equipement,o.date_debutpromo,o.date_finpromo,o.pourcentagepromo FROM equipement a ,offre o where a.ref_equipement=o.ref_equipement ";
            
            

            Statement st = new MyConnection().getCnx().createStatement();
           ResultSet res =st.executeQuery(requete);
          while (res.next()){
              Offre off = new Offre();
              off.setId_promotion(res.getInt("id_promotion"));
              off.setPrix_equipement(res.getDouble("Prix"));
              off.setNom_equipement(res.getString("nom"));
              off.setRef_equipement(res.getInt("ref_equipement"));
              off.setDate_debutpromo(res.getDate("Date_debutpromo"));
            off.setDate_finpromo(res.getDate("Date_finpromo"));
            off.setPourcentagepromo(res.getString("Pourcentagepromo"));
        
          
   myList.add(off);
                  }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        ObservableList<Offre>List=FXCollections.observableList(myList);
        
   return List;
   }
       
       
       
       
       
       
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
     
  public void modifieroffre(Offre O ) {
        try {
            String req = "UPDATE Offre SET Date_debutpromo='" + O.getDate_debutpromo() + "',Date_finpromo='" + O.getDate_finpromo() +"',Pourcentagepromo='" + O.getPourcentagepromo() + "' WHERE Id_promotion=" + O.getId_promotion();
            Statement st = new MyConnection().getCnx().createStatement();
            st.executeUpdate(req);
            System.out.println("offre modifiee !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
  
     public void supprimeroffre(int id) {
        try {
            String req = "DELETE FROM Offre where Id_promotion=" + id;
            Statement st = new MyConnection().getCnx().createStatement();
            st.executeUpdate(req);
            System.out.println("offre supprimé !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
     
     
     
     /*
       public void promo(int prix_equipement){
       int total=0;
       int b=50;
        try {
            String requete =" SELECT a.prix_equipement * o.pourcentagepromo FROM equipement a,Offre o where a.ref_equipement=o.ref_equipement ";
            Statement st = new MyConnection().getCnx().createStatement();
           ResultSet res =st.executeQuery(requete);
          while (res.next()){
          Offre O = new Offre();
          Equipement a = new Equipement();
         a.setPrix_equipement(res.getInt("prix_equipement"));
          O.setPourcentagepromo(res.getString("Pourcentagepromo"));
          total=prix_equipement *b;
          System.out.println("le prix apres promo est "+total+"dinar");
                  
                  }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
   
   }
     
    */   
     
    /* 
     public void promo(){
  Offre O=new Offre();
        double HT=0,tva=0,r=0,netc=0,ttc=0;
        System.out.println("entrer N");
           int n=Offre.nextInt();
           for(int i=0;i<n;i++){
            System.out.println("Entrer prix");
            double prix=clavier.nextDouble();
            HT+=prix;}
        if(HT>100){
        r=HT*0.1; 
        netc=HT-r;
        }
        tva=netc*0.2;
        ttc=netc+tva;
        
       
    System.out.println("le montant est" +HT+"dinars");
    System.out.println("La remise 1% est"+r+"dinars");
    System.out.println("net commercial est"+netc+"dinars");
    System.out.println("TVA est"+tva+"dinars");
}

*/















}