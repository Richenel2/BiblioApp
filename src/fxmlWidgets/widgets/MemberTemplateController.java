/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxmlWidgets.widgets;

import classeApp.Member;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

/**
 *
 * @author the Richenel
 */
public class MemberTemplateController implements Initializable{
    public static final ArrayList<MemberTemplateController> list =new ArrayList<MemberTemplateController>();
    public  static ArrayList liste=null;
    private Boolean isActif=false;
    private Member membre;
    private Boolean a;
    private double width;
    private VBox vbox;
    @FXML
    private Label nomShow;
    @FXML
    private Label filiereShow;
    @FXML
    private Label niveauShow;
    @FXML
    private Circle circle;
    @FXML
    private JFXButton optionShow;
    @FXML
    private HBox hboxLivre;
    private AnchorPane anchorLivre;
    private AnchorPane anchor;
    @FXML
    private JFXCheckBox checkShow;

    public MemberTemplateController(Member membre,Boolean a,double b,VBox vbox) {
        this.membre=membre;
        this.a=a;
        width=b;
        this.vbox=vbox;
        list.add(this);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //To change body of generated methods, choose Tools | Templates.

        nomShow.setText(membre.nom);
        filiereShow.setText(membre.filiere);
        niveauShow.setText(""+membre.niveau);
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
        if (membre.status){
            circle.setFill(Color.RED);
        }
    }
    
    @FXML
     public void showOption(ActionEvent event){
         
         JFXDialogLayout dialogLayout=new JFXDialogLayout();
         JFXDialog dialog=new JFXDialog(((StackPane)((Node)event.getSource()).getScene().getRoot()),dialogLayout,JFXDialog.DialogTransition.TOP);
         JFXButton button= new JFXButton("Supprimer");
         button.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent eh)->{
             dialog.close();
             membre.supprimer();
             vbox.getChildren().removeAll(hboxLivre,anchorLivre);
             
         });
         JFXButton button2= new JFXButton("Modifier");
         button2.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent eh)->{
             dialog.close();
             membre.supprimer();
             vbox.getChildren().removeAll(hboxLivre);
             
         });
         JFXButton button3= new JFXButton("Emprunter");
         button3.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent eh)->{
             dialog.close();
             membre.supprimer();
             vbox.getChildren().removeAll(hboxLivre);
             
         });
         dialog.setOnDialogClosed((t) -> {
             ((StackPane)vbox.getScene().getRoot()).getChildren().get(0).setEffect(null);
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
         button3.setStyle(str);
         Text txt=new Text("Que voulez vous faire?");
         txt.setFill(Color.WHITE);
         dialogLayout.setHeading(txt);
         dialogLayout.setActions(button,button2,button3);
         dialogLayout.setStyle("-fx-background-color: #1c1b1f;-fx-background-radius: 0em;");
         dialog.setStyle("-fx-background-color:#55aaff55");
         dialog.show();
         ((StackPane)vbox.getScene().getRoot()).getChildren().get(0).setEffect(blur);
     }
    
    public void setActive(Boolean bool){
        isActif=bool;
    }
    public Boolean getActive(){
        return isActif;
    }
    public Member getMembre(){
        return membre;
    }
    public void supprimer(){
        list.remove(this);
        membre.supprimer();
    }

    @FXML
    private void activeLivre(ActionEvent event) {
        if(checkShow.isSelected()){
            this.setActive(true);
        }else{
            this.setActive(false);
        }
        if(liste!=null){
            JFXDialogLayout dialogLayout=new JFXDialogLayout();
            JFXDialog dialog=new JFXDialog(((StackPane)((Node)event.getSource()).getScene().getRoot()),dialogLayout,JFXDialog.DialogTransition.TOP);
            
            BoxBlur blur = new BoxBlur(3,3,5);
            dialog.setOnDialogClosed((t) -> {
                ((StackPane)((Node)event.getSource()).getScene().getRoot()).getChildren().get(0).setEffect(null);
            });
            Text txt=new Text("Ce Membre a deja emprunter des Documents");
 
            txt.setFill(Color.WHITE);
            dialogLayout.setHeading(txt);
            dialogLayout.setStyle("-fx-background-color: #1c1b1f;-fx-background-radius: 0em;");
            dialog.setStyle("-fx-background-color:#55aaff55");
            if(!membre.status){
                membre.emprunteLivre(liste);
                txt.setText("Emprunt Effectuer");
                dialog.setOnDialogClosed((t) -> {
                    try {
                        ((StackPane)((Node)event.getSource()).getScene().getRoot()).getChildren().get(0).setEffect(null);
                        TargetButton.list.get(4).activeMenu();
                    } catch (IOException ex) {
                        Logger.getLogger(MemberTemplateController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
            }
            dialog.show();
            ((StackPane)((Node)event.getSource()).getScene().getRoot()).getChildren().get(0).setEffect(blur);
            checkShow.setSelected(false);
        }
    }
}
