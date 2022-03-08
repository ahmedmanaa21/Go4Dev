/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.JavaPIDEV.GUI;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import static edu.JavaPIDEV.GUI.NewFXMain.UserconnectedC;
import edu.JavaPIDEV.services.PanierCRUD;

//import edu.connexion3a16.services.PanierCRUD;
import edu.JavaPIDEV.utils.MyConnection;
import edu.JavaPIDEV.entities.Commande;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.mail.Message;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author 21627
 */
public class AddCommandeController implements Initializable {
    Connection cnx=null;
    ResultSet rs=null;
    PreparedStatement st=null;
@FXML
 private TextField Nom;
@FXML
 private TextField Prenom;
 @FXML
 private TextField codepostal;
@FXML
private TextField NumTel;
@FXML
 private TextField AdressMail;
@FXML
 private TextField   tfrecherche;
private Connection con = null;

       private Parent fxml;
    private Scene scene;
    private Stage stage;
@FXML
private AnchorPane anchor;
Commande c= new Commande();


/*ComboBox */

    @FXML
    private ComboBox<String> Etatt;
    ObservableList <String> mf = FXCollections.observableArrayList("Male","Female");
   @FXML
    private ComboBox<String> paiement;
    ObservableList  <String> e = FXCollections.observableArrayList("espèces","par cartes bancaires","par chèque");
    
    /*Button */
    
    @FXML
    private Button bsave;
    @FXML
    private Button bupdate;
    @FXML
     private Button bdelete;
    @FXML
     private Button btnfacture;
    
    
    
   //  private Parent fxml;
   // private Scene scene;
   // private Stage stage;
    //int index=-1;
    ObservableList<Commande> listC;
    ObservableList<Commande> list = FXCollections.observableArrayList();
    
    /*Table view */
    @FXML
     private TableView<Commande> table;  
    @FXML
    private TableColumn<Commande, Integer> colcin;
    @FXML
    private TableColumn<Commande, String> colnom;
    @FXML
    private TableColumn<Commande,String> colprenom;
    @FXML
    private TableColumn<Commande,Integer> coltel;
    @FXML
    private TableColumn<Commande,Integer> colpostal;
    @FXML
    private TableColumn<Commande,String> coletat;
    @FXML
    private TableColumn<Commande,String> coladresse;
    @FXML
    private TableColumn<Commande,String> colpayement;
    @FXML
    private Button bvalider;
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
    @FXML
    private ImageView btnpanier;
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        paiement.setPromptText("Veuillez selectionner un mode de paiement");
        paiement.setItems(e);
       
        AdressMail.setText(UserconnectedC.getEmail());
        Nom.setText(UserconnectedC.getNomPrenom());
        Prenom.setText(UserconnectedC.getSurnom());
        
        Etatt.setPromptText("Male ou Female");
        Etatt.setItems(mf);
        AfficherCommande();
        Rechercher();
        
              

        
    }    
    
    @FXML
