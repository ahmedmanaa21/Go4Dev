/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.JavaPIDEV.GUI;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 *
 * @author melki
 */
public class main extends Application {
    
    @Override
    public void start(Stage primaryStage) {
    Parent root;
    
        try {
            
//            root = FXMLLoader.load(getClass().getResource("AjouterEvenement.fxml"));
            root = FXMLLoader.load(getClass().getResource("menu.fxml"));
//            root = FXMLLoader.load(getClass().getResource("GestionEvenement.fxml"));


            Scene scene = new Scene(root, 600, 400);
//            primaryStage.setTitle("Ajouter Evenement");
//              primaryStage.setTitle("Gestion Zone de Camping");
              primaryStage.setTitle("Gestion Evenement");

            primaryStage.setScene(scene);
            primaryStage.show();
                    } 
        catch (IOException ex) {
            System.out.println(ex.getMessage());  }
    }


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}

// auto complete , map api geocoding latitude langitude (mapbox , open street map , leaftlet ) 
//paiement ( stripe , paypal ) , SMS ,qrcode, fluxrss , parsing rss feed java 
//Netoyer la base de donnée