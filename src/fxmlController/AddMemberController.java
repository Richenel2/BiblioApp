/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this
| Templates template file, choose Tools  * and open the template in the editor. */

package fxmlController;

import classeApp.Member;
import com.jfoenix.controls.JFXComboBox;
import fxmlWidgets.widgets.TargetButton;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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
public class AddMemberController implements Initializable {

    File file;
    @FXML
    private ImageView imageChange;
    @FXML
    private AnchorPane imageCointain;
    @FXML
    private TextField nomMembre;
    @FXML
    private TextField emailMembre;
    @FXML
    private TextField telMembre;
    @FXML
    private TextField niveauMembre;
    @FXML
    private JFXComboBox<String> filiereMembre;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        filiereMembre.getItems().addAll("ISN","CDN","INS");
        filiereMembre.getSelectionModel().select(0);
    }    

    @FXML
    private void addImage(MouseEvent event) {
        FileChooser chooser = new FileChooser();
        file = chooser.showOpenDialog(telMembre.getScene().getWindow());
        imageChange.setImage(new Image(file.toURI().toString()));
    }

    @FXML
    private void initialiserChamps(ActionEvent event) {
        nomMembre.setText("");
        emailMembre.setText("");
        telMembre.setText("");
        niveauMembre.setText("");
        filiereMembre.getSelectionModel().select(0);
        imageChange.setImage(new Image (getClass().getResource("/ressources/livre.jpg").toExternalForm()));
    }
    @FXML
    private void enregistreMembre(ActionEvent event) {
        Boolean a = true;
        if (nomMembre.getText().equals("")){
            refuse(nomMembre);
            a=false;
        }else{
            accepte(nomMembre);
        }
        Pattern p = Pattern.compile("^(.+)@(.+)$");
        Matcher m = p.matcher(emailMembre.getText().toString());
        if (!m.find()){
            refuse(emailMembre);
            a=false;
        }else{
            accepte(emailMembre);
        }
        if (telMembre.getText().equals("")){
            refuse(telMembre);
            a=false;
        }else{
            accepte(telMembre);
        }
        if (niveauMembre.getText().equals("")){
            refuse(niveauMembre);
            a=false;
        }else{
            accepte(niveauMembre);
        }
        if (filiereMembre.getSelectionModel().getSelectedItem().equals("")){
            refuse(filiereMembre);
            a=false;
        }else{
            accepte(filiereMembre);
        }
        int tel=0,niveau=0;
        try{
            tel=Integer.parseInt(telMembre.getText());
        }catch(Exception e){
            refuse(telMembre);
            a=false;
        }
        try{
            niveau=Integer.parseInt(niveauMembre.getText());
        }catch(Exception e){
            refuse(niveauMembre);
            a=false;
        }
        String img = "";
        try{
            img=file.toURI().toString();
        }catch(Exception e){
            img="/ressources/livre.jpg";
        }
        if (a){
            new Member(nomMembre.getText(),emailMembre.getText(),tel,niveau,filiereMembre.getSelectionModel().getSelectedItem());
            TargetButton btn = new TargetButton();
            try {
                btn.list.get(3).activeMenu();
            } catch (IOException ex) {
                Logger.getLogger(AddMemberController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void refuse(Node a){
        a.setStyle("-fx-border-color:#ff007f");
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
    public void accepte(Node a){
        a.setStyle("-fx-border-color:green");
    }

}
