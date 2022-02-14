/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.connexion3a16.services;

import edu.connexion3a16.entities.Personne;
import edu.connexion3a16.utils.MyConnection;
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
 * @author karra
 */
public class PersonneCRUD {

    public void ajouterPersonne() {
        try {
            String requete = "INSERT INTO personne (nom,prenom) VALUES (' nom ',' prenom ')";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            st.executeUpdate(requete);
            System.out.println("Personne ajoutée!");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void ajouterPersonne2(Personne p) {
        try {
            String requete = "INSERT INTO personne (nom,prenom) VALUES (?,?)";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setString(1, p.getNom());
            pst.setString(2, p.getPrenom());

            pst.executeUpdate();
            System.out.println("Votre personne est ajoutée!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void SupprimerPersonne(Personne p) {
        try {
            String nom = "nom";
            String requete = "DELETE FROM personne WHERE nom = " + nom + ";";
            PreparedStatement st = MyConnection.getInstance().getCnx().prepareStatement(requete);
            st.executeUpdate();
            System.out.println("Votre personne est Supprimé!");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public List<Personne> affichagePersonnes() {
        List<Personne> myList = new ArrayList();
        try {
            String requete = "SELECT * FROM personne";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet res = st.executeQuery(requete);

            while (res.next()) {
                Personne p = new Personne();
                p.setId(res.getInt(1));
                p.setNom(res.getString("nom"));
                p.setPrenom(res.getString("prenom"));

                myList.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }
}
