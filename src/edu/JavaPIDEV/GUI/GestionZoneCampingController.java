/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.JavaPIDEV.GUI;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import edu.JavaPIDEV.entities.Evenements;
import edu.JavaPIDEV.entities.ZoneCamping;
import edu.JavaPIDEV.services.PDFevenement;
import edu.JavaPIDEV.services.PDFzoneCamping;
import edu.JavaPIDEV.services.ZoneCampingCRUD;
import edu.JavaPIDEV.utils.MyConnection;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.mail.Message;
import javax.swing.text.Document;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author melki
 */
public class GestionZoneCampingController implements Initializable {

    @FXML
    private TextField fx_nomCentre;
    @FXML
    private ComboBox<String> fx_region;
    ObservableList<String> listRegion = FXCollections.observableArrayList("Ariana", "Béja", "Ben Arous", "Bizerte","Gabès","Gafsa","Jendouba","Kairouan","Kasserine","Kébili","Kef","Mahdia","Manouba","Médenine","Monastir","Nabeul","Sfax","Sidi Bouzid","Siliana","Sousse","Tataouine","Tozeur","Tunis","Zaghouan");

    @FXML
    private TextField fx_delegation;
    @FXML
    private TextArea fx_desc;
    @FXML
    private Button bt_ajouter;
    @FXML
    private Button bt_modifier;
    @FXML
    private Button bt_supprimer;
    @FXML
    private ImageView checknomCentre;
    @FXML
    private ImageView checkregion;
    @FXML
    private ImageView checkdelegation;
    @FXML
    private ImageView checkdescription;

    ZoneCampingCRUD zc = new ZoneCampingCRUD() ; 
    @FXML
    private TableColumn<ZoneCamping, String> collnomCentre;
    @FXML
    private TableColumn<ZoneCamping, String> collregion;
    @FXML
    private TableColumn<ZoneCamping, String> colldelegation;
    @FXML
    private TableColumn<ZoneCamping, String> colldescription;
    @FXML
    private TableView<ZoneCamping> tabzc;
    String erreur;
    ZoneCampingCRUD zcc=new ZoneCampingCRUD();
     ObservableList<ZoneCamping> data = FXCollections.observableArrayList(zcc.affichageZoneCamping());
    @FXML
    private Button pdf;
    @FXML
    private TextField recherchezc;
    @FXML
    private Button btnretour;
     
@FXML
    private void handleButtonAction(ActionEvent event) throws SQLException, DocumentException {
        
        if (event.getSource()== bt_ajouter){
            AjouterZoneCamping();
        }
        else if (event.getSource() == bt_modifier){
            ModifierZonecamping(event);
    }
        else if (event.getSource() == bt_supprimer){
            SupprimerZoneCamping(event);
    }
         else if (event.getSource() == pdf){
        ExportPDF();  
    }
    }  
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO        
        AfficherZoneCamping();
        search_ZoneCamping();
        fx_region.setPromptText("Veuillez selectionner votre région");
        fx_region.setItems(listRegion);
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

//collid.setCellValueFactory(new PropertyValueFactory<ZoneCamping, Integer>("id"));
collregion.setCellValueFactory(new PropertyValueFactory<ZoneCamping, String>("Région"));
colldelegation.setCellValueFactory(new PropertyValueFactory<ZoneCamping, String>("Délégation"));
collnomCentre.setCellValueFactory(new PropertyValueFactory<ZoneCamping, String>("Nom du centre"));
colldescription.setCellValueFactory(new PropertyValueFactory<ZoneCamping, String>("Description"));
tabzc.setItems(liste);

//editttttttttttt
this.tabzc.setEditable(true);
        this.collnomCentre.setCellFactory(TextFieldTableCell.forTableColumn());
         this.collregion.setCellFactory(TextFieldTableCell.forTableColumn());
          this.colldelegation.setCellFactory(TextFieldTableCell.forTableColumn());
           this.colldescription.setCellFactory(TextFieldTableCell.forTableColumn());
}
            
