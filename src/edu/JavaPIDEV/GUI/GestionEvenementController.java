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
import edu.JavaPIDEV.services.EvenementsCRUD;
import edu.JavaPIDEV.services.PDFevenement;
import edu.JavaPIDEV.utils.MyConnection;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.mail.Message;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author melki
 */
public class GestionEvenementController implements Initializable {
    private String path ;
    @FXML
    private ImageView fx_image;
    File selectedFile;
    @FXML
    private Button bt_image;
    @FXML
    private TextField fx_nom;
    @FXML
    private DatePicker fx_dateDeb;
    @FXML
    private DatePicker fx_dateFin;
    @FXML
    private TextArea fx_description;
    @FXML
    private TableView<Evenements> tabev;
    @FXML
    private TableColumn<Evenements, String> collnom;
    @FXML
    private TableColumn<Evenements, String> colldescription;
    @FXML
    private TableColumn<Evenements, Date> colldateDeb;
    @FXML
    private TableColumn<Evenements, Date> colldateFn;
    @FXML
    private TableColumn<Evenements, String> collaffiche;
    @FXML
    private Button bt_ajouter;
    @FXML
    private Button bt_modifier;
    @FXML
    private Button bt_supprimer;
    String erreur;
    @FXML
    private ImageView checknom;
    @FXML
    private ImageView checkdescription;
    @FXML
    private ImageView checkdateDeb;
    @FXML
    private ImageView checkdateFin;
    @FXML
    private Button pdf;
    EvenementsCRUD evc=new EvenementsCRUD();
    ObservableList<Evenements> data = FXCollections.observableArrayList(evc.affichageEvenements());
//    ObservableList<Evenements> datalist;
    @FXML
    private TextField recherche;
    @FXML
    private Button btnretour;
    @FXML
    private Button refresh;
    @FXML
    private AnchorPane displayArea;
    @FXML
    private TextField idm;
    @FXML
    private ImageView fxlogout;
    @FXML
    private ImageView fxlogo;
    
        @FXML
    private void handleButtonAction(ActionEvent event) throws SQLException, DocumentException {
        
        
        if (event.getSource()== bt_ajouter){
            AjouterEvenement();
        }
        else if (event.getSource() == bt_modifier){
            ModifierEvenement(event);
    }
        else if (event.getSource() == bt_supprimer){
            SupprimerEvenement(event);
    }
        else if (event.getSource() == pdf){
        ExportPDF();  
    }
    }
    private final ObservableList<Evenements> dataList=FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fxlogo.setImage(new Image("file:C:\\Users\\MSI\\Desktop\\CampersDen\\src\\Images\\logo.png"));
        fxlogout.setImage(new Image("file:C:\\Users\\MSI\\Desktop\\CampersDen\\src\\Images\\logout.png"));
        fx_image.setImage(new Image("file:C:\\Users\\MSI\\Desktop\\CampersDen\\src\\Images\\import.png"));
        // TODO
        AfficherEvenement();
        
