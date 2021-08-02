/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxmlController;

import classeApp.Materiel;
import classeApp.Member;
import com.jfoenix.controls.JFXButton;
import fxmlWidgets.widgets.MemberTemplateController;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author the Richenel
 */
public class ShowMemberController extends Controller implements Initializable{
    public static ArrayList<Materiel> list=null;
    @FXML
    private JFXButton btnDelete;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO */
        choixSearch.getItems().addAll("Nom","Email","Telephone","niveau","Filiere");
        changePage();
        choixSearch.getSelectionModel().select(0);
        if(list==null){
            btnAdd.setText("Ajouter un Membre");
            title.setText("Les Membres");
        }else{
            btnDelete.setVisible(false);
            btnAdd.setVisible(false);
            title.setText("Selectionner un membre");
            MemberTemplateController.liste=list;
        }
    } 
    @FXML
    private void addNewMember(ActionEvent event) {
        if(list==null){
            AnchorPane parent =(AnchorPane) changeAnchor.getParent();
            parent.getChildren().remove(parent.getChildren().indexOf(changeAnchor));
            AnchorPane newChildrenNode=null;
            try {
                newChildrenNode = (AnchorPane) FXMLLoader.load(getClass().getResource("/fxmlFile/AddMember.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(ShowBookController.class.getName()).log(Level.SEVERE, null, ex);
            }
            parent.getChildren().add(newChildrenNode);
            for (TargetButton button : TargetButton.list ){
                button.childrenNode=newChildrenNode;
            }
        }
    }

    @FXML
    private void searchMember(KeyEvent event) {
        Member.research(choixSearch.getSelectionModel().getSelectedItem(),searchField.getText());
        changePage();
    }

    @FXML
    private void deleteMember(ActionEvent event) {
      for (MemberTemplateController con : MemberTemplateController.list){
          if(con.getActive()){
              con.getMembre().supprimer();
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
        MemberTemplateController.list.clear();
        String str="";
        Boolean a =true;
        vboxLivre.getChildren().removeAll(vboxLivre.getChildren());
        for (Member membre : Member.list){
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmlWidgets/fxmlFile/MemberHboxTemplate.fxml"));
                MemberTemplateController controller = new MemberTemplateController(membre,a,hBoxPrincipale.getWidth(),vboxLivre);
                loader.setController(controller);
                vboxLivre.getChildren().add(loader.load());
                if (a){
                    a=false;
                }else{
                    a=true;
                }
            } catch (IOException ex) {
                Logger.getLogger(ShowMemberController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void tri(ActionEvent event) {
        Member.tri(choixSearch.getSelectionModel().getSelectedItem());
        changePage();
    }

    
}
