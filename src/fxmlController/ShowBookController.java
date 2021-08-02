/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxmlController;

import classeApp.Livre;
import classeApp.Materiel;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXScrollPane;
import fxmlWidgets.widgets.LivreTemplateController;
import fxmlWidgets.widgets.TargetButton;
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
import javafx.scene.control.TextField;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author the Richenel
 */
public class ShowBookController extends Controller implements Initializable {

    @FXML
    private AnchorPane changeAnchor;
    @FXML
    private Label title;
    @FXML
    private TextField searchField;
    @FXML
    private JFXComboBox<String> choixSearch;
    @FXML
    private JFXRadioButton radioButton;
    @FXML
    private HBox hBoxPrincipale;
    @FXML
    private JFXScrollPane scrollpane;
    @FXML
    private VBox vboxLivre;
    @FXML
    private AnchorPane Menu;
    @FXML
    private JFXButton btnAdd;
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO */
        choixSearch.getItems().addAll("Titre","Auteur","Type","Nombre restant","Localisation");
        changePage();
        choixSearch.getSelectionModel().select(0);
    } 
    @FXML
    private void addNewMaterial(ActionEvent event) {
        AnchorPane parent =(AnchorPane) changeAnchor.getParent();
        parent.getChildren().remove(parent.getChildren().indexOf(changeAnchor));
        AnchorPane newChildrenNode=null;
        try {
            newChildrenNode = (AnchorPane) FXMLLoader.load(getClass().getResource("/fxmlFile/AddBook.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(ShowBookController.class.getName()).log(Level.SEVERE, null, ex);
        }
        parent.getChildren().add(newChildrenNode);
        for (TargetButton button : TargetButton.list ){
            button.childrenNode=newChildrenNode;
        }
        Menu.setVisible(false);
    }

    @FXML
    private void searchMaterial(KeyEvent event) {
        new Livre().research(choixSearch.getSelectionModel().getSelectedItem(),searchField.getText());
        changePage();
    }

    @FXML
    private void deleteMaterials(ActionEvent event) {
      for (LivreTemplateController con : LivreTemplateController.list){
          if(con.getActive()){
              con.getLivre().supprimer();
          }
      }
      changePage();
      Menu.setVisible(false);
      
    }

    @FXML
    private void changeToGrille(ActionEvent event) {
        bool=radioButton.isSelected();
        changePage();
    }
    
    private void changePage(){
        LivreTemplateController.list.clear();
        String str=null;
        if(bool){
            
        str="LivreGridTemplate";
        }else{
            
        str="LivreHboxTemplate";
        }
        
        Boolean a =true;
        vboxLivre.getChildren().removeAll(vboxLivre.getChildren());
        for (Livre livre : Livre.list){
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmlWidgets/fxmlFile/"+str+".fxml"));
                LivreTemplateController controller = new LivreTemplateController(livre,a,hBoxPrincipale.getWidth(),vboxLivre);
                loader.setController(controller);
                vboxLivre.getChildren().add(loader.load());
                if (a){
                    a=false;
                }else{
                    a=true;
                }
            } catch (IOException ex) {
                Logger.getLogger(ShowBookController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void tri(ActionEvent event) {
        Livre.tri(choixSearch.getSelectionModel().getSelectedItem());
        changePage();
    }

    @FXML
    private void setVisibility(ActionEvent event) {
        Menu.setVisible(true);
    }

    @FXML
    private void setVisible(MouseEvent event) {
        Menu.setVisible(false);
    }

    @FXML
    private void emprunterLivre(ActionEvent event) {
        ArrayList <Materiel> list= new ArrayList<Materiel>();
        for (LivreTemplateController con : LivreTemplateController.list){
            if(con.getActive()){
              list.add(con.getLivre());
            }
        }
        if (list.size()<2 || list.size()>4){
                     
            JFXDialogLayout dialogLayout=new JFXDialogLayout();
            JFXDialog dialog=new JFXDialog(((StackPane)((Node)event.getSource()).getScene().getRoot()),dialogLayout,JFXDialog.DialogTransition.TOP);
            JFXButton button= new JFXButton("Okay");
            button.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent eh)->{
                dialog.close();

            });
            dialog.setOnDialogClosed((t) -> {
                ((StackPane)Menu.getScene().getRoot()).getChildren().get(0).setEffect(null);
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
            Text txt=new Text();
            if(list.size()<2){
                txt.setText("Desole mais vous devez emprunter minimum 2 Documents");
            }else{
                txt.setText("Desole mais vous pouvez emprunter maximum 4 Documents");
            }
            txt.setFill(Color.WHITE);
            dialogLayout.setHeading(txt);
            dialogLayout.setActions(button);
            dialogLayout.setStyle("-fx-background-color: #1c1b1f;-fx-background-radius: 0em;");
            dialog.setStyle("-fx-background-color:#55aaff55");
            dialog.show();
            ((StackPane)Menu.getScene().getRoot()).getChildren().get(0).setEffect(blur);
        }else{
            try {
                TargetButton.list.get(2).activeMenu(list);
            } catch (IOException ex) {
                Logger.getLogger(ShowBookController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
