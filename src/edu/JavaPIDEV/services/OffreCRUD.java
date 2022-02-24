/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.JavaPIDEV.services;

import edu.JavaPIDEV.entities.Equipement;
import edu.JavaPIDEV.entities.Offre;
import edu.JavaPIDEV.utils.MyConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
               pst.setString(2, o.getDate_debutpromo());
               pst.setString(3, o.getDate_finpromo());
               pst.setString(4, o.getPourcentagepromo());
             
               
            pst.executeUpdate();
            System.out.println("promo ajoute!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

      }
      
      /*
      public List <Offre> affichagepromotion(){
       List<Offre>myList=new ArrayList();
        try {
            String requete ="SELECT Id_promotion ,Date_debutpromo,Date_finpromo,Pourcentagepromo + equipement.prix_equipement , offre.prix_equipement  FROM equipement,offre  WHERE offre.ref_equipement= equipement.ref_equipement ";
            Statement st = new MyConnection().getCnx().createStatement();
           ResultSet res =st.executeQuery(requete);
          while (res.next()){
          Offre O = new Offre();
          O.setId_promotion(res.getInt(1));
          O.setRef_equipement(res.getInt(2));
          O.setDate_debutpromo(res.getString("Date_debutpromo")); //getint :tjib les donne li mawjoudine f column
          O.setDate_finpromo(res.getString("Date_finpromo"));
          O.setPourcentagepromo(res.getString("Pourcentagepromo"));
           O.setPrix_equipement(res.getDouble("prix_equipement"));
          //O.setPrix_aprespromotion(res.getDouble("Prix_aprespromotion"));
     
          myList.add(O);
                  }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
   return myList;
   }
      */
      
      
      
      
      
      
      
      
      
      
      
      
      
 
      
      
 public void  affichagepromotion(){
      
       
       int prix;
        try {
            
           // String requete ="SELECT * FROM Offre  ";
             String requete ="SELECT a.prix_equipement as 'Prix' ,o.id_promotion,o.ref_equipement,o.date_debutpromo,o.date_finpromo,o.pourcentagepromo FROM equipement a ,offre o where a.ref_equipement=o.ref_equipement ";
            
            

            Statement st = new MyConnection().getCnx().createStatement();
           ResultSet res =st.executeQuery(requete);
          while (res.next()){

          prix = res.getInt(1);
          int IDpromotion = res.getInt("id_promotion");
          int Ref_equipement = res.getInt("ref_equipement");
          String Date_debutpromo = res.getString("Date_debutpromo"); //getint :tjib les donne li mawjoudine f column
          String Date_finpromo = res.getString("Date_finpromo");
          String Pourcentagepromo = res.getString("Pourcentagepromo");
          
          System.out.println("Prix : "+prix+"\n"+"ID promotion:"+IDpromotion+"\n"+"reference equipement: "+Ref_equipement+"\n"+"Date debut promotion : "+Date_debutpromo+"\n");
          
          
          
         // Equipement a = new Equipement();
     //   a.getprix_equipement(res.getInt(4));
         // a.setPrix_equipement(res.getInt("prix_equipement"));
         
         
         
                  }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
   
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
       public void promo(int Prix_equipement,String pourcentagepromo){
       
        try {
            String requete =" SELECT a.Prix_equipement * o.pourcentagepromo FROM equipement a,Offre o where a.ref_equipement=o.ref_equipement ";
            Statement st = new MyConnection().getCnx().createStatement();
           ResultSet res =st.executeQuery(requete);
          while (res.next()){
          Offre O = new Offre();
          Equipement a = new Equipement();
         a.setPrix_equipement(res.getInt("Prix_equipement"));
          O.setPourcentagepromo(res.getString("Pourcentagepromo"));
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