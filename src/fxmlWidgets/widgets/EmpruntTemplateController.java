/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxmlWidgets.widgets;

import classeApp.Emprunt;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
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
public class EmpruntTemplateController implements Initializable{
    public static final ArrayList<EmpruntTemplateController> list =new ArrayList<EmpruntTemplateController>();
    private Boolean isActif=false;
    private Emprunt emprunt;
    private Boolean a;
    private double width;
    private VBox vbox;
    @FXML
    private Label nomMembreShow;
    @FXML
    private Label nomMaterielShow;
    @FXML
    private Label priceShow;
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

    public EmpruntTemplateController(Emprunt emprunt,Boolean a,double b,VBox vbox) {
        this.emprunt=emprunt;
        this.a=a;
        width=b;
        this.vbox=vbox;
        list.add(this);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //To change body of generated methods, choose Tools | Templates.

        nomMembreShow.setText(emprunt.getMembreName());
        nomMaterielShow.setText(emprunt.getMaterielName());
        priceShow.setText(emprunt.getPrice()+" Fcfa");
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
        if (emprunt.status){
            circle.setFill(Color.RED);
        }
    }
    
    @FXML
     public void showOption(ActionEvent event){
         
         JFXDialogLayout dialogLayout=new JFXDialogLayout();
         JFXDialog dialog=new JFXDialog(((StackPane)((Node)event.getSource()).getScene().getRoot()),dialogLayout,JFXDialog.DialogTransition.TOP);
         JFXButton button= new JFXButton("Remettre");
         button.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent eh)->{
             dialog.close();
             emprunt.remettre();
             vbox.getChildren().removeAll(hboxLivre,anchorLivre);
             
         });
         JFXButton button2= new JFXButton("Relancer");
         button2.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent eh)->{
             dialog.close();
             emprunt.relancer();
             vbox.getChildren().removeAll(hboxLivre);
             
         });
         dialog.setOnDialogClosed((t) -> {
             ((StackPane)vbox.getScene().getRoot()).getChildren().get(0).setEffect(null);
         });
         BoxBlur blur = new BoxBlur(3,3,5);
         String str = """
                        -fx-background-color :#ff007f;
                        -fx-background-radius: 2em;
                        -fx-border-color: none;
                        -fx-text-fill: white;
                        -fx-font-family: Montserrat;-fx-font-size:15px;
                      """;
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
    
    public void setActive(Boolean bool){
        isActif=bool;
    }
    public Boolean getActive(){
        return isActif;
    }
    public Emprunt getEmprunt(){
        return emprunt;
    }

    @FXML
    private void activeEmprunt(ActionEvent event) {
        if(checkShow.isSelected()){
            this.setActive(true);
        }else{
            this.setActive(false);
        }

    }
}
