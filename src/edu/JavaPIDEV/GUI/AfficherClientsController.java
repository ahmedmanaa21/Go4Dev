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

import java.net.URL;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
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
public class AfficherClientsController implements Initializable {
    
    Connection cnx;
    public AfficherClientsController(){
        cnx = MyConnection.getInstance().getCnx();
    }

    ClientCRUD pc = new ClientCRUD();
    Client c = new Client();
    @FXML
    private TableView<Client> table;
    @FXML
    private TableColumn<Client, Integer> txtcin;
    @FXML
    private TableColumn<Client, String> txtnom;
    @FXML
    private TableColumn<Client, String> txtsurnom;
    @FXML
    private TableColumn<Client, String> txtemail;
    @FXML
    private TableColumn<Client, String> txtmdp;
    @FXML
    private TableColumn<Client, Date> txtdate;
    @FXML
    private TableColumn<Client, String> txtadresse;
    @FXML
    private TableColumn<Client, String> txtimage;
    @FXML
    private Button btnsupprimer;
    @FXML
    private Button btnmodifier;
    @FXML
    private Button btnajouter;

    public ObservableList<Client> dataClient = FXCollections.observableArrayList();
    @FXML
    private Button btnretour;
    @FXML
    private TextField txtrecherche;
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
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fxlogo.setImage(new Image("file:C:\\Users\\MSI\\Desktop\\CampersDen\\src\\Images\\logo.png"));
        fxlogout.setImage(new Image("file:C:\\Users\\MSI\\Desktop\\CampersDen\\src\\Images\\logout.png"));
        loadData();
        searchAdmin();
        /**
         *
         *
         */
//        try {
//            Connection cnx = new MyConnection().getCnx();
//            ResultSet rs = cnx.createStatement().executeQuery("SELECT cin,nomPrenom ,surnom , email,mdp , dateNaissance,adresse,image FROM client");
//            while (rs.next()) {
//                dataClient.add(new Client(rs.getInt("cin"), rs.getString("nomPrenom"), rs.getString("surnom"), rs.getString("email"), rs.getString("mdp"), rs.getDate("dateNaissance"), rs.getString("adresse"), rs.getString("image")));
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(AfficherClientsController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        String requete ="SELECT mdp FROM client where cin='"+c.getCin()+"' ";
//        String hiddenPass = "";
//        for (int i = 0; i < requete.length(); i++) {
//            hiddenPass += "*";   
//        }
//
//        table.setEditable(true);
//        txtcin.setCellValueFactory(new PropertyValueFactory<>("cin"));
//        txtcin.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
//        txtnom.setCellValueFactory(new PropertyValueFactory<>("nomPrenom"));
//        txtsurnom.setCellValueFactory(new PropertyValueFactory<>("surnom"));
//        txtemail.setCellValueFactory(new PropertyValueFactory<>("email"));
//        txtmdp.setCellValueFactory(new PropertyValueFactory<>(hiddenPass));
//        txtdate.setCellValueFactory(new PropertyValueFactory<>("dateNaissance"));
//        txtadresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
//        txtimage.setCellValueFactory(new PropertyValueFactory<>("image"));
//        table.setItems(dataClient);

        txtcin.setSortType(TableColumn.SortType.DESCENDING);
        txtnom.setSortType(TableColumn.SortType.DESCENDING);
        txtsurnom.setSortType(TableColumn.SortType.DESCENDING);
        txtemail.setSortType(TableColumn.SortType.DESCENDING);
        txtdate.setSortType(TableColumn.SortType.DESCENDING);
        txtadresse.setSortType(TableColumn.SortType.DESCENDING);

    }

