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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
        
        
        java.util.Date AjoutDate = new java.util.Date("2022/05/31");
        java.sql.Date sqldate1 = new java.sql.Date(AjoutDate.getTime());
        java.util.Date AjoutDate2 = new java.util.Date("2022/06/20");
        java.sql.Date sqldate2 = new java.sql.Date(AjoutDate2.getTime());
//        Evenements e= new Evenements("Camp3", "join us!!", sqldate1, sqldate2,"image");
        
//        ecd.ajouterEvenements2(e);

//        ecd.ajouterEvenements();


       
//        ecd.modifierEvenements(new Evenements(16, "Tozeur", "camping", sqldate1, sqldate2,"image.jpg"));

        
        
//        ecd.supprimerEvenements(new Evenements(17));

        System.out.println("****** afficher la liste des évenements ********");
        List<Evenements>listEvenements=ecd.affichageEvenements() ;
        for(Evenements ev:listEvenements)
        {
            System.out.println(ev); 
        }
        



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