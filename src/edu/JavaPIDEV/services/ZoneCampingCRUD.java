/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.JavaPIDEV.services;

import edu.JavaPIDEV.entities.Evenements;
import edu.JavaPIDEV.entities.ZoneCamping;
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
public class ZoneCampingCRUD {
        
    
        public void ajouterZoneCamping(ZoneCamping z) {
        
        try {
            String requete="INSERT INTO zonecamping (region,delegation,nom_centre,latitude,longitude,description) VALUES(?,?,?,?,?,?)";
            PreparedStatement pst=new MyConnection().getCnx().prepareStatement(requete);
            pst.setString(1, z.getRegion());
            pst.setString(2, z.getDelegation());
            pst.setString(3, z.getNom_centre());
            pst.setDouble(4, z.getLatitude());
            pst.setDouble(5, z.getLongitude());
            pst.setString(6, z.getDescription());
            
            pst.executeUpdate();
            System.out.println("la zone de camping est ajoutée !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            Logger.getLogger(EvenementsCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
        
        public List<ZoneCamping> affichageZoneCamping() {
            
        List<ZoneCamping> myList = new ArrayList();
        try {
            String requete = "SELECT * FROM zonecamping";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet res = st.executeQuery(requete);

            while (res.next()) {
                ZoneCamping z = new ZoneCamping();
                z.setId(res.getInt(1));
                z.setRegion(res.getString(2));
                z.setDelegation(res.getString(3));
                z.setNom_centre(res.getString(4));
                z.setLatitude(res.getDouble(5));
                z.setLongitude(res.getDouble(6));
                z.setDescription(res.getString(7));

                myList.add(z);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }
        
        public void modifierZoneCampipng(ZoneCamping z) {
        try {
            String requete = "UPDATE zonecamping SET region='" + z.getRegion() + "',delegation='" + z.getDelegation()+ "',nom_centre='" + z.getNom_centre() +"',latitude='" + z.getLatitude()+"',longitude='" + z.getLongitude()+"',description='" + z.getDescription() + "' WHERE id=" + z.getId();
            Statement st = new MyConnection().getCnx().createStatement();
            st.executeUpdate(requete);
            System.out.println("Zone de camping modifée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
        
        
        public void supprimerZoneCamping(ZoneCamping z) {
        try {
            String requete = "DELETE FROM zonecamping where id=" + z.getId();
            Statement st = new MyConnection().getCnx().createStatement();
            st.executeUpdate(requete);
            System.out.println("Zone de camping supprimée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
        
        
            public void statistique(){
  
     try {
            
            String requete="select count(*) , region from zonecamping where region ='Bizerte' "; 
            Statement st = new MyConnection().getCnx().createStatement();
             ResultSet res=st.executeQuery(requete);
             while (res.next()){
                 System.out.println("Region : " +res.getString(2)+" Nombre de zone : "+res.getInt(1));
            
             }
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
}
        
        
    
}
