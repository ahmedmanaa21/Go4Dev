/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.connexion3a16.services;

import edu.connexion3a16.entities.Client;
import edu.connexion3a16.utils.MyConnection;
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
//    public void ajouterClient(){
//        try {
//           String requete= "INSERT INTO client(cin,nomPrenom,surnom,mdp,email,adresse) VALUES(12345678,'ahmed manaa','ahmed12','ahmed123','ahmed.manaa@esprt.tn','hay ghazela')";
//           Statement st = new MyConnection().getCnx().createStatement();
//           st.executeUpdate(requete);
//           System.out.println("Client ajoutee ");
//        } catch (SQLException ex) {
//            System.err.println(ex.getMessage());
//        }
//    }
    public void ajouterClient(Client C){
    
     try {
            String requete = "INSERT INTO client (cin,nomPrenom,surnom,mdp,email,adresse) VALUES (?,?,?,?,?,?)";
            PreparedStatement pst =MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1, C.getCin());
            pst.setString(2, C.getNomPrenom());
            pst.setString(3, C.getSurnom());
            pst.setString(4, C.getMdp());
            pst.setString(5, C.getEmail());
            pst.setString(6, C.getAdresse());
            
            pst.executeUpdate();
            System.out.println("Client ajoutée!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
       public List<Client> affichageClient(){
       List<Client>myList=new ArrayList();
        try {
           String requete ="SELECT * FROM client";
           Statement st = new MyConnection().getCnx().createStatement();
           ResultSet res =st.executeQuery(requete);
           
          while (res.next()){
          Client C = new Client();
          C.setCin(res.getInt(1));
          C.setNomPrenom(res.getString(2)); 
          C.setSurnom(res.getString(3));
          C.setMdp(res.getString(4));
          C.setEmail(res.getString(5));
          C.setAdresse(res.getString(6));
          myList.add(C);
                  }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
   return myList;
   
   
   }   
   
    public void modifierClient(Client C) {
        try {
            String req = "UPDATE client SET nomPrenom='" + C.getNomPrenom() + "', surnom='" + C.getSurnom() +"', mdp='" + C.getMdp() + "', email='" + C.getEmail() + "', adresse='" + C.getAdresse() + "' WHERE cin=" + C.getCin();
            Statement st = new MyConnection().getCnx().createStatement();
            st.executeUpdate(req);
            System.out.println("Client modifée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    
     public void supprimerClient(int Cin) {
        try {
            String req = "DELETE FROM client where cin=" + Cin ;
            Statement st = new MyConnection().getCnx().createStatement();
            st.executeUpdate(req);
            System.out.println("Client supprimée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
  
    
}