/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.JavaPIDEV.services;

import edu.JavaPIDEV.entities.Client;
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
 * @author MSI
 */
public class ClientCRUD {

    Connection cnx;
    
    public ClientCRUD(){
        cnx = MyConnection.getInstance().getCnx();
    }

    public void ajouterClient(Client C) {

        try {
            String requete = "INSERT INTO client (cin,nomPrenom,surnom,email,mdp,dateNaissance,adresse,image) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1, C.getCin());
            pst.setString(2, C.getNomPrenom());
            pst.setString(3, C.getSurnom());
            pst.setString(4, C.getEmail());
            pst.setString(5, C.getMdp());
            pst.setDate(6, C.getDateNaissance());
            pst.setString(7, C.getAdresse());
            pst.setString(8, C.getImage());

            pst.executeUpdate();
            System.out.println("Client ajoutée!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Client> affichageClient() {
        List<Client> myList = new ArrayList();
        try {
            String requete = "SELECT * FROM client";
            Statement st = cnx.createStatement();
            ResultSet res = st.executeQuery(requete);

            while (res.next()) {
                Client C = new Client();
                C.setCin(res.getInt(1));
                C.setNomPrenom(res.getString(2));
                C.setSurnom(res.getString(3));
                C.setEmail(res.getString(4));
                C.setMdp(res.getString(5));
                C.setDateNaissance(res.getDate(6));
                C.setAdresse(res.getString(7));
                C.setImage("file:C:\\xampp\\htdocs\\images\\" + res.getString(8));
                myList.add(C);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return myList;

    }

    public void modifierClient(Client C) {
        try {
            String req = "UPDATE client SET nomPrenom='" + C.getNomPrenom() + "', surnom='" + C.getSurnom() + "', email='" + C.getEmail() + "', mdp='" + C.getMdp() + "', dateNaissance='" + C.getDateNaissance() + "', adresse='" + C.getAdresse() + "', image='" + C.getImage() + "' WHERE cin=" + C.getCin();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Client modifée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void supprimerClient(int Cin) {
        try {
            String req = "DELETE FROM client where cin=" + Cin;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Client supprimée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void statistique(String adresse) {

        try {

            String requete = "SELECT count(*) cin from client WHERE adresse ='" + adresse + "' ";
            String req = "SELECT count(adresse) FROM client WHERE adresse ='" + adresse + "'";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                System.out.println("Adresse : " + rs.getString(7) + " Nombre de Clients : " + rs.getInt(1));

            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public boolean supprimerC(Client C) throws SQLException {
        PreparedStatement pre = cnx.prepareStatement("DELETE FROM client where cin =? AND email =?");
        pre.setInt(1, C.getCin());
        pre.setString(2, C.getEmail());
        pre.executeUpdate();
        int rowsDeleted = pre.executeUpdate();
        if (rowsDeleted > 0) {
            System.out.println("A User was deleted successfully!");
        }
        return true;

    }

}
