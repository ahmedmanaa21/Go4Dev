/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.JavaPIDEV.GUI;

import edu.JavaPIDEV.services.AdminCRUD;
import edu.JavaPIDEV.services.MailerService;
import edu.JavaPIDEV.utils.MyConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;




/**
 * FXML Controller class
 *
 * @author majdi
 */
public class PswoubController implements Initializable {
    Connection cnx;
    public PswoubController(){
        cnx = MyConnection.getInstance().getCnx();
    }
    
    MailerService email = new MailerService();
    public static String c;
    public static String cc;
    @FXML
    private TextField codeclient;
    @FXML
    private Button verify;
    @FXML
    private TextField em;
    @FXML
    private Button envoyer;
    public static String code;
    AdminCRUD crud =new AdminCRUD();
    @FXML
    private Button ret;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        code = email.getPassword();
//        email.sendEmail("ahmed.manaa@esprit.tn",code);

        // TODO    
    }    
 @FXML
    private void envouyercode(ActionEvent event) throws SQLException, Exception {
        cc = em.getText();
//        code = email.getPassword();
        code = "A1Z2E" ;
        if (crud.VerifyUserByEmail(cc)){
        String nom = "User";
        String message = "Bonjour Mr/Mme " + nom + "votre code est : "  + code + "\n Bonne Journée! ";
        email.replyMail(cc, nom, message);
//        email.sendEmail(cc, code);
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Check Your Email !!!");
        alert.show();
             
        }else{
            
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("You Don't Have Account !!!");
        alert.show();
            
        }
    }
    @FXML
    private void getpass(ActionEvent event) {
        cc = em.getText();
        c = codeclient.getText();
        if(code.equals(c)){
            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("changerpwd.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                //stage.setTitle("Inscription");
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                 System.err.println("Erreur");
            }
         
        }else{    
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Enter Correct Code !!!");
        alert.show();
        }
    }

    @FXML
    private void retour(ActionEvent event) {
    }

   
    
}
