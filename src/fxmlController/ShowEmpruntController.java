/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxmlController;

import classeApp.Emprunt;
import com.jfoenix.controls.JFXButton;
import fxmlWidgets.widgets.EmpruntTemplateController;
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

/**
 *
 * @author the Richenel
 */
public class ShowEmpruntController extends Controller implements Initializable{
    @FXML
    private JFXButton btnDelete;
    @FXML
    private JFXButton btnRemettre;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO */
        choixSearch.getItems().addAll("Nom Membre","Nom Livre","penalite");
        changePage();
        choixSearch.getSelectionModel().select(0);
    } 
    @FXML
    private void remettreBook(ActionEvent event) {
      for (EmpruntTemplateController con : EmpruntTemplateController.list){
          if(con.getActive()){
              con.getEmprunt().remettre();
          }
      }
      changePage();
    }

    @FXML
    private void searchEmprunt(KeyEvent event) {
        Emprunt.research(choixSearch.getSelectionModel().getSelectedItem(),searchField.getText());
        changePage();
    }

    @FXML
    private void changeToGrille(ActionEvent event) {
        bool=radioButton.isSelected();
        changePage();
    }
    
    private void changePage(){
        EmpruntTemplateController.list.clear();
        String str="";
        Boolean a =true;
        vboxLivre.getChildren().removeAll(vboxLivre.getChildren());
        Emprunt.list.clear();
        Emprunt.initialise();
        for (Emprunt emprunt : Emprunt.list){
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmlWidgets/fxmlFile/EmpruntHboxTemplate.fxml"));
                EmpruntTemplateController controller = new EmpruntTemplateController(emprunt,a,hBoxPrincipale.getWidth(),vboxLivre);
                loader.setController(controller);
                vboxLivre.getChildren().add(loader.load());
                if (a){
                    a=false;
                }else{
                    a=true;
                }
            } catch (IOException ex) {
                Logger.getLogger(ShowEmpruntController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void tri(ActionEvent event) {
        Emprunt.tri(choixSearch.getSelectionModel().getSelectedItem());
        changePage();
    }

    
}
