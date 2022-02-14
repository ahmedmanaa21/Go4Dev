/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.connexion3a16.tests;

import edu.connexion3a16.entities.Admin;
import edu.connexion3a16.entities.Client;
import edu.connexion3a16.services.AdminCRUD;
import edu.connexion3a16.services.ClientCRUD;
import edu.connexion3a16.utils.MyConnection;
import java.util.List;

/**
 *
 * @author karra
 */
public class MainClass {

    public static void main(String[] args) {
        MyConnection mc = new MyConnection();
        MyConnection m1 = MyConnection.getInstance();
        AdminCRUD ad = new AdminCRUD();
//        Admin A1 = new Admin("yassine","manaa","yass@esprit.tn","mdp11",12345678);
//        ad.ajouterAdmin(A1);
//        List<Admin> listAdmin = ad.affichageAdmin();
//        for (Admin equ : listAdmin) {
//            System.out.println(ad);
//        }
//        ad.modifierAdmin(new Admin (5,"test","test","test@esprit.tn","test",111111111)); 
//        ad.supprimerAdmin(5);
        ClientCRUD cl = new ClientCRUD();
//        Client C1 = new Client(12345678,"ahmed manaa","ahmed12","mdp1","ahmed@esprit.tn","ghazela");
//        Client C2 = new Client(12345677,"yassine manaa","yassine12","mdp11","yass@esprit.tn","ghazela");
//        cl.ajouterClient(C1);
//        cl.ajouterClient(C2);
//        List<Client> listClient = cl.affichageClient();
//        for (Client cli : listClient) {
//            System.out.println(cli.toString());
//        }
//        cl.modifierClient(new Client (12345678,"test","test","test","test@esprit.tn","test")); 
//        cl.supprimerClient(12345678);
        
    }
}
