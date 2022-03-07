/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.JavaPIDEV.services;

import edu.JavaPIDEV.entities.Reservation;
import edu.JavaPIDEV.utils.MyConnection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jasser BOUKRAYA
 */
public class ReservationCRUD {
    
    
      public void ajouterReservation(Reservation r){
    
     try {
            String requete = "INSERT INTO reservation (id_reservation,cin,date_reservation,id_zoneCamping,nbrPersonne) VALUES (?,?,?,?,?)";
            PreparedStatement pst =MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setShort(1, (short) r.getId_res());
            pst.setShort(2, (short) r.getCin());
            pst.setDate(3,r.getDate_res());
            pst.setShort(4,(short) r.getId_zoneCamping());
            pst.setShort(5, (short) r.getNrbPersonne() );
            
                    
            pst.executeUpdate();
            System.out.println("Votre reservation est ajoutée!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    
    }
      
    public List<Reservation> affichageReservation() {
        List<Reservation> myList = new ArrayList();
        try {
            String requete = "SELECT * FROM reservation";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet res = st.executeQuery(requete);

            while (res.next()) {
                Reservation r = new Reservation();
                r.setId_res(res.getInt(1));
                r.setCin(res.getInt(2));
                r.setDate_res(res.getDate(3));
                r.setId_zoneCamping(res.getInt(4));
                r.setNrbPersonne(res.getInt(5));

                myList.add(r);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }
    public void modifierReservation(Reservation r) {
        try {
            String req = "UPDATE reservation SET date_reservation='" + r.getDate_res() +"',nbrPersonne='" + r.getNrbPersonne() +"' WHERE id_reservation=" + r.getId_res();
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            st.executeUpdate(req);
            System.out.println("Reservation modifée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public boolean modifierRes(int id_res, Date date_res,int nrbPersonne)
     {
          String req = "UPDATE reservation SET date_reservation='" + date_res +"',nbrPersonne='" + nrbPersonne +"' WHERE id_reservation=" + id_res;

          try {
            Statement st = MyConnection.getInstance().getCnx().createStatement();
               st.executeUpdate(req);
            System.out.println("Réservation est bien modifiée");
                return true;
            

        } catch (SQLException ex) {
            System.out.println("error en modification de Réservation");
            System.out.println(ex.getMessage());
            
        }
        return false;
     
     }
    
    
    
       public void supprimerReservation(Reservation r) {
        try {
            String req = "DELETE FROM reservation where id_reservation=" + r.getId_res();
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            st.executeUpdate(req);
            System.out.println("Reservation supprimée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
        public void statistique(){
  
     try {
            
            String requete3="select count(*) , nom_centre from zonecamping z join reservation r where r.id_zoneCamping = z.id_zoneCamping group by nom_centre";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
             ResultSet rs=st.executeQuery(requete3);
             while (rs.next()){
                 System.out.println("nom_centre : " +rs.getString(2)+" Nombre de Reservation : "+rs.getInt(1));
            
             }
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
}
    
}
