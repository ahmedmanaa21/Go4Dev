/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.JavaPIDEV.GUI;

import edu.JavaPIDEV.entities.Equipement;
import edu.JavaPIDEV.services.EquipementCRUD;
import edu.JavaPIDEV.utils.equipementutils;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;
import org.apache.commons.io.FileUtils;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class AjouterequipementController implements Initializable {
    String erreur;
    private String path ;
 @FXML
    private TextField fx_nom;
    @FXML
    private TextField fx_prix;
    @FXML
    private TextField fx_descri;
    public ObservableList<Equipement> data = FXCollections.observableArrayList();
   
    @FXML
    private ImageView imaagee;
    File selectedFile;
    @FXML
    private Button boutonimage;
    @FXML
    private Button fx_bouton;
    @FXML
    private Button fxretour;
    @FXML
    private ImageView cntrl;
    
    public String filename="";
    @FXML
    private AnchorPane displayArea;
    @FXML
    private TextField idm;
    @FXML
    private ImageView fxlogout;
    @FXML
    private ImageView fxlogo;
    
    public AjouterequipementController() {
    }
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fxlogo.setImage(new Image("file:C:\\Users\\MSI\\Desktop\\CampersDen\\src\\Images\\logo.png"));
        fxlogout.setImage(new Image("file:C:\\Users\\MSI\\Desktop\\CampersDen\\src\\Images\\logout.png"));
      
    } 
    
    
    
  
    @FXML
    public void addequipement(ActionEvent event) throws IOException, AddressException, MessagingException {
        equipementutils eu = new equipementutils();
        String nom = fx_nom.getText() ;
        String description = fx_descri.getText() ;
        String prix =fx_prix.getText();
        
        
        String nom_equipement =fx_nom.getText();
        
      //  Double prix_equipement = Double.valueOf(fx_prix.getText());
        String description_equipement =fx_descri.getText();
        
       
        EquipementCRUD pc = new EquipementCRUD();
        
        
        if( nom.isEmpty() )
        {alert_Box("Verifier le nom de l'equipement", "Le nom ne doit pas être vide");}
else if( !eu.testNom(nom) ) 
       {alert_Box("Verifier le nom de l'equipement","le nom doit contenir que des caracteres");}


else if ( description.isEmpty() )
        {alert_Box("Verifier la description de l'equipement", "La description  ne doit pas être vide");}
 
        
        else if ( prix.isEmpty() )
        {alert_Box("Verifier le prix d article", "Le titre ne doit pas être vide");}
        else if( !eu.testPrix(prix) ) 
       {alert_Box("Verifier le prix de l'article","il doit contenir que des chiffre");}
        
        
        
        
        
        else {  
            
            Double prix_equipement =Double.valueOf(prix);
        Equipement e = new Equipement (nom_equipement,filename,prix_equipement,description_equipement);
        pc.ajouterequipement(e);
        TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            tray.setTitle("Add Success");
            tray.setMessage("offre ajouter avec succes ");
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(1000));

       FXMLLoader loader=new FXMLLoader(getClass().getResource("Afficherequipement.fxml"));
        Parent root = loader.load();     
        fx_nom.getScene().setRoot(root);
        //pc.modifierequipement(e);
    }
    }
    public void information_Box(String title, String message) {
        Alert dg = new Alert(Alert.AlertType.INFORMATION);
        dg.setTitle(title);
        dg.setContentText(message);
        dg.show();
    }

    public void alert_Box(String title, String message) {
        Alert dg = new Alert(Alert.AlertType.WARNING);
        dg.setTitle(title);
        dg.setContentText(message);
        dg.show();
    }
//}

    @FXML
    private void upload(ActionEvent event) throws MalformedURLException {
 
        byte[] reclamation_image = null;
        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilterJPG
                = new FileChooser.ExtensionFilter("JPG files (*.JPG)", "*.JPG");
        FileChooser.ExtensionFilter extFilterjpg
                = new FileChooser.ExtensionFilter("jpg files (*.jpg)", "*.jpg");
        FileChooser.ExtensionFilter extFilterPNG
                = new FileChooser.ExtensionFilter("PNG files (*.PNG)", "*.PNG");
        FileChooser.ExtensionFilter extFilterpng
                = new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
        fileChooser.getExtensionFilters()
                .addAll(extFilterJPG, extFilterjpg, extFilterPNG, extFilterpng);
        //Show open file dialog
        File file = fileChooser.showOpenDialog(null);

        try {                                    
            BufferedImage bufferedImage = ImageIO.read(file);
            
            filename = file.getName();
            String path = "C:\\Users\\MSI\\Desktop\\CampersDen\\src\\Images\\" + filename;
             File outputfile = new File(path);
                //save  
            ImageIO.write(bufferedImage,"png" , outputfile);
            
            WritableImage image = SwingFXUtils.toFXImage(bufferedImage, null);
            imaagee.setImage(image);
            imaagee.setFitWidth(200);
            imaagee.setFitHeight(200);
            imaagee.scaleXProperty();
            imaagee.scaleYProperty();
            imaagee.setSmooth(true);
            imaagee.setCache(true);
            FileInputStream fin = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];

            for (int readNum; (readNum = fin.read(buf)) != -1;) {
                bos.write(buf, 0, readNum);
            }
            reclamation_image = bos.toByteArray();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }}

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

    

