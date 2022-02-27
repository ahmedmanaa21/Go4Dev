/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.JavaPIDEV.GUI;

import edu.JavaPIDEV.entities.Admin;
import edu.JavaPIDEV.services.AdminCRUD;
import edu.JavaPIDEV.utils.MyConnection;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import edu.JavaPIDEV.utils.MyConnection;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import static javafx.scene.input.KeyCode.S;
import javafx.scene.input.MouseEvent;
import javafx.util.converter.IntegerStringConverter;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class AfficherAdminsController implements Initializable {

    AdminCRUD pc = new AdminCRUD();
    Connection cnx = new MyConnection().getCnx();
    @FXML
    private TableView<Admin> table;
    @FXML
    private TableColumn<Admin, String> txtnom;
    @FXML
    private TableColumn<Admin, String> txtprenom;
    @FXML
    private TableColumn<Admin, String> txtemail;
    @FXML
    private TableColumn<Admin, String> txtmdp;
    @FXML
    private TableColumn<Admin, Integer> txtnum;
    @FXML
    private Button btnsupprimer;
    @FXML
    private Button btnmodifier;
    @FXML
    private TextField txtrecherche;

    public ObservableList<Admin> dataAdmin = FXCollections.observableArrayList();

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
            ResultSet rs = cnx.createStatement().executeQuery("SELECT * FROM admin");
            while (rs.next()) {
                dataAdmin.add(new Admin(rs.getInt("id"),rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), rs.getString("mdp"), rs.getInt("numtel")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AfficherAdminsController.class.getName()).log(Level.SEVERE, null, ex);
        }

        table.setEditable(true);
        table.getSelectionModel().cellSelectionEnabledProperty().set(true);
        txtnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        txtnom.setCellFactory(TextFieldTableCell.forTableColumn());
        txtprenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        txtprenom.setCellFactory(TextFieldTableCell.forTableColumn());
        txtemail.setCellValueFactory(new PropertyValueFactory<>("email"));
        txtemail.setCellFactory(TextFieldTableCell.forTableColumn());
        txtmdp.setCellFactory(TextFieldTableCell.forTableColumn());
        txtnum.setCellValueFactory(new PropertyValueFactory<>("numtel"));
        txtnum.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        table.setItems(dataAdmin);
        
    }

    @FXML
    private void supprimerAdmins(ActionEvent event) {

        if (table.getSelectionModel().getSelectedItem() != null) {
            Alert deleteAdmintAlert = new Alert(Alert.AlertType.CONFIRMATION);
            deleteAdmintAlert.setTitle("Supprimer Admin");
            deleteAdmintAlert.setHeaderText(null);
            deleteAdmintAlert.setContentText("Voulez-vous vraiment supprimer cet Admin ? ?");
            Optional<ButtonType> optionDeleteBookAlert = deleteAdmintAlert.showAndWait();
            if (optionDeleteBookAlert.get() == ButtonType.OK) {
                Admin A = table.getSelectionModel().getSelectedItem();
                pc.supprimerAdmin(A.getId());
                dataAdmin.clear();
                dataAdmin.addAll(pc.affichageAdmin());
                //Alert Delete Blog :
                Alert succDeleteAdminAlert = new Alert(Alert.AlertType.INFORMATION);
                succDeleteAdminAlert.setTitle("Supprimer Admin");
                succDeleteAdminAlert.setHeaderText("Resultats:");
                succDeleteAdminAlert.setContentText("Admin supprimé !");
                succDeleteAdminAlert.showAndWait();
            } else if (optionDeleteBookAlert.get() == ButtonType.CANCEL) {
            }
        } else {
            Alert selectBookAlert = new Alert(Alert.AlertType.WARNING);
            selectBookAlert.setTitle("Choisir un Admin");
            selectBookAlert.setHeaderText(null);
            selectBookAlert.setContentText("Choisir un Admin avant de supprimer!");
            selectBookAlert.showAndWait();
        }
    }
    
    @FXML
    private void modifieradmins(ActionEvent event) {
        if (table.getSelectionModel().getSelectedItem() != null) {

            Admin ec = table.getSelectionModel().getSelectedItem();
            pc.updateAdmin(ec.getNom(),ec.getPrenom(),ec.getEmail(),ec.getMdp(),ec.getNumtel(),ec.getId());
            Alert BookAlert = new Alert(Alert.AlertType.INFORMATION);
            BookAlert.setTitle("Modifier");
            BookAlert.setHeaderText(null);
            BookAlert.setContentText("Admin Modifié");
            BookAlert.showAndWait();

        } else {

            Alert selectBookAlert = new Alert(Alert.AlertType.WARNING);
            selectBookAlert.setTitle("Selectionner un admin");
            selectBookAlert.setHeaderText(null);
            selectBookAlert.setContentText("Selectionner un Admin !");
            selectBookAlert.showAndWait();

            
        }
    }
}


