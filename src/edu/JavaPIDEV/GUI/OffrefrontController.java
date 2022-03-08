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
import javafx.scene.image.ImageView;

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
    private ImageView checkref;
    @FXML
    private ImageView checkdatedeb;
    @FXML
    private ImageView checkdatefin;
    @FXML
    private ImageView checkpourcentage;
    @FXML
    private TextField searchb;
    @FXML
    private Button fxretour;
      public ObservableList<Offre> data = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
    
}
