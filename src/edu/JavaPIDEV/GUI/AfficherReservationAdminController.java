/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.JavaPIDEV.GUI;

import edu.JavaPIDEV.entities.Reservation;
import edu.JavaPIDEV.services.ReservationCRUD;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Jasser BOUKRAYA
 */
public class AfficherReservationAdminController implements Initializable {

    @FXML
    private Button update;
    @FXML
    private Button delet;
    @FXML
    private Button retour;
    @FXML
    private TableView<Reservation> listRES;
    @FXML
    private TableColumn<Reservation,Integer> COLOid;
    @FXML
    private TableColumn<Reservation,Integer> COLOcin;
    @FXML
    private TableColumn<Reservation,Date> COLOdate;
    @FXML
    private TableColumn<Reservation,Integer> zone;
    @FXML
    private TableColumn<Reservation,Integer> nbrP;
    @FXML
    private Button navigation;
public ObservableList<Reservation> dataa = FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
            COLOid.setCellValueFactory(new PropertyValueFactory<>("id_reservation"));
    COLOcin.setCellValueFactory(new PropertyValueFactory<>("cin"));
    COLOdate.setCellValueFactory(new PropertyValueFactory<>("date_reservation"));
    zone.setCellValueFactory(new PropertyValueFactory<>("id_zoneCamping"));
    nbrP.setCellValueFactory(new PropertyValueFactory<>("nbrPersonne"));
         Refresh();  
AfficherReservation();
    }    

    @FXML
    private void Refresh() {
          dataa.clear();
                ReservationCRUD RS = new ReservationCRUD();
        dataa.addAll(RS.affichageReservation());
        RS.affichageReservation();
        
        listRES.setItems(dataa);
    }
private void AfficherReservation() {
    
       Refresh();
 
    COLOid.setCellValueFactory(new PropertyValueFactory<>("id_reservation"));
    COLOcin.setCellValueFactory(new PropertyValueFactory<>("cin"));
    COLOdate.setCellValueFactory(new PropertyValueFactory<>("date_reservation"));
    zone.setCellValueFactory(new PropertyValueFactory<>("id_zoneCamping"));
    nbrP.setCellValueFactory(new PropertyValueFactory<>("nbrPersonne"));
    }

    @FXML
    private void DELET(ActionEvent event) {
          
        if (listRES.getSelectionModel().getSelectedItem() != null) {
            Alert deleteEventAlert = new Alert(Alert.AlertType.CONFIRMATION);
            deleteEventAlert.setTitle("Suppression d'une Réservation");
            deleteEventAlert.setHeaderText(null);
            deleteEventAlert.setContentText("Veuillez supprimer cette Réservation ?");
            Optional<ButtonType> optionDeleteBookAlert = deleteEventAlert.showAndWait();
            if (optionDeleteBookAlert.get() == ButtonType.OK) {
                Reservation R = listRES.getSelectionModel().getSelectedItem();
                ReservationCRUD rc= new ReservationCRUD();
                rc.supprimerReservation(R);
                dataa.clear();
                dataa.addAll(rc.affichageReservation());
               
                Alert succDeleteEventAlert = new Alert(Alert.AlertType.INFORMATION);
                succDeleteEventAlert.setTitle("Supprimer Résérvation");
               
                succDeleteEventAlert.setContentText("Résérvation déja supprimée!");

                succDeleteEventAlert.showAndWait();
            } else if (optionDeleteBookAlert.get() == ButtonType.CANCEL) {

            }

        } else {

          
            Alert selectBookAlert = new Alert(Alert.AlertType.WARNING);
            selectBookAlert.setTitle("Sélectionner une Résérvation");
            selectBookAlert.setHeaderText(null);
            selectBookAlert.setContentText("D'abord il faut Sélectionner une Résérvation !");
            selectBookAlert.showAndWait();
           

        } 
        
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
    private void navigation(ActionEvent event) {
          try {
            Parent root = FXMLLoader.load(getClass().getResource("Chart.fxml"));
            Scene scene = new Scene(root, 800, 500);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(scene);
            appStage.show();
        } catch (IOException ex) {
            ex.getMessage();
        }
    }
    
    
}
