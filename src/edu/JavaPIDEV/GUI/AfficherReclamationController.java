/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.JavaPIDEV.GUI;

import static com.sun.xml.internal.fastinfoset.alphabet.BuiltInRestrictedAlphabets.table;
import edu.JavaPIDEV.entities.Reclamation;
import edu.JavaPIDEV.services.ReclamationCRUD;
import edu.JavaPIDEV.utils.MyConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Jasser BOUKRAYA
 */
public class AfficherReclamationController implements Initializable {

    @FXML
    private TableView<Reclamation> list;
    @FXML
    private TableColumn<Reclamation,String> typeColumn;
    @FXML
    private TableColumn<Reclamation,String> descColumn;
    @FXML
    private TableColumn<Reclamation,Date> dateColumn;
    @FXML
    private TableColumn<Reclamation,Integer> cinColumn;
    @FXML
    private TableColumn<Reclamation,String> imageColumn;
    @FXML
    private TableColumn<Reclamation,String> email;
    @FXML
    private Button refresh;
     public ObservableList<Reclamation> data = FXCollections.observableArrayList();
       static Reclamation selectionedReclamation;
    @FXML
    private Button supp;
    @FXML
    private Button retour;
    @FXML
    private TextField Rechercher;


   
    
    
//    @FXML
//    private TableView<Reclamation> list;
//    @FXML
//    private TableColumn<Reclamation,Integer> idColumn;
//    @FXML
//    private TableColumn<Reclamation,String> typeColumn;
//    @FXML
//    private TableColumn<Reclamation,String> descColumn;
//    @FXML
//    private TableColumn<Reclamation,Date> dateColumn;
//    @FXML
//    private TableColumn<Reclamation,Integer> cinColumn;
//    @FXML
//    private TableColumn<Reclamation,String> imageColumn;
//     @FXML
//    private TableColumn<Reclamation,String> email;
//    @FXML
//    private Button refresh;
//    public ObservableList<Reclamation> data = FXCollections.observableArrayList();
//        static Reclamation selectionedReclamation;
//    @FXML
//    private Button supp;
//    @FXML
//    private Button retour;
//    @FXML
//    private TextField Rechercher;
   
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadData();
        search_Rec();
//      try {
//            Connection cnx = MyConnection.getInstance().getCnx();
//            
//            ResultSet rs = cnx.createStatement().executeQuery("SELECT id_rec,type_rec,description_rec,date_rec,cin,screenshot FROM reclamation");
//            while(rs.next())
//            {
//                data.add(new Reclamation( rs.getInt("id_rec"),rs.getString("type_rec"), rs.getString("description_rec"),rs.getDate("date_rec"), rs.getInt("cin"),rs.getString("screenshot")));
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(AfficherReclamationController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    idColumn.setCellValueFactory(new PropertyValueFactory<>("id_rec"));
//    typeColumn.setCellValueFactory(new PropertyValueFactory<>("type_rec"));
//    descColumn.setCellValueFactory(new PropertyValueFactory<>("description_rec"));
//    dateColumn.setCellValueFactory(new PropertyValueFactory<>("date_rec"));
//    cinColumn.setCellValueFactory(new PropertyValueFactory<>("cin"));
//    imageColumn.setCellValueFactory(new PropertyValueFactory<>("screenshot"));
//    list.setItems(data);
    
        // TODO
    }
    
//    @FXML
//    private void ModifierReclamation(ActionEvent event) throws IOException
//    {
//        selectionedReclamation = list.getSelectionModel().getSelectedItem();
//        System.out.println("zaama ?");
//     Parent root = FXMLLoader.load(getClass().getResource("ModifierReclamation.fxml"));
//           
//
////     Scene scene = new Scene(root);
////                        stage.setScene(scene);
////                        stage.show();
//Scene scene = new Scene(root);
//            stageModifier.setScene(scene);
//            stageModifier.show();
//            
//    }

    @FXML
    private void Refresh() {
        data.clear();
                ReclamationCRUD RC = new ReclamationCRUD();
        data.addAll(RC.affichageReclamation());
        RC.affichageReclamation();
        
        list.setItems(data);
    }

