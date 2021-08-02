/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxmlWidgets.widgets;

import classeApp.Materiel;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import fxmlController.AddBookController;
import fxmlController.Controller;
import fxmlController.ShowBookController;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 *
 * @author the Richenel
 */
public class LivreTemplateController implements Initializable{
    public static final ArrayList<LivreTemplateController> list =new ArrayList<LivreTemplateController>();
    private Boolean isActif=false;
    private Materiel materiel;
    private Boolean a;
    private double width;
    private VBox vbox;
    @FXML
    private Label titreShow;
    @FXML
    private Label auteruShow;
    @FXML
    private Label nbRestantShow;
    @FXML
    private Label localisationShow;
    @FXML
    private JFXButton optionShow;
    @FXML
    private HBox hboxLivre;
    @FXML
    private AnchorPane anchorLivre;
    @FXML
    private AnchorPane anchor;
    @FXML
    private JFXCheckBox checkShow;

    public LivreTemplateController(Materiel materiel,Boolean a,double b,VBox vbox) {
        this.materiel=materiel;
        this.a=a;
        width=b;
        this.vbox=vbox;
        list.add(this);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //To change body of generated methods, choose Tools | Templates.

        titreShow.setText(materiel.titre);
        auteruShow.setText(materiel.auteur);
        nbRestantShow.setText(""+materiel.nbRestant);
        localisationShow.setText(materiel.localisation);
        String color = null;
        if (a){
            color="#1c1b1f";
        }
        else{
            color="#2f303e";
        }
        if(hboxLivre==null){  
            anchor.setStyle("-fx-background-color:"+color+";");
        }
        if(anchorLivre==null){  
            hboxLivre.setStyle("-fx-background-color:"+color+";");
        }
    }
    
    @FXML
     public void showOption(ActionEvent event){
         
         JFXDialogLayout dialogLayout=new JFXDialogLayout();
         JFXDialog dialog=new JFXDialog(((StackPane)((Node)event.getSource()).getScene().getRoot()),dialogLayout,JFXDialog.DialogTransition.TOP);
         JFXButton button= new JFXButton("Supprimer");
         dialog.setOnDialogClosed((t) -> {
         ((StackPane)((Node) event.getSource()).getScene().getRoot()).getChildren().get(0).setEffect(null);
         });
         button.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent eh)->{
             dialog.close();
             materiel.supprimer();
             vbox.getChildren().removeAll(hboxLivre,anchorLivre);
             
         });
         JFXButton button2= new JFXButton("Modifier");
         button2.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent eh)->{
             dialog.close();
             AddBookController.materiel=materiel;
             TargetButton btn = new TargetButton("","AddBook");
             btn.act();
             
             
         });
         BoxBlur blur = new BoxBlur(3,3,5);
         String str = "\n" +
            "    -fx-background-color :#ff007f;\n" +
            "    -fx-background-radius: 2em;\n" +
            "    -fx-border-color: none;\n" +
            "    -fx-text-fill: white;\n" +
            "    -fx-font-family: Montserrat;"
            + "-fx-font-size:15px;";
         button.setStyle(str);
         button2.setStyle(str);
         Text txt=new Text("Que voulez vous faire?");
         txt.setFill(Color.WHITE);
         dialogLayout.setHeading(txt);
         dialogLayout.setActions(button,button2);
         dialogLayout.setStyle("-fx-background-color: #1c1b1f;-fx-background-radius: 0em;");
         dialog.setStyle("-fx-background-color:#55aaff55");
         dialog.show();
         ((StackPane)vbox.getScene().getRoot()).getChildren().get(0).setEffect(blur);
     }
    @FXML
    private void activeLivre(ActionEvent event) {
        if(checkShow.isSelected()){
            this.setActive(true);
        }else{
            this.setActive(false);
        }
    }
    
    public void setActive(Boolean bool){
        isActif=bool;
    }
    public Boolean getActive(){
        return isActif;
    }
    public Materiel getLivre(){
        return materiel;
    }
    public void supprimer(){
        list.remove(this);
        materiel.supprimer();
    }
}
