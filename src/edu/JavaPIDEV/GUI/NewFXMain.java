package edu.JavaPIDEV.GUI;


import edu.JavaPIDEV.entities.Admin;
import edu.JavaPIDEV.entities.Client;
import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author broum
 */
public class NewFXMain extends Application {

    public static Admin Userconnected = new Admin();
    public static Client UserconnectedC = new Client();
    @Override
    public void start(Stage primaryStage) {
        Parent root;
        try {

            root = FXMLLoader.load(getClass().getResource("afficherAdmins.fxml"));
            Scene scene = new Scene(root, 840, 615);
            primaryStage.setTitle("CamperDen");
            primaryStage.getIcons().add(new Image("file:C:\\Users\\MSI\\Desktop\\CampersDen\\src\\Images\\logo.png"));
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
