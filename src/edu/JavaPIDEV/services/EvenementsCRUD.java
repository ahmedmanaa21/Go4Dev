/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.JavaPIDEV.services;

import edu.JavaPIDEV.entities.Evenements;
import edu.JavaPIDEV.utils.MyConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author melki
 */
public class EvenementsCRUD {
    
    public void ajouterEvenements(){
        try {
            String requete="INSERT INTO evenement (nom,description,date_deb,date_fin) VALUES('Camping mahboul','Ijew aamlou jaw maana','22-02-25','22-02-27')";
            Statement st=new MyConnection().getCnx().createStatement();
            st.executeUpdate(requete); 
            System.out.println("Evénement ajouté");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
           
    }
    public void ajouterEvenements2(Evenements e) {
        
        try {
            String requete="INSERT INTO evenement (nom,description,date_deb,date_fin) VALUES(?,?,STR_TO_DATE('"+e.getDate_deb()+"','%d-%m-%Y'),STR_TO_DATE('"+e.getDate_fin()+"','%d-%m-%Y'))";
            PreparedStatement pst=new MyConnection().getCnx().prepareStatement(requete);
            pst.setString(1, e.getNom());
            pst.setString(2, e.getDescription());
//            pst.setString(3, e.getDate_deb());
//            pst.setString(4, e.getDate_fin());
            
            pst.executeUpdate();
            System.out.println("l'evenement est ajouté!");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            Logger.getLogger(EvenementsCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    
    
    public List<Evenements> affichageEvenements() {
        List<Evenements> myList = new ArrayList();
        try {
            String requete = "SELECT * FROM evenement";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet res = st.executeQuery(requete);

            while (res.next()) {
                Evenements e = new Evenements();
                e.setId(res.getInt(1));
                e.setNom(res.getString(2));
                e.setDescription(res.getString(3));
                e.setDate_deb(res.getString(4));
                e.setDate_fin(res.getString(5));

                myList.add(e);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }
    
    public void modifierEvenements(Evenements e) {
        try {
            String requete = "UPDATE evenement SET nom='" + e.getNom() +"',description='" + e.getDescription() + "',date_deb='" + e.getDate_deb()+ "',date_fin='" + e.getDate_fin() + "' WHERE id=" + e.getId();
            Statement st = new MyConnection().getCnx().createStatement();
            st.executeUpdate(requete);
            System.out.println("Evenement modifé !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void supprimerEvenements(Evenements e) {
        try {
            String requete = "DELETE FROM evenement where id=" + e.getId();
            Statement st = new MyConnection().getCnx().createStatement();
            st.executeUpdate(requete);
            System.out.println("Evenement supprimé !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    


    
}
