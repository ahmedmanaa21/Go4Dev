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
      
     //equ.ajouterequipement();
      //List<Equipement>listEquipement=equ.affichageEquipement();
      //for (Equipement equi : listEquipement){
        //  System.out.println(equi);
     // }
      
      //equ.rechercherparnom("rass");
    //  equ.modifierequipement(new Equipement (10,"cammm","image",50, "loisir bbbb")); 
    
      
      
      OffreCRUD off = new OffreCRUD();
      //Offre o1 = new Offre(28,"30/30/222","85/55/2020","50%");
      
      //Offre o2 = new Offre(11,11,"debut","fin");
      //Offre o = new Offre(15,"nnn","zz");
    //off.ajouteroffre(o1);
      off.affichagepromotion();
     
      
//      for (Offre Offr : listOffre){
//          System.out.println(Offr);
     // off.modifieroffre(new Offre (27,10,"50/25/225","50/50/500","96%",199.99,174.96)); 
       //off.supprimeroffre(28);
       //off.statistique();
       //off.promo(123,"25%");
       
       //Mailing.sendMail("maissa.choura@esprit.tn");
    }
}
