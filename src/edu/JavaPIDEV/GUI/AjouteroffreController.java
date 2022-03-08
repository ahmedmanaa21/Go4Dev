/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.JavaPIDEV.GUI;

import edu.JavaPIDEV.entities.Offre;
import edu.JavaPIDEV.services.OffreCRUD;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class AjouteroffreController implements Initializable {

    OffreCRUD oft =new OffreCRUD() ;
    @FXML
    private DatePicker combodated;
    @FXML
    private DatePicker combodatef;
    @FXML
    private ComboBox<String> txtpourcentage;
    ObservableList<String>  listpromo = FXCollections.observableArrayList("5%","10%","15%","20%","25%","30%","35%","40%","45%","50%","55%","60%","65%","70%","75%","80%","85%","90%","95%","100%");

    @FXML
    private TableView<Offre> offre;
    @FXML
    private TextField ref;
    
    
    
    @FXML
    private TableColumn<Offre,Integer> txtref;
    @FXML
    private TableColumn<Offre,Date> dated;
    @FXML
    private TableColumn<Offre, Date> datef;
    @FXML
    private TableColumn<Offre, String> pourcentage;
    public ObservableList<Offre> data = FXCollections.observableArrayList();
    @FXML
    private Button fxretour;
    @FXML
    private TableColumn<Offre, Double> prix;
    @FXML
    private TableColumn<?, ?> id;
    String erreur ; 
    @FXML
    private ImageView checkref;
    @FXML
    private ImageView checkdatedeb;
    @FXML
    private ImageView checkdatefin;
    @FXML
    private ImageView checkpourcentage;
    @FXML
    private TableColumn<Offre, String> nom;
    @FXML
    private TextField searchb;
    @FXML
    private AnchorPane displayArea;
    @FXML
    private TextField idm;
    @FXML
    private ImageView fxlogout;
    @FXML
    private ImageView fxlogo;
    /**
     * Initializes the controller class.
     */
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fxlogo.setImage(new Image("file:C:\\Users\\MSI\\Desktop\\CampersDen\\src\\Images\\logo.png"));
        fxlogout.setImage(new Image("file:C:\\Users\\MSI\\Desktop\\CampersDen\\src\\Images\\logout.png"));
        
        
        
        
        
        
        
        
        

        
     // OffreCRUD off = new OffreCRUD();
        data = oft.affichagepromotion();
        txtpourcentage.setPromptText("selectionner votre promotion");
        txtpourcentage.setItems(listpromo);
        id.setCellValueFactory(new PropertyValueFactory<>("id_promotion"));
    txtref.setCellValueFactory(new PropertyValueFactory<>("ref_equipement"));
    
    dated.setCellValueFactory(new PropertyValueFactory<>("date_debutpromo"));
    datef.setCellValueFactory(new PropertyValueFactory<>("date_finpromo"));
    pourcentage.setCellValueFactory(new PropertyValueFactory<>("Pourcentagepromo"));
    prix.setCellValueFactory(new PropertyValueFactory<>("Prix_equipement"));
       nom.setCellValueFactory(new PropertyValueFactory<>("Nom_equipement"));
    offre.setItems(data);
    offre.refresh(); 
        this.offre.setEditable(true);
        this.pourcentage.setCellFactory(TextFieldTableCell.forTableColumn());
   //      this.combodated.setDayCellFactory((Callback<DatePicker, DateCell>) dated);
      offre.refresh();
    }   
  

   


    @FXML
    private void DeleteAction(ActionEvent event) {
         if (offre.getSelectionModel().getSelectedItem() != null) {
            Alert deleteEventAlert = new Alert(Alert.AlertType.CONFIRMATION);
            deleteEventAlert.setTitle("Delete offre");
            deleteEventAlert.setHeaderText(null);
            deleteEventAlert.setContentText("Are you sure want to delete this offre ?");
            Optional<ButtonType> optionDeleteBookAlert = deleteEventAlert.showAndWait();
            if (optionDeleteBookAlert.get() == ButtonType.OK) {
                Offre f = offre.getSelectionModel().getSelectedItem();
                 oft.supprimeroffre(f.getId_promotion());
                data.clear();
                 data.addAll(oft.affichagepromotion());
               // data.addAll(oft.affichagepromotion());
                //Alert Delete Blog :
                Alert succDeleteEventAlert = new Alert(Alert.AlertType.INFORMATION);
                succDeleteEventAlert.setTitle("Delete offre");
                succDeleteEventAlert.setHeaderText("Results:");
                succDeleteEventAlert.setContentText("offre deleted successfully!");

                succDeleteEventAlert.showAndWait();
            } else if (optionDeleteBookAlert.get() == ButtonType.CANCEL) {

            }

        } else {

            //Alert Select BOOK :
            Alert selectBookAlert = new Alert(Alert.AlertType.WARNING);
            selectBookAlert.setTitle("Select offre");
            selectBookAlert.setHeaderText(null);
            selectBookAlert.setContentText("You need to select offre first!");
            selectBookAlert.showAndWait();
            //Alert Select Book !

        } 
           
  
    }

    @FXML
    private void EditAction(ActionEvent event) {
             if (offre.getSelectionModel().getSelectedItem() != null) {

            Offre O = offre.getSelectionModel().getSelectedItem();

            oft.modifieroffre(O);
            
            Alert EventAlert = new Alert(Alert.AlertType.INFORMATION);
            EventAlert.setTitle("edit");
            EventAlert.setHeaderText(null);
            EventAlert.setContentText("offre was succfuly edit");
            EventAlert.showAndWait();

        } else {
         
            Alert selectEventAlert = new Alert(Alert.AlertType.WARNING);
            selectEventAlert.setTitle("Select an offre");
            selectEventAlert.setHeaderText(null);
            selectEventAlert.setContentText("You need to select offre first!");
            selectEventAlert.showAndWait();
            //Alert Select Book !
        }
    }
    

    @FXML
    private void addoffre(ActionEvent event) throws IOException, AddressException, MessagingException {
        
        if (testSaisie()){
            int ref_equipement = Integer.valueOf(ref.getText());
        
        java.sql.Date date_debutpromo = java.sql.Date.valueOf(combodated.getValue());
        java.sql.Date date_finpromo = java.sql.Date.valueOf(combodatef.getValue());
       // String pourcentagepromo = txtpourcentage.getText();
        String pourcentagepromo = txtpourcentage.getValue().toString();

        Offre off = new Offre(ref_equipement,date_debutpromo,date_finpromo,pourcentagepromo);
        OffreCRUD of = new OffreCRUD();
        of.ajouteroffre(off);

            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            tray.setTitle("Add Success");
            tray.setMessage("offre ajouter avec succes ");
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(1000));
            
            String host = "smtp.gmail.com";
            String user = "testahmedahmed210@gmail.com";
            String pass = "CampersDen210";
            String to = "maissa.choura@esprit.tn";
            String subject = "Nouveau offre !";
            String message = "Nouveau offre ! " ;
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
            JOptionPane.showMessageDialog(null, "Nouveau offre !");
        
         FXMLLoader loader=new FXMLLoader(getClass().getResource("Ajouteroffre.fxml"));
        Parent root = loader.load();
        
       of.affichagepromotion();
      
        }

    }

    @FXML
    private void refresh(ActionEvent event) {
        data.clear();
                OffreCRUD cl = new OffreCRUD();
        data=cl.affichagepromotion();
        cl.affichagepromotion();
        
        offre.setItems(data);
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
    private void changepourcentage(CellEditEvent edittedCell) {
        Offre offreSelected = offre.getSelectionModel().getSelectedItem();
        offreSelected.setPourcentagepromo(edittedCell.getNewValue().toString());
    }
    
    
    

    private Boolean testDateDeb() {
        
        LocalDate now = LocalDate.now();
        if ( combodated.getValue()!= null && combodated.getValue().compareTo(now) >= 0 ) {
                checkdatedeb.setImage(new Image("images/checkMark.png"));
                return true;
            } else {
                checkdatedeb.setImage(new Image("images/ErreurcheckMark.png"));
            }
                return false;
    }
      private Boolean testDateFin() {
          
        if ( combodatef.getValue()!= null && combodatef.getValue().compareTo(combodated.getValue()) > 0 ) {
                checkdatefin.setImage(new Image("images/checkMark.png"));
                return true;
            } else {
                checkdatefin.setImage(new Image("images/ErreurcheckMark.png"));
            }
                return false;
    }
      private Boolean testpourcentage() {
      
      if(txtpourcentage.getSelectionModel().getSelectedItem() != null ){
          checkpourcentage.setImage(new Image("images/checkMark.png"));
                return true;
            } else {
                checkpourcentage.setImage(new Image("images/ErreurcheckMark.png"));
            }
                return false;
      }
      
      
      
      
      
      
      
private Boolean testSaisie() {
        erreur = "";
 
        if (!testDateDeb()) {
            erreur = erreur + ("Veuillez verifier votre Date début: seulement une date non dépassée \n");
        }
        if (!testDateFin()) {
            erreur = erreur + ("Veuillez verifier votre Date Fin: seulement une date après la date du début \n");
        }
        if (!testpourcentage()) {
            erreur = erreur + ("Veuillez verifier votre pourcentage: seulement un entier inférieur  à 90 \n");
        }

        

        return   testDateDeb() && testDateFin() && testpourcentage();
    }

    @FXML
    private void filtrer(ActionEvent event) {
        
          data.clear();
        data.addAll(oft.affichagepromotion().stream().filter((art)
                -> art.getNom_equipement().toLowerCase().contains(searchb.getText().toLowerCase())
                || art.getPourcentagepromo().toLowerCase().contains(searchb.getText().toLowerCase())
        ).collect(Collectors.toList()));
    }
    
//    private void noti() {
//        Notifications notificationBuilder = Notifications.create()
//                .title("alert").text("succés").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
//                .position(Pos.CENTER_LEFT)
//                .onAction(new EventHandler<ActionEvent>(){
//                    public void handle(ActionEvent event)
//                    {
//                        System.out.println("clicked ON");
//                    }});
//        notificationBuilder.darkStyle();
//        notificationBuilder.show();
//    }

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



       
   

        
        
        
        
        
        
        
        
    
    

