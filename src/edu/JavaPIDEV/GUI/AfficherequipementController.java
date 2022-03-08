/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.JavaPIDEV.GUI;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import edu.JavaPIDEV.entities.Equipement;
import edu.JavaPIDEV.services.EquipementCRUD;
import edu.JavaPIDEV.services.pdfequipement;
import edu.JavaPIDEV.utils.MyConnection;



import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import edu.JavaPIDEV.utils.MyConnection;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import static javafx.scene.input.KeyCode.S;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import javax.mail.Message;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class AfficherequipementController implements Initializable {
 EquipementCRUD pc = new EquipementCRUD();
    @FXML
    private TableView<Equipement>table;
    @FXML
    private TableColumn<Equipement,String> txtnom;
    @FXML
    private TableColumn<Equipement,Integer> txtref;
    @FXML
    private TableColumn<Equipement,String> txtimage;
  
    public ObservableList<Equipement> data = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Equipement,Double> txtprix;
    @FXML
    private TableColumn<Equipement, String > txtdescri;
    @FXML
    private TextField searcha;
    @FXML
    private Button fxretour;
    @FXML
    private ImageView imageview;
    @FXML
    private Button btndelete;
    @FXML
    private Button btnupdate;
    @FXML
    private Button pdf;
    Connection cnx;
    @FXML
    private AnchorPane displayArea;
    @FXML
    private TextField idm;
    @FXML
    private ImageView fxlogout;
    @FXML
    private ImageView fxlogo;
    public AfficherequipementController() {
        cnx = MyConnection.getInstance().getCnx();
    }

    
    
    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fxlogo.setImage(new Image("file:C:\\Users\\MSI\\Desktop\\CampersDen\\src\\Images\\logo.png"));
        fxlogout.setImage(new Image("file:C:\\Users\\MSI\\Desktop\\CampersDen\\src\\Images\\logout.png"));
        
        
        /**
         * 
         * */
         
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
        
        //edit 
        this.table.setEditable(true);
        this.txtnom.setCellFactory(TextFieldTableCell.forTableColumn());
        // this.txtimage.setCellFactory(TextFieldTableCell.forTableColumn());
        this.txtprix.setCellFactory(TextFieldTableCell.<Equipement, Double>forTableColumn(new DoubleStringConverter()));
        this.txtdescri.setCellFactory(TextFieldTableCell.forTableColumn());
       
    }
 


    @FXML
    private void supprimer(ActionEvent event) {
        
        
         if (table.getSelectionModel().getSelectedItem() != null) {
            Alert deleteEventAlert = new Alert(Alert.AlertType.CONFIRMATION);
            deleteEventAlert.setTitle("Delete equipement");
            deleteEventAlert.setHeaderText(null);
            deleteEventAlert.setContentText("Are you sure want to delete this Equipement ?");
            Optional<ButtonType> optionDeleteBookAlert = deleteEventAlert.showAndWait();
            if (optionDeleteBookAlert.get() == ButtonType.OK) {
                Equipement e = table.getSelectionModel().getSelectedItem();
                pc.supprimerequipement(e.getRef_equipement());
                data.clear();
                data.addAll(pc.affichageEquipement());
                //Alert Delete Blog :
                Alert succDeleteEventAlert = new Alert(Alert.AlertType.INFORMATION);
                succDeleteEventAlert.setTitle("Delete Equipement");
                succDeleteEventAlert.setHeaderText("Results:");
                succDeleteEventAlert.setContentText("Equipement deleted successfully!");

                succDeleteEventAlert.showAndWait();
            } else if (optionDeleteBookAlert.get() == ButtonType.CANCEL) {

            }

        } else {

            //Alert Select BOOK :
            Alert selectBookAlert = new Alert(Alert.AlertType.WARNING);
            selectBookAlert.setTitle("Select Equipement");
            selectBookAlert.setHeaderText(null);
            selectBookAlert.setContentText("You need to select equipement first!");
            selectBookAlert.showAndWait();
            //Alert Select Book !

        } 
           
        
    }

    @FXML
    private void Modifier(ActionEvent event) {
        
        
         if (table.getSelectionModel().getSelectedItem() != null) {

            Equipement e = table.getSelectionModel().getSelectedItem();

            pc.modifierequipement(e);
            Alert EventAlert = new Alert(Alert.AlertType.INFORMATION);
            EventAlert.setTitle("edit");
            EventAlert.setHeaderText(null);
            EventAlert.setContentText("equipement was succfuly edit");
            EventAlert.showAndWait();

        } else {
         
            Alert selectEventAlert = new Alert(Alert.AlertType.WARNING);
            selectEventAlert.setTitle("Select an equipement");
            selectEventAlert.setHeaderText(null);
            selectEventAlert.setContentText("You need to select equipement first!");
            selectEventAlert.showAndWait();
            //Alert Select Book !
        }
    }

    
    

    
    @FXML
    private void changeprix(CellEditEvent edittedCell) {
         Equipement equipementSelected = table.getSelectionModel().getSelectedItem();
        equipementSelected.setPrix_equipement(Double.parseDouble(edittedCell.getNewValue().toString()));
    }

    @FXML
    private void changedescri(CellEditEvent edittedCell) {
                 Equipement equipementSelected = table.getSelectionModel().getSelectedItem();
        equipementSelected.setDescription_equipement(edittedCell.getNewValue().toString());
    }

  
   

    @FXML
    private void changenomCellEvent(CellEditEvent edittedCell) {
         Equipement equipementSelected = table.getSelectionModel().getSelectedItem();
        equipementSelected.setNom_equipement(edittedCell.getNewValue().toString());
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
    private void pdf(ActionEvent event) throws DocumentException, FileNotFoundException {
       Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de création du PDF");
        alert.setHeaderText("vous voulez creer une pdf qui contient la liste des equipements ?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
             pdfequipement pdfeq= new pdfequipement();
        pdfeq.liste_equipement();
        }
    }

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
    private void qrcode(ActionEvent event) throws SQLException {
            
          QRcodeController q=new QRcodeController();
       q.ini(table.getSelectionModel().getSelectedItem());
    }

    @FXML
    private void afficherr(MouseEvent event) {
    }

    @FXML
    private void logout(ActionEvent event) {
    }

    @FXML
    private void changeScreenAdmin(ActionEvent event) {
    }

    @FXML
    private void changeScreenClient(ActionEvent event) {
    }

    @FXML
    private void changeScreenReclamations(ActionEvent event) {
    }

    @FXML
    private void changeScreenZoneCamp(ActionEvent event) {
    }

    @FXML
    private void changeScreenOfre(ActionEvent event) {
    }

    @FXML
    private void changeScreenCommandes(ActionEvent event) {
    }

    @FXML
    private void changeScreenEvent(ActionEvent event) {
    }

    @FXML
    private void changeScreenEquip(ActionEvent event) {
    }

    @FXML
    private void changeScreenPanier(ActionEvent event) {
    }
    }
   
    


