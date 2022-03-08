/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.JavaPIDEV.services;


import edu.connexion3a16.entities.Panier;
import edu.JavaPIDEV.utils.MyConnection;
import java.sql.Connection;
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
 * @author 21627
 */
public class PanierCRUD {
    
    Connection cnx;

    public PanierCRUD() {
        cnx = MyConnection.getInstance().getCnx();
    }
    
    ArrayList<Panier> listeProduits = new ArrayList<>();
    private float totalPrice;
    public void ajouterPanier(Panier p) {
    try {
            String requete = "INSERT INTO panier (id_panier,ref_equipement,total_panier,nbr_equipement, prix_equipement,cin) VALUES (?,?,?,?,?,?)";
            PreparedStatement pst =MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1, p.getId_panier());
            pst.setInt(2, p.getRef_equipement());
           
            pst.setDouble(4, p.getTotal_panier());
            pst.setInt(5, p.getNbr_equipement());
            pst.setInt(6,p.getPrix_equipement());
            pst.setInt(6,p.getCin());
          
            pst.executeUpdate();
            System.out.println("Votre ligne de commande est ajoutée dans le panier!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
     }
    
    public void modifierPanier(Panier p) {
        try {
            String req = "UPDATE panier SET ref_equipement=" +p.getRef_equipement() +", total_panier="+p.getTotal_panier()+",nbr_equipement="+p.getNbr_equipement()+",prix_equipement="+p.getPrix_equipement()+" WHERE id_panier=" + p.getId_panier();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("votre panier est modifée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
     public void supprimerPanier(Panier p) {
        try {
            String req = "DELETE FROM panier where id_panier=" + p.getId_panier();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("un article est supprimée du panier !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
     

 public List<Panier> affichagePanier() {
        List<Panier> myList = new ArrayList();
        try {
            String requete = "SELECT * FROM panier;";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet res = st.executeQuery(requete);

            while (res.next()) {
                Panier p = new Panier();
                p.setId_panier(res.getInt(1));
                p.setRef_equipement(res.getInt(2));
                
                p.setTotal_panier(res.getDouble(3));
                p.setNbr_equipement(res.getInt(4));
                p.setPrix_equipement(res.getInt(5));
                p.setCin(res.getInt(6));

                
                  myList.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
 }
 
  double consulterMontantPanier() {
        double total = 0.0;
        if (listeProduits.size() > 0) {
            for (int i = 0; i < this.listeProduits.size(); i++) {
                total = total + this.listeProduits.get(i).getPrix_equipement();
            }
        }
        return total;
    }
  
  public ObservableList<Panier> getPanier(int CIN) {
        ObservableList<Panier> myList = FXCollections.observableArrayList();
        try {
            String requete = "SELECT * FROM panier where CIN="+CIN;
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet res = st.executeQuery(requete);

            while (res.next()) {
                Panier p = new Panier();
                p.setId_panier(res.getInt(1));
                p.setRef_equipement(res.getInt(2));
                
                p.setTotal_panier(res.getDouble(3));
                p.setNbr_equipement(res.getInt(4));
                p.setPrix_equipement(res.getInt(5));
                p.setCin(res.getInt(6));

                
                  myList.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
 }
  
  public double sommePanier(int cin)
  {
      double prix_total=0;
      String req="Select sum(total_panier) from panier where cin="+cin;
      
      
      try {
      Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet res = st.executeQuery(req);

            while (res.next()) {
                    prix_total=res.getDouble(1);
            }
            }
         catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

  return prix_total;
  }
 
}
