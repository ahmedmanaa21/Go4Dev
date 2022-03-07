/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.JavaPIDEV.GUI;

import com.itextpdf.text.DocumentException;
import static edu.JavaPIDEV.GUI.NewFXMain.Userconnected;
import static edu.JavaPIDEV.GUI.NewFXMain.UserconnectedC;
import edu.JavaPIDEV.entities.Admin;
import edu.JavaPIDEV.services.AdminCRUD;
import edu.JavaPIDEV.services.pdfBackup;

import java.net.URL;
import java.sql.Connection;
import edu.JavaPIDEV.utils.MyConnection;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class AfficherAdminsController implements Initializable {

    Connection cnx;
    @FXML
    private Button btnstat;
    @FXML
    private ImageView refresh;
    @FXML
    private ImageView stat;
    @FXML
    private Button btnsupprimer1;

    public AfficherAdminsController() {
        cnx = MyConnection.getInstance().getCnx();
    }

    AdminCRUD pc = new AdminCRUD();
    @FXML
    private TableView<Admin> table;
    @FXML
    private TableColumn<Admin, String> txtnom;
    @FXML
    private TableColumn<Admin, String> txtprenom;
    @FXML
    private TableColumn<Admin, String> txtemail;
    @FXML
    private TableColumn<Admin, String> txtmdp;
    @FXML
    private TableColumn<Admin, Integer> txtnum;
    @FXML
    private Button btnsupprimer;
    @FXML
    private Button btnref;
    @FXML
    private Button btnajouter;
    @FXML
    private TextField txtrecherche;

    public ObservableList<Admin> dataAdmin = FXCollections.observableArrayList();
    @FXML
    private AnchorPane displayArea;
    @FXML
    private TextField idm;
    @FXML
    private ImageView fxlogo;
    @FXML
    private ImageView fxlogout;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fxlogo.setImage(new Image("file:C:\\Users\\MSI\\Desktop\\CampersDen\\src\\Images\\logo.png"));
        fxlogout.setImage(new Image("file:C:\\Users\\MSI\\Desktop\\CampersDen\\src\\Images\\logout.png"));
        refresh.setImage(new Image("file:C:\\Users\\MSI\\Desktop\\CampersDen\\src\\Images\\reload.png"));
        stat.setImage(new Image("file:C:\\Users\\MSI\\Desktop\\CampersDen\\src\\Images\\bar-chart.png"));

        loadData();
        searchAdmin();
        /**
         *
         *
         */
//        try {
//            ResultSet rs = cnx.createStatement().executeQuery("SELECT * FROM admin");
//            while (rs.next()) {
//                dataAdmin.add(new Admin(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), rs.getString("mdp"), rs.getInt("numtel")));
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(AfficherAdminsController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        table.setEditable(true);
//        txtnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
//        txtnom.setCellFactory(TextFieldTableCell.forTableColumn());
//        txtprenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
//        txtemail.setCellValueFactory(new PropertyValueFactory<>("email"));
//        txtnum.setCellValueFactory(new PropertyValueFactory<>("numtel"));
//        txtnum.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
//        table.setItems(dataAdmin);

    }

    @FXML
    private void supprimerAdmins(ActionEvent event) {

        if (table.getSelectionModel().getSelectedItem() != null) {
            Alert deleteAdmintAlert = new Alert(Alert.AlertType.CONFIRMATION);
            deleteAdmintAlert.setTitle("Supprimer Admin");
            deleteAdmintAlert.setHeaderText(null);
            deleteAdmintAlert.setContentText("Voulez-vous vraiment supprimer cet Admin ? ?");
            Optional<ButtonType> optionDeleteBookAlert = deleteAdmintAlert.showAndWait();
            if (optionDeleteBookAlert.get() == ButtonType.OK) {
                Admin A = table.getSelectionModel().getSelectedItem();
                pc.supprimerAdmin(A.getId());
                dataAdmin.clear();
                dataAdmin.addAll(pc.affichageAdmin());
                //Alert Delete Blog :
                Alert succDeleteAdminAlert = new Alert(Alert.AlertType.INFORMATION);
                succDeleteAdminAlert.setTitle("Supprimer Admin");
                succDeleteAdminAlert.setHeaderText("Resultats:");
                succDeleteAdminAlert.setContentText("Admin supprimé !");
                succDeleteAdminAlert.showAndWait();
            } else if (optionDeleteBookAlert.get() == ButtonType.CANCEL) {
            }
        } else {
            Alert selectBookAlert = new Alert(Alert.AlertType.WARNING);
            selectBookAlert.setTitle("Choisir un Admin");
            selectBookAlert.setHeaderText(null);
            selectBookAlert.setContentText("Choisir un Admin avant de supprimer!");
            selectBookAlert.showAndWait();
        }
    }

    @FXML
    private void Refresh() {
        dataAdmin.clear();
        AdminCRUD ad = new AdminCRUD();
        dataAdmin.addAll(ad.affichageAdmin());
        ad.affichageAdmin();

        table.setItems(dataAdmin);
    }

    private void loadData() {
        Refresh();
//        String mdp = null;
//        pc.cryptPassword(mdp);
        txtnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        txtprenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        txtemail.setCellValueFactory(new PropertyValueFactory<>("email"));
//        txtmdp.setCellValueFactory(new PropertyValueFactory<>(mdp));
        txtnum.setCellValueFactory(new PropertyValueFactory<>("numtel"));
        table.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                if (event.getClickCount() == 2) {
                    FXMLLoader Loader = new FXMLLoader();
                    Loader.setLocation(getClass().getResource("ModifierAdmins.fxml"));
                    try {
                        Loader.load();
                    } catch (IOException ex) {
                        // ex.printStackTrace();

                        System.out.println("error : " + ex.getMessage());;
                    }
                    ModifierAdminsController Mdc = Loader.getController();
                    Mdc.setData(table.getSelectionModel().getSelectedItem().getId(),
                            table.getSelectionModel().getSelectedItem().getNom(),
                            table.getSelectionModel().getSelectedItem().getPrenom(),
                            table.getSelectionModel().getSelectedItem().getEmail(),
                            table.getSelectionModel().getSelectedItem().getMdp(),
                            table.getSelectionModel().getSelectedItem().getNumtel());

                    Parent p = Loader.getRoot();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(p));
                    stage.show();
                }
            }

        });
    }

    @FXML
    private void ajouterAdmins(ActionEvent event) {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("ajouterAdmins.fxml"));
            Scene scene = new Scene(root, 1335, 909);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(scene);
            appStage.show();
        } catch (IOException ex) {
            ex.getMessage();
        }

    }

    void searchAdmin() {
        Admin ad = new Admin();

        txtnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        txtprenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        txtemail.setCellValueFactory(new PropertyValueFactory<>("email"));
        txtmdp.setCellValueFactory(new PropertyValueFactory<>(""));
        txtnum.setCellValueFactory(new PropertyValueFactory<>("numtel"));

        table.setItems(dataAdmin);

        FilteredList<Admin> filteredData = new FilteredList<>(dataAdmin, b -> true);

        txtrecherche.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate((Admin adm) -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (adm.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches first name.
                } else if (adm.getPrenom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                }
                if (adm.getEmail().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches first name.
                } else {
                    return false; // Does not match.
                }
            });
        });
        SortedList<Admin> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(sortedData);
    }

    @FXML
    private void changeScreenAdmin(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("afficherAdmins.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }

    @FXML
    private void changeScreenClient(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("afficherClients.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
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
    private void changeScreenReclamations(ActionEvent event) {
    }

    @FXML
    private void changeScreenPanier(ActionEvent event) {
    }

    @FXML
    private void stat(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("chartsSexe.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }

    @FXML
    private void PDF(ActionEvent event) throws FileNotFoundException, DocumentException {
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de création du PDF");
        alert.setHeaderText("Etes vous sur de vouloir créer un PDF qui contient la liste des Admins ?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
             pdfBackup pdfbackup= new pdfBackup();
        pdfbackup.PdfBackup();
        }
        
    }
}
