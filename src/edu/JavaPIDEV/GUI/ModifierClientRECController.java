/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.JavaPIDEV.GUI;

import java.io.File;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Properties;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Jasser BOUKRAYA
 */
public class ModifierClientRECController implements Initializable {

    @FXML
    private TextField Mcin;
    @FXML
    private Button Mimage;
    @FXML
    private DatePicker Mdate_rec;
    @FXML
    private TextArea Mdescription;
    @FXML
    private Button RepondreRec;
    @FXML
    private ImageView Mscreenshot;
    File selectedFile;

    @FXML
    private ComboBox<String> Mtype_rec;
    ObservableList<String> listRoles = FXCollections.observableArrayList("reservation", "Compte", "commande", "Autre");
    @FXML
    private ImageView imagecheck;
    String erreur;
    @FXML
    private TextField MEmail;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         Mcin.setDisable(true);
         MEmail.setDisable(true);
         Mdate_rec.setDisable(true);
//         Mdescription.setDisable(true);
         Mtype_rec.setDisable(true);
         
//   Mscreenshot.setImage(new Image("file:C:\\Users\\Jasser BOUKRAYA\\Documents\\NetBeansProjects\\JavaPIDEV\\src\\Images\\upload.gif"));

         Mtype_rec.setPromptText("Veuillez selectionner un type");
        Mtype_rec.setItems(listRoles);
    }    
     public void setData(String type_rec, String description_rec, Date date_rec, int cin ,String screenshot, String email) {
 
   // Mid_rec.setText("" + id_rec);
       
        LocalDate lDate = date_rec.toLocalDate();
        Mdate_rec.setValue(lDate);
        Mdescription.setText(""+description_rec);
        Mcin.setText(""+cin);
        MEmail.setText(""+email);
        Mtype_rec.setPromptText(""+type_rec);
        //Mtype_rec.setItems(type_rec);
        //Mscreenshot.setImage(new Image(AfficherReclamationController.selectionedReclamation.getScreenshot()));
        //screenshotView.setFitHeight(150);
        //screenshotView.setFitWidth(250);
//        path=new File(AfficherReclamationController.selectionedReclamation.getScreenshot()).getName();
//        image.setText(path);
      //  Mscreenshot.setImage(screenshot);
       
    }


    @FXML
    private void ModifImage(ActionEvent event) {
    }

    @FXML
    private void RepondreREC(ActionEvent event) {
        
        try {
            Random rand = new Random();
         //   int randomcode = rand.nextInt(9999);
            String host = "smtp.gmail.com";
            String user = "jasser.boukraya@esprit.tn";
            String pass = "191JFT2024";
            String to = MEmail.getText();
            String subject = "Initialisation Code";

            String message = "Votre Réclamation est prise en considération";
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
        
    }
    
}
