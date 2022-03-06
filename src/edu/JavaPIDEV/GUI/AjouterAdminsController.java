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
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Nesrine
 */
public class AjouterAdminsController implements Initializable {
    
    Connection cnx;
    public AjouterAdminsController(){
        cnx = MyConnection.getInstance().getCnx();
    }

    String erreur;
    
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
    private Button btnajouter;
    @FXML
    private ImageView checkNom;
    @FXML
    private ImageView checkNum;
    @FXML
    private ImageView checkPrenom;
    @FXML
    private ImageView emailCheckMark;
    @FXML
    private ImageView checkMdp;
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

    /**
     * Initializes the controller class.
     */
    public void initialize(URL url, ResourceBundle rb) {
       
        fxlogo.setImage(new Image("file:C:\\Users\\MSI\\Desktop\\CampersDen\\src\\Images\\logo.png"));
        fxlogout.setImage(new Image("file:C:\\Users\\MSI\\Desktop\\CampersDen\\src\\Images\\logout.png"));
//        int maxLength = 8;
//        fxnum.textProperty().addListener(new ChangeListener(fxnum, maxLength));
        
    }

    
    @FXML
    private void addAdmin(ActionEvent event) {
        if(testSaisie()){
        String nom = fxnom.getText();
        String prenom = fxprenom.getText();
        String email = fxemail.getText();
        String mdp = fxmdp.getText();
        int numtel = Integer.valueOf(fxnum.getText());
        Admin A = new Admin(nom,prenom,email,mdp,numtel);
        AdminCRUD pc = new AdminCRUD();
        pc.ajouterAdmin(A);
        
        FXMLLoader Loader = new FXMLLoader(getClass().getResource("afficherAdmins.fxml"));
        Parent root;
        try {
            root = Loader.load();
            AfficherAdminsController ac = Loader.getController();
            fxnom.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        }
    }
    private Boolean testNom() {
         String nomReg = "^[a-zA-Z]+";

        Pattern pat = Pattern.compile(nomReg);
        if (fxnom.getText() == null) {
            return false;
        }

        if (pat.matcher(fxnom.getText()).matches() == false) {
            checkNom.setImage(new Image("Images/ErreurcheckMark.png"));
            erreur = erreur + ("Veuillez verifier votre nom\n");
            return false;
//            

        } else {
            checkNom.setImage(new Image("Images/checkMark.png"));
        }
        return true;

    }
    private Boolean testPrenom() {
         int nbNonChar = 0;
        for (int i = 1; i < fxprenom.getText().trim().length(); i++) {
            char ch = fxprenom.getText().charAt(i);
            if (!Character.isLetter(ch)) {
                nbNonChar++;
            }
        }

        if (nbNonChar == 0 && fxnom.getText().trim().length() >= 3) {
            checkPrenom.setImage(new Image("Images/checkMark.png"));
            
            return true;
        } else {
            checkPrenom.setImage(new Image("Images/erreurCheckMark.png"));
          
//                erreur = erreur + ("Pas de caractere permit dans le telephone\n");
            return false;

        }

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
            emailCheckMark.setImage(new Image("Images/ErreurcheckMark.png"));
            erreur = erreur + ("Veuillez verifier votre adresse email\n");
            return false;
//            

        } else {
            emailCheckMark.setImage(new Image("Images/checkMark.png"));
        }
        return true;

    }
    private Boolean testTel() {
        if (fxnum.getText().trim().length() == 8) {
            int nbChar = 0;
            for (int i = 1; i < fxnum.getText().trim().length(); i++) {
                char ch = fxnum.getText().charAt(i);
                if (Character.isLetter(ch)) {
                    nbChar++;
                }
            }

            if (nbChar == 0) {
                checkNum.setImage(new Image("Images/checkMark.png"));
                
                return true;
            } else {
                checkNum.setImage(new Image("Images/ErreurcheckMark.png"));
//                erreur = erreur + ("Pas de caractere permit dans le telephone\n");
                return false;

            }
        } else if (fxnum.getText().trim().length() != 8) {
//            erreur = erreur + ("Il faut saisir 8 chiffres dans le numéro de telephone\n");
                            checkNum.setImage(new Image("Images/ErreurcheckMark.png"));

            return false;
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
    private Boolean testSaisie() {
        erreur = "";
        
       
        if (!testNom()) {
            erreur = erreur + ("Veuillez verifier votre Nom \n");
        }
        if (!testPrenom()) {
            erreur = erreur + ("Veuillez verifier votre Prenom \n");
        }
        if (!testEmail()) {
            erreur = erreur + ("Veuillez verifier votre Email\n");
        }
        if (!testmdp()) {
            erreur = erreur + ("Veuillez verifier votre Mot de passe\n");
        }
        if (!testTel()) {
            erreur = erreur + ("Veuillez verifier votre Numero de tel\n");
        }

        return   testNom() && testPrenom() && testEmail() && testmdp() && testTel()  ;
    }

    @FXML
    private void retour(ActionEvent event) {
        try {

            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();

            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("afficherAdmins.fxml")));
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
