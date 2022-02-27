package edu.JavaPIDEV.GUI;

import java.sql.SQLException;

import edu.JavaPIDEV.GUI.jdbcLogin;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Window;

public class LoginController {

    @FXML
    private TextField txtemail;

    @FXML
    private PasswordField txtmdp;
    @FXML
    private Label txtmdpoub;

    @FXML
    private Button btnconn;
    @FXML
    private Button btninscri;

    @FXML
    public void login(ActionEvent event) throws SQLException {

        Window owner = btnconn.getScene().getWindow();
        
        System.out.println(txtemail.getText());
        System.out.println(txtmdp.getText());

        if (txtemail.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please enter your email id");
            return;
        }
        if (txtmdp.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please enter a password");
            return;
        }

        String email = txtemail.getText();
        String password = txtmdp.getText();

        jdbcLogin login = new jdbcLogin();
        boolean flag = login.validate(email, password);

        if (!flag) {
            infoBox("Please enter correct Email and Password", null, "Failed");
        } else {
            infoBox("Login Successful!", null, "Failed");
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
}