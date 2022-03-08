/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.JavaPIDEV.services;

import edu.JavaPIDEV.entities.Commande;
import edu.JavaPIDEV.utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 21627
 */
public class CommandeCRUD {
    Connection cnx;

    public CommandeCRUD() {
        cnx = MyConnection.getInstance().getCnx();
    }
    
     public void ajouterCommande(Commande c) {
        try {
            String requete = "INSERT INTO commande   VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement pst =MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1, c.getId_cmd());
            pst.setString(2, c.getNom());
            pst.setString(3, c.getPrenom());
            pst.setInt(4, c.getNum_tel());
            pst.setInt(5, c.getCodepostal());
            pst.setString(6, c.getEtat());
            pst.setString(7, c.getAdressmail());
            pst.setString(8, c.getMode_paiment());
            
            
            pst.executeUpdate();
            System.out.println("Votre commande est ajoutée!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
     }
      public List<Commande> affichageCommandes() {
        List<Commande> myList = new ArrayList();
        try {
            String requete = "SELECT * FROM commande";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet res = st.executeQuery(requete);

            while (res.next()) {
                Commande c = new Commande();
                c.setId_cmd(res.getInt(1));
                c.setNom(res.getString(2));
                c.setPrenom(res.getString(3));
                c.setNum_tel(res.getInt(4));
                c.setMode_paiment(res.getString(5));
                c.setCodepostal(res.getInt(6));
                c.setEtat(res.getString(7));
                c.setAdressmail(res.getString(8));
                
                
                  

                myList.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;}
        
         public void supprimerCommande(Commande c) {
        try {
            String req = "DELETE FROM Commande where id_cmd=" + c.getId_cmd();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Commande supprimée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
        
       public void modifierCommande(Commande c) {
        try {
            String req = "UPDATE Commande SET nom='" + c.getNom() + "'prenom='"+c.getPrenom()+"'num_tel'"+c.getNum_tel() + "', Codepostal='" + c.getCodepostal() +"'AdressMail='"+c.getAdressmail()+ "', Etat='" + c.getEtat() + "', mode_paiment='" + c.getMode_paiment() + "' WHERE id_cmd=" + c.getId_cmd();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
           System.out.println("La commande est modifée !");
        } catch (SQLException ex) {
          System.out.println(ex.getMessage());
        }
            
         
    }
       
      
  }


     

