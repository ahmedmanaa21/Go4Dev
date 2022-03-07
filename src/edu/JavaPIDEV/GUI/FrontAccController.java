/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.JavaPIDEV.GUI;

import static edu.JavaPIDEV.GUI.NewFXMain.UserconnectedC;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class FrontAccController implements Initializable {

    @FXML
    private TextField SearchBar;
    @FXML
    private Label txtemail;
    @FXML
    private Label txtmdp;
    @FXML
    private Label txtcin;
    @FXML
    private Label txtsurnom;
    @FXML
    private Label txtadresse;
    @FXML
    private Button btnmod;
    @FXML
    private Label txtnom;
    @FXML
    private Label txtdate;
    @FXML
    private ImageView logo;
    @FXML
    private Button btnacc;
    @FXML
    private Button btnequip;
    @FXML
    private Button btnoffre;
    @FXML
    private Button btnrec;
    @FXML
    private Label txtsexe;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        logo.setImage(new Image("file:C:\\Users\\MSI\\Desktop\\CampersDen\\src\\Images\\logo.png"));
        
        txtnom.setText(UserconnectedC.getNomPrenom());
        txtemail.setText(UserconnectedC.getEmail());
        txtmdp.setText(UserconnectedC.getMdp());
        txtcin.setText(String.valueOf(UserconnectedC.getCin()));
        txtdate.setText(String.valueOf(UserconnectedC.getDateNaissance()));
        txtsurnom.setText(UserconnectedC.getSurnom());
        txtadresse.setText(UserconnectedC.getAdresse());
        txtsexe.setText(UserconnectedC.getSexe());
        
        
    }    

    @FXML
    private void logout(ActionEvent event) {
        
        UserconnectedC.setCin(0);
        UserconnectedC.setNomPrenom("");
        UserconnectedC.setSurnom("");
        UserconnectedC.setEmail("");
        UserconnectedC.setMdp("");
        UserconnectedC.setAdresse("");
        UserconnectedC.setImage("");
        UserconnectedC.setSexe("");

        FXMLLoader LOADER = new FXMLLoader(getClass().getResource("login.fxml"));
        try {
            Parent root = LOADER.load();
            Scene sc = new Scene(root);
            LoginController cntr = LOADER.getController();
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(sc);
            window.show();
        } catch (IOException ex) {

        }
    }

    @FXML
    private void modpro(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Profile.fxml"));
        Parent root = loader.load();
//        txtemail.getScene().setRoot(root);
        Stage window = (Stage) txtemail.getScene().getWindow();
        window.setScene(new Scene(root, 1363, 890));
    }

    @FXML
    private void changeScreenViewAcc(ActionEvent event) throws IOException {
        Parent tableViewParent=FXMLLoader.load(getClass().getResource("FrontAcc.fxml"));
        Scene tableViewScene= new Scene(tableViewParent);
        Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }

    @FXML
    private void changeScreenViewEquip(ActionEvent event) {
    }

    @FXML
    private void changeScreenViewOffre(ActionEvent event) {
    }

    @FXML
    private void changeScreenViewRec(ActionEvent event) {
    }

    @FXML
    private void changeScreenViewProd(MouseEvent event) {
    }

    @FXML
    private void changeScreenViewProm(MouseEvent event) {
    }

    @FXML
    private void changeScreenViewReclam(MouseEvent event) {
    }
    
}
