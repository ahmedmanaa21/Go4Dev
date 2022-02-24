/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.JavaPIDEV.tests;

import edu.JavaPIDEV.entities.Evenements;
import edu.JavaPIDEV.entities.ZoneCamping;
import edu.JavaPIDEV.services.EvenementsCRUD;
import edu.JavaPIDEV.services.ZoneCampingCRUD;
import edu.JavaPIDEV.utils.MyConnection;
import java.util.List;

/**
 *
 * @author melki
 */
public class MainClass {
    public static void main(String[] args) {
        //MyConnection mc= new MyConnection();
        
        
        System.out.println("*********************   Evenements   ************************");
        EvenementsCRUD ecd=new EvenementsCRUD(); 
//        Evenements e= new Evenements("Bni mtir", "Camping", "12-02-2022", "15-02-2022");
//        ecd.ajouterEvenements2(e);

//        ecd.ajouterEvenements();
        System.out.println("****** afficher la liste des évenements ********");
        List<Evenements>listEvenements=ecd.affichageEvenements() ;
        for(Evenements ev:listEvenements)
        {
            System.out.println(ev); 
        }
        
//        ecd.modifierEvenements(new Evenements(12, "ain drahem", "camp", "15-12-22  ", "17-12-22"));
        
        
        ecd.supprimerEvenements(new Evenements(12));


//                   *************   Zone de camping   *****************



        System.out.println("********************   Zone de camping   ************************");




        ZoneCampingCRUD zcd=new ZoneCampingCRUD();
        
        
        ZoneCamping z=new ZoneCamping("Bizerte", "Mjez ll beb", "Boukraya center", 12.5, 1.01, "Marahbe bikom");
//        zcd.ajouterZoneCamping(z);
        
        System.out.println("****** afficher la liste des zone de camping ********");
        List<ZoneCamping>listZoneCamping=zcd.affichageZoneCamping() ;
        for(ZoneCamping zc:listZoneCamping)
        {
            System.out.println(zc); 
        }
        
        zcd.modifierZoneCampipng(new ZoneCamping(6, "Kelibia", "Dar aalouch", "foret dar aalouch",2.1,5.4, "Venez decouvrir notre espace!"));
        

//        zcd.supprimerZoneCamping(new ZoneCamping(7));  
          zcd.statistique();
          
         

          
          
          
    }
}
// API auto complete , longitude latitude double => geocouting =>adresse
//Localiser par rapport a la l'adresse ip 