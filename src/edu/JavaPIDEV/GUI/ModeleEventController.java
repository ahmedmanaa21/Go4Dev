/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.JavaPIDEV.GUI;

import edu.JavaPIDEV.Interfaces.MylistenerE;
import edu.JavaPIDEV.entities.Evenements;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author melki
 */
public class ModeleEventController implements Initializable {

    @FXML
    private ImageView img;
    @FXML
    private Label nom;
    private Evenements evenement;
    private MylistenerE myListener;
    private VBox parent;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    
    public void setData(Evenements evenement, MylistenerE myListener, VBox parent) {
        this.evenement = evenement;
        this.myListener = myListener;
        this.parent = parent;
        this.nom.setText(evenement.getNom());
//        this.LabelDes.setText(evenement.getDescription());
//        priceLable.setText(String.valueOf(product.getPrix()));
//        priceLable1.setText(Zero.CURRENCY);
        File file = new File(evenement.getImage().replace('/' , '\\'));
        //System.out.println(file);
        
        Image im = null;
        if(file.exists()){ 
                 im = new Image(file.toURI().toString());
        }else{
            im = new Image("\\src\\image\\import.png"); // this is the defualt photo of the product
        }
//        ServiceLike sl = new ServiceLike(); 
        this.img.setImage(im);
//         int nm = sl.countLikes(product.getId());
        //System.out.println(sl.countLikes(product.getId()));
//        this.like_number.setText(String.valueOf(nm));
//        Image im = null;
//        try {
//            //System.out.println("img " + product.getImg());
//            URL imageUrl = new URL(product.getImg());
//            System.out.println(imageUrl);
//            InputStream in = imageUrl.openStream();
//            System.out.println(in);
//            BufferedImage image = ImageIO.read(in);
//            im = SwingFXUtils.toFXImage(image, null);
//            System.out.println(image);
//            in.close();
//        }catch (IOException ioe) {
//           //im = new Image("/zerobug/ressources/cannes.jpg");
//        }
//        this.img.setImage(im);

    }
    @FXML
    private void click(MouseEvent event)  {
        //myListener.onClickListener(product);
        HBox l = (HBox)this.parent.getChildren().get(0);
        Label name = (Label)l.getChildren().get(0);
//        Label price = (Label)l.getChildren().get(1);
//        Label id = (Label)l.getChildren().get(1);
//        id.setText(this.id.getText());
//        System.out.println(id.getText());
        //Label id = (Label) l.getChildren().get(1);
        //Label des = (Label)l.getChildren().get(3);
        name.setText(this.nom.getText());
//        price.setText(this.priceLable.getText());
        //des.setText(this.LabelDes.getText());
//        Label des = (Label)this.parent.getChildren().get(2);
//        des.setText(this.LabelDes.getText());
//        HBox l2 = (HBox)this.parent.getChildren().get(3);
//        Label like = (Label)l2.getChildren().get(0);
//        like.setText(this.like_number.getText());
//        
        
        
        //id.setText(this.id.getText());
        ImageView im = (ImageView)this.parent.getChildren().get(1);
        im.setImage(this.img.getImage());
        
    }
    
}
