/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.JavaPIDEV.GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author melki
 */
public class MenuController implements Initializable {

    @FXML
    private Button fxge;
    @FXML
    private Button fxgz;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Gevent(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("GestionEvenement.fxml"));
            Scene scene = new Scene(root, 1200, 650);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(scene);
            appStage.show();
            } catch (IOException ex) {
               ex.getMessage();
            }
    }

    @FXML
    private void Gcamping(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("GestionZoneCamping.fxml"));
            Scene scene = new Scene(root, 1200, 600);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(scene);
            appStage.show();
            } catch (IOException ex) {
               ex.getMessage();
            }
    }
    
}
