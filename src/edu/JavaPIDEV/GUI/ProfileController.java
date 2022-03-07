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
import java.sql.SQLException;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class ProfileController implements Initializable {

    Connection cnx;
    @FXML
    private TextField fxsexe;

    public ProfileController() {
        cnx = MyConnection.getInstance().getCnx();
    }

    private String path;
    File selectedFile;
    ClientCRUD su = new ClientCRUD();
    @FXML
    private ImageView logo;
    @FXML
    private TextField SearchBar;
    @FXML
    private TextField fxcin;
    @FXML
    private TextField fxnom;
    @FXML
    private TextField fxsurnom;
    @FXML
    private TextField fxemail;
    @FXML
    private TextField fxadresse;
    @FXML
    private Button btnmodif;
    @FXML
    private DatePicker fxdate;
    @FXML
    private PasswordField fxmdp;
    @FXML
    private Button btnimage;
    @FXML
    private ImageView fximage;
    @FXML
    private Button btnsup;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        logo.setImage(new Image("file:C:\\Users\\MSI\\Desktop\\CampersDen\\src\\Images\\logo.png"));
        fximage.setImage(new Image("file:C:\\Users\\MSI\\Desktop\\CampersDen\\src\\Images\\import.gif"));

        fxcin.setText(String.valueOf(UserconnectedC.getCin()));
        fxnom.setText(UserconnectedC.getNomPrenom());
        fxsurnom.setText(UserconnectedC.getSurnom());
        fxsexe.setText(UserconnectedC.getSexe());
        fxemail.setText(UserconnectedC.getEmail());
        fxmdp.setText(UserconnectedC.getMdp());
        fxadresse.setText(UserconnectedC.getAdresse());
//        fximage.setImage(UserconnectedC.getImage());

    }

    @FXML
    private void changeScreenViewProd(MouseEvent event) {
    }

    @FXML
    private void changeScreenViewProm(MouseEvent event) {
    }

    @FXML
    private void changeScreenViewReclam(MouseEvent event) {
    }

    @FXML
    private void logout(ActionEvent event) {
        UserconnectedC.setCin(0);
        UserconnectedC.setNomPrenom("");
        UserconnectedC.setSurnom("");
        UserconnectedC.setSexe("");
        UserconnectedC.setEmail("");
        UserconnectedC.setMdp("");
        UserconnectedC.setAdresse("");
        UserconnectedC.setImage("");

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
    private void updateClients(ActionEvent event) {

        int cin = Integer.valueOf(fxcin.getText());
        String nomPrenom = fxnom.getText();
        String surnom = fxsurnom.getText();
        String sexe = fxsexe.getText();
        String email = fxemail.getText();
        String mdp = fxmdp.getText();
        java.sql.Date date = java.sql.Date.valueOf(fxdate.getValue());
        String adresse = fxadresse.getText();
//        String image = fximage.getText();

        try {

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
            Alert ModifyClientAlert = new Alert(Alert.AlertType.CONFIRMATION);
            ModifyClientAlert.setTitle("Modifier Votre Profile");
            ModifyClientAlert.setHeaderText(null);
            ModifyClientAlert.setContentText("Voulez-vous vraiment modifier votre prfile ?");
            Optional<ButtonType> optionModifyBookAlert = ModifyClientAlert.showAndWait();
            if (optionModifyBookAlert.get() == ButtonType.OK) {
                Client c = new Client(cin, nomPrenom, surnom, sexe, email, mdp, date, adresse, path);
                ClientCRUD ad = new ClientCRUD();
                ad.modifierClient(new Client(cin, nomPrenom, surnom, sexe, email, mdp, date, adresse, path));
                if (selectedFile != null) {
                    try {
                        File source = new File(selectedFile.toString());
                        File dest = new File("C:\\xampp\\htdocs\\images");
                        FileUtils.copyFileToDirectory(source, dest);
                    } catch (IOException e) {
                    }
                }
                System.out.println("Modifié avec succes");
                Alert succDeleteAdminAlert = new Alert(Alert.AlertType.INFORMATION);
                succDeleteAdminAlert.setTitle("Modifier votre profile");
                succDeleteAdminAlert.setHeaderText("Resultats:");
                succDeleteAdminAlert.setContentText("Profil Modifié !");
                succDeleteAdminAlert.showAndWait();
                Parent root = FXMLLoader.load(getClass().getResource("FrontAcc.fxml"));
                Scene scene = new Scene(root, 1363, 890);
                Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                appStage.setScene(scene);
                appStage.show();
            }
        } catch (Exception ex) {
            System.out.println("error : " + ex.getMessage());
        }

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
    private void sup(ActionEvent event) throws SQLException {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Suppression ");
        alert.setContentText("Voulez-vous vraiment supprimer Votre compte ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            if (su.supprimerC(UserconnectedC)) {
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
            } else {
            }

        }
    }

}
