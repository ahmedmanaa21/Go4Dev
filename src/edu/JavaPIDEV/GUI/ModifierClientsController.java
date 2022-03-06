/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.JavaPIDEV.GUI;

import static edu.JavaPIDEV.GUI.NewFXMain.Userconnected;
import static edu.JavaPIDEV.GUI.NewFXMain.UserconnectedC;
import edu.JavaPIDEV.entities.Client;
import edu.JavaPIDEV.services.ClientCRUD;
import edu.JavaPIDEV.utils.MyConnection;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class ModifierClientsController implements Initializable {
    
    Connection cnx;
    public ModifierClientsController(){
        cnx = MyConnection.getInstance().getCnx();
    }

    /**
     * Initializes the controller class.
     */
    private String path;

    @FXML
    private TextField fxcin;
    @FXML
    private TextField fxnom;
    @FXML
    private TextField fxsurnom;
    @FXML
    private TextField fxemail;
    @FXML
    private PasswordField fxmdp;
    @FXML
    private DatePicker fxdate;
    @FXML
    private TextField fxadresse;
    @FXML
    private ImageView fximage;
    File selectedFile;
    @FXML
    private Button btnmodif;
    @FXML
    private Button btnimage;
    @FXML
    private AnchorPane displayArea;
    @FXML
    private TextField idm;
    @FXML
    private ImageView fxlogout;
    @FXML
    private ImageView fxlogo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fxlogo.setImage(new Image("file:C:\\Users\\MSI\\Desktop\\CampersDen\\src\\Images\\logo.png"));
        fxlogout.setImage(new Image("file:C:\\Users\\MSI\\Desktop\\CampersDen\\src\\Images\\logout.png"));
        fximage.setImage(new Image("file:C:\\Users\\MSI\\Desktop\\CampersDen\\src\\Images\\import.gif"));
    }

    @FXML
    private void updateClients(ActionEvent event) {

        int cin = Integer.valueOf(fxcin.getText());
        String nomPrenom = fxnom.getText();
        String surnom = fxsurnom.getText();
        String email = fxemail.getText();
        String mdp = fxmdp.getText();
        java.sql.Date date = java.sql.Date.valueOf(fxdate.getValue());
        String adresse = fxadresse.getText();
//        String image = fximage.getText();

        try {

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
            Alert ModifyClientAlert = new Alert(Alert.AlertType.CONFIRMATION);
            ModifyClientAlert.setTitle("Modifier Client");
            ModifyClientAlert.setHeaderText(null);
            ModifyClientAlert.setContentText("Voulez-vous vraiment modifier cet Client ?");
            Optional<ButtonType> optionModifyBookAlert = ModifyClientAlert.showAndWait();
            if (optionModifyBookAlert.get() == ButtonType.OK) {
                Client c = new Client(cin, nomPrenom, surnom, email, mdp, date, adresse, path);
                ClientCRUD ad = new ClientCRUD();
                ad.modifierClient(new Client(cin, nomPrenom, surnom, email, mdp, date, adresse, path));
                if (selectedFile != null) {
                    try {
                        File source = new File(selectedFile.toString());
                        File dest = new File("C:\\xampp\\htdocs\\images");
                        FileUtils.copyFileToDirectory(source, dest);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Modifié avec succes");
                Alert succDeleteAdminAlert = new Alert(Alert.AlertType.INFORMATION);
                succDeleteAdminAlert.setTitle("Modifier Client");
                succDeleteAdminAlert.setHeaderText("Resultats:");
                succDeleteAdminAlert.setContentText("Client Modifié !");
                succDeleteAdminAlert.showAndWait();
            }
        } catch (Exception ex) {
            System.out.println("error : " + ex.getMessage());
        }

    }

    public void setData(int cin, String nomPrenom, String surnom, String email, String mdp, Date date, String adresse, String image) {

        fxcin.setText("" + cin);
        fxnom.setText("" + nomPrenom);
        fxsurnom.setText("" + surnom);
        fxemail.setText("" + email);
        fxmdp.setText("" + mdp);
//        java.sql.Date.valueOf(fxdate.getValue());
        fxadresse.setText("" + adresse);
//        fximage.setImage(image);

    }

    @FXML
    private void dropImage(ActionEvent event) throws MalformedURLException {
        FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File(System.getProperty("user.home") + "\\Desktop"));
        fc.setTitle("Veuillez choisir l'image");
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image", ".jpg", ".png"),
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg")
        );
        selectedFile = fc.showOpenDialog(null);

        if (selectedFile != null) {

            path = selectedFile.getName();
            path = selectedFile.toURI().toURL().toExternalForm();
            fximage.setImage(new Image(selectedFile.toURI().toURL().toString()));
            fximage.setFitHeight(150);
            fximage.setFitWidth(250);
            btnimage.setText(path);

        }
    }

    @FXML
    private void logout(ActionEvent event) {
        UserconnectedC.setCin(0);
        UserconnectedC.setNomPrenom("");
        UserconnectedC.setSurnom("");
        UserconnectedC.setEmail("");
        UserconnectedC.setMdp("");
        UserconnectedC.setAdresse("");
        UserconnectedC.setImage("");

        Userconnected.setId(0);
        Userconnected.setNom("");
        Userconnected.setPrenom("");
        Userconnected.setEmail("");
        Userconnected.setMdp("");
        Userconnected.setNumtel(0);

        FXMLLoader LOADER = new FXMLLoader(getClass().getResource("login.fxml"));
        try {
            Parent root = LOADER.load();
            Scene sc = new Scene(root);
            LoginController cntr = LOADER.getController();
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(sc);
            window.show();
        } catch (IOException ex) {

        }
    }

    @FXML
    private void changeScreenAdmin(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("afficherAdmins.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }

    @FXML
    private void changeScreenClient(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("afficherClients.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
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
