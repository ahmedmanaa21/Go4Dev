/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.JavaPIDEV.GUI;

import edu.JavaPIDEV.entities.Reclamation;
import edu.JavaPIDEV.entities.Reservation;
import edu.JavaPIDEV.services.ReclamationCRUD;
import edu.JavaPIDEV.services.ReservationCRUD;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;

/**
 * FXML Controller class
 *
 * @author Jasser BOUKRAYA
 */
public class ModifierReservationController implements Initializable {

    @FXML
    private TextField Mid_res;
    @FXML
    private TextField Mnbr_res;
    @FXML
    private TextField Mcin_res;
    @FXML
    private DatePicker Mdate_res;
    @FXML
    private TextField MidZ_res;
    @FXML
    private Button MRES;
    @FXML
    private ImageView NBRCheckMark;
     String erreur;
  

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Mid_res.setDisable(true);
        Mcin_res.setDisable(true);
        MidZ_res.setDisable(true);
    }    

   
    public void setData(int id_reservation, int cin,Date date_reservation, int id_zoneCamping,int nbrPersonne) {
 
    Mid_res.setText("" + id_reservation);
    Mcin_res.setText("" + cin);
    LocalDate lDate = date_reservation.toLocalDate();
    Mdate_res.setValue(lDate);
    MidZ_res.setText(""+id_zoneCamping);
    Mnbr_res.setText(""+nbrPersonne);
    
       
    }

    @FXML
    private void MODIFRes(ActionEvent event) {
        if(testSaisie()){
         int id=Integer.valueOf(Mid_res.getText());
        int NBR = Integer.valueOf(Mnbr_res.getText());
        int CIN = Integer.valueOf(Mcin_res.getText());
        java.sql.Date date = java.sql.Date.valueOf(Mdate_res.getValue());
        int Zone = Integer.valueOf(Mnbr_res.getText());       
        try{
             Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
               stage.close();
             Alert ModifyRECLAMATIONAlert = new Alert(Alert.AlertType.CONFIRMATION);
            ModifyRECLAMATIONAlert.setTitle("Modifier Réservation");
            ModifyRECLAMATIONAlert.setHeaderText(null);
            ModifyRECLAMATIONAlert.setContentText("Veuillez modifier cette Réservation ?");
            Optional<ButtonType> optionModifyBookAlert = ModifyRECLAMATIONAlert.showAndWait();
            if (optionModifyBookAlert.get() == ButtonType.OK){
                
        Reservation rr = new Reservation(id, CIN, date, Zone, NBR);
        ReservationCRUD RS = new ReservationCRUD();
        
        RS.modifierReservation(rr);
        System.out.println("Modifié avec succes");
      
                Alert succDeleteAdminAlert = new Alert(Alert.AlertType.INFORMATION);
                succDeleteAdminAlert.setTitle("Modifier Réservation");
                succDeleteAdminAlert.setHeaderText("Resultats:");
                succDeleteAdminAlert.setContentText("Réservation Modifié !");
                succDeleteAdminAlert.showAndWait(); 
          
        }
        
        }
        catch(Exception ex)
                {
                System.out.println("error : "+ex.getMessage());
        }
    }
    }
        
    
         private Boolean testNBRP() {
        if (Integer.valueOf(Mnbr_res.getText()) < 9 && Integer.valueOf(Mnbr_res.getText()) > 0  ) {
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

    
    
}
