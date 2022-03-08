/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.JavaPIDEV.GUI;

import edu.JavaPIDEV.entities.ZoneCamping;
import edu.JavaPIDEV.services.ZoneCampingCRUD;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author melki
 */
public class GestionZoneCampingClientController implements Initializable {

    @FXML
    private TableView<ZoneCamping> tabzcC;
    @FXML
    private TextField recherche;
    @FXML
    private Button retour;
    @FXML
    private Button bt_reserver;
    @FXML
    private TableColumn<ZoneCamping, String> collregion;
    @FXML
    private TableColumn<ZoneCamping, String> colldelegation;
    @FXML
    private TableColumn<ZoneCamping, String> collnomCentre;
    @FXML
    private TableColumn<ZoneCamping, String> colldescription;
    ZoneCampingCRUD zcc=new ZoneCampingCRUD();
    ObservableList<ZoneCamping> data = FXCollections.observableArrayList(zcc.affichageZoneCamping());
    @FXML
    private Button btnacc;
    @FXML
    private Button btnequip;
    @FXML
    private Button btnoffre;
    @FXML
    private Button btnrec;
    @FXML
    private Button btnrec1;
    @FXML
    private ImageView logo;
    @FXML
    private TextField SearchBar;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        logo.setImage(new Image("file:C:\\Users\\MSI\\Desktop\\CampersDen\\src\\Images\\logo.png"));
        
        // TODO
        AfficherZoneCamping();
        search_ZoneCamping();
    }
        public Connection getConnection(){
        Connection cnx;
            try{
                cnx= (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/PIDEVdb", "root", "");
                return cnx ; }
            catch(SQLException ex){
        System.out.println("Erreur de connexion");
    return null ; }
    }
    public ObservableList<ZoneCamping> getZoneCampingList(){
        ObservableList<ZoneCamping> Zonecamp =FXCollections.observableArrayList();
        Connection cnx = getConnection();
        String query="SELECT *From zonecamping";
        Statement st;
        ResultSet rs ;
                try {
                    st= cnx.createStatement();
                    rs=st.executeQuery(query);
                    ZoneCamping zonecamping;
                    while(rs.next()){
                        zonecamping=new ZoneCamping(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(7));
                    Zonecamp.add(zonecamping);
                            }                        
                }catch (Exception ex){
                    ex.printStackTrace();
                }
                return Zonecamp;               
    }
    public void AfficherZoneCamping(){
        ObservableList<ZoneCamping> liste=getZoneCampingList();

        collregion.setCellValueFactory(new PropertyValueFactory<ZoneCamping, String>("Région"));
        colldelegation.setCellValueFactory(new PropertyValueFactory<ZoneCamping, String>("Délégation"));
        collnomCentre.setCellValueFactory(new PropertyValueFactory<ZoneCamping, String>("Nom du centre"));
        colldescription.setCellValueFactory(new PropertyValueFactory<ZoneCamping, String>("Description"));
        tabzcC.setItems(liste);
    }
    void search_ZoneCamping() {
        ZoneCamping z = new ZoneCamping();
        
        collregion.setCellValueFactory(new PropertyValueFactory<>("region"));
        colldelegation.setCellValueFactory(new PropertyValueFactory<>("delegation"));
        collnomCentre.setCellValueFactory(new PropertyValueFactory<>("nom_centre"));
        colldescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        tabzcC.setItems(data);
        FilteredList<ZoneCamping> filteredData = new FilteredList<>(data, b -> true);
        recherche.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate((ZoneCamping zc) -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (zc.getNom_centre().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
                                } 
                                else if (zc.getRegion().indexOf(lowerCaseFilter)!=-1){
				     return true;
                                }
                                else if (zc.getDelegation().indexOf(lowerCaseFilter)!=-1){
				     return true;
                                }
                                else if (zc.getDescription().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
				     else  
				    	 return false; // Does not match.
			});
		});
        SortedList<ZoneCamping> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tabzcC.comparatorProperty());
        tabzcC.setItems(sortedData);
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
    
    @FXML
    private void qrCode(ActionEvent event) throws SQLException  {
        if (tabzcC.getSelectionModel().getSelectedItem() != null) {
          QRcode2Controller q=new QRcode2Controller();
       q.ini(tabzcC.getSelectionModel().getSelectedItem());}
        else {
                Alert selectEventAlert = new Alert(Alert.AlertType.WARNING);
                selectEventAlert.setTitle("Selectionez une zone de camping");
                selectEventAlert.setHeaderText(null);
                selectEventAlert.setContentText("Vous devez selectionner une zone de camping !");
                selectEventAlert.showAndWait();          
            }
    }
    @FXML
    private void reserver(ActionEvent event) {
    }

    @FXML
    private void Refresh(ActionEvent event) {
        data.clear();
        ZoneCampingCRUD zc = new ZoneCampingCRUD();
        data.addAll(zc.affichageZoneCamping());
        zc.affichageZoneCamping();

        tabzcC.setItems(data);
        search_ZoneCamping();
    }
        @FXML
    void Gerermap(ActionEvent event) throws IOException {
       AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource("map.FXML"));
        Scene scene = new Scene(newLoadedPane);
        Stage stage = new Stage();
        stage.setTitle("Interface ");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void changeScreenViewAcc(ActionEvent event) {
    }

    @FXML
    private void changeScreenViewEquip(ActionEvent event) {
    }

    @FXML
    private void changeScreenViewProd(MouseEvent event) {
    }

    @FXML
    private void changeScreenViewOffre(ActionEvent event) {
    }

    @FXML
    private void changeScreenViewProm(MouseEvent event) {
    }

    @FXML
    private void changeScreenViewRec(ActionEvent event) {
    }

    @FXML
    private void changeScreenViewReclam(MouseEvent event) {
    }

    @FXML
    private void changeScreenViewResev(ActionEvent event) {
    }

    @FXML
    private void logout(ActionEvent event) {
    }
    
}
