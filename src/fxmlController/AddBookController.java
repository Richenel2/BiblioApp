/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this
| Templates template file, choose Tools  * and open the template in the editor. */

package fxmlController;

import classeApp.Livre;
import classeApp.Materiel;
import fxmlWidgets.widgets.TargetButton;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.util.Duration;


/**
 * FXML Controller class
 *
 * @author the Richenel
 */
public class AddBookController implements Initializable {

    @FXML
    private AnchorPane imageCointain;
    @FXML
    private TextField titreLivre;
    @FXML
    private TextField auteurLivre;
    @FXML
    private TextField typeLivre;
    @FXML
    private TextField nbExemplaireLivre;
    @FXML
    private TextField localisationLivre;
    @FXML
    private ImageView imageChange;
    File file;
    public static Materiel materiel;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        if (materiel != null){
            titreLivre.setText(materiel.titre);
            auteurLivre.setText(materiel.auteur);
            typeLivre.setText(materiel.type);
            nbExemplaireLivre.setText(materiel.nbEx+"");
            localisationLivre.setText(materiel.localisation);
        }
    }    

    @FXML
    private void addImage(MouseEvent event) {
        FileChooser chooser = new FileChooser();
        file = chooser.showOpenDialog(titreLivre.getScene().getWindow());
        imageChange.setImage(new Image(file.toURI().toString()));
    }

    @FXML
    private void initialiserChamps(ActionEvent event) {
        titreLivre.setText("");
        auteurLivre.setText("");
        typeLivre.setText("");
        nbExemplaireLivre.setText("");
        localisationLivre.setText("");
        imageChange.setImage(new Image (getClass().getResource("/ressources/livre.jpg").toExternalForm()));
    }

    @FXML
    private void enregistreLivre(ActionEvent event) {
        Boolean a = true;
        if (titreLivre.getText().equals("")){
            refuse(titreLivre);
            a=false;
        }else{
            accepte(titreLivre);
        }
        if (auteurLivre.getText().equals("")){
            refuse(auteurLivre);
            a=false;
        }else{
            accepte(auteurLivre);
        }
        if (typeLivre.getText().equals("")){
            refuse(typeLivre);
            a=false;
        }else{
            accepte(typeLivre);
        }
        if (nbExemplaireLivre.getText().equals("")){
            refuse(nbExemplaireLivre);
            a=false;
        }else{
            accepte(nbExemplaireLivre);
        }if (localisationLivre.getText().equals("")){
            refuse(localisationLivre);
            a=false;
        }else{
            accepte(localisationLivre);
        }
        int nbExemplaire=0;
        try{
            nbExemplaire=Integer.parseInt(nbExemplaireLivre.getText());
        }catch(Exception e){
            refuse(nbExemplaireLivre);
            a=false;
        }
        String img = "";
        try{
            img=file.toURI().toString();
        }catch(Exception e){
            img="/ressources/livre.jpg";
        }
        if (a){
            if (materiel == null){  
                new Livre(titreLivre.getText(),auteurLivre.getText(),nbExemplaire,nbExemplaire,typeLivre.getText(),localisationLivre.getText(),img);
            }else{
                materiel.auteur=auteurLivre.getText();
                materiel.titre=titreLivre.getText();
                materiel.localisation=localisationLivre.getText();
                materiel.nbRestant=nbExemplaire;
                materiel.type=typeLivre.getText();
                materiel.modifier();
            }
            try {
                materiel=null;
                TargetButton.list.get(0).activeMenu();
            } catch (IOException ex) {
                Logger.getLogger(AddBookController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void refuse(TextField a){
        a.setStyle("-fx-border-color:#ff007f");
        a.setText("");
        TranslateTransition transition=new TranslateTransition(Duration.millis(200),a);
        transition.setToX(-10);
        transition.play();
        TranslateTransition transition1=new TranslateTransition(Duration.millis(200),a);
        transition1.setToX(10);
        transition1.setDelay(Duration.millis(200));
        transition1.play();
        TranslateTransition transition2=new TranslateTransition(Duration.millis(200),a);
        transition2.setToX(0);
        transition2.setDelay(Duration.millis(400));
        transition2.play();

    }
    public void accepte(TextField a){
        a.setStyle("-fx-border-color:green");
    }
}
