/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.JavaPIDEV.services;

import edu.JavaPIDEV.entities.Panier;
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
public class PanierCRUD {
  
    
    
    
    
    public void ajouterPanier(Panier p){
    
     try {
            String requete = "INSERT INTO Panier (id_panier,ref_equipement,total_panier) VALUES (?,?,?)";
            PreparedStatement pst = new MyConnection().getCnx().prepareStatement(requete);
            pst.setShort(1, (short) p.getId_panier());
           pst.setShort(2, (short) p.getRef_equipement());
            pst.setShort(3, (short) p.getTotal_panier());
            pst.executeUpdate();
            System.out.println("panier ajoute!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
   public List <Panier> affichagepanier(){
       List<Panier>myList=new ArrayList();
        try {
            String requete ="SELECT * FROM Panier";
            Statement st = new MyConnection().getCnx().createStatement();
           ResultSet res =st.executeQuery(requete);
           
          while (res.next()){
          Panier P = new Panier();
          P.setId_panier(res.getInt(1));
          P.setRef_equipement(res.getInt(2));
          P.setTotal_panier(res.getInt(3));
          myList.add(P);
          
                  }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
   return myList;
   } 
 public void modifierpanier(Panier P) {
        try {
            String req = "UPDATE Panier SET Total_panier='" + P.getTotal_panier() + "' WHERE Id_panier=" + P.getId_panier();
            Statement st = new MyConnection().getCnx().createStatement();
            st.executeUpdate(req);
            System.out.println("panier modifie !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
  public void supprimerpanier(Panier P) {
        try {
            String req = "DELETE FROM Panier where Ref_equipement=" + P.getRef_equipement();
            Statement st = new MyConnection().getCnx().createStatement();
            st.executeUpdate(req);
            System.out.println("equipement supprimé!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

public void statistique(){
  
     try {
            
            String requete3="select count(*) , p.Total_panier from Panier p join Equipement e where p.Id_panier = e.ref_equipement  group by p.Total_panier";
            Statement st = new MyConnection().getCnx().createStatement();
             ResultSet rs=st.executeQuery(requete3);
             while (rs.next()){
               System.out.println("Type : " +rs.getString(3)+" eqquipement : "+rs.getInt(1));

            
             }
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
}












}
    



