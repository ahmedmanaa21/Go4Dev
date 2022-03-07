/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.JavaPIDEV.GUI;

import static edu.JavaPIDEV.GUI.PswoubController.cc;
import edu.JavaPIDEV.services.AdminCRUD;
import edu.JavaPIDEV.services.ClientCRUD;
import edu.JavaPIDEV.utils.MyConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.Duration;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author majdi
 */
public class ChangerpwdController implements Initializable {
    
    Connection cnx;
    public ChangerpwdController(){
        cnx = MyConnection.getInstance().getCnx();
    }

    @FXML
    private PasswordField pass1;
    @FXML
    private PasswordField pass2;
    private Label err;
    @FXML
    private Button cha;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //err.setText(cc);
    }

    public void closeChangerpwd() {
        Stage Acceuil2Stage = (Stage) pass1.getScene().getWindow();
        Acceuil2Stage.hide();
    }

    @FXML
    private void changer(ActionEvent event) throws SQLException {
        String p1 = pass1.getText();
        String p2 = pass2.getText();

        ClientCRUD crud = new ClientCRUD();

        if (p1.equals(p2)) {

            crud.updatemdp(cc, p1);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Mot de passe modifié !");
            alert.show();

            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("Login.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                //stage.setTitle("Inscription");
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                System.out.println("Erreur chh !!!");
            }

        } else {
            err.setText("Password False !!");
        }

    }
}
