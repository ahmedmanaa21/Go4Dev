/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.JavaPIDEV.tests;

import edu.JavaPIDEV.entities.Equipement;
import edu.JavaPIDEV.entities.Panier;
import edu.JavaPIDEV.services.EquipementCRUD;
import edu.JavaPIDEV.services.PanierCRUD;
import edu.JavaPIDEV.utils.MyConnection;
import java.util.List;

/**
 *
 * @author dell
 */

public class MainClass {
    public static void main(String[] args) {
        
      //MyConnection mc= new MyConnection(); 
      EquipementCRUD equ = new EquipementCRUD();
  
      //equ.ajouterequipement();
     /* List<Equipement>listEquipement=equ.affichageEquipement();
      for (Equipement equi : listEquipement){
          System.out.println(equi);
      }
      */
      //equ.rechercherparnom("rass");
      //equ.modifierequipement(new Equipement (8,"cammm","image",50, "loisir")); 
     //equ.supprimerequipement (new Equipement (2, "tente","image",250, "tente camping"));
    
     /****************************************************/
     PanierCRUD pan = new PanierCRUD();
     Panier p1 = new Panier(2,8,1);
    System.out.println("*****ajout*****");
   //pan.ajouterPanier(p1);
    
    List<Panier>listPanier=pan.affichagepanier();
      for (Panier pani : listPanier) {
          System.out.println(pani);
      }
   // pan.modifierpanier(new Panier (2,8,3)); 
   // pan.supprimerpanier (new Panier (2,8,3));
   pan.statistique();
    }
}
