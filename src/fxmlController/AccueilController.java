/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxmlController;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import fxmlWidgets.widgets.TargetButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author LABO
 */
public class AccueilController implements Initializable {

    @FXML
    private AnchorPane close;
    @FXML
    private AnchorPane minimize;
    @FXML
    private AnchorPane maxApp;
    
    private Boolean appIsMaximized= false;
    @FXML
    private AnchorPane childrenNode;
    @FXML
    private AnchorPane parentNode;
    @FXML
    private VBox menuMateriel;
    @FXML
    private VBox menuOption;
    @FXML
    private VBox menuMembre;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        TargetButton button = new TargetButton("Les Livres", "ShowBook",parentNode,childrenNode);
        menuMateriel.getChildren().add(button);
        menuMateriel.getChildren().add(new TargetButton("Les CDs", "ShowCD",parentNode,childrenNode));
        menuMembre.getChildren().add(new TargetButton("Les Membres", "ShowMember",parentNode,childrenNode));
        menuOption.getChildren().add(new TargetButton("Les Emprunts", "ShowEmprunt",parentNode,childrenNode));
        menuOption.getChildren().add(new TargetButton("Les Dons", "AddBook",parentNode,childrenNode));
        final URL cssURL = getClass().getResource("/fxmlCss/accueil.css"); 
        menuOption.getStylesheets().add(cssURL.toExternalForm());
        menuMateriel.getStylesheets().add(cssURL.toExternalForm());
        menuMembre.getStylesheets().add(cssURL.toExternalForm());
        try {
            button.activeMenu();
        } catch (IOException ex) {
            Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    Stage stage;

    @FXML
    private void closeApp(MouseEvent event) { 
         JFXDialogLayout dialogLayout=new JFXDialogLayout();
         JFXDialog dialog=new JFXDialog(((StackPane)((Node)event.getSource()).getScene().getRoot()),dialogLayout,JFXDialog.DialogTransition.TOP);
         JFXButton button= new JFXButton("Oui");
         button.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent eh)->{
             dialog.close();
            stage = (Stage)(close.getScene().getWindow()) ;
            stage.close(); 
             
         });
         JFXButton button2= new JFXButton("Non");
         button2.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent eh)->{
             dialog.close();             
         });
         dialog.setOnDialogClosed((t) -> {
             ((StackPane)close.getScene().getRoot()).getChildren().get(0).setEffect(null);
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
         Text txt=new Text("Etes vous sur de vouloir Fermer BiblioApp?");
         txt.setFill(Color.WHITE);
         dialogLayout.setHeading(txt);
         dialogLayout.setActions(button,button2);
         dialogLayout.setStyle("-fx-background-color: #1c1b1f;-fx-background-radius: 0em;");
         dialog.setStyle("-fx-background-color:#55aaff55");
         dialog.show();
         ((StackPane)close.getScene().getRoot()).getChildren().get(0).setEffect(blur);
    }

    @FXML
    private void minimizeApp(MouseEvent event) {
        stage = (Stage)(close.getScene().getWindow()) ;
        stage.toBack();
    }

    @FXML
    private void maximiseApp(MouseEvent event) {
        stage = (Stage)(close.getScene().getWindow()) ;
        if (appIsMaximized){
        appIsMaximized = false;
        }else{
        appIsMaximized = true;
        }
        stage.setMaximized(appIsMaximized);
    }
    
    
}
