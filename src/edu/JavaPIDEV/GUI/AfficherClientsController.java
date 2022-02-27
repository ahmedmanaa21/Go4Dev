/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.JavaPIDEV.GUI;

import edu.JavaPIDEV.entities.Client;
import edu.JavaPIDEV.services.ClientCRUD;
import edu.JavaPIDEV.utils.MyConnection;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import edu.JavaPIDEV.utils.MyConnection;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import static javafx.scene.input.KeyCode.S;
import javafx.scene.input.MouseEvent;
import javafx.util.converter.DateStringConverter;
import javafx.util.converter.IntegerStringConverter;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class AfficherClientsController implements Initializable {

    ClientCRUD pc = new ClientCRUD();
    Client c = new Client();
    @FXML
    private TableView<Client> table;
    @FXML
    private TableColumn<Client, Integer> txtcin;
    @FXML
    private TableColumn<Client, String> txtnom;
    @FXML
    private TableColumn<Client, String> txtsurnom;
    @FXML
    private TableColumn<Client, String> txtemail;
    @FXML
    private TableColumn<Client, String> txtmdp;
    @FXML
    private TableColumn<Client, Date> txtdate;
    @FXML
    private TableColumn<Client, String> txtadresse;
    @FXML
    private TableColumn<Client, String> txtimage;
    @FXML
    private Button btnsupprimer;
    @FXML
    private Button btnmodifier;
    @FXML
    private Label lbltotal;

    public ObservableList<Client> dataClient = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        /**
         *
         *
         */
        try {
            Connection cnx = new MyConnection().getCnx();
            ResultSet rs = cnx.createStatement().executeQuery("SELECT cin,nomPrenom ,surnom , email,mdp , dateNaissance,adresse,image FROM client");
            while (rs.next()) {
                dataClient.add(new Client(rs.getInt("cin"), rs.getString("nomPrenom"), rs.getString("surnom"), rs.getString("email"), rs.getString("mdp"), rs.getDate("dateNaissance"), rs.getString("adresse"), rs.getString("image")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AfficherClientsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String requete ="SELECT mdp FROM client where cin='"+c.getCin()+"' ";
        String hiddenPass = "";
        for (int i = 0; i < requete.length(); i++) {
            hiddenPass += "*";   
        }

        table.setEditable(true);
        table.getSelectionModel().cellSelectionEnabledProperty().set(true);
        txtcin.setCellValueFactory(new PropertyValueFactory<>("cin"));
        txtcin.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        txtnom.setCellValueFactory(new PropertyValueFactory<>("nomPrenom"));
        txtnom.setCellFactory(TextFieldTableCell.forTableColumn());
        txtsurnom.setCellValueFactory(new PropertyValueFactory<>("surnom"));
        txtsurnom.setCellFactory(TextFieldTableCell.forTableColumn());
        txtemail.setCellValueFactory(new PropertyValueFactory<>("email"));
        txtemail.setCellFactory(TextFieldTableCell.forTableColumn());
        txtmdp.setCellValueFactory(new PropertyValueFactory<>(hiddenPass));
        txtdate.setCellValueFactory(new PropertyValueFactory<>("dateNaissance"));
//        txtdate.setCellFactory(TextFieldTableCell.forTableColumn(new DateStringConverter()));
        txtadresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        txtadresse.setCellFactory(TextFieldTableCell.forTableColumn());
        txtimage.setCellValueFactory(new PropertyValueFactory<>("image"));
        txtimage.setCellFactory(TextFieldTableCell.forTableColumn());
        table.setItems(dataClient);
        
        
        txtcin.setSortType(TableColumn.SortType.DESCENDING);
        txtnom.setSortType(TableColumn.SortType.DESCENDING);
        txtsurnom.setSortType(TableColumn.SortType.DESCENDING);
        txtemail.setSortType(TableColumn.SortType.DESCENDING);
        txtdate.setSortType(TableColumn.SortType.DESCENDING);
        txtadresse.setSortType(TableColumn.SortType.DESCENDING);
        
    }

    @FXML
    private void supprimerClients(ActionEvent event) {
        
        
         if (table.getSelectionModel().getSelectedItem() != null) {
            Alert deleteAdmintAlert = new Alert(Alert.AlertType.CONFIRMATION);
            deleteAdmintAlert.setTitle("Supprimer Client");
            deleteAdmintAlert.setHeaderText(null);
            deleteAdmintAlert.setContentText("Voulez-vous vraiment supprimer cet Client ?");
            Optional<ButtonType> optionDeleteBookAlert = deleteAdmintAlert.showAndWait();
            if (optionDeleteBookAlert.get() == ButtonType.OK) {
                Client A = table.getSelectionModel().getSelectedItem();
                pc.supprimerClient(A.getCin());
                dataClient.clear();
                dataClient.addAll(pc.affichageClient());
                //Alert Delete Blog :
                Alert succDeleteAdminAlert = new Alert(Alert.AlertType.INFORMATION);
                succDeleteAdminAlert.setTitle("Supprimer Client");
                succDeleteAdminAlert.setHeaderText("Resultats:");
                succDeleteAdminAlert.setContentText("Client supprimé !");

                succDeleteAdminAlert.showAndWait();
            } else if (optionDeleteBookAlert.get() == ButtonType.CANCEL) {

            }

        } else {

            //Alert Select BOOK :
            Alert selectBookAlert = new Alert(Alert.AlertType.WARNING);
            selectBookAlert.setTitle("Choisir un Client");
            selectBookAlert.setHeaderText(null);
            selectBookAlert.setContentText("Choisir un Client avant de supprimer!");
            selectBookAlert.showAndWait();
            //Alert Select Book !

        } 
           
        
    }
    
    @FXML
    private void modifierClients(ActionEvent event) {
        if (table.getSelectionModel().getSelectedItem() != null) {

            Client ec = table.getSelectionModel().getSelectedItem();
            pc.modifierClient(new Client(ec.getCin(),ec.getNomPrenom(),ec.getSurnom(),ec.getEmail(),ec.getMdp(),ec.getDateNaissance(),ec.getAdresse(),ec.getImage()));
            Alert BookAlert = new Alert(Alert.AlertType.INFORMATION);
            BookAlert.setTitle("Modifier");
            BookAlert.setHeaderText(null);
            BookAlert.setContentText("Client Modifié");
            BookAlert.showAndWait();

        } else {

            Alert selectBookAlert = new Alert(Alert.AlertType.WARNING);
            selectBookAlert.setTitle("Selectionner un Client");
            selectBookAlert.setHeaderText(null);
            selectBookAlert.setContentText("Selectioneer un Client !");
            selectBookAlert.showAndWait();
            
        }
    }
}
