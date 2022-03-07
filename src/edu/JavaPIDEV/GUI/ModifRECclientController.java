/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.JavaPIDEV.GUI;

import edu.JavaPIDEV.entities.Reclamation;
import edu.JavaPIDEV.services.ReclamationCRUD;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import static java.util.Arrays.stream;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;

/**
 * FXML Controller class
 *
 * @author Jasser BOUKRAYA
 */
public class ModifRECclientController implements Initializable {
private String path;
    @FXML
    private Button Moimage;
    @FXML
    private DatePicker Modate_rec;
    @FXML
    private TextArea Modescription;
    @FXML
    private Button MOdif;
    @FXML
    private ImageView Moscreenshot;
    File selectedFile;
    @FXML
    private ComboBox<String> Motype_rec;
    ObservableList<String> listRoless = FXCollections.observableArrayList("reservation", "Compte", "commande", "Autre");
    
    @FXML
    private ImageView imagecheck;
    String erreur;

    @FXML
    private TextField MoEmail;
    @FXML
    private TextField MoidREC;
    @FXML
    private ImageView checkmail;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
          MoidREC.setDisable(true);
        Moscreenshot.setImage(new Image("file:C:\\Users\\Jasser BOUKRAYA\\Documents\\NetBeansProjects\\JavaPIDEV\\src\\Images\\upload.gif"));

         Motype_rec.setPromptText("Veuillez selectionner un type");
        Motype_rec.setItems(listRoless);
        
        
         Moscreenshot.setOnDragOver(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                if (db.hasFiles()) {
                    event.acceptTransferModes(TransferMode.COPY);
                } else {
                    event.consume();
                }
            }
        });
        
        // Dropping over surface
        Moscreenshot.setOnDragDropped(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                boolean success = false;
                if (db.hasFiles()) {
                    success = true;
                    path = null;
                    for (File file : db.getFiles()) {
                        path = file.getName();
                        selectedFile = new File(file.getAbsolutePath());
                        System.out.println("Drag and drop file done and path=" + file.getAbsolutePath());
                        Moscreenshot.setImage(new Image("file:" + file.getAbsolutePath()));
//                        screenshot.setFitHeight(150);
//                        screenshot.setFitWidth(250);
                        Moimage.setText(path);
                    }
                }
                event.setDropCompleted(success);
                event.consume();
            }
        });
        
        
    }  
    
    public void setDataa(int id_rec,String type_rec, String description_rec, Date date_rec,String screenshot, String email) throws FileNotFoundException {
 
        MoidREC.setText("" + id_rec);
        //Motype_rec.setPromptText(""+type_rec);
        LocalDate lDate = date_rec.toLocalDate();
        Modate_rec.setValue(lDate);
        Modescription.setText(""+description_rec);
       // Mcin.setText(""+cin);
        MoEmail.setText(""+email);
//        InputStream Stream = new FileInputStream(screenshot);
//        Image image= new Image(Stream);
//        Moscreenshot.setImage(image);
        
       
    }

    @FXML
    private void ModifImage(ActionEvent event) throws MalformedURLException {
         FileChooser fc = new FileChooser(); 
        fc.setInitialDirectory(new File(System.getProperty("user.home") + "\\Desktop"));
        fc.setTitle("Veuillez choisir l'image");
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image", "*.jpg", "*.png"),
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg")
        );
        selectedFile = fc.showOpenDialog(null);

        if (selectedFile != null) {

            path = selectedFile.getName();
//                path = selectedFile.toURI().toURL().toExternalForm();
            Moscreenshot.setImage(new Image(selectedFile.toURI().toURL().toString()));
            Moscreenshot.setFitHeight(150);
            Moscreenshot.setFitWidth(250);
            Moimage.setText(path);

        }
    }

    @FXML
    private void MODIFrec(ActionEvent event) {
        
        if(testSaisie()){
        int id=Integer.valueOf(MoidREC.getText());
//        int CIN = Integer.valueOf(Mocin.getText());
        String Description = Modescription.getText();
        String TYPE =Motype_rec.getValue().toString();
        java.sql.Date date = java.sql.Date.valueOf(Modate_rec.getValue());        
        String Email =MoEmail.getText();
       try{
        
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
        
        Alert ModifyRECLAMATIONAlert = new Alert(Alert.AlertType.CONFIRMATION);
            ModifyRECLAMATIONAlert.setTitle("Modifier Réclamation");
            ModifyRECLAMATIONAlert.setHeaderText(null);
            ModifyRECLAMATIONAlert.setContentText("Veuillez modifier cette Réclamation ?");
            Optional<ButtonType> optionModifyBookAlert = ModifyRECLAMATIONAlert.showAndWait();
            if (optionModifyBookAlert.get() == ButtonType.OK) {
                Reclamation rrc = new Reclamation(id, TYPE, Description, date, id, path, Email);
        ReclamationCRUD RC = new ReclamationCRUD();
        
        RC.modifierReclamation(new Reclamation(id, TYPE, Description, date, id, path, Email));
        System.out.println("Modifié avec succes");
        if (selectedFile != null) {
            try{
            File source = new File(selectedFile.toString());
            File dest = new File("J:\\xampp\\htdocs\\images");
            FileUtils.copyFileToDirectory(source, dest);
             } catch (IOException e) {
                    e.printStackTrace();
                }
            }
                System.out.println("Modifié avec succes");
                Alert succDeleteAdminAlert = new Alert(Alert.AlertType.INFORMATION);
                succDeleteAdminAlert.setTitle("Modifier Réclamation");
                succDeleteAdminAlert.setHeaderText("Resultats:");
                succDeleteAdminAlert.setContentText("Réclamation Modifié !");
                succDeleteAdminAlert.showAndWait();
            }
        } catch (Exception ex) {
            System.out.println("error : " + ex.getMessage());
        }
        
           
            
        }
    }

  private Boolean testDescription() {
       
        for (int i = 1; i < Modescription.getText().trim().length(); i++) {
            char ch = Modescription.getText().charAt(i);
            
        }

        if ( Modescription.getText().trim().length() >= 10) {
            imagecheck.setImage(new Image("Images/correct.gif"));
            
            
            return true;
        } else {
           imagecheck.setImage(new Image("Images/wrong.png"));
            return false;

        }

    }
    
         private Boolean testSaisie() {
        erreur = "";
       
        
        if (!testDescription()) {
            erreur = erreur + ("Veuillez verifier votre description: seulement des caractères et de nombre >= 10 \n");
        }
        if (!testMail()) {
            erreur = erreur + ("Veuillez verifier votre EMAIL \n");
        }
        return testDescription() && testMail();
    }
    private Boolean testMail() {

        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."
                + "[a-zA-Z0-9_+&*-]+)*@"
                + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
                + "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (MoEmail.getText() == null) {
            return false;
        }

        if (pat.matcher(MoEmail.getText()).matches() == false) {
            checkmail.setImage(new Image("Images/wrong.png"));
//                erreur = erreur + ("Veuillez verifier votre adresse email\n");
            return false;
//            

        } else {
            checkmail.setImage(new Image("Images/verifier.png"));
        }
        return true;

    }
}
