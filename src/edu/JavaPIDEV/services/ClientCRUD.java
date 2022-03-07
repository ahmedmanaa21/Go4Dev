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

    public ClientCRUD() {
        cnx = MyConnection.getInstance().getCnx();
    }

    public void ajouterClient(Client C) {

        try {
            String requete = "INSERT INTO client (cin,nomPrenom,surnom,Sexe,email,mdp,dateNaissance,adresse,image) VALUES (?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1, C.getCin());
            pst.setString(2, C.getNomPrenom());
            pst.setString(3, C.getSurnom());
            pst.setString(4, C.getSexe());
            pst.setString(5, C.getEmail());
            pst.setString(6, C.getMdp());
            pst.setDate(7, C.getDateNaissance());
            pst.setString(8, C.getAdresse());
            pst.setString(9, C.getImage());

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
                C.setSexe(res.getString(4));
                C.setEmail(res.getString(5));
                C.setMdp(res.getString(6));
                C.setDateNaissance(res.getDate(7));
                C.setAdresse(res.getString(8));
                C.setImage("file:C:\\xampp\\htdocs\\images\\" + res.getString(9));
                myList.add(C);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return myList;

    }

    public void modifierClient(Client C) {
        try {
            String req = "UPDATE client SET nomPrenom='" + C.getNomPrenom() + "', surnom='" + C.getSurnom() +"', Sexe='" + C.getSexe() + "', email='" + C.getEmail() + "', mdp='" + C.getMdp() + "', dateNaissance='" + C.getDateNaissance() + "', adresse='" + C.getAdresse() + "', image='" + C.getImage() + "' WHERE cin=" + C.getCin();
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

    public Boolean VerifyClientByEmail(String email) throws SQLException {
        Client u = new Client();
        //Boolean found = false;  Statement stm = conn.createStatement();
        Statement stm = cnx.createStatement();

        String query = "select * from client where email = '" + email + "'";
        try {
            ResultSet rst = stm.executeQuery(query);
            if (rst.next()) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("erreur" + ex.getMessage());
        }
        return false;
    }

    public void updatemdp(String email, String mdp) throws SQLException {
        Statement stm = cnx.createStatement();
        String query = "UPDATE client SET mdp= '" + mdp + "' WHERE email='" + email + "'";
        stm.executeUpdate(query);

    }

    public int countMen() throws SQLException {
        int count = 0;
        Statement stmt3 = cnx.createStatement();
        ResultSet rs3 = stmt3.executeQuery("SELECT COUNT(*) FROM client WHERE `Sexe` = \"Homme\"");
        while (rs3.next()) {
            count = rs3.getInt(1);
        }
        return count;
    }

    public int countWomen() throws SQLException {
        int count = 0;
        Statement stmt3 = cnx.createStatement();
        ResultSet rs3 = stmt3.executeQuery("SELECT COUNT(*) FROM client WHERE `Sexe` = \"Femme\"");
        while (rs3.next()) {
            count = rs3.getInt(1);
        }
        return count;
    }

}
