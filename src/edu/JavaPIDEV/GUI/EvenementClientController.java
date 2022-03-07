/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.JavaPIDEV.GUI;

import edu.JavaPIDEV.Interfaces.MylistenerE;
import edu.JavaPIDEV.entities.Evenements;
import edu.JavaPIDEV.services.EvenementsCRUD;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author melki
 */
public class EvenementClientController implements Initializable {

    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    @FXML
    private Label even;
    @FXML
    private ImageView evenaffiche;
    @FXML
    private Label description;
    private final List<Evenements> events = new ArrayList<>();
    private MylistenerE myListener;
    private Evenements ev;
    @FXML
    private VBox chosenEventCard;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        EvenementsCRUD sp = new EvenementsCRUD();
        List<Evenements> pro = sp.affichageEvenements();
        loadEvents(pro);
//        SearchBar.textProperty().addListener((observable, oldValue, newValue) -> {
//            ServiceArticle sa = new ServiceArticle();
//            List<Article> l ;
//            l =  sa.searchArticle(newValue);
//             loadArticles(l);
//        });
        if (events.size() > 0) {
            setChosenEvent(events.get(0));
            MylistenerE myListener = new MylistenerE() {
                

                @Override
                public void onClickListener( Evenements evt) {
                    setChosenEvent(evt); }

//                @Override
//                public void onClickListener(Evenements evt) {
//                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//                }
            };
    }    
    }
    private void loadEvents(List<Evenements> evt){
    grid.getChildren().clear();
//        ServiceArticle sp = new ServiceArticle();
//        List<Article> l = sp.AfficherArticle();
//        System.out.println("items " + l.toString());
        int row = 1, cl =0;
            try{
                for(Evenements evenement : evt){
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/zero/views/ModeleArticle.fxml"));
                    Node postbox = loader.load();
                    ModeleEventController pc = loader.getController();             
                    pc.setData(evenement, myListener, this.chosenEventCard);
                    if(cl== 3){
                         cl= 0;
                         row++;
                    }
                    this.grid.add(postbox, cl++, row);
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(postbox, new Insets(10));
                }
            }catch(IOException e){
                System.out.println("no load for event in client");
            }
}
     private void det(ActionEvent event) throws IOException {
        try{
                
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("ArticleDetail.fxml"));
                    Node postbox = loader.load();
                    EvenementDetailController pc = loader.getController();
                    pc.setData(ev);
                    
                    //pc.setData(product, myListener, this.chosenProductCard);}
//        try{           
//                        ArticleDetailController.article=prod;
//                        Parent page1 = FXMLLoader.load(getClass().getResource("/zerobug/gui/ArticleDetail.fxml"));
//                        Scene scene = new Scene(page1);
//                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//                        stage.setScene(scene);
//                        stage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(EvenementClientController.class.getName()).log(Level.SEVERE, null, ex);
                    }

    }
    private void setChosenEvent(Evenements evenement) {
//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(getClass().getResource("/zerobug/gui/TestItem.fxml"));
//        //Node postbox = loader.load();
//        TestItemController pc = loader.getController();
//        pc.setData(product, myListener, this.chosenProductCard);
        even.setText(evenement.getNom());
//        productPriceLabel.setText(product.getPrix()+Zero.CURRENCY );
        description.setText(evenement.getDescription());
        File file = new File(evenement.getImage().replace('/' , '\\'));
        System.out.println(file);
        
        Image im = null;
        if(file.exists()){ 
                 im = new Image(file.toURI().toString());
        }else{
            //im = new Image("resources/default-article.jpg"); // this is the defualt photo of the product
        }
         this.evenaffiche.setImage(im);
//         ServiceLike sl = new ServiceLike();
//        if(sl.getLike(product.getId(), -1, Integer.parseInt(CLIENT_ID)) == true){
//            this.like_icon.setImage(new Image("/news/src/resources/like-press.png"));
//        }else{
//           this.like_icon.setImage(new Image("/news/src/resources/like.png"));
//        }
//         int nm = sl.countLikes(product.getId());
        //System.out.println(sl.countLikes(product.getId()));
//        this.like_number.setText(String.valueOf(nm));
        
//        chosenProductCard.setStyle("-fx-background-color: red" + product.getDescription() + ";\n" +
//                "    -fx-background-radius: 30;");
  
    }
}