    private void loadData() {
    Refresh(); 
  //  idColumn.setCellValueFactory(new PropertyValueFactory<>("id_rec"));
    typeColumn.setCellValueFactory(new PropertyValueFactory<>("type_rec"));
    descColumn.setCellValueFactory(new PropertyValueFactory<>("description_rec"));
    dateColumn.setCellValueFactory(new PropertyValueFactory<>("date_rec"));
    cinColumn.setCellValueFactory(new PropertyValueFactory<>("cin"));
    imageColumn.setCellValueFactory(new PropertyValueFactory<>("screenshot"));
    email.setCellValueFactory(new PropertyValueFactory<>("email"));
    
    list.setOnMousePressed(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {

                if (event.getClickCount() == 2){
                FXMLLoader Loader = new FXMLLoader();
                Loader.setLocation(getClass().getResource("RepondreREC.fxml"));
                try{
                    Loader.load();
                }catch (IOException ex) {
                // ex.printStackTrace();
                    
                    System.out.println("error : "+ex.getMessage());;
                }
                ModifierClientRECController Rdc = Loader.getController();
                Rdc.setData(list.getSelectionModel().getSelectedItem().getType_rec()
                        ,list.getSelectionModel().getSelectedItem().getDescription_rec()
                        ,list.getSelectionModel().getSelectedItem().getDate_rec()
                        ,list.getSelectionModel().getSelectedItem().getCin()
                        ,list.getSelectionModel().getSelectedItem().getScreenshot()
                ,list.getSelectionModel().getSelectedItem().getMail());
                     
                
             Parent p = Loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(p));
                stage.show();}
            }
            
            
        });
    }

    @FXML
    private void supprimer(ActionEvent event) {
    
    if (list.getSelectionModel().getSelectedItem() != null) {
            Alert deleteEventAlert = new Alert(Alert.AlertType.CONFIRMATION);
            deleteEventAlert.setTitle("Suppression d'une Réclamation");
            deleteEventAlert.setHeaderText(null);
            deleteEventAlert.setContentText("Veuillez supprimer cette Réclamation ?");
            Optional<ButtonType> optionDeleteBookAlert = deleteEventAlert.showAndWait();
            if (optionDeleteBookAlert.get() == ButtonType.OK) {
                Reclamation R = list.getSelectionModel().getSelectedItem();
                ReclamationCRUD rc= new ReclamationCRUD();
                rc.supprimerReclamation(R);
                data.clear();
                data.addAll(rc.affichageReclamation());
                //Alert Delete Blog :
                Alert succDeleteEventAlert = new Alert(Alert.AlertType.INFORMATION);
                succDeleteEventAlert.setTitle("Supprimer Réclamation");
                //succDeleteEventAlert.setHeaderText("Resultats:");
                succDeleteEventAlert.setContentText("Réclamation déja supprimée!");

                succDeleteEventAlert.showAndWait();
            } else if (optionDeleteBookAlert.get() == ButtonType.CANCEL) {

            }

        } else {

            //Alert Select BOOK :
            Alert selectBookAlert = new Alert(Alert.AlertType.WARNING);
            selectBookAlert.setTitle("Sélectionner une réclamation");
            selectBookAlert.setHeaderText(null);
            selectBookAlert.setContentText("D'abord il faut Sélectionner une réclamation !");
            selectBookAlert.showAndWait();
            //Alert Select Book !

        } 
        
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
    
   void search_Rec() {
        Reclamation rec = new Reclamation();
        
      typeColumn.setCellValueFactory(new PropertyValueFactory<>("type_rec"));
        
       
        list.setItems(data);
       
        FilteredList<Reclamation> filteredData = new FilteredList<>(data, b -> true);
       
         Rechercher.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate((Reclamation recl) -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (recl.getType_rec().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} 
                            else  
				    	 return false; // Does not match.
			});
		});
        SortedList<Reclamation> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(list.comparatorProperty());
        list.setItems(sortedData);
   }

  
    }    

  /* private void AfficherReclamation(ActionEvent event) throws IOException {
        list.setOnMouseClicked((MouseEvent event2)
                -> {
            if (event2.getClickCount() >= 2) {
                // if (list.getSelectionModel().getSelectedItem() != null) {
               //    selectionedReclamation = list.getSelectionModel().getSelectedItem();
                    Stage stage = new Stage();
                    Parent root;
                    try {
                        root = FXMLLoader.load(getClass().getResource("AfficherReclamationUniqueFXML.fxml"));
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(AfficherReclamationController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                //}
            }
        });
    }*/
//}