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
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
 * @author Nesrine
 */
public class AjouterClientsController implements Initializable {
    
    Connection cnx;
    @FXML
    private ImageView checkSexe;
    @FXML
    private TextField fxsexe;
    public AjouterClientsController(){
        cnx = MyConnection.getInstance().getCnx();
    }
            
    private String path;
    private String erreur;
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
    private Button btnajouter;
    @FXML
    private Button btnimage;
    @FXML
    private Button btnretour;
    @FXML
    private AnchorPane displayArea;
    @FXML
    private TextField idm;
    @FXML
    private ImageView fxlogout;
    @FXML
    private ImageView fxlogo;
    @FXML
    private ImageView checkCin;
    @FXML
    private ImageView checkNom;
    @FXML
    private ImageView checkSurnom;
    @FXML
    private ImageView checkEmail;
    @FXML
    private ImageView checkAdresse;
    @FXML
    private ImageView checkMdp;
    /**
     * Initializes the controller class.
     */
    public void initialize(URL url, ResourceBundle rb) {
        fximage.setImage(new Image("file:C:\\Users\\MSI\\Desktop\\CampersDen\\src\\Images\\import.gif"));
        fxlogo.setImage(new Image("file:C:\\Users\\MSI\\Desktop\\CampersDen\\src\\Images\\logo.png"));
        fxlogout.setImage(new Image("file:C:\\Users\\MSI\\Desktop\\CampersDen\\src\\Images\\logout.png"));
    }

    @FXML
    private void addClient(ActionEvent event) {
        if(testSaisie()){
        int cin = Integer.valueOf(fxcin.getText());
        String nom = fxnom.getText();
        String surnom = fxsurnom.getText();
        String email = fxemail.getText();
        String sexe = fxsexe.getText();
        String mdp = fxmdp.getText();
        java.sql.Date date = java.sql.Date.valueOf(fxdate.getValue());
        String adresse = fxadresse.getText();
        Client C = new Client(cin,nom,surnom,sexe,email,mdp,date,adresse,path);
        ClientCRUD pc = new ClientCRUD();
        pc.ajouterClient(C);
        if (selectedFile != null) {
            try{
            File source = new File(selectedFile.toString());
            File dest = new File("C:\\xampp\\htdocs\\images");
            FileUtils.copyFileToDirectory(source, dest);
             } catch (IOException err) {
                    err.printStackTrace();
                }
            }
        FXMLLoader Loader = new FXMLLoader(getClass().getResource("afficherClients.fxml"));
        Parent root;
        try {
            root = Loader.load();
            AfficherClientsController ac = Loader.getController();
            fxnom.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
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
//                path = selectedFile.toURI().toURL().toExternalForm();
            fximage.setImage(new Image(selectedFile.toURI().toURL().toString()));
            fximage.setFitHeight(150);
            fximage.setFitWidth(250);
            btnimage.setText(path);

        }
    }
private Boolean testNom() {
        
        String nomReg = "^[a-zA-Z\\s]+";

        Pattern pat = Pattern.compile(nomReg);
        if (fxnom.getText() == null) {
            return false;
        }

        if (pat.matcher(fxnom.getText()).matches() == false) {
            checkNom.setImage(new Image("Images/ErreurcheckMark.png"));
            erreur = erreur + ("Veuillez verifier votre surnom\n");
            return false;
//            

        } else {
            checkNom.setImage(new Image("Images/checkMark.png"));
        }
        return true;

    }

    private Boolean testEmail() {

        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."
                + "[a-zA-Z0-9_+&*-]+)*@"
                + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
                + "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (fxemail.getText() == null) {
            return false;
        }

        if (pat.matcher(fxemail.getText()).matches() == false) {
            checkEmail.setImage(new Image("Images/ErreurcheckMark.png"));
            erreur = erreur + ("Veuillez verifier votre adresse email\n");
            return false;
//            

        } else {
            checkEmail.setImage(new Image("Images/checkMark.png"));
        }
        return true;

    }
    private Boolean testSurnom() {

        String surnomReg = "^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){3,18}[a-zA-Z0-9]$";

        Pattern pat = Pattern.compile(surnomReg);
        if (fxsurnom.getText() == null) {
            return false;
        }

        if (pat.matcher(fxsurnom.getText()).matches() == false) {
            checkSurnom.setImage(new Image("Images/ErreurcheckMark.png"));
//            erreur = erreur + ("Veuillez verifier votre surnom\n");
            return false;           

        } else {
            checkSurnom.setImage(new Image("Images/checkMark.png"));
        }
        return true;

    }
    private Boolean testmdp() {

        String mdpReg = "^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){3,18}[a-zA-Z0-9]$";

        Pattern pat = Pattern.compile(mdpReg);
        if (fxmdp.getText() == null) {
            return false;
        }

        if (pat.matcher(fxmdp.getText()).matches() == false) {
            checkMdp.setImage(new Image("Images/ErreurcheckMark.png"));
//            erreur = erreur + ("Veuillez verifier votre surnom\n");
            return false;           

        } else {
            checkMdp.setImage(new Image("Images/checkMark.png"));
        }
        return true;

    }
    private Boolean testCin() {
        if (fxcin.getText().trim().length() == 8) {
            int nbChar = 0;
            for (int i = 1; i < fxcin.getText().trim().length(); i++) {
                char ch = fxcin.getText().charAt(i);
                if (Character.isLetter(ch)) {
                    nbChar++;
                }
            }

            if (nbChar == 0) {
                checkCin.setImage(new Image("Images/checkMark.png"));
                
                return true;
            } else {
                checkCin.setImage(new Image("Images/ErreurcheckMark.png"));
                erreur = erreur + ("Pas de caractere permit dans le Cin\n");
                return false;

            }
        } else if (fxcin.getText().trim().length() != 8) {
            erreur = erreur + ("Il faut saisir 8 chiffres dans le numéro de Cin\n");
                            checkCin.setImage(new Image("Images/ErreurcheckMark.png"));

            return false;
        }

        return true;
    }
    private Boolean testSaisie() {
        erreur = "";
        
       
        if (!testNom()) {
            erreur = erreur + ("Veuillez verifier votre Nom \n");
        }
        if (!testSurnom()) {
            erreur = erreur + ("Veuillez verifier votre surnom \n");
        }
        if (!testEmail()) {
            erreur = erreur + ("Veuillez verifier votre email \n");
        }
        if (!testCin()) {
            erreur = erreur + ("Veuillez verifier votre Numero de Cin : seulement des nombres = 8 \n");
        }
        if (!testmdp()) {
            erreur = erreur + ("Veuillez verifier votre Mot de passe \n");
        }

        return   testNom() && testCin() && testSurnom() && testEmail() && testmdp()  ;
    }

    @FXML
    private void retour(ActionEvent event) {
        try {

            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();

            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("afficherClients.fxml")));
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            System.err.println(ex.getMessage());
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
