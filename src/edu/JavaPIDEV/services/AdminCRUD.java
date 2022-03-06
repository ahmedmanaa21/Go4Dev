/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.JavaPIDEV.services;

import edu.JavaPIDEV.entities.Admin;
import edu.JavaPIDEV.utils.MyConnection;
import java.security.MessageDigest;
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
    Connection cnx;
    
    public AdminCRUD(){
        cnx = MyConnection.getInstance().getCnx();
    }

    public void ajouterAdmin(Admin A) {
            try {
                String requete = "INSERT INTO admin (nom,prenom,email,mdp,numtel) VALUES (?,?,?,?,?)";
                PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
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
    

    public List<Admin> affichageAdmin() {
        List<Admin> myList = new ArrayList();
        try {
            String requete = "SELECT * FROM admin";
            Statement st = cnx.createStatement();
            ResultSet res = st.executeQuery(requete);

            while (res.next()) {
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
            String req = "UPDATE admin SET nom='" + E.getNom() + "', prenom='" + E.getPrenom() + "', email='" + E.getEmail() + "', mdp='" + E.getMdp() + "', numtel='" + E.getNumtel() + "' WHERE id=" + E.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Admin modifée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public boolean updateAdmin(String nom, String prenom, String email, String mdp, int num, int id) {
        String requete = "UPDATE admin SET  nom= ? , prenom = ? , email = ? , mdp = ? , numTel = ?  where id = ? ";
        try {
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
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Admin supprimée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public String cryptPassword(String pass) {
        byte[] uniqueKey = pass.getBytes();
        byte[] hash = null;
        try {
            hash = MessageDigest.getInstance("MD5").digest(uniqueKey);
        } catch (Exception e) { // Il ne doit jamais avoir d'erreurs ici
            e.printStackTrace();
        }
        StringBuffer hashString = new StringBuffer();
        for (int i = 0; i < hash.length; ++i) {
            String hex = Integer.toHexString(hash[i]);
            if (hex.length() == 1) {
                hashString.append('0');
                hashString.append(hex.charAt(hex.length() - 1));
            } else {
                hashString.append(hex.substring(hex.length() - 2));
            }
        }
        return hashString.toString();
    }

    public Boolean VerifyUserByEmail(String email) throws SQLException {
        Admin u = new Admin();
        //Boolean found = false;  Statement stm = conn.createStatement();
        Statement stm = cnx.createStatement();

        String query = "select * from admin where email = '" + email + "'";
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
        String query = "UPDATE admin SET mdp= '"+mdp+"' WHERE email='"+email+"'";
        stm.executeUpdate(query); 
        
    }


}