private void AjouterZoneCamping(){
    
if(testSaisie()){
//Evenement e =new Evenements(tfnom.getText(),tfprenom.getText(),tfemail.getText());
ZoneCamping zc =new ZoneCamping();
zc.setRegion(fx_region.getValue().toString());
zc.setDelegation(fx_delegation.getText());
zc.setNom_centre(fx_nomCentre.getText());
zc.setDescription(fx_desc.getText());
zcc.ajouterZoneCamping(zc);
//AfficherZoneCamping();
Notifications notificationBuilder =Notifications.create()
        .title("Gestion des zones de camping")
        .text("Ajout de l'événement avec succés")
        .graphic(null)
        .hideAfter(Duration.seconds(5))
        .position(Pos.BOTTOM_RIGHT);
notificationBuilder.darkStyle();
notificationBuilder.showInformation();

}
Refresh();
}           
//    private void SupprimerZoneCamping(){
//        
//    ZoneCamping z= new ZoneCamping(tabzc.getSelectionModel().getSelectedItem().getId());
//    zcc.supprimerZoneCamping(z);
//    data.removeAll(data);
//    AfficherZoneCamping();
//    }
    private void SupprimerZoneCamping(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation modification");
        alert.setContentText("Etes vous sûr de vouloir supprimer cet événement ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get().getButtonData() == ButtonBar.ButtonData.OK_DONE) {
            if (tabzc.getSelectionModel().getSelectedItem() != null) {
                zcc.supprimerZoneCamping(tabzc.getSelectionModel().getSelectedItem());
                data.removeAll(data);
                for (ZoneCamping ee : FXCollections.observableArrayList(zcc.affichageZoneCamping())) {
                    data.add(ee); }
                Notifications notificationBuilder = Notifications.create()
                .title("Suppression zone camping")
                .text("votre zone de camping a bien été supprimé !")
                .graphic(null)
                .hideAfter(Duration.seconds(5))
                .position(Pos.BOTTOM_RIGHT);
                notificationBuilder.darkStyle();
                notificationBuilder.showInformation();
                fx_delegation.setText("");
                fx_nomCentre.setText("");
                fx_desc.setText("");
            }
            else {
                Alert selectEventAlert = new Alert(Alert.AlertType.WARNING);
                selectEventAlert.setTitle("Selectionez une zone de camping");
                selectEventAlert.setHeaderText(null);
                selectEventAlert.setContentText("Vous devez selectionner une zone de camping !");
                selectEventAlert.showAndWait();
            }
//            clear();
        } else {
            System.out.println("Cancel");
            Notifications notificationBuilder = Notifications.create()
            .title("Suppression annulée")
            .text("votre zone de camping n'a pas été supprimé !")
            .graphic(null)
            .hideAfter(Duration.seconds(5))
            .position(Pos.BOTTOM_RIGHT);
            notificationBuilder.darkStyle();
            notificationBuilder.showInformation();
        }
        Refresh();
    }    

    private void ModifierZonecamping(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation modification");
        alert.setContentText("Etes vous sûr de modifier cette zone de camping ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get().getButtonData() == ButtonBar.ButtonData.OK_DONE) {
        if (tabzc.getSelectionModel().getSelectedItem() != null) {

            ZoneCamping z = tabzc.getSelectionModel().getSelectedItem();

            zcc.modifierZoneCampipng(z);
            Notifications notificationBuilder = Notifications.create()
            .title("Modification zone de camping")
            .text("votre zone de camping a bien été modifié !")
            .graphic(null)
            .hideAfter(Duration.seconds(5))
            .position(Pos.BOTTOM_RIGHT);
            notificationBuilder.darkStyle();
            notificationBuilder.showInformation();}
            
        else if (tabzc.getSelectionModel().getSelectedItem() == null){
            //Alert Select BOOK :
            Alert selectEventAlert = new Alert(Alert.AlertType.WARNING);
            selectEventAlert.setTitle("Selectionez une zone de camping");
            selectEventAlert.setHeaderText(null);
            selectEventAlert.setContentText("Vous devez selectionner une zone de camping !");
            selectEventAlert.showAndWait();
            //Alert Select Book !
        }
        }
        else {
            System.out.println("Cancel");
            Notifications notificationBuilder = Notifications.create()
            .title("modification annulée")
            .text("vore zone de camping n'a pas été modifiée !")
            .graphic(null)
            .hideAfter(Duration.seconds(5))
            .position(Pos.BOTTOM_RIGHT);
            notificationBuilder.darkStyle();
            notificationBuilder.showInformation();
        }
      Refresh();
    }
    
 




