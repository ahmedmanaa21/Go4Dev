package edu.JavaPIDEV.GUI;

import edu.JavaPIDEV.GUI.AjouterAdminsController;
import edu.JavaPIDEV.GUI.AjouterClientsController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author broum
 */
public class NewFXMain extends Application {

    @Override
    public void start(Stage primaryStage) {
        Parent root;
        try {

            root = FXMLLoader.load(getClass().getResource("Login.fxml"));
            Scene scene = new Scene(root, 603, 400);
            primaryStage.setTitle("");
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
