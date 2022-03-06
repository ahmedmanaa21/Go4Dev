/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.JavaPIDEV.tests;

import edu.JavaPIDEV.entities.Admin;
import edu.JavaPIDEV.entities.Client;
import edu.JavaPIDEV.services.AdminCRUD;
import edu.JavaPIDEV.services.ClientCRUD;
import edu.JavaPIDEV.utils.MyConnection;
import java.time.Period;
import java.util.List;

/**
 *
 * @author karra
 */
public class MainClass {

    public static void main(String[] args) {
        System.out.println("***********   Connection BDD   ***************");
        MyConnection m1 = MyConnection.getInstance();
        System.out.println("***********   Gestion Admins   ***************");
        AdminCRUD ad = new AdminCRUD();
        Admin A1 = new Admin("jihed","melki","jihed@esprit.tn","mdp21",22225678);
//        ad.ajouterAdmin(A1);
//        List<Admin> listAdmin = ad.affichageAdmin();
//        for (Admin equ : listAdmin) {
//            System.out.println(ad);
//        }
//        ad.updateAdmin("test","test","test@esprit.tn","nyest",131212,7); 
//        ad.supprimerAdmin(10);
        System.out.println("***********   Gestion Clients   ***************");
        ClientCRUD cl = new ClientCRUD();
        java.util.Date AjoutDate = new java.util.Date("2022/01/10");
        java.sql.Date sqldate = new java.sql.Date(AjoutDate.getTime());
        Client C1 = new Client(1112345,"jasser manaa","ahmed12","ahmed@esprit.tn","mdp1",sqldate,"ghazela","image");
//        Client C2 = new Client(11345677,"bbb manaa","ab12","tss@esprit.tn","mdp1","1998/07/17","ghazela");
//        Client C3 = new Client(17345677,"jihed","jii12","nes11","nessrine@esprit.tn",1999/01/21,"Ariana");
//        cl.ajouterClient(C1);
//        cl.ajouterClient(C2);
//        cl.ajouterClient(C3);
//        List<Client> listClient = cl.affichageClient();
//        for (Client cli : listClient) {
//            System.out.println(cli.toString());
//        }
//        cl.modifierClient(new Client (1112345,"test","test","test@esprit.tn","mdpt",sqldate,"test","image2")); 
//        cl.supprimerClient(12345678);
//        cl.statistique("ghazela");

//        String dateNaisse = "1999/11/27";
//        String[] arrayString = dateNaisse.split("/");
//        int  arrayInt [] = {0,0,0};
//        for(int i=0 ; i<arrayString.length;i++){
//            arrayInt[i] = Integer.parseInt(arrayString[i]);
//      }
//        LocalDate date = LocalDate.of(arrayInt[0], arrayInt[1], arrayInt[2]);
//        LocalDate now = LocalDate.now();
//        Period period2 = Period.between(date, now);
//        System.out.println("L'age du Client est : "+period2.getYears()+"ans "+" et " +period2.getMonths()+"mois");
//        
//    }
}
}