        search_Event();
    fx_image.setOnDragOver(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                if (db.hasFiles()) {
                    event.acceptTransferModes(TransferMode.COPY);
                } else {
                    event.consume();
                }
            }
        });   
        // Dropping over surface
        fx_image.setOnDragDropped(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                boolean success = false;
                if (db.hasFiles()) {
                    success = true;
                    path = null;
                    for (File file : db.getFiles()) {
                        path = file.getName();
                        selectedFile = new File(file.getAbsolutePath());
                        System.out.println("Drag and drop file done and path=" + file.getAbsolutePath());//file.getAbsolutePath(🙁"C:\Users\X\Desktop\ScreenShot.6.png"
                        fx_image.setImage(new Image("file:" + file.getAbsolutePath()));
//                        fx_image.setFitHeight(150);
//                        screenshot.setFitWidth(250);
                        bt_image.setText(path);
                    }
                }
                event.setDropCompleted(success);
                event.consume();
            }
        });
    }
        @FXML
    private void image(ActionEvent event) throws MalformedURLException {
        FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File(System.getProperty("user.home") + "\\"));
        fc.setTitle("Veuillez choisir l'image");
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image", "*.jpg", "*.png"),
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg")
        );
        selectedFile = fc.showOpenDialog(null);

        if (selectedFile != null) {
            //path = selectedFile.getName();
            path = selectedFile.getName();
            fx_image.setImage(new Image(selectedFile.toURI().toURL().toString()));
            fx_image.setFitHeight(150);
            fx_image.setFitWidth(250);
            bt_image.setText(path);
        }
    }

        public Connection getConnection(){
        Connection cnx;
                try{
cnx= (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/PIDEVdb", "root", "");
return cnx ; 
}catch(SQLException ex){
    System.out.println("Erreur de connexion");
    return null ; 
}
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
                    Events.add(Evenement);
                            }               
                }catch (Exception ex){
                    ex.printStackTrace();
                }
                return Events;
                
                 }
            public void AfficherEvenement(){
ObservableList<Evenements> list=getEvenementList();
//collid.setCellValueFactory(new PropertyValueFactory<Evenements, Integer>("id"));
collnom.setCellValueFactory(new PropertyValueFactory<Evenements, String>("Nom"));
colldescription.setCellValueFactory(new PropertyValueFactory<Evenements, String>("Description"));
//colldateDeb.setCellValueFactory( da -> {
//      SimpleStringProperty s = new SimpleStringProperty();
//      DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//      s.setValue(dateFormat.format(da.getValue().getDate_deb()));
//      return s ; });
colldateDeb.setCellValueFactory(new PropertyValueFactory<>("Date début"));
colldateFn.setCellValueFactory(new PropertyValueFactory<>("Date fin"));
 collaffiche.setCellValueFactory(data ->
        {
            return new ReadOnlyStringWrapper(data.getValue().getImage());
        });
//collaffiche.setCellValueFactory(new PropertyValueFactory<Evenements, String>("Affiche"));
tabev.setItems(list);
//editttttttttttt
this.tabev.setEditable(true);
        this.collnom.setCellFactory(TextFieldTableCell.forTableColumn());
         this.colldescription.setCellFactory(TextFieldTableCell.forTableColumn());
         this.collaffiche.setCellFactory(TextFieldTableCell.forTableColumn());
         
}
private void AjouterEvenement(){
    
    EvenementsCRUD evc= new EvenementsCRUD();
if(testSaisie()){
//Evenement e =new Evenements(tfnom.getText(),tfprenom.getText(),tfemail.getText());
Evenements e =new Evenements();
e.setImage("C:\\xampp\\htdocs\\images"+path);
e.setNom(fx_nom.getText());
e.setDescription(fx_description.getText());
LocalDate deb = fx_dateDeb.getValue();
      e.setDate_deb(Date.valueOf(deb));
LocalDate fin = fx_dateFin.getValue();
      e.setDate_fin(Date.valueOf(fin));      

evc.ajouterEvenements2(e);
//AfficherEvenement();
Notifications notificationBuilder =Notifications.create()
        .title("Gestion des événements")
        .text("Ajout de l'événement avec succés")
        .graphic(null)
        .hideAfter(Duration.seconds(5))
        .position(Pos.BOTTOM_RIGHT);
notificationBuilder.darkStyle();
notificationBuilder.showInformation();

}
Refresh();
search_Event();
}           
   
    private void SupprimerEvenement(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation modification");
        alert.setContentText("Etes vous sûr de vouloir supprimer cet événement ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get().getButtonData() == ButtonBar.ButtonData.OK_DONE) {
            if (tabev.getSelectionModel().getSelectedItem() != null) {
                evc.supprimerEvenements(tabev.getSelectionModel().getSelectedItem());
                data.removeAll(data);
                for (Evenements ee : FXCollections.observableArrayList(evc.affichageEvenements())) {
                    data.add(ee);}
                Notifications notificationBuilder = Notifications.create()
                .title("Suppression événement")
                .text("votre événement a bien été supprimé  !")
                .graphic(null)
                .hideAfter(Duration.seconds(5))
                .position(Pos.BOTTOM_RIGHT);
                notificationBuilder.darkStyle();
                notificationBuilder.showInformation();
                fx_nom.setText("");
                fx_description.setText("");
            }
            else {
                Alert selectEventAlert = new Alert(Alert.AlertType.WARNING);
                selectEventAlert.setTitle("Selectionez un événement");
                selectEventAlert.setHeaderText(null);
                selectEventAlert.setContentText("Vous devez selectionner un événement !");
                selectEventAlert.showAndWait();          
            }
//            clear();
        } else {
            System.out.println("Cancel");
            Notifications notificationBuilder = Notifications.create()
            .title("Suppression annulée")
            .text("votre événement n'a pas été supprimé  !")
            .graphic(null)
            .hideAfter(Duration.seconds(5))
            .position(Pos.BOTTOM_RIGHT);
            notificationBuilder.darkStyle();
            notificationBuilder.showInformation();
        } 
        Refresh();
        search_Event();
    }    
    
    private void ModifierEvenement(ActionEvent event) {
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation modification");
        alert.setContentText("Etes vous sûr de modifier cet événement ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get().getButtonData() == ButtonBar.ButtonData.OK_DONE) {
        if (tabev.getSelectionModel().getSelectedItem() != null) {

            Evenements l = tabev.getSelectionModel().getSelectedItem();

            evc.modifierEvenements(l);
            Notifications notificationBuilder = Notifications.create()
            .title("Modification événement")
            .text("votre événement a été bien modifié  !")
            .graphic(null)
            .hideAfter(Duration.seconds(5))
            .position(Pos.BOTTOM_RIGHT);
            notificationBuilder.darkStyle();
            notificationBuilder.showInformation();}
            
        else if (tabev.getSelectionModel().getSelectedItem() == null){
            //Alert Select BOOK :
            Alert selectEventAlert = new Alert(Alert.AlertType.WARNING);
            selectEventAlert.setTitle("Selectionez un événement");
            selectEventAlert.setHeaderText(null);
            selectEventAlert.setContentText("Vous devez selectionner un événement !");
            selectEventAlert.showAndWait();
            //Alert Select Book !
        }
        }
        else {
            System.out.println("Cancel");
            Notifications notificationBuilder = Notifications.create()
            .title("modification annulée")
            .text("votre événement n'a pas été modifié  !")
            .graphic(null)
            .hideAfter(Duration.seconds(5))
            .position(Pos.BOTTOM_RIGHT);
            notificationBuilder.darkStyle();
            notificationBuilder.showInformation();
        }
        Refresh();
        search_Event();
    }    

