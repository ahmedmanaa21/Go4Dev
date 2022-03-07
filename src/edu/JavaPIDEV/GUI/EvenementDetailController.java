/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.JavaPIDEV.GUI;

import edu.JavaPIDEV.entities.Evenements;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author melki
 */
public class EvenementDetailController implements Initializable {

    @FXML
    private ImageView img;
    @FXML
    private Label nameLabel;
    @FXML
    private Label descriptionLabel;
    @FXML
    private Label prixLabel;
    private Evenements evenement;
    Evenements edetails = new Evenements();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void setData(Evenements evenement) {
        this.evenement=evenement;
        
        //this.myListener = myListener;
        //this.parent = parent;
//        this.id.setText(String.valueOf(product.getId()));
        this.nameLabel.setText(evenement.getNom());
        this.descriptionLabel.setText(evenement.getDescription());
//        prixLabel.setText(product.getPrix()+ Zero.CURRENCY );
        File file = new File(evenement.getImage().replace('/' , '\\'));
        System.out.println(file);
        
        Image im = null;
        if(file.exists()){ 
                 im = new Image(file.toURI().toString());
        }else{
            //im = new Image("resources/default-article.jpg"); // this is the defualt photo of the product
        }
         this.img.setImage(im);
    }
    public void setproduit(Evenements E) {
        this.edetails = E;
    }
}
