/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this
| Templates template file, choose Tools  * and open the template in the editor. */

package fxmlController;

import classeApp.CD;
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
public class AddCDController implements Initializable {

    File file;
    @FXML
    private ImageView imageChange;
    @FXML
    private AnchorPane imageCointain;
    @FXML
    private TextField titreCD;
    @FXML
    private TextField auteurCD;
    @FXML
    private TextField typeCD;
    @FXML
    private TextField nbExemplaireCD;
    @FXML
    private TextField localisationCD;
    @FXML
    private TextField dureeCD;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void addImage(MouseEvent event) {
        FileChooser chooser = new FileChooser();
        file = chooser.showOpenDialog(titreCD.getScene().getWindow());
        imageChange.setImage(new Image(file.toURI().toString()));
    }

    @FXML
    private void initialiserChamps(ActionEvent event) {
        titreCD.setText("");
        auteurCD.setText("");
        typeCD.setText("");
        nbExemplaireCD.setText("");
        localisationCD.setText("");
        dureeCD.setText("");
        imageChange.setImage(new Image (getClass().getResource("/ressources/livre.jpg").toExternalForm()));
    }

    @FXML
    private void enregistreLivre(ActionEvent event) {
        Boolean a = true;
        if (titreCD.getText().equals("")){
            refuse(titreCD);
            a=false;
        }else{
            accepte(titreCD);
        }
        if (auteurCD.getText().equals("")){
            refuse(auteurCD);
            a=false;
        }else{
            accepte(auteurCD);
        }
        if (typeCD.getText().equals("")){
            refuse(typeCD);
            a=false;
        }else{
            accepte(typeCD);
        }
        if (dureeCD.getText().equals("")){
            refuse(dureeCD);
            a=false;
        }else{
            accepte(dureeCD);
        }
        if (nbExemplaireCD.getText().equals("")){
            refuse(nbExemplaireCD);
            a=false;
        }else{
            accepte(nbExemplaireCD);
        }if (localisationCD.getText().equals("")){
            refuse(localisationCD);
            a=false;
        }else{
            accepte(localisationCD);
        }
        int nbExemplaire=0,duree=0;
        try{
            nbExemplaire=Integer.parseInt(nbExemplaireCD.getText());
        }catch(Exception e){
            refuse(nbExemplaireCD);
            a=false;
        }
        try{
            duree=Integer.parseInt(dureeCD.getText());
        }catch(Exception e){
            refuse(dureeCD);
            a=false;
        }
        String img = "";
        try{
            img=file.toURI().toString();
        }catch(Exception e){
            img="/ressources/livre.jpg";
        }
        if (a){
            new CD(titreCD.getText(),auteurCD.getText(),nbExemplaire,nbExemplaire,typeCD.getText(),duree,localisationCD.getText(),img);
            TargetButton btn = new TargetButton();
            try {
                btn.list.get(1).activeMenu();
            } catch (IOException ex) {
                Logger.getLogger(AddCDController.class.getName()).log(Level.SEVERE, null, ex);
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
