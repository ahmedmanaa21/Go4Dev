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
    
    public void ajouterReclamation(Reclamation r,java.sql.Timestamp dateDispo){
    
     try {
            String requete = "INSERT INTO reclamation (id_rec,type_rec,description_rec,date_rec,cin,screenshot,status) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement pst =MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setShort(1, (short) r.getId_rec());
            pst.setString(2, r.getType_rec());
            pst.setString(3, r.getDescription_rec());
            pst.setDate(4,r.getDate_rec());
            pst.setInt(5, (short) r.getCin());
            pst.setString(6, r.getScreenshot());
            pst.setString(7, r.getStatus());
            

            pst.executeUpdate();
            System.out.println("Votre reclamation est ajoutée!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    
    }
    
    
    
    public void ajouterReclamation3(Reclamation R, java.sql.Timestamp dateDispo) {
        // java.util.Date date_util = new java.util.Date();
        // java.sql.Date date_sql = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        java.sql.Timestamp date_sql = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());

        try { // LES var declaré dans le try ne sont vue que dans le try, et inversement pour en dhors du try
            if (R.getRef_compte() != 0 && R.getId_author() != 0) {
                String requete = "INSERT INTO reclamation(ref_compte,description,objet,date_creation,date_traitement,status,priorite,screenshot,nom,prenom,email,numero_mobile,date_disponibilite,id_author)  VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete); // import java.sql.Statement
                pst.setInt(1, R.getRef_compte());
                pst.setString(2, R.getDescription());
                pst.setString(3, R.getObjet());
                pst.setObject(4, date_sql);
                pst.setDate(5, R.getDate_traitement());
                pst.setString(6, R.getStatus());
                pst.setInt(7, R.getPriorite());
                pst.setString(8, R.getScreenshot());
                pst.setString(9, R.getNom());
                pst.setString(10, R.getPrenom());//prenom
                pst.setString(11, R.getEmail());//email
                pst.setInt(12, R.getNumero_mobile());
                pst.setObject(13, dateDispo);
//            pst.setDate(13, R.getDate_disponibilite());
                pst.setInt(14, R.getId_author());
                pst.executeUpdate();
            } else  {
                String requete = "INSERT INTO reclamation(ref_compte,description,objet,date_creation,date_traitement,status,priorite,screenshot,nom,prenom,email,numero_mobile,date_disponibilite)  VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete); // import java.sql.Statement
                pst.setInt(1, R.getRef_compte());
                pst.setString(2, R.getDescription());
                pst.setString(3, R.getObjet());
                pst.setObject(4, date_sql);
                pst.setDate(5, R.getDate_traitement());
                pst.setString(6, R.getStatus());
                pst.setInt(7, R.getPriorite());
                pst.setString(8, R.getScreenshot());
                pst.setString(9, R.getNom());
                pst.setString(10, R.getPrenom());//prenom
                pst.setString(11, R.getEmail());//email
                pst.setInt(12, R.getNumero_mobile());
                pst.setObject(13, dateDispo);
//            pst.setDate(13, R.getDate_disponibilite());
                pst.executeUpdate();

            System.out.println("Reclamation Ajouté");

        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
            System.err.println("SQLSTATE: " + ex.getSQLState());
            System.err.println("VnedorError: " + ex.getErrorCode());

        }

    }}
    public List<Reclamation> affichageReclamation() {
        List<Reclamation> myList = new ArrayList();
        try {
            String requete = "SELECT * FROM reclamation";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet res = st.executeQuery(requete);

            while (res.next()) {
                Reclamation r = new Reclamation();
                r.setId_rec(res.getInt(1));
                r.setType_rec(res.getString(2));
                r.setDescription_rec(res.getString(3));
                r.setDate_rec(res.getDate(4));
                r.setCin(res.getInt(5));

                myList.add(r);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }
    public void modifierReclamation(Reclamation r) {
        try {
            String req = "UPDATE reclamation SET type_rec='" + r.getType_rec() +"',description_rec='" + r.getDescription_rec() + "',date_rec='" + r.getDate_rec()+ "' WHERE id_rec=" + r.getId_rec();
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            st.executeUpdate(req);
            System.out.println("Réclamation modifée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
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
                .filter(r -> r.getDate_rec().getDate() - currentDate.getDate() < 7)
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
      
}