private void tablehandleButtonAction(MouseEvent event) {
         Commande c = table.getSelectionModel().getSelectedItem();
//      Id_cmd.setText(String.valueOf(c.getId_cmd()));
        Nom.setText(String.valueOf(c.getNom()));
        Prenom.setText(String.valueOf(c.getPrenom()));
        NumTel.setText(String.valueOf(c.getNum_tel()));
        codepostal.setText(String.valueOf(c.getCodepostal()));
      Etatt.getSelectionModel().select(c.getEtat());
        AdressMail.setText(String.valueOf(c.getAdressmail()));
            paiement.getSelectionModel().select(c.getMode_paiment());
       bsave.setDisable(true);
       

       
    }
    
    
    
    private void AfficherCommande() {
        list.clear();
         list = getCommande();
         colcin.setCellValueFactory(new PropertyValueFactory<>("id_cmd"));
        colnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            colprenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            coltel.setCellValueFactory(new PropertyValueFactory<>("num_tel"));
            colpostal.setCellValueFactory(new PropertyValueFactory<>("codepostal"));
            coletat.setCellValueFactory(new PropertyValueFactory<>("Etat"));
            coladresse.setCellValueFactory(new PropertyValueFactory<>("adressmail"));
            colpayement.setCellValueFactory(new PropertyValueFactory<>("mode_paiment"));
        table.setItems(list);
    }

    private ObservableList<Commande> getCommande() {
      
         ObservableList<Commande> resultat = FXCollections.observableArrayList();

        java.sql.Connection cnx = MyConnection.getInstance().getCnx();
        String select = "SELECT * FROM commande";

        try {
            PreparedStatement st = cnx.prepareStatement(select);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Commande c = new Commande();
                c.setId_cmd(rs.getInt("id_cmd"));
                c.setNom(rs.getString("nom"));
                c.setPrenom(rs.getString("prenom"));
                c.setNum_tel(rs.getInt("num_tel"));
                c.setCodepostal(rs.getInt("codepostal"));
                c.setEtat(rs.getString("etat"));
                c.setAdressmail(rs.getString("adressmail"));
                c.setMode_paiment(rs.getString("mode_paiment"));
                //c.setImage_chambre(rs.getString("image"));
               
                resultat.add(c);
            }
            
            
          
        } catch (SQLException ex) {
            System.err.println("ereeur offfff" +ex);

        }
        return resultat;
    }
    
    
    private void insert() {
        
        //cnx = MyConnection.getInstance().getCnx();
        String insert = "INSERT INTO commande (`nom`,`prenom`,`num_tel`,`codepostal`,`Etat`,`adressmail`,`mode_paiment`) VALUES (?,?,?,?,?,?,?) ;";
        try {
        PreparedStatement st =MyConnection.getInstance().getCnx().prepareStatement(insert);
//            
//           st.setString(1, Id_cmd.getText());
            st.setString(1, Nom.getText());
            st.setString(2, Prenom.getText());
            st.setInt(3,Integer.parseInt(NumTel.getText())); 
            st.setInt(4, Integer.parseInt(codepostal.getText()));
            st.setString(5,Etatt.getSelectionModel().getSelectedItem());
            st.setString(6, AdressMail.getText());
            
            st.setString(7,paiement.getSelectionModel().getSelectedItem());
            
            
//                Alert alert = new Alert(AlertType.INFORMATION);
//
//                alert.setTitle("MarcoMan Message");
//                alert.setHeaderText(null);
//                alert.setContentText("Successfully !");
//                alert.showAndWait();
               TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            tray.setTitle("Add Success");
            tray.setMessage("Commande ajouter avec succes ");
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(1000));

//
////           Alert alert = new Alert(AlertType.INFORMATION);
////		alert.setTitle("Deleting user");
////		 Header Text: null
////		alert.setHeaderText(null);
////		alert.setContentText("'utilisateur " +nom.getText()+"   "+prenom.getText()+" est ajouté avec succés");
////
////		alert.showAndWait();
           
//            
         st.executeUpdate();
           AfficherCommande();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    
    private void delete() throws SQLException {
        String delete = "DELETE  FROM commande  where id_cmd= ?";
        PreparedStatement st =MyConnection.getInstance().getCnx().prepareStatement(delete);
        
        try {
            // st.setString(1,(Nom.getText()));
           
            st.setInt(1, table.getSelectionModel().getSelectedItem().getId_cmd());

//            Alert alert = new Alert(AlertType.INFORMATION);
//            alert.setTitle("Deleting user");
//
//            // Header Text: null
//            alert.setHeaderText(null);
//            alert.setContentText(" Commande " + Nom.getText() + " avec ID" + Id_cmd.getText() + " est supprimé avec succés");
//
//            alert.showAndWait();
//
         st.executeUpdate();
   TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            tray.setTitle("Delete Success");
            tray.setMessage("Commande supprimee ");
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(1000));
            AfficherCommande();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    
    @FXML
    public void Update() throws SQLException {
        
       ;
        
        String update = "Update  FROM commande  where id_cmd = ?";
  PreparedStatement st =MyConnection.getInstance().getCnx().prepareStatement(update);

        update = "UPDATE commande SET `nom` = '" 
                +Nom.getText() + "',`prenom` = '" 
                + Prenom.getText()
                + "', `num_tel` = '" 
                + NumTel.getText() + "', `codepostal` = '" 
                + codepostal.getText()
                + "', `Etat` = '" 
                + Etatt.getSelectionModel().getSelectedItem() 
                 
                +"', `adressmail` = '" 
                + AdressMail.getText() +"', `mode_paiment` = '"
                + paiement.getSelectionModel().getSelectedItem() 
               
               
                + "' WHERE id_cmd = '" + table.getSelectionModel().getSelectedItem().getId_cmd()+ "'";
             try{
         
//            
//            if( Nom.getText().isEmpty()
//               | Prenom.getText().isEmpty()  
//                 | paiement.getSelectionModel().isEmpty()
//                | Etatt.getSelectionModel().isEmpty() )
//                     
//                       
//                 {
//                
//                 TrayNotification tray = new TrayNotification();
//            AnimationType type = AnimationType.POPUP;
//            tray.setAnimationType(type);
//            tray.setTitle("Add Success");
//            tray.setMessage("Faild ");
//            tray.setNotificationType(NotificationType.ERROR);
//            tray.showAndDismiss(Duration.millis(1000));
//                
//            }else{
      st.executeUpdate(update);
                 TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            tray.setTitle("Add Success");
            tray.setMessage("Commande modifiéé ");
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(1000));
               
               AfficherCommande();
               
        }catch(Exception e){}
      
    }
    
    
  
    @FXML
    private void saveEvent(ActionEvent event) {
        insert();
    }

    private void updateEvent(ActionEvent event) throws SQLException {
        Update();
    }

    @FXML
    private void deleteEvent(ActionEvent event) throws SQLException {
        delete();
    }

    

      void setUtilisateur(int i) {
PanierCRUD Pa=new PanierCRUD();
                //double prixtotal=Pa.sommePanier(u.getCin());
                     }
      
      @FXML
      private void validerCommande(ActionEvent event){
//         CommandeCRUD cc=new CommandeCRUD();
//         cc.ajouterCommande(c);
           //DecimalFormat df = new DecimalFormat("#");
          // df.setMaximumFractionDigits(0);
          // Smsapi.sendSMS("+216"+df.format(table.getSelectionModel().getSelectedItem().getNum_tel()), "Commande valide ");
          

      }
      
      @FXML
      private void Rechercher(){
          FilteredList<Commande> filteredlist=new FilteredList<Commande>(list,b->true);
        tfrecherche.textProperty().addListener(
                (observable,oldValue,newValue)->{
                    filteredlist.setPredicate(Commande->{
                        if(newValue==null || newValue.isEmpty()){
                            return true;
                        }
                        String lowercasenewvalue=newValue.toLowerCase();
                        if(Commande.getMode_paiment().toLowerCase().indexOf(lowercasenewvalue)!=-1){
                            return true;
                        }
                        else if(Commande.getNom().toLowerCase().indexOf(lowercasenewvalue)!=-1){
                            return true;
                        }

                        else if(Commande.getPrenom().toLowerCase().indexOf(lowercasenewvalue)!=-1){
                            return true;
                        }

                     
                        else if(String.valueOf(Commande.getNum_tel()).toLowerCase().indexOf(lowercasenewvalue)!=-1){
                            return true;
                        }
                       
                       else if(String.valueOf(Commande.getCodepostal()).toLowerCase().indexOf(lowercasenewvalue)!=-1){
                            return true;
                        }
                       
                       
                        else{
                            return false;
                        }
                       
                    });
                }
        );
        table.setItems(filteredlist);
      }
@FXML
      private void Facture(ActionEvent event) throws DocumentException {
             try {
            String file_name = ("Facture.pdf");
            com.itextpdf.text.Document document = new com.itextpdf.text.Document();
            PdfWriter.getInstance(document, new FileOutputStream(file_name));
 
            document.open();
           
            document.addTitle("Votre Facture ");
            Paragraph paraHeader2= new Paragraph("                                                                   ".concat("Votre facture "));
             document.add(paraHeader2);
           //         ObservableList<Message> list =pcr.getMessage();
                  // while(list)

           
                 // ObservableList<Message> ProductList = FXCollections.observableArrayList();
        String requete = "SELECT a.id_cmd,a.nom,a.prenom,a.num_tel,a.codepostal,a.adressmail,a.mode_paiment,b.prix_equipement FROM commande as a ,equipement as b WHERE a.id_cmd=b.ref_equipement";
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
                        Paragraph paraHeader11 = new Paragraph("La facture numéro "+(rs.getInt("id_cmd"))+(" du Client   ")+(rs.getString("nom"))+(" ")+(rs.getString("prenom"))+("/n num tél:  ")+(rs.getInt("num_tel"))+("    /n code postale:          ")+(rs.getInt("codepostal"))+("    /n Prix:          ")+rs.getDouble("prix_equipement"));
            document.add(paraHeader11);
                }

            } catch (Exception ex) {
                //System.out.println("AHAYYYAA L7KEEEYAAAAA!!!!");
                ex.printStackTrace();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Commande.class.getName()).log(Level.SEVERE, null, ex);
        }    
            document.close();
            Desktop.getDesktop().open(new File(file_name));
        } catch (IOException ex) {
            Logger.getLogger(AddCommandeController.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    @FXML
    private void goToPanier(MouseEvent event) {
    }
      }




