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
public class MenuRecController implements Initializable {

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
    private TextField SearchBar;
    @FXML
    private Button btnaff;
    @FXML
    private Button btnaj;
    @FXML
    private Button btnaj1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        logo.setImage(new Image("file:C:\\Users\\MSI\\Desktop\\CampersDen\\src\\Images\\logo.png"));
//        fxlogout.setImage(new Image("file:C:\\Users\\MSI\\Desktop\\CampersDen\\src\\Images\\logout.png"));
        // TODO
    }    

    @FXML
    private void changeScreenViewAcc(ActionEvent event) {
    }

    @FXML
    private void changeScreenViewEquip(ActionEvent event) {
    }

    @FXML
    private void changeScreenViewProd(MouseEvent event) {
    }

    @FXML
    private void changeScreenViewOffre(ActionEvent event) {
    }

    @FXML
    private void changeScreenViewProm(MouseEvent event) {
    }

    @FXML
    private void changeScreenViewRec(ActionEvent event) {
    }

    private void changeScreenReclamations(ActionEvent event) throws IOException {
        Parent tableViewParent=FXMLLoader.load(getClass().getResource("ManuRec.fxml"));
        Scene tableViewScene= new Scene(tableViewParent);
        Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }

    @FXML
    private void logout(ActionEvent event) {
    }

    @FXML
    private void afficherRec(ActionEvent event) throws IOException {
        Parent tableViewParent=FXMLLoader.load(getClass().getResource("AfficherClinetREC.fxml"));
        Scene tableViewScene= new Scene(tableViewParent);
        Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }

    @FXML
    private void passerRec(ActionEvent event) throws IOException {
        Parent tableViewParent=FXMLLoader.load(getClass().getResource("AjouterReclamation.fxml"));
        Scene tableViewScene= new Scene(tableViewParent);
        Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }

    @FXML
    private void changeScreenViewReclam(MouseEvent event) throws IOException {
        Parent tableViewParent=FXMLLoader.load(getClass().getResource("MenuRec.fxml"));
        Scene tableViewScene= new Scene(tableViewParent);
        Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }

    @FXML
    private void modifRec(ActionEvent event) throws IOException {
        Parent tableViewParent=FXMLLoader.load(getClass().getResource("ModifRECclient.fxml"));
        Scene tableViewScene= new Scene(tableViewParent);
        Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }
    
}
