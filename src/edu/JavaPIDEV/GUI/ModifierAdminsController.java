/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.JavaPIDEV.GUI;

import static edu.JavaPIDEV.GUI.NewFXMain.Userconnected;
import static edu.JavaPIDEV.GUI.NewFXMain.UserconnectedC;
import edu.JavaPIDEV.entities.Admin;
import edu.JavaPIDEV.services.AdminCRUD;
import edu.JavaPIDEV.utils.MyConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class ModifierAdminsController implements Initializable {
    
    Connection cnx;
    public ModifierAdminsController(){
        cnx = MyConnection.getInstance().getCnx();
    }

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField fxnom;
    @FXML
    private TextField fxprenom;
    @FXML
    private TextField fxemail;
    @FXML
    private PasswordField fxmdp;
    @FXML
    private TextField fxnum;
    @FXML
    private TextField fxid;
    @FXML
    private Button btnmodif;
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
        // TODO
    }

    @FXML
    private void UpdateAdmin(ActionEvent event) {

        int id = Integer.valueOf(fxid.getText());
        String nom = fxnom.getText();
        String prenom = fxprenom.getText();
        String email = fxemail.getText();
        String mdp = fxmdp.getText();
        int num = Integer.valueOf(fxnum.getText());
//        java.sql.Date date = java.sql.Date.valueOf(Mdate_rec.getValue());
        try {

            System.out.println("Modifié avec succes");
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
            Alert ModifyAdmintAlert = new Alert(Alert.AlertType.CONFIRMATION);
            ModifyAdmintAlert.setTitle("Modifier Admin");
            ModifyAdmintAlert.setHeaderText(null);
            ModifyAdmintAlert.setContentText("Voulez-vous vraiment supprimer cet Admin ? ?");
            Optional<ButtonType> optionModifyBookAlert = ModifyAdmintAlert.showAndWait();
            if (optionModifyBookAlert.get() == ButtonType.OK) {
                Admin r = new Admin(id, nom, prenom, email, mdp, num);
                AdminCRUD ad = new AdminCRUD();
                ad.updateAdmin(nom, prenom, email, mdp, num, id);
                Alert succModifyAdminAlert = new Alert(Alert.AlertType.INFORMATION);
                succModifyAdminAlert.setTitle("Modifier Admin");
                succModifyAdminAlert.setHeaderText("Resultats:");
                succModifyAdminAlert.setContentText("Admin Modifié !");
                succModifyAdminAlert.showAndWait();
            }else if (optionModifyBookAlert.get() == ButtonType.CANCEL) {
            }
            

        } catch (Exception ex) {
            System.out.println("error : " + ex.getMessage());
        }

    }

    public void setData(int id, String nom, String prenom, String email, String mdp, int num) {

        fxid.setText("" + id);
        fxnom.setText("" + nom);
        // java.sql.Date date = java.sql.Date.valueOf(date.getValue());
        fxprenom.setText("" + prenom);
        fxemail.setText("" + email);
        fxmdp.setText("" + mdp);
        fxnum.setText("" + num);
        // Mscreenshot.setImage(screenshot);

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
        Parent tableViewParent=FXMLLoader.load(getClass().getResource("afficherAdmins.fxml"));
        Scene tableViewScene= new Scene(tableViewParent);
        Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }

    @FXML
    private void changeScreenClient(ActionEvent event) throws IOException {
        Parent tableViewParent=FXMLLoader.load(getClass().getResource("afficherClients.fxml"));
        Scene tableViewScene= new Scene(tableViewParent);
        Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
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
