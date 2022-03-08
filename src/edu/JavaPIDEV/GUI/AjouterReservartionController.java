/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.JavaPIDEV.GUI;

import static edu.JavaPIDEV.GUI.NewFXMain.UserconnectedC;
import edu.JavaPIDEV.entities.Reservation;
import edu.JavaPIDEV.services.ReservationCRUD;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Jasser BOUKRAYA
 */
public class AjouterReservartionController implements Initializable {

    @FXML
    private TextField id_res;
    @FXML
    private TextField nbr_res;
    @FXML
    private TextField cin_res;
    @FXML
    private DatePicker date_res;
    @FXML
    private TextField idZ_res;
    @FXML
    private Button RES;
    @FXML
    private Button retour;
    @FXML
    private ImageView NBRCheckMark;
    String erreur;
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
    private Button btnrec1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        logo.setImage(new Image("file:C:\\Users\\MSI\\Desktop\\CampersDen\\src\\Images\\logo.png"));
        cin_res.setText(String.valueOf(UserconnectedC.getCin()));
        // TODO
    }    

    @FXML
    private void AddReservation(ActionEvent event) throws IOException {
        if(testSaisie()){ 
        int ID = Integer.valueOf(id_res.getText());
        int CIN = Integer.valueOf(cin_res.getText());
        java.sql.Date date = java.sql.Date.valueOf(date_res.getValue());
         int IDZ = Integer.valueOf(idZ_res.getText());
  
        int NBR = Integer.valueOf(nbr_res.getText());
       
        
       
        
        Reservation res =new Reservation(ID, CIN, date, IDZ, NBR);
        ReservationCRUD RS = new ReservationCRUD();
        RS.ajouterReservation(res);
        System.out.println("ajouté avec succes");

        FXMLLoader loader=new FXMLLoader(getClass().getResource("AfficherReclamation.fxml"));
        Parent root = loader.load();  
        
        
        
    }
    }
    
    private Boolean testNBRP() {
        if (Integer.valueOf(nbr_res.getText()) < 9 && Integer.valueOf(nbr_res.getText()) > 0  ) {
           NBRCheckMark.setImage(new Image("Images/correct.gif"));
                return true;
}
        else
            NBRCheckMark.setImage(new Image("Images/wrong.png"));
                return false;
              
    }
private Boolean testSaisie() {
        erreur = "";
       
        
        if (!testNBRP()) {
            erreur = erreur + ("Veuillez verifier votre nombre des personnnes réserver: il faut qu'il est inferieur a 9 et superieur a 0 \n");
        }
        return testNBRP();
    }

    @FXML
    private void retour(ActionEvent event) {
         try {

            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();

            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Menu.fxml")));
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
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

    @FXML
    private void changeScreenViewReclam(MouseEvent event) {
    }

    @FXML
    private void logout(ActionEvent event) {
    }

    @FXML
    private void changeScreenViewReserv(ActionEvent event) throws IOException {
        Parent tableViewParent=FXMLLoader.load(getClass().getResource("MenuReserv.fxml"));
        Scene tableViewScene= new Scene(tableViewParent);
        Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }
    
}
