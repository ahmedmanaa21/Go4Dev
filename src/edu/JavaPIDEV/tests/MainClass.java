/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.JavaPIDEV.tests;

import edu.JavaPIDEV.entities.Equipement;
import edu.JavaPIDEV.entities.Mailing;
import edu.JavaPIDEV.entities.Offre;

import edu.JavaPIDEV.services.EquipementCRUD;
import edu.JavaPIDEV.services.OffreCRUD;

import edu.JavaPIDEV.utils.MyConnection;
import java.util.List;

/**
 *
 * @author dell
 */

public class MainClass {
    public static void main(String[] args) throws Exception {
        
      MyConnection mc= new MyConnection(); 
     EquipementCRUD equ = new EquipementCRUD();
      
    //equ.ajouterequipement(new Equipement("maya","image",22.2,"descrii"));
      //List<Equipement>listEquipement=equ.affichageEquipement();
      //for (Equipement equi : listEquipement){
        //  System.out.println(equi);
     // }
      
      //equ.rechercherparnom("rass");
     //equ.modifierequipement(new Equipement (28,"cammm","image",50, "loisir bbbb")); 
    
      
      
      OffreCRUD off = new OffreCRUD();
   //   java.util.Date AjoutDate = new java.util.Date("2000/05/31");
     //   java.sql.Date sqlDate = new java.sql.Date(AjoutDate.getTime());
   //   java.util.Date AjoutDate1 = new java.util.Date("1999/05/30");
    //    java.sql.Date sqlDate1 = new java.sql.Date(AjoutDate1.getTime());
   //   Offre o1 = new Offre(28,sqlDate,sqlDate1,"50%");
      
      //Offre o2 = new Offre(11,11,"debut","fin");
      //Offre o = new Offre(15,"nnn","zz");
    //off.ajouteroffre(o1);
      //off.affichagepromotion();
    // off.promo(50);
      
    //  for (Offre Offr : listOffre){
         System.out.println(off.affichagepromotion());
    //  off.modifieroffre(new Offre (46,28,sqlDate,sqlDate1,"50%")); 
       //off.supprimeroffre(46);
  
       
       //Mailing.sendMail("maissa.choura@esprit.tn");
    }
}