@FXML
private void handleMouseAction(MouseEvent event) {
        ZoneCamping zcc = tabzc.getSelectionModel().getSelectedItem();
        
//                tfid.setText(""+livreur.getId());

        fx_delegation.setText(zcc.getDelegation());
        fx_nomCentre.setText(zcc.getNom_centre());
        fx_desc.setText(zcc.getDescription());
        
    }

    @FXML
    private void edittnomcentre(CellEditEvent edittedCell) {
        ZoneCamping zonecampingSelected = tabzc.getSelectionModel().getSelectedItem();
        zonecampingSelected.setNom_centre(edittedCell.getNewValue().toString());
    }

    @FXML
    private void editregion(CellEditEvent edittedCell) {
         ZoneCamping zonecampingSelected = tabzc.getSelectionModel().getSelectedItem();
        zonecampingSelected.setRegion(edittedCell.getNewValue().toString());
    }

    @FXML
    private void delegation(CellEditEvent edittedCell) {
               ZoneCamping zonecampingSelected = tabzc.getSelectionModel().getSelectedItem();
        zonecampingSelected.setDelegation(edittedCell.getNewValue().toString());
    }

    @FXML
    private void description(CellEditEvent edittedCell) {
            ZoneCamping zonecampingSelected = tabzc.getSelectionModel().getSelectedItem();
        zonecampingSelected.setDescription(edittedCell.getNewValue().toString());
    }
private Boolean testDelegation() {
        int nbNonChar = 0;
        for (int i = 1; i < fx_delegation.getText().trim().length(); i++) {
            char ch = fx_delegation.getText().charAt(i);
            if (!Character.isLetter(ch)) {
                nbNonChar++;
            }
        }

        if (nbNonChar <= 2 && fx_delegation.getText().trim().length() >= 3) {
            checkdelegation.setImage(new Image("image/checkMark.png"));
            
            return true;
        } else {
            checkdelegation.setImage(new Image("image/erreurCheckMark.png"));
          
//                erreur = erreur + ("Pas de caractere permit dans le telephone\n");
            return false;

        }
    }
private Boolean testNomCentre() {
        int nbNonChar = 0;
        for (int i = 1; i < fx_nomCentre.getText().trim().length(); i++) {
            char ch = fx_nomCentre.getText().charAt(i);
            if (!Character.isLetter(ch)) {
                nbNonChar++;
            }
        }

        if (nbNonChar <= 2 && fx_nomCentre.getText().trim().length() >= 3) {
            checknomCentre.setImage(new Image("image/checkMark.png"));
            
            return true;
        } else {
            checknomCentre.setImage(new Image("image/erreurCheckMark.png"));
          
//                erreur = erreur + ("Pas de caractere permit dans le telephone\n");
            return false;

        }
    }
private Boolean testDesc() {
       
        for (int i = 1; i < fx_desc.getText().trim().length(); i++) {
            char ch = fx_desc.getText().charAt(i);
            
        }

        if ( fx_desc.getText().trim().length() >= 10) {
            checkdescription.setImage(new Image("image/checkMark.png"));
            
            
            return true;
        } else {
           checkdescription.setImage(new Image("image/erreurCheckMark.png"));
//                erreur = erreur + ("Pas de caractere permit dans le telephone\n");
            return false;

        }
    }

private Boolean testRegion() {

        if ( fx_region.getSelectionModel().getSelectedItem() != null) {
            checkregion.setImage(new Image("image/checkMark.png"));
            
            
            return true;
        } else {
           checkregion.setImage(new Image("image/erreurCheckMark.png"));
//                erreur = erreur + ("Pas de caractere permit dans le telephone\n");
            return false;

        }
    }

private Boolean testSaisie() {
        erreur = "";
        
       
        if (!testNomCentre()) {
            erreur = erreur + ("Veuillez verifier votre Nom: seulement des caractères et de nombre >= 3 \n");
        }
        if (!testDesc()) {
            erreur = erreur + ("Veuillez verifier votre Description: seulement des caractères et de nombre >= 10");
        }
        if (!testDelegation()) {
            erreur = erreur + ("Veuillez verifier votre délégation: seulement des caractères et de nombre >= 3 \n");
        }
        if (!testRegion()) {
            erreur = erreur + ("Veuillez selectionner une région \n");
        }

        return   testNomCentre()&& testDesc() && testDelegation() && testRegion();
    }
