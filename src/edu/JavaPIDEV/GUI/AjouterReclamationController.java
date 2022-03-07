/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.JavaPIDEV.GUI;
//import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.io.FileUtils;
//import org.apache.commons.dbutils;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.Loader;
import edu.JavaPIDEV.entities.Reclamation;
import edu.JavaPIDEV.services.ReclamationCRUD;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;



/**
 * FXML Controller class
 *
 * @author Nesrine
 */
public class AjouterReclamationController implements Initializable {
    private String path;

//    @FXML
//    private TextField id_rec;
    @FXML
    private ComboBox<String> type_rec;
    ObservableList<String> listRoles = FXCollections.observableArrayList("reservation", "Compte", "commande", "Autre");
    @FXML
    private TextField cin;
    @FXML
    private DatePicker date_rec;
    @FXML
    private TextArea description;
     @FXML
    private ImageView screenshot;
     File selectedFile;
     @FXML
    private Button image;
    @FXML
    private Button AjouterRec;
    @FXML
    private ImageView checkDES;
    
    String erreur;
    @FXML
    private Button retour;
    @FXML
    private TextField email;
    @FXML
    private ImageView checkmail;

    /**
     * Initializes the controller class.
     */
    public void initialize(URL url, ResourceBundle rb) {
        
        ReclamationCRUD rec = new ReclamationCRUD();
        
        rec.archiverReclamation();
        // TODO
        screenshot.setOnDragOver(new EventHandler<DragEvent>() {
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
        screenshot.setOnDragDropped(new EventHandler<DragEvent>() {
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
                        System.out.println("Drag and drop file done and path=" + file.getAbsolutePath());//file.getAbsolutePath()="C:\Users\X\Desktop\ScreenShot.6.png"
                        screenshot.setImage(new Image("file:" + file.getAbsolutePath()));
//                        screenshot.setFitHeight(150);
//                        screenshot.setFitWidth(250);
                        image.setText(path);
                    }
                }
                event.setDropCompleted(success);
                event.consume();
            }
        });
         screenshot.setImage(new Image("file:C:\\Users\\Jasser BOUKRAYA\\Documents\\NetBeansProjects\\JavaPIDEV\\src\\Images\\upload.gif"));
        
        type_rec.setPromptText("Veuillez selectionner un type");
        type_rec.setItems(listRoles);
    }
  @FXML
    private void image(ActionEvent event) throws MalformedURLException {
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
            screenshot.setImage(new Image(selectedFile.toURI().toURL().toString()));
            screenshot.setFitHeight(150);
            screenshot.setFitWidth(250);
            image.setText(path);

        }

    }
    @FXML
    private void AddRec(ActionEvent event) throws IOException {
   if(testSaisie()){   
        //int id = Integer.valueOf(id_rec.getText());
        int CIN = Integer.valueOf(cin.getText());
        String Description = description.getText();
        String TYPE =type_rec.getValue().toString();
        java.sql.Date date = java.sql.Date.valueOf(date_rec.getValue());
        String Email = email.getText();
        try{
        Alert ModifyRECLAMATIONAlert = new Alert(Alert.AlertType.CONFIRMATION);
            ModifyRECLAMATIONAlert.setTitle("Modifier Réclamation");
            ModifyRECLAMATIONAlert.setHeaderText(null);
            ModifyRECLAMATIONAlert.setContentText("Veuillez modifier cette Réclamation ?");
            Optional<ButtonType> optionModifyBookAlert = ModifyRECLAMATIONAlert.showAndWait();
            if (optionModifyBookAlert.get() == ButtonType.OK){
        
          Reclamation rr = new Reclamation(TYPE, Description, date, CIN,path,Email);
        ReclamationCRUD RC = new ReclamationCRUD();
        RC.ajouterReclamation(rr);
        System.out.println("ajouté avec succes");
        
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
        FXMLLoader loader=new FXMLLoader(getClass().getResource("AfficherReclamation.fxml"));
        Parent root = loader.load();     
      
        
        //fx_nom.getScene().setRoot(root);
        
//        FXMLLoader Loader = new FXMLLoader(getClass().getResource("AfficherReclamation.fxml"));
//        Parent root;
//        try {
//            root = Loader.load();
//            AfficherReclamationController ac = Loader.getController();
//         // ac.setList(RC.affichageReclamation().toString());
//            id_rec.getScene().setRoot(root);
//        } catch (IOException ex) {
//            System.out.println(ex.getMessage());
//        }
    }
    }
    
    private Boolean testDescription() {
       
        for (int i = 1; i < description.getText().trim().length(); i++) {
            char ch = description.getText().charAt(i);
            
        }

        if ( description.getText().trim().length() >= 10) {
            checkDES.setImage(new Image("Images/correct.gif"));
            
            
            return true;
        } else {
           checkDES.setImage(new Image("Images/wrong.png"));
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
        if (email.getText() == null) {
            return false;
        }

        if (pat.matcher(email.getText()).matches() == false) {
            checkmail.setImage(new Image("Images/wrong.png"));
//                erreur = erreur + ("Veuillez verifier votre adresse email\n");
            return false;
//            

        } else {
            checkmail.setImage(new Image("Images/verifier.png"));
        }
        return true;

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

}