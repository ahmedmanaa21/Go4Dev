/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.JavaPIDEV.GUI;

import edu.JavaPIDEV.services.AdminCRUD;
import edu.JavaPIDEV.services.ClientCRUD;
import edu.JavaPIDEV.services.MailerService;
//import edu.JavaPIDEV.services.MailerService;
import edu.JavaPIDEV.utils.MyConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Random;
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
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;




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
    
    Random rand = new Random();
    int randomcode = rand.nextInt(9999999);
    String code = String.valueOf(randomcode);
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
    ClientCRUD crud =new ClientCRUD();
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
    private void envouyercode(ActionEvent event) throws SQLException {
        cc=em.getText();
        if (crud.VerifyClientByEmail(cc)){
        try {
            
            String host = "smtp.gmail.com";
            String user = "testahmedahmed210@gmail.com";
            String pass = "CampersDen210";
            String to = em.getText();
            String subject = "Initialisation Code";

            String message = "Your reset code is " + randomcode;
            boolean sessionDebug = false;
            Properties pros = System.getProperties();
            pros.put("mail.smtp.starttls.enable", "true");
            pros.put("mail.smtp.host", "host");
            pros.put("mail.smtp.port", "587");
            pros.put("mail.smtp.auth", "true");
            pros.put("mail.smtp.starttls.required", "true");
            pros.put("mail.smtp.ssl.trust", "smtp.gmail.com");
            Session mailSession = Session.getDefaultInstance(pros, null);
            mailSession.setDebug(sessionDebug);
            Message msg = new MimeMessage(mailSession);
            msg.setFrom(new InternetAddress(user));
            InternetAddress[] address = {new InternetAddress(to)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(subject);
            msg.setText(message);
            Transport transport = mailSession.getTransport("smtp");
            transport.connect(host, user, pass);
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();
            JOptionPane.showMessageDialog(null, "Code envoyé");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        }else{
            
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Vous n'avez pas de compte, veuillez vous inscrire !");
        alert.show();
            
        }
    }
    @FXML
    private void getpass(ActionEvent event) {
        cc = em.getText();
        c = codeclient.getText();
        if(code.equals(c)){
            try {
                Parent root = FXMLLoader.load(getClass().getResource("changerpwd.fxml"));
                Scene scene = new Scene(root, 840, 615);
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
