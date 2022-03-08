/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.JavaPIDEV.GUI;

import edu.JavaPIDEV.entities.Equipement;
import edu.JavaPIDEV.services.EquipementCRUD;
import edu.JavaPIDEV.utils.MyConnection;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class FrontequipementController implements Initializable {

    @FXML
    private TableView<Equipement> table;
    @FXML
    private TableColumn<Equipement, Integer> txtref;
    @FXML
    private TableColumn<Equipement, String> txtnom;
    @FXML
    private TableColumn<Equipement, String> txtimage;
    @FXML
    private TableColumn<Equipement, Double> txtprix;
    @FXML
    private TableColumn<Equipement, String> txtdescri;
    @FXML
    private Label lbltotal;
    @FXML
    private TextField searcha;
    @FXML
    private ImageView imageview;
     public ObservableList<Equipement> data = FXCollections.observableArrayList();
       EquipementCRUD pc = new EquipementCRUD();
       Connection cnx;
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
    public FrontequipementController() {
        cnx = MyConnection.getInstance().getCnx();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
//        logo.setImage(new Image("file:C:\\Users\\MSI\\Desktop\\CampersDen\\src\\Images\\logo.png"));
        
            
    try {
            ResultSet rs = cnx.createStatement().executeQuery("SELECT ref_equipement ,nom_equipement , image,prix_equipement , description_equipement FROM Equipement");
            while(rs.next())
            {
                data.add(new Equipement( rs.getInt("ref_equipement"),rs.getString("nom_equipement"), rs.getString("image"), rs.getDouble("prix_equipement"),rs.getString("description_equipement")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AfficherequipementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    txtref.setCellValueFactory(new PropertyValueFactory<>("ref_equipement"));
    txtnom.setCellValueFactory(new PropertyValueFactory<>("nom_equipement"));
    txtimage.setCellValueFactory(new PropertyValueFactory<>("image"));
    txtprix.setCellValueFactory(new PropertyValueFactory<>("prix_equipement"));
    txtdescri.setCellValueFactory(new PropertyValueFactory<>("description_equipement"));
    table.setItems(data);
        // TODO
    }    


    @FXML
    private void affichageimage(MouseEvent event) {
          Equipement e =table.getSelectionModel().getSelectedItem();
        
        try {
            Image image = new Image(new FileInputStream("C:\\Users\\MSI\\Desktop\\CampersDen\\src\\Images\\"+e.getImage()));
           
        imageview.setImage(image);
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void filter(ActionEvent event) {
         data.clear();
        // System.out.println("heyy yuuu");
        data.addAll(pc.affichageEquipement().stream().filter((art)
                -> art.getNom_equipement().toLowerCase().contains(searcha.getText().toLowerCase())
                || art.getImage().toLowerCase().contains(searcha.getText().toLowerCase())
        ).collect(Collectors.toList()));
    }


    @FXML
    private void afficher(MouseEvent event) {
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
