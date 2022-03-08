/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.JavaPIDEV.GUI;

import static edu.JavaPIDEV.GUI.NewFXMain.UserconnectedC;
import edu.JavaPIDEV.services.PanierCRUD;
import edu.connexion3a16.entities.Panier;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class PanierController implements Initializable {

    @FXML
    private TableView<Panier> tableview_panier;
    
    @FXML
    private TableColumn<Panier, Integer> col_id_panier;
    @FXML
    private TableColumn<Panier, Integer> col_ref_equipement;
    @FXML
    private TableColumn<Panier, Double> col_total_panier;
    @FXML
    private TableColumn<Panier, Integer> col_nbr_equipement;
    @FXML
    private TableColumn<Panier, Integer> col_prix_equipement;
    @FXML
    private TableColumn<Panier, Integer> col_CIN;
    
    private Parent fxml;
    private Scene scene;
    private Stage stage;
    int index=-1;
    ObservableList<Panier> listP;
    @FXML
    private Button modifier_panier;
    @FXML
    private Button supprimer_panier;
    @FXML
    private TextField txt_id_panier;
    @FXML
    private TextField txt_ref_equipement;
    @FXML
    private TextField txt_total_panier;
    @FXML
    private TextField txt_nbr_equipement;
    @FXML
    private TextField txt_prix_equipement;
    @FXML
    private TextField txt_CIN;
    @FXML
    private Button button_passer_commande;
    private TextField tfrecherche;
    ObservableList<Panier>list=FXCollections.observableArrayList();
    
    Panier pp =new Panier();
    @FXML
    private ImageView logo;
    @FXML
    private TextField SearchBar;
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

       
    /**
     * Initializes the controller class.
    
    * 
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        logo.setImage(new Image("file:C:\\Users\\MSI\\Desktop\\CampersDen\\src\\Images\\logo.png"));
                 
         
 
        // TODO
        PanierCRUD P=new PanierCRUD();
        
        col_id_panier.setCellValueFactory(new PropertyValueFactory<Panier,Integer>("id_panier"));
        col_ref_equipement.setCellValueFactory(new PropertyValueFactory<Panier,Integer>("ref_equipement"));
        col_total_panier.setCellValueFactory(new PropertyValueFactory<Panier, Double>("total_panier"));
        col_nbr_equipement.setCellValueFactory(new PropertyValueFactory<Panier,Integer>("nbr_equipement"));
        col_prix_equipement.setCellValueFactory(new PropertyValueFactory<Panier,Integer>("prix_equipement"));
        col_CIN.setCellValueFactory(new PropertyValueFactory<Panier,Integer>("cin"));
        
            listP=P.getPanier(UserconnectedC.getCin());
        tableview_panier.setItems(listP);
          
    }   

    @FXML
    private void getSelected(MouseEvent event) {
        index=tableview_panier.getSelectionModel().getSelectedIndex();
        if(index<= -1)
        {       
            return;
        }
        txt_id_panier.setText(col_id_panier.getCellData(index).toString());
        txt_ref_equipement.setText(col_ref_equipement.getCellData(index).toString());
        txt_total_panier.setText(col_total_panier.getCellData(index).toString());
        txt_nbr_equipement.setText(col_nbr_equipement.getCellData(index).toString());
        txt_prix_equipement.setText(col_prix_equipement.getCellData(index).toString());
        txt_CIN.setText(col_CIN.getCellData(index).toString());
        
        txt_id_panier.setDisable(true);
        txt_CIN.setDisable(true);
        txt_prix_equipement.setDisable(true);
        txt_ref_equipement.setDisable(true);
        txt_total_panier.setDisable(true);
        txt_nbr_equipement.setDisable(false);
        
        modifier_panier.setDisable(false);
        supprimer_panier.setDisable(false);
        
        
        
    }

    @FXML
    private void supprimer_panier(ActionEvent event) {
        
        if(txt_id_panier.getText().toString()!="")
        {
          PanierCRUD Pa=new PanierCRUD();
          Panier P=new Panier();
          P.setId_panier(Integer.parseInt(txt_id_panier.getText()));
          Pa.supprimerPanier(P);
          
            col_id_panier.setCellValueFactory(new PropertyValueFactory<Panier,Integer>("id_panier"));
            col_ref_equipement.setCellValueFactory(new PropertyValueFactory<Panier,Integer>("ref_equipement"));
            col_total_panier.setCellValueFactory(new PropertyValueFactory<Panier, Double>("total_panier"));
            col_nbr_equipement.setCellValueFactory(new PropertyValueFactory<Panier,Integer>("nbr_equipement"));
            col_prix_equipement.setCellValueFactory(new PropertyValueFactory<Panier,Integer>("prix_equipement"));
            col_CIN.setCellValueFactory(new PropertyValueFactory<Panier,Integer>("cin"));
        
            listP=Pa.getPanier(123);
            tableview_panier.setItems(listP);
            txt_nbr_equipement.setDisable(true);
            
        }
        
        modifier_panier.setDisable(true);
        supprimer_panier.setDisable(true);
//        

 TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            tray.setTitle("Add Success");
            tray.setMessage("article supprimée  ");
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(1000));

    }

    @FXML
    private void modifier_panier(ActionEvent event) {
        
        if(txt_id_panier.getText().toString()!="")
        {
            PanierCRUD Pa=new PanierCRUD();
            Panier P=new Panier();
            P.setId_panier(Integer.parseInt(txt_id_panier.getText()));
            P.setNbr_equipement(Integer.parseInt(txt_nbr_equipement.getText()));
            P.setPrix_equipement(Integer.parseInt(txt_prix_equipement.getText()));
            double total_panier=Integer.parseInt(txt_nbr_equipement.getText())*Integer.parseInt(txt_prix_equipement.getText());
            P.setTotal_panier(total_panier);
            P.setRef_equipement(Integer.parseInt(txt_ref_equipement.getText()));
            Pa.modifierPanier(P);
            modifier_panier.setDisable(true);
            supprimer_panier.setDisable(true);
            txt_nbr_equipement.setDisable(true);

        
            col_id_panier.setCellValueFactory(new PropertyValueFactory<Panier,Integer>("id_panier"));
            col_ref_equipement.setCellValueFactory(new PropertyValueFactory<Panier,Integer>("ref_equipement"));
            col_total_panier.setCellValueFactory(new PropertyValueFactory<Panier, Double>("total_panier"));
            col_nbr_equipement.setCellValueFactory(new PropertyValueFactory<Panier,Integer>("nbr_equipement"));
            col_prix_equipement.setCellValueFactory(new PropertyValueFactory<Panier,Integer>("prix_equipement"));
            col_CIN.setCellValueFactory(new PropertyValueFactory<Panier,Integer>("cin"));
        
            listP=Pa.getPanier(123);
            tableview_panier.setItems(listP);
            
        }}
        
    

    @FXML
    private void setTotalPanier(KeyEvent event) {
        
    txt_total_panier.setText(Integer.toString(Integer.parseInt(txt_nbr_equipement.getText())*Integer.parseInt(txt_prix_equipement.getText())));
    
    }

    @FXML
    private void passer_commande(ActionEvent event) throws IOException {
    
        PanierCRUD Pa=new PanierCRUD();
        Pa.sommePanier(123);

             FXMLLoader loader = new FXMLLoader(getClass().getResource("AddCommande.fxml"));
             fxml=loader.load();
             AddCommandeController addCommandecontroller=loader.getController();
             addCommandecontroller.setUtilisateur(123);
             stage=(Stage)((Node)event.getSource()).getScene().getWindow();
             scene=new Scene(fxml);
             stage.setScene(scene);
             stage.show();
 
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

   

    
    }

 
 

   

   
    

 

   

   
    

