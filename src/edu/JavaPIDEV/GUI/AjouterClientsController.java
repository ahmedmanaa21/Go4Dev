/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.JavaPIDEV.GUI;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.Loader;
import edu.JavaPIDEV.entities.Client;
import edu.JavaPIDEV.services.ClientCRUD;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Nesrine
 */
public class AjouterClientsController implements Initializable {

    @FXML
    private TextField fxcin;
    @FXML
    private TextField fxnom;
    @FXML
    private TextField fxsurnom;
    @FXML
    private TextField fxemail;
    @FXML
    private PasswordField fxmdp;
    @FXML
    private DatePicker fxdate;
    @FXML
    private TextField fxadresse;
    @FXML
    private TextField fximage;
    @FXML
    private Button btnajouter;

    /**
     * Initializes the controller class.
     */
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void addClient(ActionEvent event) {
        int cin = Integer.valueOf(fxcin.getText());
        String nom = fxnom.getText();
        String surnom = fxsurnom.getText();
        String email = fxemail.getText();
        String mdp = fxmdp.getText();
        java.sql.Date date = java.sql.Date.valueOf(fxdate.getValue());
        String adresse = fxadresse.getText();
        String image = fximage.getText();
        Client C = new Client(cin,nom,surnom,email,mdp,date,adresse,image);
        ClientCRUD pc = new ClientCRUD();
        pc.ajouterClient(C);
        FXMLLoader Loader = new FXMLLoader(getClass().getResource("afficherClients.fxml"));
        Parent root;
        try {
            root = Loader.load();
            AfficherClientsController ac = Loader.getController();
            fxnom.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
