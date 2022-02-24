/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.JavaPIDEV.tests;

import edu.JavaPIDEV.entities.Reclamation;
import edu.JavaPIDEV.entities.Reservation;
import edu.JavaPIDEV.services.ReclamationCRUD;
import edu.JavaPIDEV.services.ReservationCRUD;
import edu.JavaPIDEV.utils.MyConnection;
import java.util.List;

/**
 *
 * @author Jasser BOUKRAYA
 */
public class MainClass {
    public static void main(String[] args) {
       //MyConnection mc = new MyConnection();
       
      java.util.Date AjoutDate = new java.util.Date("10/10/2009");
java.sql.Date sqlDate = new java.sql.Date(AjoutDate.getTime());
       /////////////reclamation////////////////
        ReclamationCRUD rec = new ReclamationCRUD();
      // Reclamation r1 = new Reclamation(15,"reservation","reservation  4004!!!!",sqlDate, 99);
       // Reclamation r2 = new Reclamation(16,"financier","mes argents  404!!!!",sqlDate, 90);
       
        System.out.println("****** ajout ********");
     // rec.ajouterReclamation(r1);
       
      System.out.println("****** afficher ********");
        List<Reclamation>listReclamation=rec.affichageReclamation();
        for(Reclamation reec:listReclamation)
       {
            System.out.println(reec); 
       }
         
       System.out.println("****** modifier ********");
java.util.Date modifDate = new java.util.Date("02/02/2022");
java.sql.Date MsqlDate = new java.sql.Date(modifDate.getTime());

      rec.modifierReclamation(new Reclamation(16,"financier","mes argents  5555404!!!!",MsqlDate, 90) );
        
      rec.archiverReclamation();
//        System.out.println("****** afficher aprés modifier ********");
//        List<Reclamation>listReclamation2=rec.affichageReclamation();
//        for(Reclamation reec:listReclamation)
//        {
//            System.out.println(reec); 
//        }
        
//       System.out.println("****** supprimer ********");
//        
//    rec.supprimerReclamation(r1);
//       
//        System.out.println("****** afficher aprés supp ********");
//        List<Reclamation>listReclamation3=rec.affichageReclamation();
//        for(Reclamation reec:listReclamation)
//        {
//            System.out.println(reec); 
//        }
        /////////////////reservation///////////
        
//        ReservationCRUD RES= new ReservationCRUD();
//        Reservation rr1 = new Reservation(66,99,"2022-02-18",10,9,87);
//       RES.ajouterReservation(rr1);
//        
//         List<Reservation>listReservation=RES.affichageReservation();
//        for(Reservation reec:listReservation)
//        {
//            System.out.println(reec); 
//        }
//        
//       RES.modifierReservation(new Reservation(66,"2022-03-14",8));
//   
//       RES.supprimerReservation(new Reservation(10));
//    
//     RES.statistique();
    
    
//    MyConnection m1 =MyConnection.getInstance();
//    MyConnection m2 =MyConnection.getInstance();
//    
//        System.out.println(m1.hashCode()+"---"+m2.hashCode());
    
    }
}
