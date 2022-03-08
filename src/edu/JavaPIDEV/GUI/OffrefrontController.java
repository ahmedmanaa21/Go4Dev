/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.JavaPIDEV.GUI;

import edu.JavaPIDEV.entities.Offre;
import edu.JavaPIDEV.services.OffreCRUD;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class OffrefrontController implements Initializable {
 OffreCRUD oft =new OffreCRUD() ;
    @FXML
    private TableView<Offre> offre;
    @FXML
    private TableColumn<Offre, Integer> id;
    @FXML
    private TableColumn<Offre, String> nom;
    @FXML
    private TableColumn<Offre, Double> prix;
    @FXML
    private TableColumn<Offre, Date> dated;
    @FXML
    private TableColumn<Offre, Date> datef;
    @FXML
    private TableColumn<Offre, String> pourcentage;
    @FXML
    private TableColumn<Offre, Integer> txtref;
    @FXML
    private TextField searchb;
    @FXML
    private Button fxretour;
      public ObservableList<Offre> data = FXCollections.observableArrayList();
    @FXML
    private Button btnpanier;
    @FXML
    private Button btnacc;
    @FXML
    private Button btnequip;
    @FXML
    private Button btnoffre;
    @FXML
    private Button btnrec;
    @FXML
    private Button btnrec1;
    @FXML
    private ImageView logo;
    @FXML
    private TextField SearchBar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        logo.setImage(new Image("file:C:\\Users\\MSI\\Desktop\\CampersDen\\src\\Images\\logo.png"));
//        fxlogout.setImage(new Image("file:C:\\Users\\MSI\\Desktop\\CampersDen\\src\\Images\\logout.png"));
        // TODO
          data = oft.affichagepromotion();
      
        id.setCellValueFactory(new PropertyValueFactory<>("id_promotion"));
    txtref.setCellValueFactory(new PropertyValueFactory<>("ref_equipement"));
    
    dated.setCellValueFactory(new PropertyValueFactory<>("date_debutpromo"));
    datef.setCellValueFactory(new PropertyValueFactory<>("date_finpromo"));
    pourcentage.setCellValueFactory(new PropertyValueFactory<>("Pourcentagepromo"));
    prix.setCellValueFactory(new PropertyValueFactory<>("Prix_equipement"));
       nom.setCellValueFactory(new PropertyValueFactory<>("Nom_equipement"));
    offre.setItems(data);
    offre.refresh(); 
        this.offre.setEditable(true);
        this.pourcentage.setCellFactory(TextFieldTableCell.forTableColumn());
    }    



    @FXML
    private void filtrer(ActionEvent event) {
                  data.clear();
        data.addAll(oft.affichagepromotion().stream().filter((art)
                -> art.getNom_equipement().toLowerCase().contains(searchb.getText().toLowerCase())
                || art.getPourcentagepromo().toLowerCase().contains(searchb.getText().toLowerCase())
        ).collect(Collectors.toList()));
    }
    

    @FXML
    private void retour(ActionEvent event) {
    }

    @FXML
    private void goToPanier(ActionEvent event) {
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
    private void changeScreenViewResev(ActionEvent event) {
    }

    @FXML
    private void logout(ActionEvent event) {
    }
    
}