//    @FXML
//    private void PDF(ActionEvent event) throws FileNotFoundException, DocumentException {
//         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//        alert.setTitle("Confirmation de création du PDF");
//        alert.setHeaderText("Etes vous sur de vouloir créer un PDF qui contient la liste des zones de camping ?");
//
//        Optional<ButtonType> result = alert.showAndWait();
//        if (result.get() == ButtonType.OK) {
//             PDFzoneCamping pdfzc= new PDFzoneCamping();
//        pdfzc.liste_EvenementPDF();
//        }
//        
//    }
     void search_ZoneCamping() {
        ZoneCamping z = new ZoneCamping();
        
        collregion.setCellValueFactory(new PropertyValueFactory<>("region"));
        colldelegation.setCellValueFactory(new PropertyValueFactory<>("delegation"));
        collnomCentre.setCellValueFactory(new PropertyValueFactory<>("nom_centre"));
        colldescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        
        
       
        tabzc.setItems(data);
       
        FilteredList<ZoneCamping> filteredData = new FilteredList<>(data, b -> true);
       
        recherchezc.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate((ZoneCamping zc) -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (zc.getNom_centre().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (zc.getDescription().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
                                else if (zc.getDelegation().indexOf(lowerCaseFilter)!=-1){
				     return true;
                                }
                                else if (zc.getRegion().indexOf(lowerCaseFilter)!=-1){
				     return true;
                                }

                                
                               
				     else  
				    	 return false; // Does not match.
			});
		});
        SortedList<ZoneCamping> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tabzc.comparatorProperty());
        tabzc.setItems(sortedData);
   }                     
private void ExportPDF() throws DocumentException {
             try {
            String file_name = ("LISTzoneCamping.pdf");
            com.itextpdf.text.Document document = new com.itextpdf.text.Document();
            PdfWriter.getInstance(document, new FileOutputStream(file_name));
 
            document.open();
           
            document.addTitle("Liste des zones de camping ");
            Paragraph paraHeader2= new Paragraph("                                                                   ".concat("Vos zones de camping "));
             document.add(paraHeader2);
             Paragraph paraHeader3= new Paragraph("             "+ ""+ "");
               document.add(paraHeader3);
                 Paragraph paraHeader1 = new Paragraph((("id".concat("            ")).concat("Région".concat("            ")).concat("Délégation".concat("           ")).concat("Nom centre".concat("            ")).concat("Description".concat("            "))));
            document.add(paraHeader1);
           //         ObservableList<Message> list =pcr.getMessage();
                  // while(list)

           
                 // ObservableList<Message> ProductList = FXCollections.observableArrayList();
        String requete = "SELECT id,region,delegation,nom_centre,description FROM zonecamping";
        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            //Statement st;
            ResultSet rs;
            try {
                //System.out.println("AHAYYYAA!!!!");
                //st=conn.createStatement();
                //System.out.println("AHAYYYAA222!!!!");
                rs = pst.executeQuery(requete);
             
                Message p;

                while (rs.next()) {
                   // Message msg = new Message(rs.getInt("id_msg"),rs.getString("message"), rs.getString("id_posteur"),rs.getDate("date_heure_post"));
                   // ProductList.add(msg);
                        Paragraph paraHeader11 = new Paragraph((rs.getInt("id"))+("            ")+(rs.getString("region"))+("              ")+(rs.getString("delegation"))+("              ")+(rs.getString("nom_centre"))+("              ")+(rs.getString("description")));
            document.add(paraHeader11);
                }

            } catch (Exception ex) {
                //System.out.println("AHAYYYAA L7KEEEYAAAAA!!!!");
                ex.printStackTrace();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Evenements.class.getName()).log(Level.SEVERE, null, ex);
        }    
            document.close();
            Desktop.getDesktop().open(new File(file_name));
        } catch (IOException ex) {
            Logger.getLogger(GestionZoneCampingController.class.getName()).log(Level.SEVERE, null, ex);
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
    private void Refresh() {
        data.clear();
        ZoneCampingCRUD zc = new ZoneCampingCRUD();
        data.addAll(zc.affichageZoneCamping());
        zc.affichageZoneCamping();

        tabzc.setItems(data);
    }
    
    
    
    
    
    
    }

