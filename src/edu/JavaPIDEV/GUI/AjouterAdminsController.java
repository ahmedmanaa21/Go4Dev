/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.JavaPIDEV.GUI;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.Loader;
import edu.JavaPIDEV.entities.Admin;
import edu.JavaPIDEV.services.AdminCRUD;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Nesrine
 */
public class AjouterAdminsController implements Initializable {

    @FXML
    private TextField fxnom;
    @FXML
    private TextField fxprenom;
    @FXML
    private TextField fxemail;
    @FXML
    private TextField fxmdp;
    @FXML
    private TextField fxnum;
    @FXML
    private Button btnajouter;

    /**
     * Initializes the controller class.
     */
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void addAdmin(ActionEvent event) {
        String nom = fxnom.getText();
        String prenom = fxprenom.getText();
        String email = fxemail.getText();
        String mdp = fxmdp.getText();
        int numtel = Integer.valueOf(fxnum.getText());
        Admin A = new Admin(nom,prenom,email,mdp,numtel);
        AdminCRUD pc = new AdminCRUD();
        pc.ajouterAdmin(A);
        FXMLLoader Loader = new FXMLLoader(getClass().getResource("afficherAdmins.fxml"));
        Parent root;
        try {
            root = Loader.load();
            AfficherAdminsController ac = Loader.getController();
            fxnom.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