    @FXML
    private void supprimerClients(ActionEvent event) {

        if (table.getSelectionModel().getSelectedItem() != null) {
            Alert deleteAdmintAlert = new Alert(Alert.AlertType.CONFIRMATION);
            deleteAdmintAlert.setTitle("Supprimer Client");
            deleteAdmintAlert.setHeaderText(null);
            deleteAdmintAlert.setContentText("Voulez-vous vraiment supprimer cet Client ?");
            Optional<ButtonType> optionDeleteBookAlert = deleteAdmintAlert.showAndWait();
            if (optionDeleteBookAlert.get() == ButtonType.OK) {
                Client A = table.getSelectionModel().getSelectedItem();
                pc.supprimerClient(A.getCin());
                dataClient.clear();
                dataClient.addAll(pc.affichageClient());
                //Alert Delete Blog :
                Alert succDeleteAdminAlert = new Alert(Alert.AlertType.INFORMATION);
                succDeleteAdminAlert.setTitle("Supprimer Client");
                succDeleteAdminAlert.setHeaderText("Resultats:");
                succDeleteAdminAlert.setContentText("Client supprimé !");

                succDeleteAdminAlert.showAndWait();
            } else if (optionDeleteBookAlert.get() == ButtonType.CANCEL) {

            }

        } else {

            //Alert Select BOOK :
            Alert selectBookAlert = new Alert(Alert.AlertType.WARNING);
            selectBookAlert.setTitle("Choisir un Client");
            selectBookAlert.setHeaderText(null);
            selectBookAlert.setContentText("Choisir un Client avant de supprimer!");
            selectBookAlert.showAndWait();
            //Alert Select Book !

        }

    }

    @FXML
    private void Refresh() {
        dataClient.clear();
        ClientCRUD cl = new ClientCRUD();
        dataClient.addAll(cl.affichageClient());
        cl.affichageClient();

        table.setItems(dataClient);
    }

    private void loadData() {

        Refresh();
        txtcin.setCellValueFactory(new PropertyValueFactory<>("cin"));
        txtnom.setCellValueFactory(new PropertyValueFactory<>("nomPrenom"));
        txtsurnom.setCellValueFactory(new PropertyValueFactory<>("surnom"));
        txtemail.setCellValueFactory(new PropertyValueFactory<>("email"));
//    txtmdp.setCellValueFactory(new PropertyValueFactory<>("mdp"));
        txtdate.setCellValueFactory(new PropertyValueFactory<>("dateNaissance"));
        txtadresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        txtimage.setCellValueFactory(new PropertyValueFactory<>("image"));
        table.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                if (event.getClickCount() == 2) {
                    FXMLLoader Loader = new FXMLLoader();
                    Loader.setLocation(getClass().getResource("ModifierClients.fxml"));
                    try {
                        Loader.load();
                    } catch (IOException ex) {
                        // ex.printStackTrace();

                        System.out.println("error : " + ex.getMessage());;
                    }
                    ModifierClientsController Mcc = Loader.getController();
                    Mcc.setData(table.getSelectionModel().getSelectedItem().getCin(),
                             table.getSelectionModel().getSelectedItem().getNomPrenom(),
                             table.getSelectionModel().getSelectedItem().getSurnom(),
                             table.getSelectionModel().getSelectedItem().getEmail(),
                             table.getSelectionModel().getSelectedItem().getMdp(),
                             table.getSelectionModel().getSelectedItem().getDateNaissance(),
                             table.getSelectionModel().getSelectedItem().getAdresse(),
                             table.getSelectionModel().getSelectedItem().getImage());

                    Parent p = Loader.getRoot();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(p));
                    stage.show();
                }
            }

        });
    }

    @FXML
    private void ajouterClients(ActionEvent event) {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("ajouterClients.fxml"));
            Scene scene = new Scene(root, 1335, 909);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(scene);
            appStage.show();
        } catch (IOException ex) {
            ex.getMessage();
        }

    }


    void searchAdmin() {
        Client ad = new Client();

        txtnom.setCellValueFactory(new PropertyValueFactory<>("nomPrenom"));
        txtsurnom.setCellValueFactory(new PropertyValueFactory<>("surnom"));
        txtemail.setCellValueFactory(new PropertyValueFactory<>("email"));
        txtadresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));

        table.setItems(dataClient);

        FilteredList<Client> filteredData = new FilteredList<>(dataClient, b -> true);

        txtrecherche.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate((Client cli) -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (cli.getNomPrenom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (cli.getSurnom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                }
                if (cli.getEmail().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                }
                if (cli.getAdresse().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false; // Does not match.
                }
            });
        });
        SortedList<Client> sortedData = new SortedList<>(filteredData);
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
    private void changeScreenPanier(ActionEvent event) {
    }
}
