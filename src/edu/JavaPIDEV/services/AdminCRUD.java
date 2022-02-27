/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.JavaPIDEV.services;

import edu.JavaPIDEV.entities.Admin;
import edu.JavaPIDEV.utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;

/**
 *
 * @author MSI
 */

public class AdminCRUD {
    public void ajouterAdmin(Admin A){
        try {
            String requete = "INSERT INTO admin (nom,prenom,email,mdp,numtel) VALUES (?,?,?,?,?)";
            PreparedStatement pst =MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setString(1, A.getNom());
            pst.setString(2, A.getPrenom());
            pst.setString(3, A.getEmail());
            pst.setString(4, A.getMdp());
            pst.setInt(5, A.getNumtel());
            
            pst.executeUpdate();
            System.out.println("Admin ajoutée!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
   public List<Admin> affichageAdmin(){
       List<Admin>myList=new ArrayList();
        try {
           String requete ="SELECT * FROM admin";
           Statement st = new MyConnection().getCnx().createStatement();
           ResultSet res =st.executeQuery(requete);
           
          while (res.next()){
          Admin E = new Admin();
          E.setId(res.getInt(1));
          E.setNom(res.getString(2)); 
          E.setPrenom(res.getString(3));
          E.setEmail(res.getString(4));
          E.setMdp(res.getString(5));
          E.setNumtel(res.getInt(6));
          myList.add(E);
                  }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
   return myList;
   
   
   }   
   
    public void modifierAdmin(Admin E) {
        try {
            String req = "UPDATE admin SET nom='" + E.getNom() + "', prenom='" + E.getPrenom() +"', email='" + E.getEmail() + "', mdp='" + E.getMdp() + "', numtel='" + E.getNumtel() + "' WHERE id=" + E.getId();
            Statement st = new MyConnection().getCnx().createStatement();
            st.executeUpdate(req);
            System.out.println("Admin modifée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
        public boolean updateAdmin(String nom, String prenom, String email, String mdp, int num, int id) {
    String requete = "UPDATE admin SET  nom= ? , prenom = ? , email = ? , mdp = ? , numTel = ?  where id = ? ";
        try {
            Connection cnx = new MyConnection().getCnx();
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, nom);
            pst.setString(2, prenom);
            pst.setString(3, email);
            pst.setString(4, mdp);
            pst.setInt(5, num);
            pst.setInt(6, id);

            if (pst.executeUpdate() != 0) {
                System.out.println("Admin Modifié");
            } else {
                System.out.println("Erreur");
            }
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return false;
    }
  
    
    
    
     public void supprimerAdmin(int Id) {
        try {
            String req = "DELETE FROM admin where id=" + Id;
            Statement st = new MyConnection().getCnx().createStatement();
            st.executeUpdate(req);
            System.out.println("Admin supprimée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
    
}