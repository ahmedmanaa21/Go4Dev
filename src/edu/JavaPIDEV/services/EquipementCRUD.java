/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.JavaPIDEV.services;

import edu.JavaPIDEV.entities.Equipement;
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
public class EquipementCRUD {
    
/*
    public void ajouterequipement(Equipement E){
    
     try {
             String requete= "INSERT INTO equipement(nom_equipement,image,prix_equipement,description_equipement) VALUES (?,?,?,?,?)";
            PreparedStatement pst =  new MyConnection().getCnx().prepareStatement(requete);
            pst.setString(2, E.getNom_equipement());
            pst.setString(3, E.getImage());
             pst.setShort(4, (short) E.getPrix_equipement());
            pst.setString(5, E.getDescription_equipement());
            pst.executeUpdate();
            System.out.println("Votre equipement est ajoutée!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    */
    public void ajouterequipement(){
        try {
            String requete= "INSERT INTO equipement(nom_equipement,image,prix_equipement,description_equipement) VALUES('tt','image',199,'table pour losir')";
           Statement st = new MyConnection().getCnx().createStatement();
           st.executeUpdate(requete);
           System.out.println("equipement ajoutee ");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
     public List <Equipement> affichageEquipement(){
       List<Equipement>myList=new ArrayList();
        try {
            String requete ="SELECT * FROM Equipement";
            Statement st = new MyConnection().getCnx().createStatement();
           ResultSet res =st.executeQuery(requete);
           
          while (res.next()){
          Equipement E = new Equipement();
          E.setRef_equipement(res.getInt(1));
          E.setNom_equipement(res.getString("Nom_equipement")); //getint :tjib les donne li mawjoudine f column
          E.setImage(res.getString("image"));
          E.setPrix_equipement(res.getInt(4));
          E.setDescription_equipement(res.getString("description_equipement"));
          myList.add(E);
          
                  }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
   return myList;
   
   
   }   
   
    public void modifierequipement(Equipement E) {
        try {
            String req = "UPDATE Equipement SET Nom_equipement='" + E.getNom_equipement() + "',Image='" + E.getImage() +"',Prix_equipement='" + E.getPrix_equipement() + "',Description_equipement='" + E.getDescription_equipement() + "' WHERE Ref_equipement=" + E.getRef_equipement();
            Statement st = new MyConnection().getCnx().createStatement();
            st.executeUpdate(req);
            System.out.println("Equipement modifée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    
     public void supprimerequipement(Equipement E) {
        try {
            String req = "DELETE FROM Equipement where Ref_equipement=" + E.getRef_equipement();
            Statement st = new MyConnection().getCnx().createStatement();
            st.executeUpdate(req);
            System.out.println("Equipement supprimée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    /*
    public void rechercherparnom (String Nom_equipement){
    try {
    String req = "SELECT * FROM Equipement where Nom_equipement ='"+Nom_equipement+"'";
    Statement st = new MyConnection().getCnx().createStatement();
    ResultSet res = st.executeQuery(req);
   res.last() ;
   int nbrRow = res.getRow() ;
   if (nbrRow!=0){
   System.out.println("equipement trouve");
   }else {
   System.out.println("equipement non trouve ");}
    } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
            
            
            
            }*/
}
    
    
    
    
    
    
    
    
    
    
    
    
    
    

