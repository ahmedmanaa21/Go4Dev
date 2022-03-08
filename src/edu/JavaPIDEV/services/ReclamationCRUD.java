/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.JavaPIDEV.services;

import edu.JavaPIDEV.entities.Reclamation;
import edu.JavaPIDEV.utils.MyConnection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author Jasser BOUKRAYA
 */
public class ReclamationCRUD {
    
    public void ajouterReclamation(Reclamation r){
    
     try {
            String requete = "INSERT INTO reclamation (id_rec,type_rec,description_rec,date_rec,cin,screenshot,email) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement pst =MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setShort(1, (short) r.getId_rec());
            pst.setString(2, r.getType_rec());
            pst.setString(3, r.getDescription_rec());
            pst.setDate(4,r.getDate_rec());
            pst.setInt(5, (short) r.getCin());
            pst.setString(6, r.getScreenshot());
            pst.setString(7, r.getMail());

           
            

            pst.executeUpdate();
            System.out.println("Votre reclamation est ajoutée!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    
    }
    
    public List<Reclamation> affichageReclamation() {
        List<Reclamation> myList = new ArrayList();
        try {
            String requete = "SELECT id_rec, type_rec, description_rec, date_rec, cin, screenshot, email  FROM reclamation ";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet res = st.executeQuery(requete);

            while (res.next()) {
                Reclamation r = new Reclamation();
                r.setId_rec(res.getInt(1));
                r.setType_rec(res.getString(2));
                r.setDescription_rec(res.getString(3));
                r.setDate_rec(res.getDate(4));
                r.setCin(res.getInt(5));
                r.setScreenshot("file:J:\\xampp\\htdocs\\images\\"+res.getString("screenshot"));
                r.setMail(res.getString("email"));

               

                myList.add(r);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }
    
    public void modifierReclamation(Reclamation r) {
        try {
            String req = "UPDATE reclamation SET type_rec='" + r.getType_rec() +"',description_rec='" + r.getDescription_rec() + "',date_rec='" + r.getDate_rec()+ "',screenshot='" + r.getScreenshot()+"',email='" + r.getMail()+ "' WHERE id_rec=" + r.getId_rec();
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            st.executeUpdate(req);
            System.out.println("Réclamation modifée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public boolean modifierRec(int Id_rec, String Type_rec, String description_rec, Date date_rec,String Screenshot,String email) {
        String req = "UPDATE reclamation SET type_rec='" + Type_rec +"',description_rec='" + description_rec + "',date_rec='" +date_rec+ "',screenshot='" +Screenshot+"',email='" +email+ "' WHERE id_rec=" +Id_rec;
                    
        try {
            Statement st = MyConnection.getInstance().getCnx().createStatement();
               st.executeUpdate(req);
            System.out.println("REclamation est bien modifiée");
                return true;
            

        } catch (SQLException ex) {
            System.out.println("error en modification de reclamation");
            System.out.println(ex.getMessage());
            
        }
        return false;
 
    }
    
    
       public void supprimerReclamation(Reclamation r) {
        try {
            String req = "DELETE FROM reclamation where id_rec=" + r.getId_rec();
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            st.executeUpdate(req);
            System.out.println("Réclamation supprimée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
       
      public void archiverReclamation() {
        List<Reclamation> listeReclamation = new ArrayList<Reclamation>();
        listeReclamation = affichageReclamation();

        Date date;
        date = new Date();

        Timestamp currentDate = new Timestamp(date.getTime());
       
       
        //archivage aprés 7 jours de la création du reclamation

        listeReclamation.stream()
                .filter(r -> r.getDate_rec().getDate() - currentDate.getDate() > 7)
                .forEach((P) -> {
                    String requete = null;
                    try {
                        requete = "UPDATE reclamation SET "
                                + "archived = 1 ";

                        Statement st = MyConnection.getInstance().getCnx().createStatement();
                        st.executeUpdate(requete);

                    } catch (SQLException e) {
                        System.out.println("erreur d'archivage reclamation");
                    }
                });
    }
    
 public void SupprimerReclamationarchiver() {
         List<Reclamation> listeReclamation = new ArrayList<Reclamation>();
        listeReclamation = affichageReclamation();
        Date date;
        date = new Date();

        Timestamp currentDate = new Timestamp(date.getTime());
       
        listeReclamation.stream()
                //si depasser akther men aam
                 .filter(r -> r.getDate_rec().getDate() - currentDate.getDate() > 7)
                .forEach((P) -> {
                   
                    try {
                       
                        String requete = "DELETE FROM reclamation WHERE archived = 1 ";
                       
                       Statement st = MyConnection.getInstance().getCnx().createStatement();
                        st.executeUpdate(requete);
                       
                       

                    } catch (SQLException e) {
                        System.out.println("erreur");
                    }
                });
    }

      
}
