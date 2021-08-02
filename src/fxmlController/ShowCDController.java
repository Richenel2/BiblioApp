/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxmlController;

import classeApp.CD;
import fxmlWidgets.widgets.LivreTemplateController;
import fxmlWidgets.widgets.TargetButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author the Richenel
 */
public class ShowCDController extends Controller implements Initializable{

     @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO */
        choixSearch.getItems().addAll("Titre","Auteur","Type","Nombre restant","Localisation");
        changePage();
        choixSearch.getSelectionModel().select(0);
        btnAdd.setText("Ajouter un CD");
        title.setText("Les CDs");
    } 
    @FXML
    private void addNewMaterial(ActionEvent event) {
        AnchorPane parent =(AnchorPane) changeAnchor.getParent();
        parent.getChildren().remove(parent.getChildren().indexOf(changeAnchor));
        AnchorPane newChildrenNode=null;
        try {
            newChildrenNode = (AnchorPane) FXMLLoader.load(getClass().getResource("/fxmlFile/AddCD.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(ShowBookController.class.getName()).log(Level.SEVERE, null, ex);
        }
        parent.getChildren().add(newChildrenNode);
        for (TargetButton button : TargetButton.list ){
            button.childrenNode=newChildrenNode;
        }
    }

    @FXML
    private void searchMaterial(KeyEvent event) {
        CD.research(choixSearch.getSelectionModel().getSelectedItem(),searchField.getText());
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
        for (CD materiel : CD.list){
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmlWidgets/fxmlFile/"+str+".fxml"));
                LivreTemplateController controller = new LivreTemplateController(materiel,a,hBoxPrincipale.getWidth(),vboxLivre);
                loader.setController(controller);
                vboxLivre.getChildren().add(loader.load());
                if (a){
                    a=false;
                }else{
                    a=true;
                }
            } catch (IOException ex) {
            }
        }
    }

    @FXML
    private void tri(ActionEvent event) {
        CD.tri(choixSearch.getSelectionModel().getSelectedItem());
        changePage();
    }

    
}