//    @FXML
//    private void ModfierEvenement(ActionEvent event) {
//         
//    Evenements e= new Evenements(tabev.getSelectionModel().getSelectedItem().getId());
////        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
////        alert.setTitle("Confirmation modification");
////        alert.setContentText("Etes vous sûr de modifier cette catégorie?");
////        Optional<ButtonType> result = alert.showAndWait();
//         if (tabev.getSelectionModel().getSelectedItem() != null) {
//        
//            evc.modifierEvenements(e);             
////          evc.modifierEvenements(new Evenements(fx_nom.getText(), fx_description.getText(), fx_dateDeb.getValue(), fx_dateFin.getValue(), path));
////          cat.update(new Categorie(tfcat.getText()),tvcat.getSelectionModel().getSelectedItem().getId());
//            data.removeAll(data);
//            for (Evenements ev : FXCollections.observableArrayList(evc.affichageEvenements())) {
//                data.add(ev);
//            }
//            //clear();
//        }
////        Notifications notificationBuilder = Notifications.create()
////            .title("Modification catégorie")
////            .text("votre catégorie a etait bien modifier  !")
////            .graphic(null)
////            .hideAfter(Duration.seconds(5))
////            .position(Pos.BOTTOM_RIGHT);
////            notificationBuilder.darkStyle();
////            notificationBuilder.showInformation(); 
////        loadDate();
//    }
@FXML
private void handleMouseAction(MouseEvent event) {
        Evenements ev = tabev.getSelectionModel().getSelectedItem();  
//                tfid.setText(""+livreur.getId());
        fx_nom.setText(ev.getNom());
        fx_description.setText(ev.getDescription());    
    }
private Boolean testNom() {
        int nbNonChar = 0;
        for (int i = 1; i < fx_nom.getText().trim().length(); i++) {
            char ch = fx_nom.getText().charAt(i);
            if (!Character.isLetter(ch)) {
                nbNonChar++;
            }
        }
        if (nbNonChar <= 3 && fx_nom.getText().trim().length() >= 4) {
            checknom.setImage(new Image("Images/checkMark.png")); 
            return true;
        } else {
            checknom.setImage(new Image("Images/erreurCheckMark.png"));       
//                erreur = erreur + ("Pas de caractere permit dans le telephone\n");
            return false;
        }
    }
//private Boolean testNom() {
//        
//        String nomReg = "^[A-Za-z0-9]+$+";
//        Pattern pat = Pattern.compile(nomReg);
//        if (fx_nom.getText() == null) {
//            return false;
//        }
//        if (pat.matcher(fx_nom.getText()).matches() == false) {
//            checknom.setImage(new Image("image/ErreurcheckMark.png"));
//            erreur = erreur + ("Veuillez verifier votre surnom\n");
//            return false;
//        } else {
//            checknom.setImage(new Image("image/checkMark.png"));
//        }
//        return true;
//    }
private Boolean testDescription() {
       
        for (int i = 1; i < fx_description.getText().trim().length(); i++) {
            char ch = fx_description.getText().charAt(i);   
        }
        if ( fx_description.getText().trim().length() >= 10) {
            checkdescription.setImage(new Image("Images/checkMark.png"));
            
            
            return true;
        } else {
           checkdescription.setImage(new Image("Images/erreurCheckMark.png"));
//                erreur = erreur + ("Pas de caractere permit dans le telephone\n");
            return false;
        }
    }

    private Boolean testDateDeb() {
        
        LocalDate now = LocalDate.now();
        if ( fx_dateDeb.getValue()!= null && fx_dateDeb.getValue().compareTo(now) > 0 ) {
                checkdateDeb.setImage(new Image("Images/checkMark.png"));
                return true;
            } else {
                checkdateDeb.setImage(new Image("Images/erreurCheckMark.png"));
            }
                return false;
    }
      private Boolean testDateFin() {
          
        if ( fx_dateFin.getValue()!= null && fx_dateFin.getValue().compareTo(fx_dateDeb.getValue()) > 0 ) {
                checkdateFin.setImage(new Image("Images/checkMark.png"));
                return true;
            } else {
                checkdateFin.setImage(new Image("Images/erreurCheckMark.png"));
            }
                return false;
    }
