/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.JavaPIDEV.GUI;

import edu.JavaPIDEV.Interfaces.MylistenerE;
import edu.JavaPIDEV.entities.Evenements;
import edu.JavaPIDEV.services.EvenementsCRUD;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author melki
 */
public class GestionEvenementClientController implements Initializable {

    @FXML
    private TableView<Evenements> tabevC;
    @FXML
    private TableColumn<Evenements, String> collnom;
    @FXML
    private TableColumn<Evenements, String> colldescription;
    @FXML
    private TableColumn<Evenements, Date> colldateDeb;
    @FXML
    private TableColumn<Evenements, Date> colldateFin;
    @FXML
    private TableColumn<Evenements, String> collAffiche;
    @FXML
    private Button bt_participer;
    @FXML
    private Button bt_retour;
    @FXML
    private TextField recherche;
    EvenementsCRUD evc=new EvenementsCRUD();
    ObservableList<Evenements> data = FXCollections.observableArrayList(evc.affichageEvenements());
    @FXML
    private Button bt_qr;
    private MylistenerE myListener;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        AfficherEvenement();
        search_Event();
        
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
    public ObservableList<Evenements> getEvenementList(){
        ObservableList<Evenements> Events =FXCollections.observableArrayList();
        Connection cnx = getConnection();
        String query="SELECT *From evenement";
        Statement st;
        ResultSet rs ;
            try {
                st= cnx.createStatement();
                rs=st.executeQuery(query);
                Evenements Evenement;
                while(rs.next()){
                    Evenement=new Evenements(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getDate(5), rs.getString(6));
                    Events.add(Evenement);}               
                }
                catch (Exception ex){
                    ex.printStackTrace();
                }
                return Events;
        }
    public void AfficherEvenement(){
    ObservableList<Evenements> list=getEvenementList();

    collnom.setCellValueFactory(new PropertyValueFactory<Evenements, String>("Nom"));
    colldescription.setCellValueFactory(new PropertyValueFactory<Evenements, String>("Description"));
    colldateDeb.setCellValueFactory(new PropertyValueFactory<Evenements, Date>("Date début"));
    colldateFin.setCellValueFactory(new PropertyValueFactory<Evenements, Date>("Date fin"));
    collAffiche.setCellValueFactory(new PropertyValueFactory<Evenements, String>("Affiche"));
    tabevC.setItems(list);
    //editttttttttttt
    this.tabevC.setEditable(true);
        this.collnom.setCellFactory(TextFieldTableCell.forTableColumn());
        this.colldescription.setCellFactory(TextFieldTableCell.forTableColumn());
        this.collAffiche.setCellFactory(TextFieldTableCell.forTableColumn());         
    }
    void search_Event() {
        Evenements ev = new Evenements();
        
        collnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colldescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        collAffiche.setCellValueFactory(new PropertyValueFactory<>("img"));
        
       
        tabevC.setItems(data);
       
        FilteredList<Evenements> filteredData = new FilteredList<>(data, b -> true);
       
        recherche.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate((Evenements evt) -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (evt.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (evt.getDescription().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
                                if (evt.getImage().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				}
                                
                               
				     else  
				    	 return false; // Does not match.
			});
		});
        SortedList<Evenements> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tabevC.comparatorProperty());
        tabevC.setItems(sortedData);
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
//     @FXML
//    private void Refresh() {
//        data.clear();
//        EvenementsCRUD ev = new EvenementsCRUD();
//        data.addAll(ev.affichageEvenements());
//        ev.affichageEvenements();
//
//        tabevC.setItems(data);
//    }  
    @FXML
    private void qrCode(ActionEvent event) throws SQLException  {
        if (tabevC.getSelectionModel().getSelectedItem() != null) {
          QRcodeController q=new QRcodeController();
       q.ini(tabevC.getSelectionModel().getSelectedItem());}
        else {
                Alert selectEventAlert = new Alert(Alert.AlertType.WARNING);
                selectEventAlert.setTitle("Selectionez un événement");
                selectEventAlert.setHeaderText(null);
                selectEventAlert.setContentText("Vous devez selectionner un événement !");
                selectEventAlert.showAndWait();          
            }
    }
    @FXML
    private void formulaire(ActionEvent event) {
        
    }

    @FXML
    private void Refresh(ActionEvent event) {
        data.clear();
        EvenementsCRUD ev = new EvenementsCRUD();
        data.addAll(ev.affichageEvenements());
        ev.affichageEvenements();

        tabevC.setItems(data);
        search_Event();
    }
    
}
