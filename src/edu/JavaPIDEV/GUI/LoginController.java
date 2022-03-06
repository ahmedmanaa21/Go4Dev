package edu.JavaPIDEV.GUI;

import static edu.JavaPIDEV.GUI.NewFXMain.Userconnected;
import static edu.JavaPIDEV.GUI.NewFXMain.UserconnectedC;
import java.sql.SQLException;

import edu.JavaPIDEV.utils.MyConnection;
import java.awt.HeadlessException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.Window;
import javax.swing.JOptionPane;

public class LoginController {
    
    Connection cnx;
    public LoginController(){
        cnx = MyConnection.getInstance().getCnx();
    }
    
    private Statement stm;
    private PreparedStatement pst;
    private ResultSet rs;

    @FXML
    private TextField txtemail;
    @FXML
    private PasswordField txtmdp;
    @FXML
    private Button btnconn;
    @FXML
    private Button btninscri;
    @FXML
    private ImageView fximage;
    @FXML
    private Button btnmdpoub;

    public void initialize(URL url, ResourceBundle rb) {
        fximage.setImage(new Image("file:C:\\Users\\MSI\\Desktop\\CampersDen\\src\\Images\\logindesk.jpg"));
    }

    @FXML
    public void login(ActionEvent event) throws SQLException, IOException {
        
        String sqlC = "Select * from client where email  = ? and mdp  = ?";
        
        try {

            pst = cnx.prepareStatement(sqlC);
            pst.setString(1, txtemail.getText());
            pst.setString(2, txtmdp.getText());
            rs = pst.executeQuery();
            if (rs.next()) {
                UserconnectedC.setCin(rs.getInt(1));
                UserconnectedC.setNomPrenom(rs.getString(2));
                UserconnectedC.setSurnom(rs.getString(3));
                UserconnectedC.setEmail(rs.getString(4));
                UserconnectedC.setMdp(rs.getString(5));
                UserconnectedC.setDateNaissance(rs.getDate(6));
                UserconnectedC.setAdresse(rs.getString(7));
                UserconnectedC.setImage(rs.getString(8));
                btnconn.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("FrontAcc.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
            } else {
                JOptionPane.showMessageDialog(null, "Invalide email or password");
            }
        } catch (HeadlessException | SQLException e) { 
        }
        
        String sql = "Select * from admin where email  = ? and mdp  = ?";
        
        try {

            pst = cnx.prepareStatement(sql);
            pst.setString(1, txtemail.getText());
            pst.setString(2, txtmdp.getText());
            rs = pst.executeQuery();
            if (rs.next()) {
                Userconnected.setId(rs.getInt(1));
                Userconnected.setNom(rs.getString(2));
                Userconnected.setPrenom(rs.getString(3));
                Userconnected.setEmail(rs.getString(4));
                Userconnected.setMdp(rs.getString(5));
                Userconnected.setNumtel(rs.getInt(6));
                btnconn.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("afficherAdmins.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
            } else {
                JOptionPane.showMessageDialog(null, "Invalide email or password");
            }
        } catch (HeadlessException | SQLException e) {

        }

    }

    public static void infoBox(String infoMessage, String headerText, String title) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }

    private static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

    @FXML
    private void inscri(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("ajouterAdmins.fxml"));
            Scene scene = new Scene(root, 1100, 650);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(scene);
            appStage.show();
        } catch (IOException ex) {
            ex.getMessage();
        }
    }

//    public static Admin FindUserPassword(String email) {
//    
//    Connection cnx = new MyConnection().getCnx();
//    Admin user = new Admin();
//    try {
//      PreparedStatement stmt = cnx.prepareStatement("Select password from admin where email=?");
//      stmt.setString(1, email);
//      ResultSet rs = stmt.executeQuery();
//      if (rs.next()) {
//        
//        user.setMdp(rs.getString("mdp"));
//      }
//    } catch (Exception e) {
//      // TODO Auto-generated catch block
//      e.printStackTrace();
//    }
//    return user;
//    
//  }
    @FXML
    private void mdpoub(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("pswoub.fxml"));
            Scene scene = new Scene(root, 1100, 650);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(scene);
            appStage.show();
        } catch (IOException ex) {
            ex.getMessage();
        }
    }
}