private Boolean testSaisie() {
        erreur = "";
        
       
        if (!testNom()) {
            erreur = erreur + ("Veuillez verifier votre Nom: seulement des caractères et de nombre >= 3 \n");
        }
        if (!testDescription()) {
            erreur = erreur + ("Veuillez verifier votre Description: seulement des caractères et de nombre >= 10");
        }
        if (!testDateDeb()) {
            erreur = erreur + ("Veuillez verifier votre Date début: seulement une date non dépassée \n");
        }
        if (!testDateFin()) {
            erreur = erreur + ("Veuillez verifier votre Date Fin: seulement une date après la date du début \n");
        }

        

        return   testNom() && testDescription() && testDateDeb() && testDateFin() ;
    }

    @FXML
    private void changenom(CellEditEvent edittedCell ){ 
            
         Evenements evenementSelected = tabev.getSelectionModel().getSelectedItem();
        evenementSelected.setNom(edittedCell.getNewValue().toString());   
            
            
            
    }

    @FXML
    private void editdescription(CellEditEvent edittedCell) {
        Evenements evenementSelected = tabev.getSelectionModel().getSelectedItem();
        evenementSelected.setDescription(edittedCell.getNewValue().toString());
    }
    
    
//    @FXML
//    private void PDF(ActionEvent event) throws FileNotFoundException, DocumentException {
//         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//        alert.setTitle("Confirmation de création du PDF");
//        alert.setHeaderText("Etes vous sur de vouloir créer un PDF qui contient la liste des événements ?");
//
//        Optional<ButtonType> result = alert.showAndWait();
//        if (result.get() == ButtonType.OK) {
//             PDFevenement pdfev= new PDFevenement();
//        pdfev.liste_EvenementPDF();
//        }
//        
//    }
    private void ExportPDF() throws DocumentException {
             try {
            String file_name = ("LISTEvenement.pdf");
            com.itextpdf.text.Document document = new com.itextpdf.text.Document();
            PdfWriter.getInstance(document, new FileOutputStream(file_name));
 
            document.open();
           
            document.addTitle("Liste des événements ");
            Paragraph paraHeader2= new Paragraph("                                                                   ".concat("Vos événements "));
             document.add(paraHeader2);
             Paragraph paraHeader3= new Paragraph("             "+ ""+ "");
               document.add(paraHeader3);
                 Paragraph paraHeader1 = new Paragraph((("id".concat("            ")).concat("Nom".concat("            ")).concat("Description".concat("           ")).concat("Date début".concat("            ")).concat("Date fin".concat("            "))));
            document.add(paraHeader1);
           //         ObservableList<Message> list =pcr.getMessage();
                  // while(list)

           
                 // ObservableList<Message> ProductList = FXCollections.observableArrayList();
        String requete = "SELECT id,nom,description,date_deb,date_fin FROM evenement";
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
                        Paragraph paraHeader11 = new Paragraph((rs.getInt("id"))+("            ")+(rs.getString("nom"))+("              ")+(rs.getString("description"))+("              ")+(rs.getDate("date_deb"))+("              ")+(rs.getDate("date_fin")));
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
    
void search_Event() {
        Evenements ev = new Evenements();
        
        collnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colldescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        collaffiche.setCellValueFactory(new PropertyValueFactory<>("img"));
        
       
        tabev.setItems(data);
       
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
        sortedData.comparatorProperty().bind(tabev.comparatorProperty());
        tabev.setItems(sortedData);
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
    private void Refresh() {
        data.clear();
        EvenementsCRUD ev = new EvenementsCRUD();
        data.addAll(ev.affichageEvenements());
        ev.affichageEvenements();

        tabev.setItems(data);
        search_Event();
    }   

    @FXML
    private void logout(ActionEvent event) {
    }

    @FXML
    private void changeScreenAdmin(ActionEvent event) {
    }

    @FXML
    private void changeScreenClient(ActionEvent event) {
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
    private void changeScreenPanier(ActionEvent event) {
    }


}
