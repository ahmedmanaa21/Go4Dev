/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.JavaPIDEV.GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Jasser BOUKRAYA
 */
public class MenuController implements Initializable {

    private AnchorPane mainAnchorPane;
    private Stage stage = new Stage();
    @FXML
    private Button afficher;
    @FXML
    private Button envRec;
    @FXML
    private Button passerRerservation;
    @FXML
    private Button ListerRerservation;
    @FXML
    private Button afficherClient;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    private void setNode(Node node) {
        mainAnchorPane.getChildren().clear();
        mainAnchorPane.getChildren().add((Node) node);
        FadeTransition ft = new FadeTransition(Duration.seconds(1));//dure de la translation
        ft.setNode(node);
        ft.setFromValue(0.10);//dispartion 
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(true);
        ft.play();
    }

    @FXML
    private void listerReclamation(ActionEvent event) throws IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("AfficherReclamation.fxml"));
            Scene scene = new Scene(root, 800, 650);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(scene);
            appStage.show();
        } catch (IOException ex) {
            ex.getMessage();
        }
    }

    @FXML
    private void envoyerReclamation(ActionEvent event) throws IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("AjouterReclamation.fxml"));
            Scene scene = new Scene(root, 800, 500);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(scene);
            appStage.show();
        } catch (IOException ex) {
            ex.getMessage();
        }

    }

    @FXML
    private void envoyerReservation(ActionEvent event) throws IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("AjouterReservartion.fxml"));
            Scene scene = new Scene(root, 800, 500);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(scene);
            appStage.show();
        } catch (IOException ex) {
            ex.getMessage();
        }

    }

    @FXML
    private void ListerRerservation(ActionEvent event) throws IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("AfficherReservation.fxml"));
            Scene scene = new Scene(root, 800, 650);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(scene);
            appStage.show();
        } catch (IOException ex) {
            ex.getMessage();
        }

    }

    @FXML
    private void listerReclamationClient(ActionEvent event) {
        
          try {
            Parent root = FXMLLoader.load(getClass().getResource("AfficherClinetREC.fxml"));
            Scene scene = new Scene(root, 800, 650);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(scene);
            appStage.show();
        } catch (IOException ex) {
            ex.getMessage();
        }
          
    }

}
